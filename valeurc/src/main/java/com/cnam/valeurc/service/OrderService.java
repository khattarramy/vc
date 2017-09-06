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
    
        public DB init() throws UnknownHostException{
            
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("valeurc");
                return db;
        }
    
        public  List<Order> getAllOrders() throws UnknownHostException {
            
                List<Order> orders =new ArrayList();
                DB db = init();
                DBCollection table = db.getCollection("order");

                DBCursor cursor = table.find();

                while (cursor.hasNext()) { 
                       orders.add((Order) AppUtils.fromDBObject(cursor.next(),Order.class));
                }
                return orders;

       }
        
         public  List<Order> getOrderById(int id_order) throws UnknownHostException {
             
                List<Order> orders =new ArrayList();
                DB db = init();
                DBCollection table = db.getCollection("order");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("id", id_order);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       orders.add((Order) AppUtils.fromDBObject(cursor.next(),Order.class));
                }
                return orders;

       }
         
          
    public Order addOrder(Order order) throws UnknownHostException {
     
        DB db = init();
        DBCollection orderCollection = db.getCollection("order");
       orderCollection.insert(AppUtils.toDBObject(order));
       return order;
    }
      
    
     
}
