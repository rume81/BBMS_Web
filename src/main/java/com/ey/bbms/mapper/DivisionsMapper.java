package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HDivisions;

public class DivisionsMapper extends BaseMapper implements RowMapper<HDivisions> {

    @Override
    public HDivisions mapRow(ResultSet rs, int rowNum) throws SQLException {
	HDivisions division = new HDivisions();

	if (findColumnNames(rs, "department_code")) {
	    division.setDepartment_code(rs.getString("department_code"));
	}

	if (findColumnNames(rs, "department_name")) {
	    division.setDepartment_name(rs.getString("department_name"));
	}

	return division;
    }

}
