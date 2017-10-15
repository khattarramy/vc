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

/**
 *
 * @author George Harik
 */
public class OrderService {

    DbConnect dbConnect = new DbConnect();
    MongoClient mongo;
    MongoCollection orderCollection, counters;
    MongoDatabase db;

    public OrderService() throws UnknownHostException {
        mongo = dbConnect.init();
        db = dbConnect.getDatabase(mongo, DB_NAME);
        if (!dbConnect.collectionExists(db, "order")) {
            db.createCollection("order", new CreateCollectionOptions().capped(false));
        }
        counters = AppUtils.checkCounters(dbConnect, db, "orderid");

        orderCollection = db.getCollection("order");

    }

    public List<Order> getAllOrders(int userId, String status, int distributorId, int manufacturerId) throws UnknownHostException {

        List<Order> orders = new ArrayList();

        MongoCursor<Document> cursor;

        if ((userId == 0) && ((status == null) || (status == "")) && (distributorId == 0) && (manufacturerId == 0)) {

            cursor = orderCollection.find().iterator();

            while (cursor.hasNext()) {
                orders.add((Order) AppUtils.fromDocument(cursor.next(), Order.class));
            }

        } else {

            BasicDBObject searchQuery = new BasicDBObject();

            if (status != null && !"".equals(status)) {

                searchQuery.put("Status", status);

            }
            if (userId > 0) {

                searchQuery.put("UserId", userId);

            }

            if (distributorId > 0 || manufacturerId > 0) {

                OrderDetailService orderDetails = new OrderDetailService();

                List<OrderDetail> orderDetailsList = orderDetails.getAllOrderDetails(0, distributorId, manufacturerId, null);

                List<Integer> orderDetailIdsList = new ArrayList();

                for (OrderDetail o : orderDetailsList) {

                    orderDetailIdsList.add(o.getOrderId());

                }

                searchQuery.put("_id", new BasicDBObject("$in", orderDetailIdsList));

            }
            if (!searchQuery.isEmpty()) {

                cursor = orderCollection.find(searchQuery).iterator();

                while (cursor.hasNext()) {
                    orders.add((Order) AppUtils.fromDocument(cursor.next(), Order.class));
                }
            }
        }

        dbConnect.close(mongo);

        return orders;

    }

    public Order getOrderById(int orderId) throws UnknownHostException {

        Order order = new Order();

        MongoCursor<Document> cursor = orderCollection.find(eq("_id", orderId)).iterator();

        while (cursor.hasNext()) {
            order = ((Order) AppUtils.fromDocument(cursor.next(), Order.class));
        }

        dbConnect.close(mongo);
        return order;

    }

    public Order addOrder(Order order) throws UnknownHostException, Exception {
        order.setOrderId((int) AppUtils.getNextSequence("orderid", counters));
        orderCollection.insertOne(AppUtils.toDocument(order));
        dbConnect.close(mongo);
        return order;
    }

    public Order updateOrder(Order order, int orderId) throws UnknownHostException {
        order.setOrderId(orderId);
        orderCollection.updateOne(eq("_id", orderId), new Document("$set", AppUtils.toDocument(order)));
        dbConnect.close(mongo);
        return order;
    }

    public void deleteOrder(int orderId) throws UnknownHostException {
        orderCollection.deleteOne(eq("_id", orderId));
        dbConnect.close(mongo);
    }
    
        public void deleteAllOrders() throws UnknownHostException {
        orderCollection.drop();
        dbConnect.close(mongo);
    }
}
