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
public class Item {
    private int ItemId;
    private String Name;
    private String Description;
    private String ModelNumber;
    private int DistributorId;
    private int ManufaturerId;

       
    public Item() {
        
    }
    public Item(int itemId, int distributorId, int manufaturerId) {
        this.ItemId = itemId;
        this.DistributorId = distributorId;
        this.ManufaturerId = manufaturerId;
        
    }
    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int id) {
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
        return ManufaturerId;
    }
    
    public void setManufacturerId(int id) {
        this.ManufaturerId = id;
    }
    
}
