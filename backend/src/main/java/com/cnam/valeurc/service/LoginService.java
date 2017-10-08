/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Login;
import com.cnam.valeurc.model.User;
import com.cnam.valeurc.model.User;
import com.cnam.valeurc.model.User;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.net.UnknownHostException;
import java.util.*;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author riidaali
 */
public class LoginService {

    DbConnect dbConnect = new DbConnect();
    MongoCollection userCollection;
    MongoDatabase db;

    public LoginService() throws UnknownHostException {
        db = dbConnect.init();
        if (!dbConnect.collectionExists("users")) {
            db.createCollection("users", null);
        }

        userCollection = db.getCollection("users");
    }

    public User loginUser(Login login) {

        User user = new User();
        
        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("Email", login.getEmail());
        searchQuery.put("Password", login.getPassword());

        DBCursor cursor = (DBCursor) userCollection.find(searchQuery);

        while (cursor.hasNext()) {
            user = ((User) AppUtils.fromDBObject(cursor.next(), User.class));
        }

        return user;

    }

}
