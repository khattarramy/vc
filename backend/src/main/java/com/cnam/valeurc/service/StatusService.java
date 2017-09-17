/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.model.Status;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author George Harik
 */
public class StatusService {

    DbConnect dbConnect = new DbConnect();
    DBCollection statusCollection;
    DB db;

    public StatusService() throws UnknownHostException {
        db = dbConnect.init();
        if (!db.collectionExists("status")) {
            db.createCollection("status", null);
        }

        statusCollection = db.getCollection("status");

    }

    public List<Status> getAllStatuss() throws UnknownHostException {

        List<Status> statuss = new ArrayList();
        DBCursor cursor = statusCollection.find();

        while (cursor.hasNext()) {
            statuss.add((Status) AppUtils.fromDBObject(cursor.next(), Status.class));
        }

        return statuss;

    }

    public Status getStatusById(String statusId) throws UnknownHostException {

        Status status = new Status();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("StatusId", statusId);

        DBCursor cursor = statusCollection.find(searchQuery);

        while (cursor.hasNext()) {
            status = ((Status) AppUtils.fromDBObject(cursor.next(), Status.class));
        }

        return status;

    }

    public Status addStatus(Status status) throws UnknownHostException {

        status.setStatusId(UUID.randomUUID());

        statusCollection.insert(AppUtils.toDBObject(status));

        return status;
    }

    public Status updateStatus(Status status, String statusId) throws UnknownHostException {
        
        Status oldStatus = new Status();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("StatusId", status.getStatusId());

        DBCursor cursor = statusCollection.find(searchQuery);

        while (cursor.hasNext()) {
            oldStatus = ((Status) AppUtils.fromDBObject(cursor.next(), Status.class));
        }

        statusCollection.update(AppUtils.toDBObject(oldStatus), AppUtils.toDBObject(status));
        
        return status;
    }

    public void deleteStatus(String statusId) throws UnknownHostException {

        Status status = new Status();

        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.put("StatusId", statusId);

        DBCursor cursor = statusCollection.find(searchQuery);

        while (cursor.hasNext()) {
            status = ((Status) AppUtils.fromDBObject(cursor.next(), Status.class));
        }

        statusCollection.remove(AppUtils.toDBObject(status));
    }
}
