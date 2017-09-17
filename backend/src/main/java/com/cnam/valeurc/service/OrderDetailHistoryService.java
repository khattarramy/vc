/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.OrderDetailHistory;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author George Harik
 */
public class OrderDetailHistoryService {

    DbConnect dbConnect = new DbConnect();
    DBCollection orderDetailHistoryCollection;
    DB db;

    public OrderDetailHistoryService() throws UnknownHostException {
        db = dbConnect.init();
        if (!db.collectionExists("orderDetailHistory")) {
            db.createCollection("orderDetailHistory", null);
        }

        orderDetailHistoryCollection = db.getCollection("orderDetailHistory");

    }

    public List<OrderDetailHistory> getAllOrderDetailHistorys() throws UnknownHostException {

        List<OrderDetailHistory> orderDetailHistorys = new ArrayList();
        DBCursor cursor = orderDetailHistoryCollection.find();

        while (cursor.hasNext()) {
            orderDetailHistorys.add((OrderDetailHistory) AppUtils.fromDBObject(cursor.next(), OrderDetailHistory.class));
        }

        return orderDetailHistorys;

    }

    public OrderDetailHistory getOrderDetailHistoryById(String orderDetailHistoryId) throws UnknownHostException {

        OrderDetailHistory orderDetailHistory = new OrderDetailHistory();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderDetailHistoryId", orderDetailHistoryId);

        DBCursor cursor = orderDetailHistoryCollection.find(searchQuery);

        while (cursor.hasNext()) {
            orderDetailHistory = ((OrderDetailHistory) AppUtils.fromDBObject(cursor.next(), OrderDetailHistory.class));
        }

        return orderDetailHistory;

    }

    public OrderDetailHistory addOrderDetailHistory(OrderDetailHistory orderDetailHistory) throws UnknownHostException {

        orderDetailHistory.setOrderDetailHistoryId(UUID.randomUUID());

        orderDetailHistoryCollection.insert(AppUtils.toDBObject(orderDetailHistory));

        return orderDetailHistory;
    }

    public OrderDetailHistory updateOrderDetailHistory(OrderDetailHistory orderDetailHistory, String orderDetailHistoryId) throws UnknownHostException {
        
        OrderDetailHistory oldOrderDetailHistory = new OrderDetailHistory();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderDetailHistoryId", orderDetailHistory.getOrderDetailHistoryId());

        DBCursor cursor = orderDetailHistoryCollection.find(searchQuery);

        while (cursor.hasNext()) {
            oldOrderDetailHistory = ((OrderDetailHistory) AppUtils.fromDBObject(cursor.next(), OrderDetailHistory.class));
        }

        orderDetailHistoryCollection.update(AppUtils.toDBObject(oldOrderDetailHistory), AppUtils.toDBObject(orderDetailHistory));
        
        return orderDetailHistory;
    }

    public void deleteOrderDetailHistory(String orderDetailHistoryId) throws UnknownHostException {

        OrderDetailHistory orderDetailHistory = new OrderDetailHistory();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderDetailHistoryId", orderDetailHistoryId);

        DBCursor cursor = orderDetailHistoryCollection.find(searchQuery);

        while (cursor.hasNext()) {
            orderDetailHistory = ((OrderDetailHistory) AppUtils.fromDBObject(cursor.next(), OrderDetailHistory.class));
        }

        orderDetailHistoryCollection.remove(AppUtils.toDBObject(orderDetailHistory));
    }
}
