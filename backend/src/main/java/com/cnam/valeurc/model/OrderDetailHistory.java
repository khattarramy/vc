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
    private int OrderDetailId;
    private Date DateTime;
    private int StatusId;
    private int UserId;
    
    public OrderDetailHistory() {
        
    }
    public OrderDetailHistory(UUID orderDetailHistoryId, int orderDetailId, int statusId,int userId) {
        this.OrderDetailHistoryId = orderDetailHistoryId;
        this.OrderDetailId = orderDetailId;
        this.StatusId = statusId;
        this.UserId = userId;
        
    }
    public UUID getOrderDetailHistoryId() {
        return OrderDetailHistoryId;
    }

    public void setOrderDetailHistoryId(UUID id) {
        this.OrderDetailHistoryId = id;
    }
    
    public int getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(int id) {
        this.OrderDetailId = id;
    }
    
    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date date) {
        this.DateTime = date;
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
