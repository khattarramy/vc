package com.cnam.valeurc;

import com.cnam.valeurc.model.User;
import com.cnam.valeurc.service.UserService;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;

import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import org.bson.Document;

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
        collection.updateOne(eq("_id", name),  new Document("$inc", new Document("seq", 1)) );
        Document myDoc = (Document) collection.find(eq("_id", name)).first();
        return myDoc.get("seq");
    }

}
