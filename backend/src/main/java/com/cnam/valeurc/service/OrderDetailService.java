/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.DbResource;
import com.cnam.valeurc.model.Item;
import com.cnam.valeurc.model.Order;
import com.cnam.valeurc.model.OrderDetail;
import com.cnam.valeurc.model.OrderDetailDto;
import com.cnam.valeurc.model.OrderDto;
import com.cnam.valeurc.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import java.net.UnknownHostException;
import java.util.*;
import static java.util.Arrays.asList;
import org.bson.BSONObject;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author George Harik
 */
public class OrderDetailService {

    MongoClient mongo;
    MongoCollection orderDetailCollection, orderCollection, counters;
    MongoDatabase db;
    DbResource dbResource = new DbResource();

    public OrderDetailService() throws UnknownHostException {
        mongo = dbResource.getMongoClient();
        db = mongo.getDatabase("valeurc");
        if (!dbResource.collectionExists(db, "orderDetail")) {
            db.createCollection("orderDetail", new CreateCollectionOptions().capped(false));
        }

        counters = AppUtils.checkCounters(db, "orderdetailid");

        orderDetailCollection = db.getCollection("orderDetail");

        if (!dbResource.collectionExists(db, "order")) {
            db.createCollection("order", new CreateCollectionOptions().capped(false));
        }

        counters = AppUtils.checkCounters(db, "orderid");

        orderCollection = db.getCollection("order");

    }

    public List<OrderDetailDto> getAllOrderDetailsDto(int orderId, int retailerId, int distributorId, int manufacturerId, String status) throws UnknownHostException {

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails = getAllOrderDetails(orderId, retailerId, distributorId, manufacturerId, status);

        List<OrderDetailDto> orderDetailsDto = new ArrayList<OrderDetailDto>();

        for (OrderDetail o : orderDetails) {

            orderDetailsDto.add(new OrderDetailDto(o.getOrderDetailId(), o.getOrderId(), o.getItemId(), o.getItem().getName(), o.getStatus(), o.getQuantity(), o.getQuantityDistributor()));

        }
        dbConnect.close(mongo);
        return orderDetailsDto;

    }

    public List<OrderDetail> getAllOrderDetails(int orderId, int retailerId, int distributorId, int manufacturerId, String status) throws UnknownHostException {
        List<OrderDetail> orderDetails = new ArrayList();

        List<Document> search = new ArrayList<>();
        search.add(new Document("$unwind", "$OrderDetails"));

        if ((retailerId == 0) && (orderId == 0) && (distributorId == 0) && (manufacturerId == 0) && ((status == null) || (status == ""))) {
            MongoCursor<Document> cursor
                    = orderCollection.aggregate(search).iterator();

            try {
                while (cursor.hasNext()) {
                    Document element = cursor.next();

                    for (String field : element.keySet()) {
                        if (field.equals("OrderDetails")) {
                            Document document = (Document) element.get(field);
                            orderDetails.add((OrderDetail) AppUtils.fromDocument(document, OrderDetail.class));
                        }

                    }
                }

            } finally {
                cursor.close();
            }
        } else {
            if (status != null && !"".equals(status)) {

                search.add(new Document("$match", new Document("OrderDetails.Status", status)));

            }

            if (orderId > 0) {

                search.add(new Document("$match", new Document("_id", orderId)));
            }
            if (distributorId > 0) {
                search.add(new Document("$match", new Document("OrderDetails.Item.DistributorId", distributorId)));

            }

            if (manufacturerId > 0) {
                search.add(new Document("$match", new Document("OrderDetails.Item.ManufacturerId", manufacturerId)));

            }

            if (retailerId > 0) {

                search.add(new Document("$match", new Document("UserId", retailerId)));
            }

            if (search.size() > 1) {
                MongoCursor<Document> cursor
                        = orderCollection.aggregate(search).iterator();

                try {
                    while (cursor.hasNext()) {
                        Document element = cursor.next();

                        for (String field : element.keySet()) {
                            if (field.equals("OrderDetails")) {
                                Document document = (Document) element.get(field);
                                orderDetails.add((OrderDetail) AppUtils.fromDocument(document, OrderDetail.class));
                            }
                        }
                    }

                } finally {
                    cursor.close();
                }

            }
        }

        return orderDetails;

    }

