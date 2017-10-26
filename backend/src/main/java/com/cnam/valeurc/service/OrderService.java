/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.service;

import com.cnam.valeurc.AppUtils;
import com.cnam.valeurc.DbResource;
import com.cnam.valeurc.model.Order;
import com.cnam.valeurc.model.OrderDetail;
import com.cnam.valeurc.model.OrderDto;
import com.cnam.valeurc.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import static com.mongodb.client.model.Filters.eq;
import java.net.UnknownHostException;
import java.util.*;
import org.bson.Document;

/**
 *
 * @author George Harik
 */
public class OrderService {

    MongoClient mongo;
    MongoCollection orderCollection, counters;
    MongoDatabase db;
    DbResource dbResource = new DbResource();

    public OrderService() throws UnknownHostException {
        mongo = dbResource.getMongoClient();
        db = mongo.getDatabase("valeurc");
        if (!dbResource.collectionExists(db, "order")) {
            db.createCollection("order", new CreateCollectionOptions().capped(false));
        }
        counters = AppUtils.checkCounters(db, "orderid");

        orderCollection = db.getCollection("order");

    }

    public List<OrderDto> getAllOrdersDto(int userId, String status, int distributorId, int manufacturerId) throws UnknownHostException {

        List<Order> orders = new ArrayList();

        List<OrderDto> orderDtos = new ArrayList<OrderDto>();

        UserService userService = new UserService();

        List<User> users = new ArrayList<User>();

        orders = getAllOrders(userId, status, distributorId, manufacturerId);

        for (Order o : orders) {

            orderDtos.add(new OrderDto(o.getOrderId(), o.getUser().getName(), o.getUserId(), o.getStatus(), o.getDateInitialized(), o.getDateFinished()));

        }

        return orderDtos;

    }

    public List<Order> getAllOrders(int userId, String status, int distributorId, int manufacturerId) throws UnknownHostException {

        List<Order> orders = new ArrayList();

        MongoCursor<Document> cursor;

        if ((userId == 0) && ((status == null) || (status == "")) && (distributorId == 0) && (manufacturerId == 0)) {

            cursor = orderCollection.find().iterator();

            while (cursor.hasNext()) {
                orders.add((Order) AppUtils.fromDocument(cursor.next(), Order.class));
            }

        } else {

            BasicDBObject searchQuery = new BasicDBObject();

            if (status != null && !"".equals(status)) {

                searchQuery.put("Status", status);

            }
            if (userId > 0) {
                searchQuery.put("UserId", userId);
            }

            if (distributorId > 0) {
                searchQuery.put("OrderDetails.Item.Distributor._id", distributorId);
            }

            if (manufacturerId > 0) {
                searchQuery.put("OrderDetails.Item.Manufacturer._id", manufacturerId);
            }

            if (!searchQuery.isEmpty()) {

                cursor = orderCollection.find(searchQuery).iterator();

                while (cursor.hasNext()) {
                    orders.add((Order) AppUtils.fromDocument(cursor.next(), Order.class));
                }
            }
        }

        return orders;

    }

    public Order getOrderById(int orderId) throws UnknownHostException {

        Order order = new Order();

        MongoCursor<Document> cursor = orderCollection.find(eq("_id", orderId)).iterator();

        while (cursor.hasNext()) {
            order = ((Order) AppUtils.fromDocument(cursor.next(), Order.class));
        }

        return order;

    }

    public Order addOrder(Order order) throws UnknownHostException, Exception {

        UserService userService = new UserService();

        order.setUser(userService.getUserById(order.getUserId()));

        order.setOrderId((int) AppUtils.getNextSequence("orderid", counters));

        orderCollection.insertOne(AppUtils.toDocument(order));

        return order;
    }

    public Order updateOrder(Order order, int orderId) throws UnknownHostException {

        order.setOrderId(orderId);

        UserService userService = new UserService();

        order.setUser(userService.getUserById(order.getUserId()));

        orderCollection.updateOne(eq("_id", orderId), new Document("$set", AppUtils.toDocument(order)));

        return order;
    }

    public void deleteOrder(int orderId) throws UnknownHostException {

        orderCollection.deleteOne(eq("_id", orderId));

    }

    public void deleteAllOrders() throws UnknownHostException {

        BasicDBObject searchQuery = new BasicDBObject();

        orderCollection.deleteMany(searchQuery);

        CounterService counterService = new CounterService();

        counterService.deleteCounter("orderid");
    }
}
