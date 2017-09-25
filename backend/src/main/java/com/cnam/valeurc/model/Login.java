/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc.model;
import java.util.UUID;

/**
 *
 * @author riidaali
 */
public class Login {
    private String Username;
    private String Password;

       
    public Login() {
        
    }
    public Login(String username,String password) {
        
        this.Username=username;
        this.Password=password;
        
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
