/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.DbResource;
import com.cnam.valeurc.model.Login;
import com.cnam.valeurc.model.OrderDetail;
import com.cnam.valeurc.model.User;
import com.cnam.valeurc.model.User;
import com.cnam.valeurc.model.User;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import java.net.UnknownHostException;
import java.util.*;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author riidaali
 */
public class LoginService {

    MongoClient mongo;
    MongoCollection userCollection;
    MongoDatabase db;
    DbResource dbResource = new DbResource();

    public LoginService() throws UnknownHostException {
        mongo = dbResource.getMongoClient();
        db = mongo.getDatabase("valeurc");
        if (!dbResource.collectionExists(db, "users")) {
            db.createCollection("users", new CreateCollectionOptions().capped(false));
        }

        userCollection = db.getCollection("users");
    }

    public User loginUser(Login login) {

        User user = new User();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("Email", login.getEmail());
        searchQuery.put("Password", login.getPassword());

        MongoCursor<Document> cursor = userCollection.find(searchQuery).iterator();

        while (cursor.hasNext()) {
            user = ((User) AppUtils.fromDocument(cursor.next(), User.class));

        }

        return user;

    }

}
