/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.OrderDetailHistory;
import com.cnam.valeurc.service.OrderDetailHistoryService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author KHATT
 */
@Path("orderdetailhistory")
public class OrderDetailHistoryResource {

    OrderDetailHistoryService orderDetailHistoryService;

    public OrderDetailHistoryResource() throws UnknownHostException {
        this.orderDetailHistoryService = new OrderDetailHistoryService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<OrderDetailHistory> getOrderDetailHistorys() throws UnknownHostException {
        return orderDetailHistoryService.getAllOrderDetailHistorys();
    }

    @GET
    @Path("/{orderDetailHistoryId}")
    @Produces(MediaType.APPLICATION_JSON)

    public OrderDetailHistory getOrderDetailHistory(@PathParam("orderDetailHistoryId") int orderDetailHistoryId) throws UnknownHostException {

        return orderDetailHistoryService.getOrderDetailHistoryById(orderDetailHistoryId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public OrderDetailHistory addOrderDetailHistory(OrderDetailHistory orderDetailHistory) throws UnknownHostException, Exception {
        return orderDetailHistoryService.addOrderDetailHistory(orderDetailHistory);

    }

    @PUT
    @Path("/{orderDetailHistoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public OrderDetailHistory updateOrderDetailHistory(OrderDetailHistory orderDetailHistory, @PathParam("orderDetailHistoryId") int orderDetailHistoryId) throws UnknownHostException {
        return orderDetailHistoryService.updateOrderDetailHistory(orderDetailHistory, orderDetailHistoryId);

    }

    @DELETE
    @Path("/{orderDetailHistoryId}")
    @Produces(MediaType.APPLICATION_JSON)

    public void deleteOrderDetailHistory(@PathParam("orderDetailHistoryId") int orderDetailHistoryId) throws UnknownHostException {
        orderDetailHistoryService.deleteOrderDetailHistory(orderDetailHistoryId);

    }

}
