/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.Order;
import com.cnam.valeurc.service.OrderService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.HttpMethod.*;
import javax.ws.rs.core.*;

/**
 *
 * @author KHATT
 */

@Path("orders")
public class OrderResource {
    
    OrderService orderService = new OrderService();
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
     public List<String> getOrders() throws UnknownHostException {
        return orderService.getDBs();
     }
    
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
     public List<String> addOrder() throws UnknownHostException {
        return orderService.getDBs();
     }
    
    
}
