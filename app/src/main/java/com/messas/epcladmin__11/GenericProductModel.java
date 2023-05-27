package com.messas.epcladmin__11;

public class GenericProductModel {
    String date,email,payment,time,type,username;

    public GenericProductModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GenericProductModel(String date, String email, String payment, String time, String type, String username) {
        this.date = date;
        this.email = email;
        this.payment = payment;
        this.time = time;
        this.type = type;
        this.username = username;
    }
}
