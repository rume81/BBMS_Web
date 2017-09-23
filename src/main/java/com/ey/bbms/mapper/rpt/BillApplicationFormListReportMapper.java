package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.BillApplicationFormListReport;

public class BillApplicationFormListReportMapper extends BaseMapper implements RowMapper<BillApplicationFormListReport> {
    @Override
    public BillApplicationFormListReport mapRow(ResultSet rs, int rowNum) throws SQLException {
	BillApplicationFormListReport capr = new BillApplicationFormListReport();
	
	if (findColumnNames(rs, "department_code")) {
	    capr.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "department_name")) {
	    capr.setDepartment_name(rs.getString("department_name"));
	}
	
	if (findColumnNames(rs, "bank_code")) {
	    capr.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    capr.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	if (findColumnNames(rs, "nc_name")) {
	    capr.setNc_name(rs.getString("nc_name"));
	}
	
	if (findColumnNames(rs, "successful_bid_interest_rate")) {
	    capr.setSuccessful_bid_interest_rate(rs.getDouble("successful_bid_interest_rate"));
	}
	
	if (findColumnNames(rs, "successful_bid_price")) {
	    capr.setSuccessful_bid_price(rs.getDouble("successful_bid_price"));
	}
	
	if (findColumnNames(rs, "bill_number")) {
	    capr.setBill_number(rs.getString("bill_number"));
	}
	
	if (findColumnNames(rs, "drawer_yy")) {
	    capr.setDrawer_yy(rs.getInt("drawer_yy"));
	}
	
	if (findColumnNames(rs, "drawer_mm")) {
	    capr.setDrawer_mm(rs.getInt("drawer_mm"));
	}
	
	if (findColumnNames(rs, "drawer_dd")) {
	    capr.setDrawer_dd(rs.getInt("drawer_dd"));
	}
	
	if (findColumnNames(rs, "maturity_yy")) {
	    capr.setMaturity_yy(rs.getInt("maturity_yy"));
	}
	
	if (findColumnNames(rs, "maturity_mm")) {
	    capr.setMaturity_mm(rs.getInt("maturity_mm"));
	}
	
	if (findColumnNames(rs, "maturity_dd")) {
	    capr.setMaturity_dd(rs.getInt("maturity_dd"));
	}
	
	if (findColumnNames(rs, "number_of_days")) {
	    capr.setNumber_of_days(rs.getLong("number_of_days"));
	}
	
	if (findColumnNames(rs, "interest_rate")) {
	    capr.setInterest_rate(rs.getDouble("interest_rate"));
	}
	
	if (findColumnNames(rs, "amount_of_money")) {
	    capr.setAmount_of_money(rs.getDouble("amount_of_money"));
	}
	
	if (findColumnNames(rs, "interest_amount")) {
	    capr.setInterest_amount(rs.getDouble("interest_amount"));
	}
	
	if (findColumnNames(rs, "remarks")) {
	    capr.setRemarks(rs.getString("remarks"));
	}
	
	if (findColumnNames(rs, "account_number")) {
	    capr.setAccount_number(rs.getString("account_number"));
	}
	
	if (findColumnNames(rs, "formated_financial_institutions")) {
	    capr.setFormated_financial_institutions(rs.getString("formated_financial_institutions"));
	}
	
	if (findColumnNames(rs, "drawer_date")) {
	    capr.setDrawer_date(rs.getString("drawer_date"));
	}
	
	if (findColumnNames(rs, "maturity_date")) {
	    capr.setMaturity_date(rs.getString("maturity_date"));
	}
	
	if (findColumnNames(rs, "minister_of_finance")) {
	    capr.setMinister_of_finance(rs.getString("minister_of_finance"));
	}
	
	if (findColumnNames(rs, "street_address")) {
	    capr.setStreet_address(rs.getString("street_address"));
	}
	
	if (findColumnNames(rs, "horei01")) {
	    capr.setHorei01(rs.getString("horei01"));
	}
	
	if (findColumnNames(rs, "horei02")) {
	    capr.setHorei02(rs.getString("horei02"));
	}
	
	if (findColumnNames(rs, "chairman_name")) {
	    capr.setChairman_name(rs.getString("chairman_name"));
	}
	
	if (findColumnNames(rs, "chairman_name_2")) {
	    capr.setChairman_name_2(rs.getString("chairman_name_2"));
	}
	
	return capr;
    }

}
