/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;
import com.cnam.valeurc.model.OrderDetail;
import com.cnam.valeurc.service.OrderDetailService;
import com.cnam.valeurc.service.OrderDetailService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
/**
 *
 * @author George Harik
 */

@Path("orderdetails/order")
public class OrderDetailsByOrderResourse {
    
    OrderDetailService orderDetailService;

    public OrderDetailsByOrderResourse() throws UnknownHostException {
        this.orderDetailService = new OrderDetailService();
    }

  
    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<OrderDetail> getOrdersByUserId(@PathParam("orderId") String orderId) throws UnknownHostException {

        return orderDetailService.getOrderDetailByOrderId(orderId);
    }
}
