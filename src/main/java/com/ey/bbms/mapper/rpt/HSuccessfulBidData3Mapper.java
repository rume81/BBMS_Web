package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.main.HSuccessfulBidData3;

public class HSuccessfulBidData3Mapper extends BaseMapper implements RowMapper<HSuccessfulBidData3> {

    @Override
    public HSuccessfulBidData3 mapRow(ResultSet rs, int rowNum) throws SQLException {
	HSuccessfulBidData3 bid = new HSuccessfulBidData3();
	
	if (findColumnNames(rs, "department_code")) {
	    bid.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "department_name")) {
	    bid.setDepartment_name(rs.getString("department_name"));
	}
	
	if (findColumnNames(rs, "business_category")) {
	    bid.setBusiness_category(rs.getString("business_category"));
	}
	
	if (findColumnNames(rs, "bank_code")) {
	    bid.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    bid.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	if (findColumnNames(rs, "bid_number")) {
	    bid.setBid_number(rs.getInt("bid_number"));
	}	
	
	if (findColumnNames(rs, "bid_interest_rate")) {
	    bid.setBid_interest_rate(rs.getDouble("bid_interest_rate"));
	}
	
	if (findColumnNames(rs, "bid_amount_money")) {
	    bid.setBid_amount_money(rs.getDouble("bid_amount_money"));
	}
		
	if (findColumnNames(rs, "successful_bid_interest_rate")) {
	    bid.setSuccessful_bid_interest_rate(rs.getDouble("successful_bid_interest_rate"));
	} 
	
	if (findColumnNames(rs, "successful_bid_price")) {
	    bid.setSuccessful_bid_price(rs.getDouble("successful_bid_price"));
	}
	
	if (findColumnNames(rs, "financial_institutions_by_flag")) {
	    bid.setFinancial_institutions_by_flag(rs.getInt("financial_institutions_by_flag"));
	}
	
	if (findColumnNames(rs, "auction_date")) {
	    bid.setAuction_date(rs.getString("auction_date"));
	}
	
	return bid;
    }

}
