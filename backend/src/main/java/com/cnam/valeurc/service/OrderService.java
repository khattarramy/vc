/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Order;
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
    MongoCollection orderCollection, counters;
    MongoDatabase db;

    public OrderService() throws UnknownHostException {
        db = dbConnect.init();
        if (!dbConnect.collectionExists("order")) {
            db.createCollection("order", new CreateCollectionOptions().capped(false));
        }
        counters = AppUtils.checkCounters(dbConnect, db, "orderid");

        orderCollection = db.getCollection("order");

    }

    public List<Order> getOrdersByUserId(String userId) throws UnknownHostException {

        List<Order> orders = new ArrayList();
//        
//        BasicDBObject searchQuery = new BasicDBObject();
//
//        searchQuery.put("UserId", userId);
//
//        DBCursor cursor = orderCollection.find(searchQuery);
//
//        while (cursor.hasNext()) {
//            orders.add((Order) AppUtils.fromDBObject(cursor.next(), Order.class));
//        }

        return orders;

    }

    public List<Order> getAllOrders() throws UnknownHostException {

        List<Order> orders = new ArrayList();
        MongoCursor<Document> cursor = orderCollection.find().iterator();

        while (cursor.hasNext()) {
            orders.add((Order) AppUtils.fromDocument(cursor.next(), Order.class));
        }

        return orders;

    }

    public Order getOrderById(int orderId) throws UnknownHostException {

        Order order = new Order();

        MongoCursor<Document> cursor = orderCollection.find(eq("_id", orderId)).iterator();

        while (cursor.hasNext()) {
            order = ((Order) AppUtils.fromDocument(cursor.next(), Order.class));
        }

        return order;

    }

    public Order addOrder(Order order) throws UnknownHostException, Exception {
        order.setOrderId((int) AppUtils.getNextSequence("orderid", counters));
        orderCollection.insertOne(AppUtils.toDocument(order));
        return order;
    }

    public Order updateOrder(Order order, int orderId) throws UnknownHostException {
        order.setOrderId(orderId);
        orderCollection.updateOne(eq("_id", orderId), new Document("$set", AppUtils.toDocument(order)));
        return order;
    }

    public void deleteOrder(int orderId) throws UnknownHostException {
        orderCollection.deleteOne(eq("_id", orderId));
    }
}