    public OrderDetail getOrderDetailById(int orderDetailId) throws UnknownHostException {
        OrderDetail orderDetail = new OrderDetail();
        List<Document> search = new ArrayList<>();
        search.add(new Document("$unwind", "$OrderDetails"));
        search.add(new Document("$match", new Document("OrderDetails._id", orderDetailId)));
        MongoCursor<Document> cursor
                = orderCollection.aggregate(search).iterator();

        try {
            while (cursor.hasNext()) {
                Document element = cursor.next();

                for (String field : element.keySet()) {
                    if (field.equals("OrderDetails")) {
                        Document document = (Document) element.get(field);
                        orderDetail = (OrderDetail) AppUtils.fromDocument(document, OrderDetail.class);
                    }

                }
            }

        } finally {
            cursor.close();
        }
        dbConnect.close(mongo);

        return orderDetail;
    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) throws UnknownHostException, Exception {

        ItemService itemService = new ItemService();

        orderDetail.setItem(itemService.getItemById(orderDetail.getItemId()));

        orderDetail.setOrderDetailId((int) AppUtils.getNextSequence("orderdetailid", counters));

        orderCollection.updateOne(eq("_id", orderDetail.getOrderId()), Updates.addToSet("OrderDetails", AppUtils.toDocument(orderDetail)));

        return orderDetail;
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail, int orderDetailId) throws UnknownHostException {
        orderDetail.setOrderDetailId(orderDetailId);

        ItemService itemService = new ItemService();

        orderDetail.setItem(itemService.getItemById(orderDetail.getItemId()));

        BasicDBObject query = new BasicDBObject();

        query.put("_id", orderDetail.getOrderId());
        query.put("OrderDetails._id", orderDetail.getOrderDetailId());

        BasicDBObject data = new BasicDBObject();

        data.put("OrderDetails.$.ItemId", orderDetail.getItemId());
        data.put("OrderDetails.$.Status", orderDetail.getStatus());
        data.put("OrderDetails.$.Quantity", orderDetail.getQuantity());
        data.put("OrderDetails.$.QuantityDistributor", orderDetail.getQuantityDistributor());
        data.put("OrderDetails.$.Item._id", orderDetail.getItem().getItemId());
        data.put("OrderDetails.$.Item.Name", orderDetail.getItem().getName());
        data.put("OrderDetails.$.Item.Description", orderDetail.getItem().getDescription());
        data.put("OrderDetails.$.Item.ModelNumber", orderDetail.getItem().getModelNumber());
        data.put("OrderDetails.$.Item.DistributorId", orderDetail.getItem().getDistributorId());
        data.put("OrderDetails.$.Item.ManufacturerId", orderDetail.getItem().getManufacturerId());
        data.put("OrderDetails.$.Item.Distributor.Name", orderDetail.getItem().getDistributor().getName());
        data.put("OrderDetails.$.Item.Manufacturer.Name", orderDetail.getItem().getManufacturer().getName());

        BasicDBObject command = new BasicDBObject();
        command.put("$set", data);

        orderCollection.updateOne(query, command);

        return orderDetail;
    }

    public void deleteOrderDetail(int orderDetailId) throws UnknownHostException {
        Order order = getOrderByOrderDetailId(orderDetailId);

        BasicDBObject match = new BasicDBObject("_id", order.getOrderId()); // to match your document
        BasicDBObject update = new BasicDBObject("OrderDetails", new BasicDBObject("_id", orderDetailId));
        orderCollection.updateOne(match, new BasicDBObject("$pull", update));

    }

    public Order getOrderByOrderDetailId(int orderDetailId) {

        Order order = new Order();
        Document document = new Document("OrderDetails._id", orderDetailId);
        MongoCursor<Document> cursor = orderCollection.find(document).iterator();

        while (cursor.hasNext()) {
            order = ((Order) AppUtils.fromDocument(cursor.next(), Order.class));
        }
        dbConnect.close(mongo);
        return order;
    }

    public void deleteAllOrderDetails() throws UnknownHostException {
        BasicDBObject searchQuery = new BasicDBObject();
        orderDetailCollection.deleteMany(searchQuery);
        CounterService counterService = new CounterService();
        counterService.deleteCounter("orderdetailid");
    }
}
