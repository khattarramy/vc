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
import javax.ws.rs.core.*;

/**
 *
 * @author KHATT
 */
@Path("orders")
public class OrderResource {

    OrderService orderService;

    public OrderResource() throws UnknownHostException {
        this.orderService = new OrderService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<Order> getOrders(@QueryParam("userId") int userId, @QueryParam("status") String status, @QueryParam("distributorId") int distributorId, @QueryParam("manufacturerId") int manufacturerId) throws UnknownHostException {
        
        return orderService.getAllOrders(userId, status, distributorId, manufacturerId);
        

    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Order getOrder(@PathParam("orderId") int orderId) throws UnknownHostException {

        return orderService.getOrderById(orderId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Order addOrder(Order order) throws UnknownHostException, Exception {
        return orderService.addOrder(order);

    }

    @PUT
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Order updateOrder(Order order, @PathParam("orderId") int orderId) throws UnknownHostException {
        return orderService.updateOrder(order, orderId);

    }

    @DELETE
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)

    public void deleteOrder(@PathParam("orderId") int orderId) throws UnknownHostException {
        orderService.deleteOrder(orderId);

    }
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)

    public void deleteAllOrders() throws UnknownHostException {
        orderService.deleteAllOrders();

    }


}
