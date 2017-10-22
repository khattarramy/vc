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
    private int _id;
    private String Name;
    private String Description;
    private String ModelNumber;
    private int DistributorId;
    private int ManufacturerId;
    private User Distributor;
    private User Manufacturer;

       
    public Item() {

    }
    public Item(int ItemId,String Name, String Description,String ModelNumber, 
            int DistributorId, int ManufacturerId, User distributor, User manufacturer) {
        
        this._id=ItemId;
        this.Name = Name;
        this.Description = Description;
        this.ModelNumber = ModelNumber;
        this.DistributorId = DistributorId;
        this.ManufacturerId = ManufacturerId;
        this.Distributor = distributor;
        this.Manufacturer = manufacturer;
    }
    public int getItemId() {
        return _id;
    }

    public void setItemId(int id) {
        this._id = id;
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
    
    
    public User getDistributor() {
        return Distributor;
    }
    
    public void setDistributor(User distributor) {
        this.Distributor = distributor;
    }
    
    public User getManufacturer() {
        return Manufacturer;
    }
    
    public void setManufacturer(User manufacturer) {
        this.Manufacturer = manufacturer;
    }
    
}
