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
    DbConnect dbConnect=new DbConnect();
    
    public  List<OrderDetail> getOrderDetailById(int id_order) throws UnknownHostException {
             
                List<OrderDetail> orderdetail =new ArrayList();
                DB db = dbConnect.init();
                DBCollection table = db.getCollection("order-detail");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("OrderDetailId", id_order);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       orderdetail.add((OrderDetail) AppUtils.fromDBObject(cursor.next(),OrderDetail.class));
                }
                return orderdetail;

       }
         
       
    public OrderDetail addOrderDetail(OrderDetail orderDetail) throws UnknownHostException {
     
        DB db = dbConnect.init();
        DBCollection userCollection = db.getCollection("order-detail");
       userCollection.insert(AppUtils.toDBObject(orderDetail));
       return orderDetail;
    }
}
