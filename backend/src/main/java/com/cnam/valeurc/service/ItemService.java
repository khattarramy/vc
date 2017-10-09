/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Item;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.net.UnknownHostException;
import java.util.*;
import org.bson.Document;

/**
 *
 * @author George Harik
 */
public class ItemService {

    DbConnect dbConnect = new DbConnect();
    MongoCollection itemCollection, counters;
    MongoDatabase db;

    public ItemService() throws UnknownHostException {
        db = dbConnect.init();
        if (!dbConnect.collectionExists("item")) {
            db.createCollection("item", null);
        }
        counters = AppUtils.checkCounters(dbConnect, db, "itemid"); 
        itemCollection = db.getCollection("item");

    }

    public List<Item> getAllItems() throws UnknownHostException {

        List<Item> items = new ArrayList();
        MongoCursor<Document> cursor = itemCollection.find().iterator();

        while (cursor.hasNext()) {
            items.add((Item) AppUtils.fromDocument(cursor.next(), Item.class));
        }

        return items;

    }

    public Item getItemById(int itemId) throws UnknownHostException {

        Item item = new Item();

        MongoCursor<Document> cursor = itemCollection.find(eq("_id", itemId)).iterator();

        while (cursor.hasNext()) {
            item = ((Item) AppUtils.fromDocument(cursor.next(), Item.class));
        }

        return item;

    }

    public Item addItem(Item item)  throws UnknownHostException, Exception {
        item.setItemId((int) AppUtils.getNextSequence("itemid", counters));
        itemCollection.insertOne(AppUtils.toDocument(item));
        return item;
    }

    public Item updateItem(Item item, int itemId) throws UnknownHostException {
        item.setItemId(itemId);
        itemCollection.updateOne(eq("_id", itemId), new Document("$set", AppUtils.toDocument(item)));
        return item;
    }

    public void deleteItem(int itemId) throws UnknownHostException {
        itemCollection.deleteOne(eq("_id", itemId));
    }
}
