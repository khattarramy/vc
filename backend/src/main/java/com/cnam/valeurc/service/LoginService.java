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
import static com.cnam.valeurc.service.DbConnect.DB_NAME;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
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

    DbConnect dbConnect = new DbConnect();
    MongoClient mongo;
    MongoCollection userCollection;
    MongoDatabase db;

    public LoginService() throws UnknownHostException {
        mongo = dbConnect.init();
        db = dbConnect.getDatabase(mongo, DB_NAME);
        if (!dbConnect.collectionExists("users")) {
            db.createCollection("users", new CreateCollectionOptions().capped(false));
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
        
        dbConnect.close(mongo);
        return user;

    }

}
