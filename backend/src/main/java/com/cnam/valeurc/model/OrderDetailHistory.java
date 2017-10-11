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
    private int _id;
    private int OrderDetailId;
    private Date DateTime;
    private String Status;
    private int UserId;
    
    public OrderDetailHistory() {
        
    }
    public OrderDetailHistory(int orderDetailHistoryId, int orderDetailId, String status,int userId) {
        this._id = orderDetailHistoryId;
        this.OrderDetailId = orderDetailId;
        this.Status = status;
        this.UserId = userId;
        
    }
    public int getOrderDetailHistoryId() {
        return _id;
    }

    public void setOrderDetailHistoryId(int id) {
        this._id = id;
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
    
    public String getStatus() {
        return Status;
    }

    public void setStatus(String id) {
        this.Status = id;
    }
    
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int id) {
        this.UserId = id;
    }
}
