package com.ey.bbms.model.main;

public class HBidDataByBankTotals {
    String department_code;
    String bank_code;
    String financial_institution_name;
    double sum_of_bid_amount_money;
    
    public String getDepartment_code() {
        return department_code;
    }
    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }
    public String getBank_code() {
        return bank_code;
    }
    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }
    public String getFinancial_institution_name() {
        return financial_institution_name;
    }
    public void setFinancial_institution_name(String financial_institution_name) {
        this.financial_institution_name = financial_institution_name;
    }
    public double getSum_of_bid_amount_money() {
        return sum_of_bid_amount_money;
    }
    public void setSum_of_bid_amount_money(double sum_of_bid_amount_money) {
        this.sum_of_bid_amount_money = sum_of_bid_amount_money;
    }   
}
