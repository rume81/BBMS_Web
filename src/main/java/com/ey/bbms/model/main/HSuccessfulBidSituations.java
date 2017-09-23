package com.ey.bbms.model.main;

public class HSuccessfulBidSituations extends HObject{
    private long sno;
    private String department_code;
    private String department_name;
    private double bid_interest_rate;
    private double bid_interest_rate2;
    private double interest_rate_by_bid_amount_of_money;
    private double average_rate;
    private double total_amount_of_money;
    private double cumulative_interest;
    private String business_category;
    private String bank_code;
    private String financial_institution_name;
    private int bid_number;
    private double bid_amount_of_money;

    public long getSno() {
	return sno;
    }

    public void setSno(long sno) {
	this.sno = sno;
    }

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

    public double getBid_interest_rate() {
	return bid_interest_rate;
    }

    public void setBid_interest_rate(double bid_interest_rate) {
	this.bid_interest_rate = bid_interest_rate;
    }

    public double getBid_interest_rate2() {
	return bid_interest_rate2;
    }

    public void setBid_interest_rate2(double bid_interest_rate2) {
	this.bid_interest_rate2 = bid_interest_rate2;
    }

    public double getInterest_rate_by_bid_amount_of_money() {
	return interest_rate_by_bid_amount_of_money;
    }

    public void setInterest_rate_by_bid_amount_of_money(double interest_rate_by_bid_amount_of_money) {
	this.interest_rate_by_bid_amount_of_money = interest_rate_by_bid_amount_of_money;
    }

    public double getAverage_rate() {
	return average_rate;
    }

    public void setAverage_rate(double average_rate) {
	this.average_rate = average_rate;
    }

    public double getTotal_amount_of_money() {
	return total_amount_of_money;
    }

    public void setTotal_amount_of_money(double total_amount_of_money) {
	this.total_amount_of_money = total_amount_of_money;
    }

    public double getCumulative_interest() {
	return cumulative_interest;
    }

    public void setCumulative_interest(double cumulative_interest) {
	this.cumulative_interest = cumulative_interest;
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
