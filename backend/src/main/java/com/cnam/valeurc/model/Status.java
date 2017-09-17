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
public class Status {
    private UUID StatusId;
    private String Description;
    
    public Status() {
        
    }
    public Status(UUID statusId, String description) {
        this.StatusId = statusId;
        this.Description = description;
        
    }
    public UUID getStatusId() {
        return StatusId;
    }

    public void setStatusId(UUID id) {
        this.StatusId = id;
    }
    
    public String getDescription() {
        return Description;
    }
    
    public void setDescription(String description) {
        this.Description = description;
}
}
