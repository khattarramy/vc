package com.cnam.valeurc;


import com.cnam.valeurc.service.DbConnect;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.util.JSON;
import org.bson.Document;
import java.net.UnknownHostException;
import com.cnam.valeurc.model.*;
import com.cnam.valeurc.service.*;
/**
 * @author Igor Baiborodine
 */
public class AppUtils {

    public static DBObject toDBObject(Object pojo) {
        String json = new Gson().toJson(pojo);
        return (DBObject) JSON.parse(json);
    }

    public static Document toDocument(Object pojo) {
        String json = new Gson().toJson(pojo);
        Document doc = Document.parse(json);
        return doc;
    }

    public static Document JSONtoDocument(String json) {
        Document doc = Document.parse(json);
        return doc;
    }

    public static Object fromDBObject(DBObject dbObj, Class clazz) {
        String json = dbObj.toString();
        return new Gson().fromJson(json, clazz);
    }

    public static Object fromDocument(Document dbDoc, Class clazz) {
        String json = dbDoc.toJson();
        return new Gson().fromJson(json, clazz);
    }

    public static BasicDBObject toBasicDBObject(Object pojo) {
        String json = new Gson().toJson(pojo);
        return (BasicDBObject) JSON.parse(json);
    }

    public static Object getNextSequence(String name, MongoCollection collection) throws Exception {
        collection.updateOne(eq("_id", name), new Document("$inc", new Document("seq", 1)));
        Document myDoc = (Document) collection.find(eq("_id", name)).first();
        return myDoc.get("seq");
    }

    public static MongoCollection checkCounters(DbConnect dbConnect, MongoDatabase db, String counter_id) {
        MongoCollection counters;
        if (!dbConnect.collectionExists("counters")) {
            db.createCollection("counters", new CreateCollectionOptions().capped(false));
        }
        counters = db.getCollection("counters");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", counter_id);
        MongoCursor<Document> cursor = counters.find(eq("_id", counter_id)).iterator();
        if (!cursor.hasNext()) {
            String json = String.format("{'_id' : '%s','seq' : 0}", counter_id);
            Document doc = AppUtils.JSONtoDocument(json);
            counters.insertOne(doc);
        }
        return counters;
    }

//    public static void main(String[] args) throws Exception {
//        try {
//            ItemService itemServ = new ItemService();
//            Item item = new Item();
//            item.setName("name12");
//            item.setDescription("address12");
//            item.setDistributorId(12);
//            item.setManufacturerId(22);
//            item.setModelNumber("password12");
//            itemServ.addItem(item);
////                        userServ.addUser(user);
////            userServ.addUser(user);
////            userServ.addUser(user);
////            userServ.addUser(user);
////            userServ.addUser(user);
////            userServ.addUser(user);
////            userServ.addUser(user);
//
////            userServ.deleteUser(6);
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }
}
