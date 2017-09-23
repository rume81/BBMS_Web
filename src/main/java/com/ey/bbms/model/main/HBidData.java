package com.ey.bbms.model.main;

public class HBidData extends HObject {
    private String department_code;
    private String business_category;
    private String bank_code;
    private String financial_institution_name;
    private Integer bid_number;
    private Double bid_interest_rate;
    private Double bid_amount_money;
    private String auction_date;
    private String flag1;
    private String flag2;
    private String flag3;

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

    public Integer getBid_number() {
	return bid_number;
    }

    public void setBid_number(Integer bid_number) {
	this.bid_number = bid_number;
    }

    public Double getBid_interest_rate() {
	return bid_interest_rate;
    }

    public void setBid_interest_rate(Double bid_interest_rate) {
	this.bid_interest_rate = bid_interest_rate;
    }

    public Double getBid_amount_money() {
	return bid_amount_money;
    }

    public void setBid_amount_money(Double bid_amount_money) {
	this.bid_amount_money = bid_amount_money;
    }

    public String getAuction_date() {
	return auction_date;
    }

    public void setAuction_date(String auction_date) {
	this.auction_date = auction_date;
    }

    public String getFlag1() {
	return flag1;
    }

    public void setFlag1(String flag1) {
	this.flag1 = flag1;
    }

    public String getFlag2() {
	return flag2;
    }

    public void setFlag2(String flag2) {
	this.flag2 = flag2;
    }

    public String getFlag3() {
	return flag3;
    }

    public void setFlag3(String flag3) {
	this.flag3 = flag3;
    }
}
