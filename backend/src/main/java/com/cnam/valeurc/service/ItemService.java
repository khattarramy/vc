/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;
import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Item;
import com.cnam.valeurc.model.Order;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;
/**
 *
 * @author George Harik
 */
public class ItemService {
    DbConnect dbConnect=new DbConnect();
    

    
        public  List<Item> getAllItems() throws UnknownHostException {
            
                List<Item> items =new ArrayList();
                DB db = dbConnect.init();
                DBCollection table = db.getCollection("item");

                DBCursor cursor = table.find();

                while (cursor.hasNext()) { 
                       items.add((Item) AppUtils.fromDBObject(cursor.next(),Item.class));
                }
                return items;

       }
        
         public  List<Item> getItemById(int itemId) throws UnknownHostException {
             
                List<Item> items =new ArrayList();
                DB db = dbConnect.init();
                DBCollection table = db.getCollection("item");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("ItemId", itemId);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       items.add((Item) AppUtils.fromDBObject(cursor.next(),Item.class));
                }
                return items;

       }
         
          
    public Item addItem(Item item) throws UnknownHostException {
     
        DB db = dbConnect.init();
        DBCollection itemCollection = db.getCollection("item");
       itemCollection.insert(AppUtils.toDBObject(item));
       return item;
    }
      
    
}
