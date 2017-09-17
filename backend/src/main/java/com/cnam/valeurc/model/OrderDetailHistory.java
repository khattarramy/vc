/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.model;

import java.util.Date;

/**
 *
 * @author George Harik
 */
public class OrderDetailHistory {
    private int orderDetailHistoryId;
    private int orderDetailId;
    private Date dateTime;
    private int statusId;
    private int userId;
    
    public OrderDetailHistory() {
        
    }
    public OrderDetailHistory(int orderDetailHistoryId, int orderDetailId, int statusId,int userId) {
        this.orderDetailHistoryId = orderDetailHistoryId;
        this.orderDetailId = orderDetailId;
        this.statusId = statusId;
        this.userId = userId;
        
    }
    public int getOrderDetailHistoryId() {
        return orderDetailHistoryId;
    }

    public void setOrderDetailHistoryId(int id) {
        this.orderDetailHistoryId = id;
    }
    
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int id) {
        this.orderDetailId = id;
    }
    
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date date) {
        this.dateTime = date;
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