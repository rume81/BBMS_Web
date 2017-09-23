package com.ey.bbms.model.main;

public class HBidDataRateOrder extends HObject{
	private String department_code;
	private String business_category;
	private String bank_code;
	private String financial_institution_name;
	private int bid_number;
	private double bid_interest_rate;
	private double bid_amount_money;
	private double total_amount_money;
	public String getDepartment_code() {
	    return department_code;
	}
	public void setDepartment_code(String department_code) {
	    this.department_code = department_code;
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
	public double getTotal_amount_money() {
	    return total_amount_money;
	}
	public void setTotal_amount_money(double total_amount_money) {
	    this.total_amount_money = total_amount_money;
	}
}

