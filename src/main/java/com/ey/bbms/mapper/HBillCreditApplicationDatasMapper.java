package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HBillCreditApplicationDatas;

public class HBillCreditApplicationDatasMapper extends BaseMapper implements RowMapper<HBillCreditApplicationDatas> {

    @Override
    public HBillCreditApplicationDatas mapRow(ResultSet rs, int rowNum) throws SQLException {
	HBillCreditApplicationDatas bc = new HBillCreditApplicationDatas();
	
	if (findColumnNames(rs, "department_code")) {
	    bc.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "bank_code")) {
	    bc.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    bc.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	if (findColumnNames(rs, "successful_bid_interest_rate")) {
	    bc.setSuccessful_bid_interest_rate(rs.getDouble("successful_bid_interest_rate"));
	}
	
	if (findColumnNames(rs, "successful_bid_price")) {
	    bc.setSuccessful_bid_price(rs.getDouble("successful_bid_price"));
	}
	
	if (findColumnNames(rs, "bill_number")) {
	    bc.setBill_number(rs.getString("bill_number"));
	}
	
	if (findColumnNames(rs, "drawer_date")) {
	    bc.setDrawer_date(rs.getString("drawer_date"));
	}
	
	if (findColumnNames(rs, "maturity_date")) {
	    bc.setMaturity_date(rs.getString("maturity_date"));
	}
	
	if (findColumnNames(rs, "number_of_days")) {
	    bc.setNumber_of_days(rs.getLong("number_of_days"));
	}
	
	if (findColumnNames(rs, "interest_rate")) {
	    bc.setInterest_rate(rs.getDouble("interest_rate"));
	}
	
	if (findColumnNames(rs, "amount_of_money")) {
	    bc.setAmount_of_money(rs.getDouble("amount_of_money"));
	}
	
	if (findColumnNames(rs, "interest_amount")) {
	    bc.setInterest_amount(rs.getDouble("interest_amount"));
	}
	
	if (findColumnNames(rs, "remarks")) {
	    bc.setRemarks(rs.getString("remarks"));
	}
	
	if (findColumnNames(rs, "auction_date")) {
	    bc.setAuction_date(rs.getString("auction_date"));
	}
	
	return bc;
    }

}
