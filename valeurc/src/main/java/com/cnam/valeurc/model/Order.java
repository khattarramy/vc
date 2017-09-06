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
    
    private int id;
    private int userId;
    private int statusId;
    private Date dateInitialized;
    private Date dateFinished;

       
    public Order() {
        
    }

    public Order(int id, int userId, int statusId) {
        this.id = id;
        this.userId = userId;
        this.statusId = statusId;
        this.dateInitialized = new Date();
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getDateInitialized() {
        return dateInitialized;
    }

    public void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }
    
}
