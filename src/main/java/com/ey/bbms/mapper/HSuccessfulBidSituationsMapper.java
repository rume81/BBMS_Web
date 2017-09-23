/* ========================================
* Scooby v. 1.0 class library
* ========================================
*
* http://www.scooby.com
*
* (C) Copyright 2010-2020, by WebHawksIT.
*
* --------------------
* HSuccessfulBidSituationsMapper.java
* --------------------
* Created on Dec 20, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Dec 20, 2016: Original version (Monsur)
*
*/
package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HSuccessfulBidSituations;

/**
 * @author OS-10 Monsur
 *
 */
public class HSuccessfulBidSituationsMapper extends BaseMapper implements RowMapper<HSuccessfulBidSituations> {

    @Override
    public HSuccessfulBidSituations mapRow(ResultSet rs, int rowNum) throws SQLException {
	HSuccessfulBidSituations bid = new HSuccessfulBidSituations();
	
	if (findColumnNames(rs, "sno")) {
	    bid.setSno(rs.getLong("sno"));
	}
	
	if (findColumnNames(rs, "department_code")) {
	    bid.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "department_name")) {
	    bid.setDepartment_code(rs.getString("department_name"));
	}
	
	if (findColumnNames(rs, "bid_interest_rate")) {
	    bid.setBid_interest_rate(rs.getDouble("bid_interest_rate"));
	}
	
	if (findColumnNames(rs, "bid_interest_rate2")) {
	    bid.setBid_interest_rate2(rs.getDouble("bid_interest_rate2"));
	}
		
	if (findColumnNames(rs, "sum_of_bid_amount_money")) {
	    bid.setInterest_rate_by_bid_amount_of_money(rs.getDouble("sum_of_bid_amount_money"));
	} 
	
	if (findColumnNames(rs, "average_rate")) {
	    bid.setAverage_rate(rs.getDouble("average_rate"));
	}
	
	if (findColumnNames(rs, "total_amount_of_money")) {
	    bid.setTotal_amount_of_money(rs.getDouble("total_amount_of_money"));
	}
	
	if (findColumnNames(rs, "cumulative_interest")) {
	    bid.setCumulative_interest(rs.getDouble("cumulative_interest"));
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
	
	if (findColumnNames(rs, "bid_amount_money")) {
	    bid.setBid_amount_of_money(rs.getDouble("bid_amount_money"));
	} else if (findColumnNames(rs, "bid_amount_of_money")) {
	    bid.setBid_amount_of_money(rs.getDouble("bid_amount_of_money"));
	}
		
	return bid;
    }

}
