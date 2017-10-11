/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.net.UnknownHostException;

/**
 *
 * @author George Harik
 */
public class DbConnect {
    public static final String DB_NAME = "valeurc";
    public MongoClient init() throws UnknownHostException {
        MongoClient mongo = new MongoClient("localhost", 27017);
        return mongo;
    }
    public MongoDatabase getDatabase(MongoClient mongo,String dbname) throws UnknownHostException {
        MongoDatabase db = mongo.getDatabase(dbname);
        return db;
    }
    public void close(MongoClient mongo) {
        mongo.close();
    }
    public boolean collectionExists(MongoDatabase db,final String collectionName) {
        MongoIterable<String> collectionNames = db.listCollectionNames();
        for (final String name : collectionNames) {
            if (name.equalsIgnoreCase(collectionName)) {
                return true;
            }
        }
        return false;
    }

}
