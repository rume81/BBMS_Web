package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.support.HProrationdataprocessing;

public class HProrationdataprocessingMapper extends BaseMapper implements RowMapper<HProrationdataprocessing> {
    @Override
    public HProrationdataprocessing mapRow(ResultSet rs, int rowNum) throws SQLException {
	HProrationdataprocessing pro = new HProrationdataprocessing();

	if (findColumnNames(rs, "total_amount_of_money")) {
	    pro.setTotal_amount_of_money(rs.getDouble("total_amount_of_money"));
	}
	
	if (findColumnNames(rs, "bank_code")) {
	    pro.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "sum_of_total_amount")) {
	    pro.setSum_of_total_amount(rs.getDouble("sum_of_total_amount"));
	}

	return pro;
    }

}
