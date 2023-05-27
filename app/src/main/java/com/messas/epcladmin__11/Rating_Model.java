package com.messas.epcladmin__11;

public class Rating_Model {
    String email,uuid,username,rating,total_refer,date;

    public Rating_Model(String email,
                        String uuid, String username, String rating, String total_refer, String date) {
        this.email = email;
        this.uuid = uuid;
        this.username = username;
        this.rating = rating;
        this.total_refer = total_refer;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTotal_refer() {
        return total_refer;
    }

    public void setTotal_refer(String total_refer) {
        this.total_refer = total_refer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Rating_Model() {
    }
}
