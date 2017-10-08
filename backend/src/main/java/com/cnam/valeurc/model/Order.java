/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.model;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 *
 * @author KHATT
 */
@XmlRootElement
public class Order {
    
    private UUID OrderId;
    private String UserId;
    private String Status;
    private Date DateInitialized;
    private Date DateFinished;

       
    public Order() {
        
    }

    public Order(UUID id, String userId, String status) {
        this.OrderId = id;
        this.UserId = userId;
        this.Status = status;
        this.DateInitialized = new Date();
    }

    
    public UUID getOrderId() {
        return OrderId;
    }

    public void setOrderId(UUID id) {
        this.OrderId = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
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
