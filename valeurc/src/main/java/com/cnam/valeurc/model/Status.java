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
public class Status {
    private int statusId;
    private String description;
    
    public Status() {
        
    }
    public Status(int statusId, String description) {
        this.statusId = statusId;
        this.description = description;
        
    }
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int id) {
        this.statusId = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
}
}
