/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Item;
import static com.cnam.valeurc.service.DbConnect.DB_NAME;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
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
    MongoClient mongo;
    MongoCollection itemCollection, counters;
    MongoDatabase db;

    public ItemService() throws UnknownHostException {
        mongo = dbConnect.init();
        db = dbConnect.getDatabase(mongo, DB_NAME);
        if (!dbConnect.collectionExists(db,"item")) {
            db.createCollection("item", new CreateCollectionOptions().capped(false));
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
        dbConnect.close(mongo);
        return items;

    }

    public List<Integer> getAllItemsIds(int distributorId, int manufacturerId) throws UnknownHostException {

        List<Integer> ids = new ArrayList();
        BasicDBObject searchQuery = new BasicDBObject();
        if (manufacturerId > 0) {
            searchQuery.put("ManufacturerId", manufacturerId);
        }
        if (distributorId > 0) {
            searchQuery.put("DistributorId", distributorId);
        }
        MongoCursor<Document> cursor = itemCollection.find(searchQuery).iterator();

        while (cursor.hasNext()) {
            ids.add((Integer) cursor.next().get("_id"));
        }
        dbConnect.close(mongo);

        return ids;

    }

    public Item getItemById(int itemId) throws UnknownHostException {

        Item item = new Item();

        MongoCursor<Document> cursor = itemCollection.find(eq("_id", itemId)).iterator();

        while (cursor.hasNext()) {
            item = ((Item) AppUtils.fromDocument(cursor.next(), Item.class));
        }
        dbConnect.close(mongo);

        return item;

    }

    public Item addItem(Item item) throws UnknownHostException, Exception {
        item.setItemId((int) AppUtils.getNextSequence("itemid", counters));
        itemCollection.insertOne(AppUtils.toDocument(item));
        dbConnect.close(mongo);
        return item;
    }

    public Item updateItem(Item item, int itemId) throws UnknownHostException {
        item.setItemId(itemId);
        itemCollection.updateOne(eq("_id", itemId), new Document("$set", AppUtils.toDocument(item)));
        dbConnect.close(mongo);
        return item;
    }

    public void deleteItem(int itemId) throws UnknownHostException {
        itemCollection.deleteOne(eq("_id", itemId));
        dbConnect.close(mongo);
    }
    
        public void deleteAllItems() throws UnknownHostException {
        itemCollection.drop();
        dbConnect.close(mongo);
    }
}
