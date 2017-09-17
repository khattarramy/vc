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
public class Type {
    
    private UUID typeId;
    private String description;
    
    public Type() {
        
    }
    public Type(UUID typeId, String description) {
        this.typeId = typeId;
        this.description = description;
        
    }
    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID id) {
        this.typeId = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
