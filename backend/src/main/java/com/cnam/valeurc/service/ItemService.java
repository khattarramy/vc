/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.DbResource;
import com.cnam.valeurc.model.Item;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import static com.mongodb.client.model.Filters.eq;
import java.net.UnknownHostException;
import java.util.*;
import javax.inject.Inject;
import org.bson.Document;

/**
 *
 * @author George Harik
 */
public class ItemService {

    
    MongoClient mongo;
    MongoCollection itemCollection, counters;
    MongoDatabase db;
    
    DbResource dbResource = new DbResource();

    public ItemService() throws UnknownHostException {
        mongo = dbResource.mongoClient;
        db = mongo.getDatabase("valeurc");

        if (!dbResource.collectionExists(db, "item")) {
            db.createCollection("item", new CreateCollectionOptions().capped(false));
        }

        counters = AppUtils.checkCounters(db, "itemid");

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

        

        return ids;

    }

    public Item getItemById(int itemId) throws UnknownHostException {

        Item item = new Item();

        MongoCursor<Document> cursor = itemCollection.find(eq("_id", itemId)).iterator();

        while (cursor.hasNext()) {
            item = ((Item) AppUtils.fromDocument(cursor.next(), Item.class));
        }
        

        return item;

    }

    public Item addItem(Item item) throws UnknownHostException, Exception {

        UserService userService = new UserService();

        item.setDistributor(userService.getUserById(item.getDistributorId()));

        item.setManufacturer(userService.getUserById(item.getManufacturerId()));

        item.setItemId((int) AppUtils.getNextSequence("itemid", counters));

        itemCollection.insertOne(AppUtils.toDocument(item));

        

        return item;
    }

    public Item updateItem(Item item, int itemId) throws UnknownHostException {
        item.setItemId(itemId);

        UserService userService = new UserService();

        item.setDistributor(userService.getUserById(item.getDistributorId()));

        item.setManufacturer(userService.getUserById(item.getManufacturerId()));

        itemCollection.updateOne(eq("_id", itemId), new Document("$set", AppUtils.toDocument(item)));

        

        return item;
    }

    public void deleteItem(int itemId) throws UnknownHostException {

        itemCollection.deleteOne(eq("_id", itemId));

        
    }

    public void deleteAllItems() throws UnknownHostException {

        BasicDBObject searchQuery = new BasicDBObject();

        itemCollection.deleteMany(searchQuery);

     

        CounterService counterService = new CounterService();

        counterService.deleteCounter("itemid");
    }
}
