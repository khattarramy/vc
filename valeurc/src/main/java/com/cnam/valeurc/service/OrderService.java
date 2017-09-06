/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Order;
import com.mongodb.*;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author KHATT
 */
public class OrderService {
    
      public  List<Order> getAllOrders2() {
      
         
        Order o1 = new Order(1,1,1);
        Order o2 = new Order(2,2,2);

        List<Order> listOrders = new ArrayList();
        listOrders.add(o1);
        listOrders.add(o2);
        
           return listOrders;

       }
     
        public  List<Order> getAllOrders() throws UnknownHostException {
            
                List<Order> orders =new ArrayList();
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("valeurc");
                DBCollection table = db.getCollection("order");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("id", 2);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       orders.add((Order) AppUtils.fromDBObject(cursor.next(),Order.class));
                }
                return orders;

       }
        
         public  List<Order> getOrderById(int id_order) throws UnknownHostException {
            
                List<Order> orders =new ArrayList();
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("valeurc");
                DBCollection table = db.getCollection("order");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("id", id_order);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       orders.add((Order) AppUtils.fromDBObject(cursor.next(),Order.class));
                }
                return orders;

       }
      
    
     
}
