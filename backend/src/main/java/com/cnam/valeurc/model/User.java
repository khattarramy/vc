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
public class User {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String TypeId;
       
    public User() {
        
    }
    public User(String name,String address,String phone,String email,String password,String type_id) {
        this.TypeId = type_id;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.email=email;
        this.password=password;
        
    }
    public String getTypeId() {
        return TypeId;
    }
    
    public void setTypeId(String id) {
        this.TypeId = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
