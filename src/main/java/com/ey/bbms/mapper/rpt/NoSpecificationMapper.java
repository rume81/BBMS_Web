package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.NoSpecification;

public class NoSpecificationMapper extends BaseMapper implements RowMapper<NoSpecification> {

	@Override
	public NoSpecification mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoSpecification nos = new NoSpecification();
		
		if (findColumnNames(rs, "bid_interest_rate")) {
			nos.setBid_interest_rate(rs.getDouble("bid_interest_rate"));
		}
		
		if (findColumnNames(rs, "interest_rate_by_bid_amount_of_money")) {
			nos.setInterest_rate_by_bid_amount_of_money(rs.getDouble("interest_rate_by_bid_amount_of_money"));
		}
		
		if (findColumnNames(rs, "average_interest_rate")) {
			nos.setAverage_interest_rate(rs.getDouble("average_interest_rate"));
		}
		
		if (findColumnNames(rs, "total_amount_of_money")) {
			nos.setTotal_amount_of_money(rs.getDouble("total_amount_of_money"));
		}
		
		return nos;
	}

}
