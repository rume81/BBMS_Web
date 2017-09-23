package com.ey.bbms.model.rpt;

public class JournalSearchResult extends TransferSlip {
    int row_number; 
    String debit_item_code;
    String debit_item_name;
    String debit_details_code;
    String debit_details_name;
    String credit_course_code;
    String credit_course_name;
    String credit_specific_code;
    String credit_specific_name;
    double debit_amount;
    double credit_amount;
    double balance_amount;
    String abstract_code;
    int processing_time;
    String debit_consumption_tax_classification;
    String credit_consumption_tax_classification;
    String customer_code;
    String customer_name;
    String type_size;
    
    public int getRow_number() {
        return row_number;
    }
    public void setRow_number(int row_number) {
        this.row_number = row_number;
    }
    public String getDebit_item_code() {
        return debit_item_code;
    }
    public void setDebit_item_code(String debit_item_code) {
        this.debit_item_code = debit_item_code;
    }
    public String getDebit_item_name() {
        return debit_item_name;
    }
    public void setDebit_item_name(String debit_item_name) {
        this.debit_item_name = debit_item_name;
    }
    public String getDebit_details_code() {
        return debit_details_code;
    }
    public void setDebit_details_code(String debit_details_code) {
        this.debit_details_code = debit_details_code;
    }
    public String getDebit_details_name() {
        return debit_details_name;
    }
    public void setDebit_details_name(String debit_details_name) {
        this.debit_details_name = debit_details_name;
    }
    public String getCredit_course_code() {
        return credit_course_code;
    }
    public void setCredit_course_code(String credit_course_code) {
        this.credit_course_code = credit_course_code;
    }
    public String getCredit_course_name() {
        return credit_course_name;
    }
    public void setCredit_course_name(String credit_course_name) {
        this.credit_course_name = credit_course_name;
    }
    public String getCredit_specific_code() {
        return credit_specific_code;
    }
    public void setCredit_specific_code(String credit_specific_code) {
        this.credit_specific_code = credit_specific_code;
    }
    public String getCredit_specific_name() {
        return credit_specific_name;
    }
    public void setCredit_specific_name(String credit_specific_name) {
        this.credit_specific_name = credit_specific_name;
    }
    public double getDebit_amount() {
        return debit_amount;
    }
    public void setDebit_amount(double debit_amount) {
        this.debit_amount = debit_amount;
    }
    public double getCredit_amount() {
        return credit_amount;
    }
    public void setCredit_amount(double credit_amount) {
        this.credit_amount = credit_amount;
    }
    public double getBalance_amount() {
        return balance_amount;
    }
    public void setBalance_amount(double balance_amount) {
        this.balance_amount = balance_amount;
    }
    public String getAbstract_code() {
        return abstract_code;
    }
    public void setAbstract_code(String abstract_code) {
        this.abstract_code = abstract_code;
    }
    public int getProcessing_time() {
        return processing_time;
    }
    public void setProcessing_time(int processing_time) {
        this.processing_time = processing_time;
    }
    public String getDebit_consumption_tax_classification() {
        return debit_consumption_tax_classification;
    }
    public void setDebit_consumption_tax_classification(String debit_consumption_tax_classification) {
        this.debit_consumption_tax_classification = debit_consumption_tax_classification;
    }
    public String getCredit_consumption_tax_classification() {
        return credit_consumption_tax_classification;
    }
    public void setCredit_consumption_tax_classification(String credit_consumption_tax_classification) {
        this.credit_consumption_tax_classification = credit_consumption_tax_classification;
    }
    public String getCustomer_code() {
        return customer_code;
    }
    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getType_size() {
        return type_size;
    }
    public void setType_size(String type_size) {
        this.type_size = type_size;
    }
}
