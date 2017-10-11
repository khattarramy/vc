/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.model;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KHATT
 */
@XmlRootElement
public class Order {
    
    private int _id;
    private int UserId;
    private String Status;
    private Date DateInitialized;
    private Date DateFinished;

       
    public Order() {
        
    }

    public Order(int id, int userId, String status) {
        this._id = id;
        this.UserId = userId;
        this.Status = status;
        this.DateInitialized = new Date();
    }

    
    public int getOrderId() {
        return _id;
    }

    public void setOrderId(int id) {
        this._id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String statusId) {
        this.Status = statusId;
    }

    public Date getDateInitialized() {
        return DateInitialized;
    }

    public void setDateInitialized(Date dateInitialized) {
        this.DateInitialized = dateInitialized;
    }

    public Date getDateFinished() {
        return DateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.DateFinished = dateFinished;
    }
    
}
