/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

/**
 *
 * @author George Harik
 */
public class DbConnect {
            public DB init() throws UnknownHostException{
            
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("valeurc");
                return db;
        }
}
