/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.dao;




import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



/**
 *
 * @author KHATT
 */
public class OrderDao {
    
  private MongoClient mongoClient;
  private String dbName;
  private String collectionName;
  private DBCollection booksCollection;
    
  public OrderDao(){
  }
  
  public void init() throws UnknownHostException {
    DB valeurcDatabase = mongoClient.getDB(dbName);
    booksCollection = valeurcDatabase.getCollection(collectionName);
  }
  
  
}
