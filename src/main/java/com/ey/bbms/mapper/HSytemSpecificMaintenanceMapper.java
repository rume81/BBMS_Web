package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.ey.bbms.model.main.HSytemSpecificMaintenance;

public class HSytemSpecificMaintenanceMapper extends BaseMapper implements RowMapper<HSytemSpecificMaintenance> {

	@Override
    public HSytemSpecificMaintenance mapRow(ResultSet rs, int rowNum) throws SQLException {
		HSytemSpecificMaintenance systemMaintenance = new HSytemSpecificMaintenance();
		
		if (findColumnNames(rs, "bank_code")) {
			systemMaintenance.setBank_code(rs.getString("bank_code"));
		}
		if (findColumnNames(rs, "financial_institution_name")) {
			systemMaintenance.setFinancial_institution_name(rs.getString("financial_institution_name"));
		}
		if (findColumnNames(rs, "store")) {
			systemMaintenance.setStore(rs.getString("store"));
		}
		if (findColumnNames(rs, "business_category")) {
			systemMaintenance.setBusiness_category(rs.getString("business_category"));
		}
		if (findColumnNames(rs, "FN")) {
			systemMaintenance.setFN(rs.getString("FN"));
		}
		if (findColumnNames(rs, "Updated")) {
			systemMaintenance.setUpdated(rs.getString("Updated"));
		}
		
	return systemMaintenance;
	}
}
