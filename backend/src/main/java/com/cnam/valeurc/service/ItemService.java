/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Item;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.net.UnknownHostException;
import java.util.*;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author George Harik
 */
public class ItemService {

    DbConnect dbConnect = new DbConnect();
    MongoCollection itemCollection;
    MongoDatabase db;

    public ItemService() throws UnknownHostException {
        db = dbConnect.init();
        if (!dbConnect.collectionExists("item")) {
            db.createCollection("item", null);
        }

        itemCollection = db.getCollection("item");

    }

    public List<Item> getAllItems() throws UnknownHostException {

        List<Item> items = new ArrayList();
        DBCursor cursor = (DBCursor) itemCollection.find();

        while (cursor.hasNext()) {
            items.add((Item) AppUtils.fromDBObject(cursor.next(), Item.class));
        }

        return items;

    }

    public Item getItemById(String itemId) throws UnknownHostException {

        Item item = new Item();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("ItemId", itemId);

        DBCursor cursor = (DBCursor) itemCollection.find(searchQuery);

        while (cursor.hasNext()) {
            item = ((Item) AppUtils.fromDBObject(cursor.next(), Item.class));
        }

        return item;

    }

    public Item addItem(Item item) throws UnknownHostException {

        item.setItemId(UUID.randomUUID());
        itemCollection.insertOne(AppUtils.toDocument(item));
        return item;
    }

    public Item updateItem(Item item, String itemId) throws UnknownHostException {
        
        itemCollection.updateOne(eq("_id", itemId), AppUtils.toDocument(item));
        return item;
    }

    public void deleteItem(String itemId) throws UnknownHostException {
        itemCollection.deleteOne(eq("_id", itemId));
    }
}
