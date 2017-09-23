/* ========================================
* Scooby v. 1.0 class library
* ========================================
*
* http://www.scooby.com
*
* (C) Copyright 2010-2020, by WebHawksIT.
*
* --------------------
* HBidDataMapper.java
* --------------------
* Created on Dec 12, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Dec 12, 2016: Original version (Monsur)
*
*/
package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HBidData;

/**
 * @author OS-10 Monsur
 *
 */
public class HBidDataMapper extends BaseMapper implements RowMapper<HBidData> {
 
    @Override
    public HBidData mapRow(ResultSet rs, int rowNum) throws SQLException {
	HBidData bid = new HBidData();
	
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
	
	if (findColumnNames(rs, "auction_date")) {
	    bid.setAuction_date(rs.getString("auction_date"));
	}
	
	if (findColumnNames(rs, "flag1")) {
	    bid.setFlag1(rs.getString("flag1"));
	}
	
	if (findColumnNames(rs, "flag2")) {
	    bid.setFlag2(rs.getString("flag2"));
	}
	
	if (findColumnNames(rs, "flag3")) {
	    bid.setFlag3(rs.getString("flag3"));
	}
	return bid;
    }

}
