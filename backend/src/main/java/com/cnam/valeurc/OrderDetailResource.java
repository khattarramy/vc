/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.OrderDetail;
import com.cnam.valeurc.service.OrderDetailService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author KHATT
 */
@Path("orderdetails")
public class OrderDetailResource {

    OrderDetailService orderDetailService;

    public OrderDetailResource() throws UnknownHostException {
        this.orderDetailService = new OrderDetailService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<OrderDetail> getOrderDetails() throws UnknownHostException {
        return orderDetailService.getAllOrderDetails();
    }

    @GET
    @Path("/{orderDetailId}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public OrderDetail getOrderDetail(@PathParam("orderDetailId") String orderDetailId) throws UnknownHostException {

        return orderDetailService.getOrderDetailById(orderDetailId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public OrderDetail addOrderDetail(OrderDetail orderDetail) throws UnknownHostException {
        return orderDetailService.addOrderDetail(orderDetail);

    }

    @PUT
    @Path("/{orderDetailId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public OrderDetail updateOrderDetail(OrderDetail orderDetail, @PathParam("orderDetailId") String orderDetailId) throws UnknownHostException {
        return orderDetailService.updateOrderDetail(orderDetail, orderDetailId);

    }

    @DELETE
    @Path("/{orderDetailId}")
    @Produces(MediaType.APPLICATION_JSON)

    public void deleteOrderDetail(@PathParam("orderDetailId") String orderDetailId) throws UnknownHostException {
        orderDetailService.deleteOrderDetail(orderDetailId);

    }

}
