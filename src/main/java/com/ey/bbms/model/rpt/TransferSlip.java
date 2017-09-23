package com.ey.bbms.model.rpt;

public class TransferSlip {
    String department_code;
    String department_name;
    int journal_number;
    int slip_number;
    int recorded_year;
    int recorded_month;
    int recorded_date;
    String abstract_name;
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
    public int getJournal_number() {
        return journal_number;
    }
    public void setJournal_number(int journal_number) {
        this.journal_number = journal_number;
    }
    public int getSlip_number() {
        return slip_number;
    }
    public void setSlip_number(int slip_number) {
        this.slip_number = slip_number;
    }
    public int getRecorded_year() {
        return recorded_year;
    }
    public void setRecorded_year(int recorded_year) {
        this.recorded_year = recorded_year;
    }
    public int getRecorded_month() {
        return recorded_month;
    }
    public void setRecorded_month(int recorded_month) {
        this.recorded_month = recorded_month;
    }
    public int getRecorded_date() {
        return recorded_date;
    }
    public void setRecorded_date(int recorded_date) {
        this.recorded_date = recorded_date;
    }
    public String getAbstract_name() {
        return abstract_name;
    }
    public void setAbstract_name(String abstract_name) {
        this.abstract_name = abstract_name;
    }
}
