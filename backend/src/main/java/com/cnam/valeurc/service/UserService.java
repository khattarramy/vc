/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.User;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.net.UnknownHostException;
import java.util.*;
import org.bson.Document;

/**
 *
 * @author George Harik
 */
public class UserService {

    DbConnect dbConnect = new DbConnect();
    MongoCollection userCollection, counters;
    MongoDatabase db;

    public UserService() throws UnknownHostException {
        db = dbConnect.init();
        if (!dbConnect.collectionExists("users")) {
            db.createCollection("users", new CreateCollectionOptions().capped(false));
        }
         if (!dbConnect.collectionExists("counters")) { 
            db.createCollection("counters", new CreateCollectionOptions().capped(false)); 
            counters = db.getCollection("counters"); 
            BasicDBObject searchQuery = new BasicDBObject(); 
            searchQuery.put("_id", "userid"); 
            MongoCursor<Document> cursor = counters.find(eq("_id", "userid")).iterator();
            if(!cursor.hasNext()){ 
                String json = "{'_id' : 'userid','seq' : 0}"; 
                Document doc = AppUtils.JSONtoDocument(json); 
                counters.insertOne(doc); 
            } 
        } else {
            counters = db.getCollection("counters"); 
        }
        userCollection = db.getCollection("users");
    }

    public User addUser(User user) throws UnknownHostException, Exception {
        counters = db.getCollection("counters"); 
        user.setUserId((int) AppUtils.getNextSequence("userid", counters));
        userCollection.insertOne(AppUtils.toDocument(user));
        return user;
    }

    public User getUserById(int userId) {

        User user = new User();

        
        MongoCursor<Document> cursor =  userCollection.find(eq("_id", userId)).iterator();

        while (cursor.hasNext()) {
            user = ((User) AppUtils.fromDocument(cursor.next(), User.class));
        }

        return user;

    }

    public List<User> getAllUsers() throws UnknownHostException {
        List<User> users = new ArrayList();
        MongoCursor<Document> cursor = userCollection.find().iterator();
        while (cursor.hasNext()) {
            users.add((User) AppUtils.fromDocument(cursor.next(), User.class));
        }
        return users;
    }

    public User updateUser(User user, int userId) {
        user.setUserId(userId);
        userCollection.updateOne(Filters.eq("_id", userId),  new Document("$set", AppUtils.toDocument(user)));
        return user;
    }

    public void deleteUser(int userId) {
        userCollection.deleteOne(eq("_id", userId));
    }
}
