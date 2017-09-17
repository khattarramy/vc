/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Item;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author George Harik
 */
public class ItemService {

    DbConnect dbConnect = new DbConnect();
    DBCollection itemCollection;
    DB db;

    public ItemService() throws UnknownHostException {
        db = dbConnect.init();
        if (!db.collectionExists("item")) {
            db.createCollection("item", null);
        }

        itemCollection = db.getCollection("item");

    }

    public List<Item> getAllItems() throws UnknownHostException {

        List<Item> items = new ArrayList();
        DBCursor cursor = itemCollection.find();

        while (cursor.hasNext()) {
            items.add((Item) AppUtils.fromDBObject(cursor.next(), Item.class));
        }
        
        cursor.close();

        return items;

    }

    public Item getItemById(String itemId) throws UnknownHostException {

        Item item = new Item();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("ItemId", itemId);

        DBCursor cursor = itemCollection.find(searchQuery);

        while (cursor.hasNext()) {
            item = ((Item) AppUtils.fromDBObject(cursor.next(), Item.class));
        }
        
        cursor.close();

        return item;

    }

    public Item addItem(Item item) throws UnknownHostException {

        item.setItemId(UUID.randomUUID());

        itemCollection.insert(AppUtils.toDBObject(item));

        return item;
    }

    public Item updateItem(Item item, String itemId) throws UnknownHostException {
        
        Item oldItem = new Item();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("ItemId", itemId);
        item.setItemId((UUID.fromString(itemId)));
        DBCursor cursor = itemCollection.find(searchQuery);

        while (cursor.hasNext()) {
            oldItem = ((Item) AppUtils.fromDBObject(cursor.next(), Item.class));
        }

        itemCollection.update(AppUtils.toDBObject(oldItem), AppUtils.toDBObject(item));
        cursor.close();
        return item;
    }

    public void deleteItem(String itemId) throws UnknownHostException {

        Item item = new Item();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("ItemId", itemId);

        DBCursor cursor = itemCollection.find(searchQuery);

        while (cursor.hasNext()) {
            item = ((Item) AppUtils.fromDBObject(cursor.next(), Item.class));
        }

        itemCollection.remove(AppUtils.toDBObject(item));
        cursor.close();
    }
}
