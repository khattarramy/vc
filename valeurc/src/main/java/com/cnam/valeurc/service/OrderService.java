/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

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
    
      public  List<Order> getAllOrders() {
      
         
        Order o1 = new Order(1,1,1);
        Order o2 = new Order(2,2,2);

        List<Order> listOrders = new ArrayList();
        listOrders.add(o1);
        listOrders.add(o2);
        
           return listOrders;

       }
      
      public  List<String> getDBs() throws UnknownHostException{
          MongoClient mongo = new MongoClient( "localhost" , 27017 );
        
        
           DB db = mongo.getDB("valeurc");    
           List<String> dbs = mongo.getDatabaseNames();
         
             return dbs;
        }
     
}
