package com.messas.epcladmin__11;

public class Bonus_Model {
    String counter,daily_income,package_,package_name,package_price,email,uuid;

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getDaily_income() {
        return daily_income;
    }

    public void setDaily_income(String daily_income) {
        this.daily_income = daily_income;
    }

    public String getPackage_() {
        return package_;
    }

    public void setPackage_(String package_) {
        this.package_ = package_;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
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

    public Bonus_Model(String counter, String daily_income, String package_,
                       String package_name, String package_price, String email, String uuid) {
        this.counter = counter;
        this.daily_income = daily_income;
        this.package_ = package_;
        this.package_name = package_name;
        this.package_price = package_price;
        this.email = email;
        this.uuid = uuid;
    }

    public Bonus_Model() {
    }
}
