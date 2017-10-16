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
public class OrderDetailDto {
    private int _id;
    private int OrderId;
    private String ItemName;
    private String Status;
    private int Quantity;
    private int QuantityDistributor;
       
    public OrderDetailDto() {
        
    }
    public OrderDetailDto(int orderDetailId, int orderId, String itemName, String status,int quantity, int quantityDistributor) {
        this._id = orderDetailId;
        this.OrderId = orderId;
        this.ItemName = itemName;
        this.Status = status;
        this.Quantity=quantity;
        this.QuantityDistributor=quantityDistributor;
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
    
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        this.ItemName = itemName;
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
