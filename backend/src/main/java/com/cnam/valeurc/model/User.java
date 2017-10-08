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
    private int _id;
    private String Type;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;
    private String Password;

       
    public User() {
        
    }
    public User(int userId, String type,String name,String address,String phone,String email,String password) {
        this._id = userId;
        this.Type = type;
        this.Name=name;
        this.Address=address;
        this.Phone=phone;
        this.Email=email;
        this.Password=password;
        
    }
    public int getUserId() {
        return _id;
    }

    public void setUserId(int id) {
        this._id = id;
    }

    public String getType() {
        return Type;
    }
    
    public void setType(String id) {
        this.Type = id;
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
    
    public String getPhone() {
        return Phone;
    }
    
    public void setPhone(String phone) {
        this.Phone = phone;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public void setEmail(String email) {
        this.Email = email;
    }
    
    public String getPassword() {
        return Password;
    }
    
    public void setPassword(String password) {
        this.Password = password;
    }
    
}
