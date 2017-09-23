package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.Financialinstitute;

public class FinancialinstituteMapper extends BaseMapper implements RowMapper<Financialinstitute> {
    @Override
    public Financialinstitute mapRow(ResultSet rs, int rowNum) throws SQLException {
	Financialinstitute inst = new Financialinstitute();

	if (findColumnNames(rs, "department_code")) {
	    inst.setDepartment_code(rs.getString("department_code"));
	}

	if (findColumnNames(rs, "department_name")) {
	    inst.setDepartment_name(rs.getString("department_name"));
	}

	if (findColumnNames(rs, "business_category")) {
	    inst.setBusiness_category(rs.getString("business_category"));
	}

	if (findColumnNames(rs, "bank_name")) {
	    inst.setBank_name(rs.getString("bank_name"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    inst.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	if (findColumnNames(rs, "bank_code")) {
	    inst.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "bid_number")) {
	    inst.setBid_number(rs.getInt("bid_number"));
	}
	
	if (findColumnNames(rs, "bid_interest_rate")) {
	    inst.setBid_interest_rate(rs.getDouble("bid_interest_rate"));
	}
	
	if (findColumnNames(rs, "bid_amount_money")) {
	    inst.setBid_amount_money(rs.getDouble("bid_amount_money"));
	}

	return inst;
    }

}
