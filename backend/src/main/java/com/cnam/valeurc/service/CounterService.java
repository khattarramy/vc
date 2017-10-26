/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.DbResource;
import com.cnam.valeurc.model.User;
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
public class CounterService {

    MongoClient mongo;
    MongoCollection countersCollection;
    MongoDatabase db;
    DbResource dbResource = new DbResource();

    public CounterService() throws UnknownHostException {
        mongo = dbResource.getMongoClient();
        db = mongo.getDatabase("valeurc");

        countersCollection = db.getCollection("counters");
    }

    public void deleteCounter(String id) {
        countersCollection.deleteOne(eq("_id", id));
    }

    public void deleteAllCounters() throws UnknownHostException {
        BasicDBObject searchQuery = new BasicDBObject();
        countersCollection.deleteMany(searchQuery);
    }
}
