package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HCommon;

public class HCommonMapper extends BaseMapper implements RowMapper<HCommon> {

    @Override
    public HCommon mapRow(ResultSet rs, int rowNum) throws SQLException {
	HCommon obj = new HCommon();

	if (rs != null) {
	    List<String> data = new ArrayList<String>();

	    ResultSetMetaData rsMetaData = rs.getMetaData();
	    int numberOfColumns = rsMetaData.getColumnCount();

	    for (int i = 1; i < numberOfColumns + 1; i++) {
		data.add(rs.getString(i));
	    }

	    obj.setDatas(data);
	}
	return obj;
    }

}
