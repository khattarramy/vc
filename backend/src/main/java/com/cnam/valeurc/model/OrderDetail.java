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
    private int _id;
    private int OrderId;
    private int ItemId;
    private String Status;
    private int Quantity;
    private int QuantityDistributor;
       
    public OrderDetail() {
        
    }
    public OrderDetail(int orderDetailId, int orderId, int itemId, String status) {
        this._id = orderDetailId;
        this.OrderId = orderId;
        this.ItemId = itemId;
        this.Status = status;
    }
    
    public int getOrderDetailId() {
        return _id;
    }

    public void setOrderDetailId(int id) {
        this._id = id;
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
    
    public String getStatus() {
        return Status;
    }

    public void setStatus(String id) {
        this.Status = id;
    }
    
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }
    public int getQuantityDistributor() {
        return QuantityDistributor;
    }

    public void setQuantityDistributor(int quantityDistributor) {
        this.QuantityDistributor = quantityDistributor;
    }
}
