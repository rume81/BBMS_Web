package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HBidDataByBankTotals;

public class HBidDataByBankTotalsMapper extends BaseMapper implements RowMapper<HBidDataByBankTotals> {
    @Override
    public HBidDataByBankTotals mapRow(ResultSet rs, int rowNum) throws SQLException {
	HBidDataByBankTotals bt = new HBidDataByBankTotals();
	
	if (findColumnNames(rs, "department_code")) {
	    bt.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "bank_code")) {
	    bt.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    bt.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	if (findColumnNames(rs, "sum_of_bid_amount_money")) {
	    bt.setSum_of_bid_amount_money(rs.getDouble("sum_of_bid_amount_money"));
	}
	
	return bt;
    }

}
