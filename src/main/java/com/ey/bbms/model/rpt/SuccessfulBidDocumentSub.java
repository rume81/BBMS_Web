package com.ey.bbms.model.rpt;

public class SuccessfulBidDocumentSub {
    String department_code;
    String department_name; 
    String business_category; 
    String bank_code; 
    String financial_institution_name;
    String bid_number1; 
    double bid_interest_rate;
    double bid_amount_money; 
    double successful_bid_interest_rate;
    double successful_bid_price;
    
    public String getDepartment_code() {
        return department_code;
    }
    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }
    public String getDepartment_name() {
        return department_name;
    }
    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
    public String getBusiness_category() {
        return business_category;
    }
    public void setBusiness_category(String business_category) {
        this.business_category = business_category;
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
    public String getBid_number1() {
        return bid_number1;
    }
    public void setBid_number1(String bid_number1) {
        this.bid_number1 = bid_number1;
    }
    public double getBid_interest_rate() {
        return bid_interest_rate;
    }
    public void setBid_interest_rate(double bid_interest_rate) {
        this.bid_interest_rate = bid_interest_rate;
    }
    public double getBid_amount_money() {
        return bid_amount_money;
    }
    public void setBid_amount_money(double bid_amount_money) {
        this.bid_amount_money = bid_amount_money;
    }
    public double getSuccessful_bid_interest_rate() {
        return successful_bid_interest_rate;
    }
    public void setSuccessful_bid_interest_rate(double successful_bid_interest_rate) {
        this.successful_bid_interest_rate = successful_bid_interest_rate;
    }
    public double getSuccessful_bid_price() {
        return successful_bid_price;
    }
    public void setSuccessful_bid_price(double successful_bid_price) {
        this.successful_bid_price = successful_bid_price;
    }
}
