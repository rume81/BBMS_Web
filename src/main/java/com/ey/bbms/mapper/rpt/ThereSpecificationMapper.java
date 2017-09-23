package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.ThereSpecification;

public class ThereSpecificationMapper extends BaseMapper implements RowMapper<ThereSpecification> {

	@Override
	public ThereSpecification mapRow(ResultSet rs, int rowNum) throws SQLException {
		ThereSpecification bs= new ThereSpecification();
		
		if (findColumnNames(rs, "sno")) {
		    bs.setSno(rs.getLong("sno"));
		}
		
		if (findColumnNames(rs, "department_code")) {
		    bs.setDepartment_code(rs.getString("department_code"));
		}
		
		if (findColumnNames(rs, "bid_interest_rate")) {
		    bs.setBid_interest_rate(rs.getDouble("bid_interest_rate"));
		}
		
		if (findColumnNames(rs, "bid_interest_rate2")) {
		    bs.setBid_interest_rate2(rs.getDouble("bid_interest_rate2"));
		}
		
		if (findColumnNames(rs, "interest_rate_by_bid_amount_of_money")) {
		    bs.setInterest_rate_by_bid_amount_of_money(rs.getDouble("interest_rate_by_bid_amount_of_money"));
		}
		
		if (findColumnNames(rs, "average_rate")) {
		    bs.setAverage_rate(rs.getDouble("average_rate"));
		}
		
		if (findColumnNames(rs, "total_amount_of_money")) {
		    bs.setTotal_amount_of_money(rs.getDouble("total_amount_of_money"));
		}
		
		if (findColumnNames(rs, "cumulative_interest")) {
		    bs.setCumulative_interest(rs.getDouble("cumulative_interest"));
		}
		
		if (findColumnNames(rs, "business_category")) {
		    bs.setBusiness_category(rs.getString("business_category"));
		}
		
		if (findColumnNames(rs, "bank_code")) {
		    bs.setBank_code(rs.getString("bank_code"));
		}
		
		if (findColumnNames(rs, "bank_name")) {
		    bs.setBank_name(rs.getString("bank_name"));
		}
		
		if (findColumnNames(rs, "financial_institution_name")) {
		    bs.setFinancial_institution_name(rs.getString("financial_institution_name"));
		}
		
		if (findColumnNames(rs, "bid_number")) {
		    bs.setBid_number(rs.getInt("bid_number"));
		}
		
		if (findColumnNames(rs, "bid_amount_of_money")) {
		    bs.setBid_amount_of_money(rs.getDouble("bid_amount_of_money"));
		}
		
		if (findColumnNames(rs, "bid_interest_rate_key")) {
		    bs.setBid_interest_rate_key(rs.getString("bid_interest_rate_key"));
		}
		
		return bs;
	}

}
