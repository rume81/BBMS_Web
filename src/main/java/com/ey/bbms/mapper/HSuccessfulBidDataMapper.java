package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HSuccessfulBidData;

public class HSuccessfulBidDataMapper extends BaseMapper implements RowMapper<HSuccessfulBidData> {

    @Override
    public HSuccessfulBidData mapRow(ResultSet rs, int rowNum) throws SQLException {
	HSuccessfulBidData bid = new HSuccessfulBidData();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	if (findColumnNames(rs, "department_code")) {
	    bid.setDepartment_code(rs.getString("department_code"));
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
	
	if (findColumnNames(rs, "total_amount_money")) {
	    bid.setTotal_amount_money(rs.getDouble("total_amount_money"));
	}
	
	if (findColumnNames(rs, "successful_bid_interest_rate")) {
	    bid.setSuccessful_bid_interest_rate(rs.getDouble("successful_bid_interest_rate"));
	} else {
	    int index =findColumnAlias(rs, "successful_bid_interest_rate");
	    if(index>0){
		bid.setSuccessful_bid_interest_rate(rs.getDouble(index));
	    }
	}
	
	if (findColumnNames(rs, "successful_bid_amount_money")) {
	    bid.setSuccessful_bid_amount_money(rs.getDouble("successful_bid_amount_money"));
	} else if(findColumnNames(rs, "successful_bid_price")){ 
	    bid.setSuccessful_bid_amount_money(rs.getDouble("successful_bid_price"));
	} else{
	    int index =findColumnAlias(rs, "successful_bid_amount_money");
	    if(index>0){
		bid.setSuccessful_bid_amount_money(rs.getDouble(index));
	    }
	}
	
	if (findColumnNames(rs, "auction_date")) {
	    Date dt = rs.getDate("auction_date");
	    if(dt!=null)
		bid.setAuction_date(format.format(dt));
	    else
		bid.setAuction_date("");
	}
	
	return bid;
    }
}
