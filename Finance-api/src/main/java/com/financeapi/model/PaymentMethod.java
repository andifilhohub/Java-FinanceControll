package com.financeapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(name = "name_method", nullable = false, unique = true)
    private String name;


    public PaymentMethod() {
    }
    public PaymentMethod(String name) {
        this.name = name;
    }

    //Getters e Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "PaymentMethod{id=" + id + ", name='" + name + "'}";
    }

}