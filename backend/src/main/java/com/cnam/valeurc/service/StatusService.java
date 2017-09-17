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
    DbConnect dbConnect=new DbConnect();
    
    public  List<Status> getStatusById(int id_Status) throws UnknownHostException {
             
                List<Status> status =new ArrayList();
                DB db = dbConnect.init();
                DBCollection table = db.getCollection("item");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("StatusId", id_Status);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       status.add((Status) AppUtils.fromDBObject(cursor.next(),Status.class));
                }
                return status;

       }
}
