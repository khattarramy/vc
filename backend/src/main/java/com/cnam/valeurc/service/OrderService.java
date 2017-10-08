/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Order;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author George Harik
 */
public class OrderService {

    DbConnect dbConnect = new DbConnect();
    DBCollection orderCollection;
    DB db;

    public OrderService() throws UnknownHostException {
        db = dbConnect.init();
        if (!db.collectionExists("order")) {
            db.createCollection("order", null);
        }

        orderCollection = db.getCollection("order");

    }

    public List<Order> getAllOrders(String userId, String statusId) throws UnknownHostException {

        List<Order> orders = new ArrayList();
        BasicDBObject searchQuery = new BasicDBObject();
        if (statusId != null && !"".equals(statusId)) {
            searchQuery.put("StatusId", statusId);
        }
        if (userId != null && !"".equals(userId)) {
            searchQuery.put("UserId", userId);
        }
        DBCursor cursor = orderCollection.find(searchQuery);

        while (cursor.hasNext()) {
            orders.add((Order) AppUtils.fromDBObject(cursor.next(), Order.class));
        }

        return orders;

    }

        public List<Order> getOrdersByUserId(String userId) throws UnknownHostException {

        List<Order> orders = new ArrayList();
        
        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("UserId", userId);

        DBCursor cursor = orderCollection.find(searchQuery);

        while (cursor.hasNext()) {
            orders.add((Order) AppUtils.fromDBObject(cursor.next(), Order.class));
        }

        return orders;

    }
        
    public Order getOrderById(String orderId) throws UnknownHostException {

        Order order = new Order();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderId", orderId);

        DBCursor cursor = orderCollection.find(searchQuery);

        while (cursor.hasNext()) {
            order = ((Order) AppUtils.fromDBObject(cursor.next(), Order.class));
        }

        return order;

    }

    public Order addOrder(Order order) throws UnknownHostException {

        order.setOrderId(UUID.randomUUID());

        orderCollection.insert(AppUtils.toDBObject(order));

        return order;
    }

    public Order updateOrder(Order order, String orderId) throws UnknownHostException {

        Order oldOrder = new Order();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderId", orderId);

        order.setOrderId((UUID.fromString(orderId)));

        DBCursor cursor = orderCollection.find(searchQuery);

        while (cursor.hasNext()) {
            oldOrder = ((Order) AppUtils.fromDBObject(cursor.next(), Order.class));
        }

        orderCollection.update(AppUtils.toDBObject(oldOrder), AppUtils.toDBObject(order));

        return order;
    }

    public void deleteOrder(String orderId) throws UnknownHostException {

        Order order = new Order();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderId", orderId);

        DBCursor cursor = orderCollection.find(searchQuery);

        while (cursor.hasNext()) {
            order = ((Order) AppUtils.fromDBObject(cursor.next(), Order.class));
        }

        orderCollection.remove(AppUtils.toDBObject(order));
    }
}
