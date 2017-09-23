package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HNameConversion;

public class HNameConversionMapper extends BaseMapper implements RowMapper<HNameConversion> {
	@Override
	public HNameConversion mapRow(ResultSet rs, int rowNum) throws SQLException {
		HNameConversion namecon = new HNameConversion();

		if (findColumnNames(rs, "bank_code")) {
			namecon.setBank_code(rs.getString("bank_code"));
		}
		if (findColumnNames(rs, "abbreviation")) {
			namecon.setAbbreviation(rs.getString("abbreviation"));
		}
		if (findColumnNames(rs, "financial_institution_name")) {
			namecon.setFinancial_institution_name(rs.getString("financial_institution_name"));
		}
		if (findColumnNames(rs, "business_category")) {
			namecon.setBusiness_category(rs.getString("business_category"));
		}
		if (findColumnNames(rs, "DelF")) {
			namecon.setDelF(rs.getInt("DelF"));
		}
		
		return namecon;
	}
}
