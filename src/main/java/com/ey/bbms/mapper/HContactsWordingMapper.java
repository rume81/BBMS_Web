package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HContactsWording;

public class HContactsWordingMapper extends BaseMapper implements RowMapper<HContactsWording> {

	@Override
	public HContactsWording mapRow(ResultSet rs, int rowNum) throws SQLException {
		HContactsWording inst = new HContactsWording();

		if (findColumnNames(rs, "department_code")) {
			inst.setDepartment_code(rs.getString("department_code"));
		}
		if (findColumnNames(rs, "horei01")) {
			inst.setHorei01(rs.getString("horei01"));
		}
		if (findColumnNames(rs, "horei02")) {
			inst.setHorei02(rs.getString("horei02"));
		}
		return inst;
	}

}
