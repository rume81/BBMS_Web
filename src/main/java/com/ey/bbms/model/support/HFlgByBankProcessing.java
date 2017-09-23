package com.ey.bbms.model.support;

import com.ey.bbms.model.main.HObject;

public class HFlgByBankProcessing extends HObject {
    String bank_code;
    int bid_number;
    String department_code;
    int financial_institutions_by_flag;
    double successful_bid_price;
    
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
    public String getDepartment_code() {
        return department_code;
    }
    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }
    public int getFinancial_institutions_by_flag() {
        return financial_institutions_by_flag;
    }
    public void setFinancial_institutions_by_flag(int financial_institutions_by_flag) {
        this.financial_institutions_by_flag = financial_institutions_by_flag;
    }
    public double getSuccessful_bid_price() {
        return successful_bid_price;
    }
    public void setSuccessful_bid_price(double successful_bid_price) {
        this.successful_bid_price = successful_bid_price;
    }
    
    
}
