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
    private UUID UserId;
    private int TypeId;
    private String Name;
    private String Address;
    private int Phone;
    private String Email;
    private String Username;
    private String Password;

       
    public User() {
        
    }
    public User(UUID userId, int typeId) {
        this.UserId = userId;
        this.TypeId = typeId;
        
    }
    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID id) {
        this.UserId = id;
    }

    public int getTypeId() {
        return TypeId;
    }
    
    public void setTypeId(int id) {
        this.TypeId = id;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setName(String name) {
        this.Name = name;
    }
    
    public String getAddress() {
        return Address;
    }
    
    public void setAddress(String address) {
        this.Address = address;
    }
    
    public int getPhone() {
        return Phone;
    }
    
    public void setPhone(int phone) {
        this.Phone = phone;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public void setEmail(String email) {
        this.Email = email;
    }
    
    public String getUserName() {
        return Username;
    }
    
    public void setUserName(String username) {
        this.Username = username;
    }
    
    public String getPassword() {
        return Password;
    }
    
    public void setPassword(String password) {
        this.Password = password;
    }
    
}
