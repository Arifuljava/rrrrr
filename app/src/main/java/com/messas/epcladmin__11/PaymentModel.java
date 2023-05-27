package com.messas.epcladmin__11;

public class PaymentModel {
    String name,time,dateandtime,taxid,convertname,
            ammount,fee,total,status,uuid,email,methode,agents,username,number,date;


    public PaymentModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public String getConvertname() {
        return convertname;
    }

    public void setConvertname(String convertname) {
        this.convertname = convertname;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public String getAgents() {
        return agents;
    }

    public void setAgents(String agents) {
        this.agents = agents;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PaymentModel(String name,
                        String time, String dateandtime,
                        String taxid, String convertname,
                        String ammount, String fee, String total, String status, String uuid,
                        String email, String methode, String agents, String username, String number, String date) {
        this.name = name;
        this.time = time;
        this.dateandtime = dateandtime;
        this.taxid = taxid;
        this.convertname = convertname;
        this.ammount = ammount;
        this.fee = fee;
        this.total = total;
        this.status = status;
        this.uuid = uuid;
        this.email = email;
        this.methode = methode;
        this.agents = agents;
        this.username = username;
        this.number = number;
        this.date = date;
    }
}
