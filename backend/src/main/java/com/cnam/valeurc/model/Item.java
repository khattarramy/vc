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
public class Item {
    private String ItemId;
    private String Name;
    private String Description;
    private String ModelNumber;
    private int DistributorId;
    private int ManufacturerId;

       
    public Item() {
        this.ItemId = UUID.randomUUID().toString();
    }
    public Item(String Name, String Description,String ModelNumber, int DistributorId, int ManufacturerId) {
        
        this.ItemId = UUID.randomUUID().toString();
        this.Name = Name;
        this.Description = Description;
        this.ModelNumber = ModelNumber;
        this.DistributorId = DistributorId;
        this.ManufacturerId = ManufacturerId;
        
    }
    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String id) {
        this.ItemId = id;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setName(String name) {
        this.Name = name;
    }
    
    public String getDescription() {
        return Description;
    }
    
    public void setDescription(String description) {
        this.Description = description;
    }
    
    public String getModelNumber() {
        return ModelNumber;
    }
    
    public void setModelNumber(String modelNumber) {
        this.ModelNumber = modelNumber;
    }

    public int getDistributorId() {
        return DistributorId;
    }
    
    public void setDistributorId(int id) {
        this.DistributorId = id;
    }
    
    public int getManufacturerId() {
        return ManufacturerId;
    }
    
    public void setManufacturerId(int id) {
        this.ManufacturerId = id;
    }
    
}
