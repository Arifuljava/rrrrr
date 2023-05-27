package com.messas.epcladmin__11;

public class Lottery_Model {
    String username,useremail,lottery_name,lottery_price,LotteryPrize,time;

    public Lottery_Model() {
    }

    public Lottery_Model(String username,
                         String useremail, String lottery_name,
                         String lottery_price, String lotteryPrize, String time) {
        this.username = username;
        this.useremail = useremail;
        this.lottery_name = lottery_name;
        this.lottery_price = lottery_price;
        LotteryPrize = lotteryPrize;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getLottery_name() {
        return lottery_name;
    }

    public void setLottery_name(String lottery_name) {
        this.lottery_name = lottery_name;
    }

    public String getLottery_price() {
        return lottery_price;
    }

    public void setLottery_price(String lottery_price) {
        this.lottery_price = lottery_price;
    }

    public String getLotteryPrize() {
        return LotteryPrize;
    }

    public void setLotteryPrize(String lotteryPrize) {
        LotteryPrize = lotteryPrize;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
