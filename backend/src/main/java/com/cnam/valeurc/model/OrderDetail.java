/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.model;

/**
 *
 * @author George Harik
 */
public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int itemId;
    private int statusId;
    private int userId;
       
    public OrderDetail() {
        
    }
    public OrderDetail(int orderDetailId, int orderId, int itemId, int statusId, int userId) {
        this.orderDetailId = orderDetailId;
        this.itemId = itemId;
        this.itemId = itemId;
        this.statusId = statusId;
        this.userId = userId;
    }
    
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int id) {
        this.orderDetailId = id;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }
    
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int id) {
        this.itemId = id;
    }
    
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int id) {
        this.statusId = id;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }
}
