package com.messas.epcladmin__11;

public class Sublime1 {
    String uuid,name,ammount,date;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Sublime1(String uuid, String name, String ammount, String date) {
        this.uuid = uuid;
        this.name = name;
        this.ammount = ammount;
        this.date = date;
    }

    public Sublime1() {
    }
}
