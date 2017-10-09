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
 * @author George Harik
 */

@Path("orderdetailhistory/orderdetail")
public class OrderDetailHistoryByOrderDetailResourse {
    
    OrderDetailHistoryService orderDetailHistoryService;

    public OrderDetailHistoryByOrderDetailResourse() throws UnknownHostException {
        this.orderDetailHistoryService = new OrderDetailHistoryService();
    }

  
    @GET
    @Path("/{orderDetailId}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<OrderDetailHistory> getOrderDetailHistoryByOrderDetail(@PathParam("orderDetailId") int orderDetailId) throws UnknownHostException {

        return orderDetailHistoryService.getOrderDetailHistoryByOrderDetailId(orderDetailId);
    }
}
