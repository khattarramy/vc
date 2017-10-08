/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.User;
import com.cnam.valeurc.model.User;
import com.cnam.valeurc.model.User;
import com.mongodb.*;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author George Harik
 */
public class UserService {

    DbConnect dbConnect = new DbConnect();
    DBCollection userCollection, counters;
    DB db;

    public UserService() throws UnknownHostException {
        db = dbConnect.init();
        if (!db.collectionExists("users")) {
            db.createCollection("users", null);
        }
        if (!db.collectionExists("counters")) {
            db.createCollection("counters", null);
            counters = db.getCollection("counters");
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", "userid");
            DBCursor cursor = counters.find(searchQuery);
            if(!cursor.hasNext()){
                String json = "{'_id' : 'userid','seq' : 0}";
                DBObject dbObject = (DBObject) JSON.parse(json);
                counters.insert(dbObject);
            }
        }
        userCollection = db.getCollection("users");

    }

    public User addUser(User user) throws UnknownHostException, Exception {
        BasicDBObject insert = AppUtils.toBasicDBObject(user);
        insert.put("_id", AppUtils.getNextSequence("userid", counters));
        userCollection.insert(insert);
        return user;
    }

    public User getUserById(String userId) {

        User user = new User();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("_id", Integer.parseInt(userId));

        DBCursor cursor = userCollection.find(searchQuery);

        while (cursor.hasNext()) {
            user = ((User) AppUtils.fromDBObject(cursor.next(), User.class));
        }

        return user;

    }

    public List<User> getAllUsers() throws UnknownHostException {
        List<User> users = new ArrayList();
        DBCursor cursor = userCollection.find();
        while (cursor.hasNext()) {
            users.add((User) AppUtils.fromDBObject(cursor.next(), User.class));
        }
        return users;
    }

    public User updateUser(User user, String userId) {
        User oldUser = new User();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("_id", Integer.parseInt(userId));

//        user.setUserId((Integer.parseInt(userId)));

        DBCursor cursor = userCollection.find(searchQuery);

        while (cursor.hasNext()) {
            oldUser = ((User) AppUtils.fromDBObject(cursor.next(), User.class));
        }

        userCollection.update(AppUtils.toDBObject(oldUser), AppUtils.toDBObject(user));

        return user;
    }

    public void deleteUser(String userId) {
        User user = new User();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("_id", Integer.parseInt(userId));

        DBCursor cursor = userCollection.find(searchQuery);

        while (cursor.hasNext()) {
            user = ((User) AppUtils.fromDBObject(cursor.next(), User.class));
        }

        userCollection.remove(AppUtils.toDBObject(user));
    }

}
