/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Type;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author George Harik
 */
public class TypeService {

    DbConnect dbConnect = new DbConnect();
    DBCollection typeCollection;
    DB db;

    public TypeService() throws UnknownHostException {
        db = dbConnect.init();
        if (!db.collectionExists("type")) {
            db.createCollection("type", null);
        }

        typeCollection = db.getCollection("type");

    }

    public List<Type> getAllTypes() throws UnknownHostException {

        List<Type> types = new ArrayList();
        DBCursor cursor = typeCollection.find();

        while (cursor.hasNext()) {
            types.add((Type) AppUtils.fromDBObject(cursor.next(), Type.class));
        }

        return types;

    }

    public Type getTypeById(String typeId) throws UnknownHostException {

        Type type = new Type();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("TypeId", typeId);

        DBCursor cursor = typeCollection.find(searchQuery);

        while (cursor.hasNext()) {
            type = ((Type) AppUtils.fromDBObject(cursor.next(), Type.class));
        }

        return type;

    }

    public Type addType(Type type) throws UnknownHostException {

        type.setTypeId(UUID.randomUUID());

        typeCollection.insert(AppUtils.toDBObject(type));

        return type;
    }

    public Type updateType(Type type, String typeId) throws UnknownHostException {
        
        Type oldType = new Type();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("TypeId", type.getTypeId());

        type.setTypeId((UUID.fromString(typeId)));
        
        DBCursor cursor = typeCollection.find(searchQuery);

        while (cursor.hasNext()) {
            oldType = ((Type) AppUtils.fromDBObject(cursor.next(), Type.class));
        }

        typeCollection.update(AppUtils.toDBObject(oldType), AppUtils.toDBObject(type));
        
        return type;
    }

    public void deleteType(String typeId) throws UnknownHostException {

        Type type = new Type();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("TypeId", typeId);

        DBCursor cursor = typeCollection.find(searchQuery);

        while (cursor.hasNext()) {
            type = ((Type) AppUtils.fromDBObject(cursor.next(), Type.class));
        }

        typeCollection.remove(AppUtils.toDBObject(type));
    }
}
