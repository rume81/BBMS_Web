package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HBusinessCategory;

public class BusinessCategoryMapper extends BaseMapper implements RowMapper<HBusinessCategory> {

	@Override
	public HBusinessCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		HBusinessCategory businesscategory = new HBusinessCategory();
		
		if (findColumnNames(rs, "businesscategory")) {
			businesscategory.setBusinesscategory(rs.getString("businesscategory"));
		}

		return businesscategory;
	}
}
