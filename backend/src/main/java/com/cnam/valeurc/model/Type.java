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
    
    private UUID TypeId;
    private String Description;
    
    public Type() {
        
    }
    public Type(UUID typeId, String description) {
        this.TypeId = typeId;
        this.Description = description;
        
    }
    public UUID getTypeId() {
        return TypeId;
    }

    public void setTypeId(UUID id) {
        this.TypeId = id;
    }
    
    public String getDescription() {
        return Description;
    }
    
    public void setDescription(String description) {
        this.Description = description;
    }
}
