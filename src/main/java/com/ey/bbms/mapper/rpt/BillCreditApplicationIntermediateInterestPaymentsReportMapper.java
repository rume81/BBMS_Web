package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.BillCreditApplicationIntermediateInterestPaymentsReport;

public class BillCreditApplicationIntermediateInterestPaymentsReportMapper extends BaseMapper implements RowMapper<BillCreditApplicationIntermediateInterestPaymentsReport> {
    @Override
    public BillCreditApplicationIntermediateInterestPaymentsReport mapRow(ResultSet rs, int rowNum)
	    throws SQLException {
	BillCreditApplicationIntermediateInterestPaymentsReport capr = new BillCreditApplicationIntermediateInterestPaymentsReport();
	
	if (findColumnNames(rs, "department_code")) {
	    capr.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "name")) {
	    capr.setName(rs.getString("name"));
	}
	
	if (findColumnNames(rs, "financial_institutions")) {
	    capr.setFinancial_institutions(rs.getString("financial_institutions"));
	}
	
	if (findColumnNames(rs, "bank_code")) {
	    capr.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    capr.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	if (findColumnNames(rs, "bankname")) {
	    capr.setBankname(rs.getString("bankname"));
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
	
	if (findColumnNames(rs, "drawer_date")) {
	    capr.setDrawer_date(rs.getString("drawer_date"));
	}
	
	if (findColumnNames(rs, "maturity_date")) {
	    capr.setMaturity_date(rs.getString("maturity_date"));
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
	
	if (findColumnNames(rs, "expr1")) {
	    capr.setExpr1(rs.getDouble("expr1"));
	}
	
	if (findColumnNames(rs, "expr2")) {
	    capr.setExpr2(rs.getString("expr2"));
	}
	
	if (findColumnNames(rs, "expr3")) {
	    capr.setExpr3(rs.getInt("expr3"));
	}
	
	return capr;
    }
    
}
