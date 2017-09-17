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
public class User {
    private int userId;
    private int typeId;
    private String name;
    private String address;
    private int phone;
    private String email;
    private String username;
    private String password;

       
    public User() {
        
    }
    public User(int userId, int typeId) {
        this.userId = userId;
        this.typeId = typeId;
        
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public int getTypeId() {
        return typeId;
    }
    
    public void setTypeId(int id) {
        this.typeId = id;
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
    
    public int getPhone() {
        return phone;
    }
    
    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUserName() {
        return username;
    }
    
    public void setUserName(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
