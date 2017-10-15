/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.User;
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
public class CounterService {

    DbConnect dbConnect = new DbConnect();
    MongoClient mongo;
    MongoCollection countersCollection;
    MongoDatabase db;

    public CounterService() throws UnknownHostException {
        mongo = dbConnect.init();
        db = dbConnect.getDatabase(mongo, DB_NAME);
        
        countersCollection = db.getCollection("counters");
    }




    public void deleteCounter(String id) {
        countersCollection.deleteOne(eq("_id", id));
        dbConnect.close(mongo);
    }

    public void deleteAllCounters() throws UnknownHostException {
        BasicDBObject searchQuery = new BasicDBObject();
        countersCollection.deleteMany(searchQuery);
        dbConnect.close(mongo);
    }
}
