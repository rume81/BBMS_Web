package com.ey.bbms.model.support;

import com.ey.bbms.model.main.HObject;

public class HProrationdataprocessing extends HObject {
    double total_amount_of_money;
    String bank_code;
    double sum_of_total_amount;
    
    public double getTotal_amount_of_money() {
        return total_amount_of_money;
    }
    public void setTotal_amount_of_money(double total_amount_of_money) {
        this.total_amount_of_money = total_amount_of_money;
    }
    public String getBank_code() {
        return bank_code;
    }
    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }
    public double getSum_of_total_amount() {
        return sum_of_total_amount;
    }
    public void setSum_of_total_amount(double sum_of_total_amount) {
        this.sum_of_total_amount = sum_of_total_amount;
    }
    
    
}
