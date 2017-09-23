package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HProrationDataInterestRateOrder;

public class HProrationDataInterestRateOrderMapper extends BaseMapper implements RowMapper<HProrationDataInterestRateOrder> {
   @Override
    public HProrationDataInterestRateOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
       HProrationDataInterestRateOrder pr = new HProrationDataInterestRateOrder();
       
       if (findColumnNames(rs, "bid_interest_rate")) {
	   pr.setBid_interest_rate(rs.getDouble("bid_interest_rate"));
	}
       
       if (findColumnNames(rs, "bank_code")) {
	   pr.setBank_code(rs.getString("bank_code"));
	}
       
       if (findColumnNames(rs, "bid_number")) {
	   pr.setBid_number(rs.getInt("bid_number"));
	}
       
       if (findColumnNames(rs, "bid_amount_of_money")) {
	   pr.setBid_amount_of_money(rs.getDouble("bid_amount_of_money"));
	}
       return pr;
    }

}
