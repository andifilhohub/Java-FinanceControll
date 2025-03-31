package com.financeapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Entity

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255) 
    private String name;  

    @Column(nullable = false, unique = true, length = 255)  
    private String email;  

    @Column(nullable = false)  
    private String password;  

    @Column(nullable = false, updatable = false)  
    private LocalDateTime createdAt; 

    private LocalDateTime lastLogin;  

    @Column(nullable = false) 
    private Boolean active;  

    @Column(length = 50)  
    private String role;  

    private String phoneNumber;  

    private String address;
    private Boolean logged; 

    //getters and setters 
    public Boolean getLogged (){
        return logged;
    }
    public void setLogged(Boolean isLoggedIn) {
        this.logged = isLoggedIn;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    // public List<Expenditures> getExpenditures() {
    //     return expenditures;
    // }

    // public void setExpenditures(List<Expenditures> expenditures) {
    //     this.expenditures = expenditures;
    // }

}