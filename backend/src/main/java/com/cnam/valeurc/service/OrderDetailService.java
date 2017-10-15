/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Order;
import com.cnam.valeurc.model.OrderDetail;
import static com.cnam.valeurc.service.DbConnect.DB_NAME;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import static com.mongodb.client.model.Filters.eq;
import java.net.UnknownHostException;
import java.util.*;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author George Harik
 */
public class OrderDetailService {

    DbConnect dbConnect = new DbConnect();
    MongoClient mongo;
    MongoCollection orderDetailCollection, counters;
    MongoDatabase db;

    public OrderDetailService() throws UnknownHostException {
        mongo = dbConnect.init();
        db = dbConnect.getDatabase(mongo, DB_NAME);
        if (!dbConnect.collectionExists(db, "orderDetail")) {
            db.createCollection("orderDetail", new CreateCollectionOptions().capped(false));
        }
        counters = AppUtils.checkCounters(dbConnect, db, "orderdetailid");

        orderDetailCollection = db.getCollection("orderDetail");

    }

    public List<OrderDetail> getAllOrderDetails(int orderId,int retailerId, int distributorId, int manufacturerId, String status) throws UnknownHostException {

        BasicDBObject searchQuery = new BasicDBObject();

        List<OrderDetail> orderDetails = new ArrayList();

        if ((orderId == 0) && (distributorId == 0) && (manufacturerId == 0) && ((status == null) || (status == ""))) {

            MongoCursor<Document> cursor = orderDetailCollection.find().iterator();

            while (cursor.hasNext()) {

                orderDetails.add((OrderDetail) AppUtils.fromDocument(cursor.next(), OrderDetail.class));
            }
        } else {
            if (status != null && !"".equals(status)) {

                searchQuery.append("Status", status);

            }

            if (orderId > 0) {

                searchQuery.append("OrderId", orderId);
            }

            ItemService items = new ItemService();

            List<Integer> itemsIds = items.getAllItemsIds(distributorId, manufacturerId);

            if (itemsIds != null && !itemsIds.isEmpty()) {

                searchQuery.append("ItemId", new BasicDBObject("$in", itemsIds));

            }
                        if (retailerId  > 0 ) {

                OrderService orders = new OrderService();

                List<Order> orderList = orders.getAllOrders(retailerId,"",0,0);
                

                List<Integer> orderIdsList = new ArrayList();

                for (Order o : orderList) {

                    orderIdsList.add(o.getOrderId());

                }

                searchQuery.put("orderId", new BasicDBObject("$in", orderIdsList));

            }

            if (!searchQuery.isEmpty()) {

                MongoCursor<Document> cursor = orderDetailCollection.find(searchQuery).iterator();

                while (cursor.hasNext()) {
                    orderDetails.add((OrderDetail) AppUtils.fromDocument(cursor.next(), OrderDetail.class));
                }
            }
        }

        dbConnect.close(mongo);

        return orderDetails;

    }

    public OrderDetail getOrderDetailById(int orderDetailId) throws UnknownHostException {

        OrderDetail orderDetail = new OrderDetail();

        MongoCursor<Document> cursor = orderDetailCollection.find(eq("_id", orderDetailId)).iterator();

        while (cursor.hasNext()) {
            orderDetail = ((OrderDetail) AppUtils.fromDocument(cursor.next(), OrderDetail.class));
        }

        dbConnect.close(mongo);
        return orderDetail;

    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) throws UnknownHostException, Exception {
        orderDetail.setOrderDetailId((int) AppUtils.getNextSequence("orderdetailid", counters));
        orderDetailCollection.insertOne(AppUtils.toDocument(orderDetail));
        dbConnect.close(mongo);
        return orderDetail;
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail, int orderDetailId) throws UnknownHostException {
        orderDetail.setOrderDetailId(orderDetailId);
        orderDetailCollection.updateOne(eq("_id", orderDetailId), new Document("$set", AppUtils.toDocument(orderDetail)));
        dbConnect.close(mongo);
        return orderDetail;
    }

    public void deleteOrderDetail(int orderDetailId) throws UnknownHostException {
        orderDetailCollection.deleteOne(eq("_id", orderDetailId));
        dbConnect.close(mongo);
    }

    public void deleteAllOrderDetails() throws UnknownHostException {
        BasicDBObject searchQuery = new BasicDBObject();
        orderDetailCollection.deleteMany(searchQuery);
        dbConnect.close(mongo);
        CounterService counterService = new CounterService();
        counterService.deleteCounter("orderdetailid");
    }
}
