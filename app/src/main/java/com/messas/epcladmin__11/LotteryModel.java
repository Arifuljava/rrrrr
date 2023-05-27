package com.messas.epcladmin__11;

public class LotteryModel {
    String Lottery_name;
    String lottery_Price;
    String lottery_prize,lottery_baki_ase , time;

    public LotteryModel(String lottery_name, String lottery_Price, String lottery_prize, String lottery_baki_ase, String time) {
        Lottery_name = lottery_name;
        this.lottery_Price = lottery_Price;
        this.lottery_prize = lottery_prize;
        this.lottery_baki_ase = lottery_baki_ase;
        this.time = time;
    }

    public String getLottery_name() {
        return Lottery_name;
    }

    public void setLottery_name(String lottery_name) {
        Lottery_name = lottery_name;
    }

    public String getLottery_Price() {
        return lottery_Price;
    }

    public void setLottery_Price(String lottery_Price) {
        this.lottery_Price = lottery_Price;
    }

    public String getLottery_prize() {
        return lottery_prize;
    }

    public void setLottery_prize(String lottery_prize) {
        this.lottery_prize = lottery_prize;
    }

    public String getLottery_baki_ase() {
        return lottery_baki_ase;
    }

    public void setLottery_baki_ase(String lottery_baki_ase) {
        this.lottery_baki_ase = lottery_baki_ase;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LotteryModel() {
    }
}
