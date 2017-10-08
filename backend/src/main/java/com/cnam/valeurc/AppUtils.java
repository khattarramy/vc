package com.cnam.valeurc;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * @author Igor Baiborodine
 */
public class AppUtils {

  public static DBObject toDBObject(Object pojo) {
    String json = new Gson().toJson(pojo);
    return (DBObject) JSON.parse(json);
  }
  public static BasicDBObject toBasicDBObject(Object pojo) {
    String json = new Gson().toJson(pojo);
    return (BasicDBObject) JSON.parse(json);
  }

  public static Object fromDBObject(DBObject dbObj, Class clazz) {
    String json = dbObj.toString();
    return new Gson().fromJson(json, clazz);
  }
  public static Object getNextSequence(String name,DBCollection collection) throws Exception {
    BasicDBObject find = new BasicDBObject();
    find.put("_id", name);
    BasicDBObject update = new BasicDBObject();
    update.put("$inc", new BasicDBObject("seq", 1));
    DBObject obj =  collection.findAndModify(find, update);
    return obj.get("seq");
   }
}
