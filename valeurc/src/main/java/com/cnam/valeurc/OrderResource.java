/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.Order;
import com.cnam.valeurc.service.OrderService;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.HttpMethod.*;
import javax.ws.rs.core.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KHATT
 */

@Path("orders")
public class OrderResource {
    
    OrderService orderService = new OrderService();
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
     public List<Order> getOrders() throws UnknownHostException {
        return orderService.getAllOrders();
     }
    
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
     public String addOrder() throws UnknownHostException {
        return null;
       
         
     }
     
    
    
}
