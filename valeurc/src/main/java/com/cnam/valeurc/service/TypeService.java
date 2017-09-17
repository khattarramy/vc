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
    
    DbConnect dbConnect=new DbConnect();
    
    public  List<Type> getTypeById(int id_type) throws UnknownHostException {
             
                List<Type> types =new ArrayList();
                DB db = dbConnect.init();
                DBCollection table = db.getCollection("item");

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("TypeId", id_type);

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) { 
                       types.add((Type) AppUtils.fromDBObject(cursor.next(),Type.class));
                }
                return types;

       }
    
}
