package com.ey.bbms.model.main;

public class HSytemSpecificMaintenance extends HObject {
	private String bank_code;
	private String financial_institution_name;
	private String store;
	private String business_category;
	private String FN;
	private String Updated;
	
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
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getBusiness_category() {
		return business_category;
	}
	public void setBusiness_category(String business_category) {
		this.business_category = business_category;
	}
	public String getFN() {
		return FN;
	}
	public void setFN(String fN) {
		FN = fN;
	}
	public String getUpdated() {
		return Updated;
	}
	public void setUpdated(String updated) {
		Updated = updated;
	}
	
	
	
}
