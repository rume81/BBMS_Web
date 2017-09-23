package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HSystemSelection;

public class HSystemSelectionMapper extends BaseMapper implements RowMapper<HSystemSelection>{
	
	@Override
    public HSystemSelection mapRow(ResultSet rs, int rowNum) throws SQLException {
		HSystemSelection systemSelection = new HSystemSelection();
		
		if (findColumnNames(rs, "system_name")) {
			systemSelection.setSystem_name(rs.getString("system_name"));
		}
		if (findColumnNames(rs, "field_name")) {
			systemSelection.setField_name(rs.getString("field_name"));
		}
		if (findColumnNames(rs, "file_name")) {
			systemSelection.setFile_name(rs.getString("file_name"));
		}
		
		return systemSelection;
	}

}
