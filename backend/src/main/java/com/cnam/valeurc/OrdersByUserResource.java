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
@Path("orders/user")
public class OrdersByUserResource {

    OrderService orderService;

    public OrdersByUserResource() throws UnknownHostException {
        this.orderService = new OrderService();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Order> getOrdersByUserId(@PathParam("userId") String userId) throws UnknownHostException {

        return orderService.getOrdersByUserId(userId);
    }

}
