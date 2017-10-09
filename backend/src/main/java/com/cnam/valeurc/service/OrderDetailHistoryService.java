/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.OrderDetailHistory;
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
public class OrderDetailHistoryService {

    DbConnect dbConnect = new DbConnect();
    MongoCollection orderDetailHistoryCollection, counters;
    MongoDatabase db;

    public OrderDetailHistoryService() throws UnknownHostException {
        db = dbConnect.init();
        if (!dbConnect.collectionExists("orderDetailHistory")) {
            db.createCollection("orderDetailHistory", new CreateCollectionOptions().capped(false));
        }
        counters = AppUtils.checkCounters(dbConnect, db, "orderdetailhistoryid");

        orderDetailHistoryCollection = db.getCollection("orderDetailHistory");

    }

    public List<OrderDetailHistory> getOrderDetailHistoryByOrderDetailId(int orderDetailId) throws UnknownHostException {

        List<OrderDetailHistory> orderDetailHistory = new ArrayList();
//         
//        BasicDBObject searchQuery = new BasicDBObject(); 
// 
//        searchQuery.put("OrderDetailId", orderDetailId); 
// 
//        DBCursor cursor = orderDetailHistoryCollection.find(searchQuery); 
// 
//        while (cursor.hasNext()) { 
//            orderDetailHistory.add((OrderDetailHistory) AppUtils.fromDBObject(cursor.next(), OrderDetailHistory.class)); 
//        } 
//        while (cursor.hasNext()) { 
//            orderDetailHistory = ((OrderDetailHistory) AppUtils.fromDocument(cursor.next(), OrderDetailHistory.class)); 
//        } 
// 
        return orderDetailHistory;

    }

    public List<OrderDetailHistory> getAllOrderDetailHistorys() throws UnknownHostException {

        List<OrderDetailHistory> orderDetailHistorys = new ArrayList();
        MongoCursor<Document> cursor = orderDetailHistoryCollection.find().iterator();

        while (cursor.hasNext()) {
            orderDetailHistorys.add((OrderDetailHistory) AppUtils.fromDocument(cursor.next(), OrderDetailHistory.class));
        }

        return orderDetailHistorys;

    }

    public OrderDetailHistory getOrderDetailHistoryById(int orderDetailHistoryId) throws UnknownHostException {

        OrderDetailHistory orderDetailHistory = new OrderDetailHistory();

        MongoCursor<Document> cursor = orderDetailHistoryCollection.find(eq("_id", orderDetailHistoryId)).iterator();

        while (cursor.hasNext()) {
            orderDetailHistory = ((OrderDetailHistory) AppUtils.fromDocument(cursor.next(), OrderDetailHistory.class));
        }

        return orderDetailHistory;

    }

    public OrderDetailHistory addOrderDetailHistory(OrderDetailHistory orderDetailHistory) throws UnknownHostException, Exception {
        orderDetailHistory.setOrderDetailHistoryId((int) AppUtils.getNextSequence("orderdetailhistoryid", counters));
        orderDetailHistoryCollection.insertOne(AppUtils.toDocument(orderDetailHistory));
        return orderDetailHistory;
    }

    public OrderDetailHistory updateOrderDetailHistory(OrderDetailHistory orderDetailHistory, int orderDetailHistoryId) throws UnknownHostException {
        orderDetailHistory.setOrderDetailHistoryId(orderDetailHistoryId);
        orderDetailHistoryCollection.updateOne(eq("_id", orderDetailHistoryId), new Document("$set", AppUtils.toDocument(orderDetailHistory)));
        return orderDetailHistory;
    }

    public void deleteOrderDetailHistory(int orderDetailHistoryId) throws UnknownHostException {
        orderDetailHistoryCollection.deleteOne(eq("_id", orderDetailHistoryId));
    }
}
