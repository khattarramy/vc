/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.model;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author George Harik
 */
public class OrderDetailHistory {
    private UUID OrderDetailHistoryId;
    private String OrderDetailId;
    private Date DateTime;
    private String Status;
    private String UserId;
    
    public OrderDetailHistory() {
        
    }
    public OrderDetailHistory(UUID orderDetailHistoryId, String orderDetailId, String status,String userId) {
        this.OrderDetailHistoryId = orderDetailHistoryId;
        this.OrderDetailId = orderDetailId;
        this.Status = status;
        this.UserId = userId;
        
    }
    public UUID getOrderDetailHistoryId() {
        return OrderDetailHistoryId;
    }

    public void setOrderDetailHistoryId(UUID id) {
        this.OrderDetailHistoryId = id;
    }
    
    public String getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(String id) {
        this.OrderDetailId = id;
    }
    
    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date date) {
        this.DateTime = date;
    }
    
    public String getStatus() {
        return Status;
    }

    public void setStatus(String id) {
        this.Status = id;
    }
    
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String id) {
        this.UserId = id;
    }
}
