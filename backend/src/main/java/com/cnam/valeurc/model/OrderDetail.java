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
    private String RetailerId;
    private String DistributorId;
    private int Quantity;
    private int QuantityDistributor;
       
    public OrderDetail() {
        
    }
    public OrderDetail(UUID orderDetailId, String orderId, String itemId, String statusId, String userId) {
        this.OrderDetailId = orderDetailId;
        this.ItemId = itemId;
        this.ItemId = itemId;
        this.StatusId = statusId;
        this.RetailerId = userId;
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
    
    public String getDistributorId() {
        return DistributorId;
    }

    public void setDistributorId(String id) {
        this.DistributorId = id;
    }
    public String getRetailerId() {
        return RetailerId;
    }

    public void setRetailerId(String id) {
        this.RetailerId = id;
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
