package com.sample.user_service.entity;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
  
    String username;
  
    String email;
  
    String password;

    boolean is_admin = false;
    
    Date last_login;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date created_at;

    public User(){
        created_at = new Date(System.currentTimeMillis());
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        created_at = new Date(System.currentTimeMillis());
    }

    // GETTER SETTERS
    public UUID getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String em){
        this.email = em;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    

    public boolean getAdmin(){
        return is_admin;
    }
    public void setAdmin(boolean ad){
        this.is_admin = ad;
    }

    public Date getLastLogin(){
        return last_login;
    }
    public void setLastLogin(Date date){
        this.last_login = date;
    }

    public Date getCreated(){
        return created_at;
    }
}
