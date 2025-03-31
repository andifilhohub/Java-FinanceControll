package com.financeapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String appName;
    private String title;
    private int notifyId;
    private LocalDateTime timeStamp;

    public Notification() {
        // Caso queira que o timeStamp seja gerado automaticamente no momento da criação
        this.timeStamp = LocalDateTime.now();  // Atribui o valor atual do timeStamp
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public String getAppName(){
        return appName;
    }
    
    public String getTitle(){
        return title;
    }
    public int getNotifyId(){
        return notifyId;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = LocalDateTime.now();;
    }
}
