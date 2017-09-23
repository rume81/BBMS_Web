package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HBidDataRateOrder;

public class HBidDataRateOrderMapper extends BaseMapper implements RowMapper<HBidDataRateOrder> {

	@Override
	public HBidDataRateOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
		HBidDataRateOrder bid = new HBidDataRateOrder();
		
		if (findColumnNames(rs, "department_code")) {
		    bid.setDepartment_code(rs.getString("department_code"));
		}
		
		if (findColumnNames(rs, "business_category")) {
		    bid.setBusiness_category(rs.getString("business_category"));
		} else if (findColumnNames(rs, "business_categorie")) {
		    bid.setBusiness_category(rs.getString("business_categorie"));
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
		} else{
		    bid.setBid_interest_rate(0.0);
		}
		
		if (findColumnNames(rs, "bid_amount_money")) {
		    bid.setBid_amount_money(rs.getDouble("bid_amount_money"));
		} else if (findColumnNames(rs, "bid_amount_of_money")) {
		    bid.setBid_amount_money(rs.getDouble("bid_amount_of_money"));
		} else{
		    bid.setBid_amount_money(0.0);
		}
		
		if (findColumnNames(rs, "total_amount_money")) {
		    bid.setTotal_amount_money(rs.getDouble("total_amount_money"));
		} else if (findColumnNames(rs, "total_amount_of_money")) {
		    bid.setTotal_amount_money(rs.getDouble("total_amount_of_money"));
		}
		else{
		    bid.setTotal_amount_money(0.0);
		}
			
		return bid;
	}

}
