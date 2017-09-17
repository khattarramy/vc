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
    private int itemId;
    private String name;
    private String description;
    private String modelNumber;
    private int distributorId;
    private int manufaturerId;

       
    public Item() {
        
    }
    public Item(int itemId, int distributorId, int manufaturerId) {
        this.itemId = itemId;
        this.distributorId = distributorId;
        this.manufaturerId = manufaturerId;
        
    }
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int id) {
        this.itemId = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getModelNumber() {
        return modelNumber;
    }
    
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public int getDistributorId() {
        return distributorId;
    }
    
    public void setDistributorId(int id) {
        this.distributorId = id;
    }
    
    public int getManufacturerId() {
        return manufaturerId;
    }
    
    public void setManufacturerId(int id) {
        this.manufaturerId = id;
    }
    
}
