/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.OrderDetail;
import com.mongodb.BasicDBObject;
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
    MongoCollection orderDetailCollection, counters;
    MongoDatabase db;

    public OrderDetailService() throws UnknownHostException {
        db = dbConnect.init();
        if (!dbConnect.collectionExists("orderDetail")) {
            db.createCollection("orderDetail", new CreateCollectionOptions().capped(false));
        }
        counters = AppUtils.checkCounters(dbConnect, db, "orderdetailid");

        orderDetailCollection = db.getCollection("orderDetail");

    }


    public List<OrderDetail> getAllOrderDetails(int orderId,int retailerId,int distributorId,int manufacturerId,String status) throws UnknownHostException {

        List<OrderDetail> orderDetails = new ArrayList();
        BasicDBObject searchQuery = new BasicDBObject(); 

        if (status != null && !"".equals(status)) {
            searchQuery.put("Status", status);
        }
        if (orderId > 0) {
            searchQuery.put("OrderId", orderId);
        }
        ItemService items = new ItemService();
        List<Integer> itemsIds = items.getAllItemsIds(distributorId, manufacturerId);
        if(itemsIds != null && !itemsIds.isEmpty()) {
            searchQuery.put("ItemId", new BasicDBObject("$in", itemsIds));
        }
        MongoCursor<Document> cursor = orderDetailCollection.find(searchQuery).iterator();

        while (cursor.hasNext()) {
            orderDetails.add((OrderDetail) AppUtils.fromDocument(cursor.next(), OrderDetail.class));
        }

        return orderDetails;

    }

    public OrderDetail getOrderDetailById(int orderDetailId) throws UnknownHostException {

        OrderDetail orderDetail = new OrderDetail();

        MongoCursor<Document> cursor = orderDetailCollection.find(eq("_id", orderDetailId)).iterator();

        while (cursor.hasNext()) {
            orderDetail = ((OrderDetail) AppUtils.fromDocument(cursor.next(), OrderDetail.class));
        }

        return orderDetail;

    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) throws UnknownHostException, Exception {
        orderDetail.setOrderDetailId((int) AppUtils.getNextSequence("orderdetailid", counters));
        orderDetailCollection.insertOne(AppUtils.toDocument(orderDetail));
        return orderDetail;
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail, int orderDetailId) throws UnknownHostException {
        orderDetail.setOrderDetailId(orderDetailId);
        orderDetailCollection.updateOne(eq("_id", orderDetailId), new Document("$set", AppUtils.toDocument(orderDetail)));
        return orderDetail;
    }

    public void deleteOrderDetail(int orderDetailId) throws UnknownHostException {
        orderDetailCollection.deleteOne(eq("_id", orderDetailId));
    }
}
