/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.Item;
import com.cnam.valeurc.service.ItemService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.net.UnknownHostException;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author riidaali
 */
@RequestScoped

public class DbResource {

    public static DbResource dbResource;

    public static String mongoIpAddress = "localhost";
    public static Integer mongoPort = 27017;

    public static final MongoClient mongoClient = new MongoClient(mongoIpAddress, mongoPort);

    @Inject
    public DbResource() throws UnknownHostException {

    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public boolean collectionExists(MongoDatabase db, final String collectionName) {
        MongoIterable<String> collectionNames = db.listCollectionNames();
        for (final String name : collectionNames) {
            if (name.equalsIgnoreCase(collectionName)) {
                return true;
            }
        }
        return false;
    }
}
