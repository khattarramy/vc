/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;
import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.User;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;
/**
 *
 * @author George Harik
 */
public class UserService {
    
        DbConnect dbConnect=new DbConnect();
    
       /* public  List<Order> getAllUsers() throws UnknownHostException {
            
                List<Order> orders =new ArrayList();
                DB db = dbConnect.init();
                DBCollection table = db.getCollection("order");

                DBCursor cursor = table.find();

                while (cursor.hasNext()) { 
                       orders.add((Order) AppUtils.fromDBObject(cursor.next(),Order.class));
                }
                return orders;

       }
        
         public  List<Order> getOrderById(int id_order) throws UnknownHostException {
             
                List<Order> orders =new ArrayList();
                DB db = dbConnect.init();
                DBCollection table = db.getCollection("order");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("id", id_order);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       orders.add((Order) AppUtils.fromDBObject(cursor.next(),Order.class));
                }
                return orders;

       }
         
        */  
    public User addUser(User user) throws UnknownHostException {
     
        DB db = dbConnect.init();
        DBCollection userCollection = db.getCollection("users");
       userCollection.insert(AppUtils.toDBObject(user));
       return user;
    }
}
