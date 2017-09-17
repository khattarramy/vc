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
    private int OrderId;
    private int ItemId;
    private int StatusId;
    private int UserId;
       
    public OrderDetail() {
        
    }
    public OrderDetail(UUID orderDetailId, int orderId, int itemId, int statusId, int userId) {
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
    
    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int id) {
        this.OrderId = id;
    }
    
    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int id) {
        this.ItemId = id;
    }
    
    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int id) {
        this.StatusId = id;
    }
    
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int id) {
        this.UserId = id;
    }
}
