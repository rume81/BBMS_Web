package com.ey.bbms.model.main;

public class HNameConversion extends HObject {
	
	private String bank_code;
	private String abbreviation;
	private String financial_institution_name;
	private String business_category;
	private int DelF;
	
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getFinancial_institution_name() {
		return financial_institution_name;
	}
	public void setFinancial_institution_name(String financial_institution_name) {
		this.financial_institution_name = financial_institution_name;
	}
	public String getBusiness_category() {
		return business_category;
	}
	public void setBusiness_category(String business_category) {
		this.business_category = business_category;
	}
	public int getDelF() {
		return DelF;
	}
	public void setDelF(int delF) {
		DelF = delF;
	}
	
	
}
