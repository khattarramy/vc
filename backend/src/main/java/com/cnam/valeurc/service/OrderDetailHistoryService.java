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
    DbConnect dbConnect=new DbConnect();
    
    public  List<OrderDetailHistory> getOrderDetailHistoryByUserId(int id_UserDetail) throws UnknownHostException {
             
                List<OrderDetailHistory> orderDetailHistory =new ArrayList();
                DB db = dbConnect.init();
                DBCollection table = db.getCollection("order-detail-history");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("UserId", id_UserDetail);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       orderDetailHistory.add((OrderDetailHistory) AppUtils.fromDBObject(cursor.next(),OrderDetailHistory.class));
                }
                return orderDetailHistory;

       }
}
