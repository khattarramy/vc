/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.OrderDetail;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author George Harik
 */
public class OrderDetailService {

    DbConnect dbConnect = new DbConnect();
    DBCollection orderDetailCollection;
    DB db;

    public OrderDetailService() throws UnknownHostException {
        db = dbConnect.init();
        if (!db.collectionExists("orderDetail")) {
            db.createCollection("orderDetail", null);
        }

        orderDetailCollection = db.getCollection("orderDetail");

    }

    public List<OrderDetail> getAllOrderDetails() throws UnknownHostException {

        List<OrderDetail> orderDetails = new ArrayList();
        DBCursor cursor = orderDetailCollection.find();

        while (cursor.hasNext()) {
            orderDetails.add((OrderDetail) AppUtils.fromDBObject(cursor.next(), OrderDetail.class));
        }

        return orderDetails;

    }

    public OrderDetail getOrderDetailById(String orderDetailId) throws UnknownHostException {

        OrderDetail orderDetail = new OrderDetail();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderDetailId", orderDetailId);

        DBCursor cursor = orderDetailCollection.find(searchQuery);

        while (cursor.hasNext()) {
            orderDetail = ((OrderDetail) AppUtils.fromDBObject(cursor.next(), OrderDetail.class));
        }

        return orderDetail;

    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) throws UnknownHostException {

        orderDetail.setOrderDetailId(UUID.randomUUID());

        orderDetailCollection.insert(AppUtils.toDBObject(orderDetail));

        return orderDetail;
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail, String orderDetailId) throws UnknownHostException {
        
        OrderDetail oldOrderDetail = new OrderDetail();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderDetailId", orderDetailId);

        orderDetail.setOrderDetailId((UUID.fromString(orderDetailId)));
        
        DBCursor cursor = orderDetailCollection.find(searchQuery);

        while (cursor.hasNext()) {
            oldOrderDetail = ((OrderDetail) AppUtils.fromDBObject(cursor.next(), OrderDetail.class));
        }

        orderDetailCollection.update(AppUtils.toDBObject(oldOrderDetail), AppUtils.toDBObject(orderDetail));
        
        return orderDetail;
    }

    public void deleteOrderDetail(String orderDetailId) throws UnknownHostException {

        OrderDetail orderDetail = new OrderDetail();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("OrderDetailId", orderDetailId);

        DBCursor cursor = orderDetailCollection.find(searchQuery);

        while (cursor.hasNext()) {
            orderDetail = ((OrderDetail) AppUtils.fromDBObject(cursor.next(), OrderDetail.class));
        }

        orderDetailCollection.remove(AppUtils.toDBObject(orderDetail));
    }
}
