/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.model;
import java.util.UUID;

/**
 *
 * @author George Harik
 */
public class OrderDetail {
    private UUID OrderDetailId;
    private String OrderId;
    private String ItemId;
    private String StatusId;
    private String UserId;
       
    public OrderDetail() {
        
    }
    public OrderDetail(UUID orderDetailId, String orderId, String itemId, String statusId, String userId) {
        this.OrderDetailId = orderDetailId;
        this.ItemId = itemId;
        this.ItemId = itemId;
        this.StatusId = statusId;
        this.UserId = userId;
    }
    
    public UUID getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(UUID id) {
        this.OrderDetailId = id;
    }
    
    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String id) {
        this.OrderId = id;
    }
    
    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String id) {
        this.ItemId = id;
    }
    
    public String getStatusId() {
        return StatusId;
    }

    public void setStatusId(String id) {
        this.StatusId = id;
    }
    
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String id) {
        this.UserId = id;
    }
}
