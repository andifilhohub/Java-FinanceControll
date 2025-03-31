package com.financeapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne; 
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import javax.persistence.Column;

@Entity
public class Expenditures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    private User user;

    private BigDecimal amount; 
    private String companyName;
    private String category;
    private LocalDateTime date;
    private String description;

    @ManyToOne 
    private PaymentMethod paymentMethod;

    private int installmentsCount;
    @Column(name = "time_stamp")  
    private LocalDateTime timeStamp;

    public String toString() {
        return "Expenditures{" +
                "amount=" + amount +
                ", companyName='" + companyName + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", installmentsCount=" + installmentsCount +
                ", date=" + date +
                ", paymentMethod=" + paymentMethod +
                '}';
    }

    // Getters and Setters

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal newAmount) {
        this.amount = newAmount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String newCompanyName) {
        this.companyName = newCompanyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime newDate) {
        this.date = newDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod newPaymentMethod) {
        this.paymentMethod = newPaymentMethod;
    }

    public int getInstallmentsCount() {
        return installmentsCount;
    }
   

    public void setInstallmentsCount(int newInstallmentsCount) {
        this.installmentsCount = newInstallmentsCount;
    }
}
