package com.ey.bbms.model.main;

public class HProrationDataInterestRateOrder extends HObject {
    double bid_interest_rate;
    String bank_code;
    int bid_number;
    double bid_amount_of_money;
    
    public double getBid_interest_rate() {
        return bid_interest_rate;
    }
    public void setBid_interest_rate(double bid_interest_rate) {
        this.bid_interest_rate = bid_interest_rate;
    }
    public String getBank_code() {
        return bank_code;
    }
    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }
    public int getBid_number() {
        return bid_number;
    }
    public void setBid_number(int bid_number) {
        this.bid_number = bid_number;
    }
    public double getBid_amount_of_money() {
        return bid_amount_of_money;
    }
    public void setBid_amount_of_money(double bid_amount_of_money) {
        this.bid_amount_of_money = bid_amount_of_money;
    }
}
