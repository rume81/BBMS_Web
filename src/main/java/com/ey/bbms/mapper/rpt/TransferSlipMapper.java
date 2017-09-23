package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.TransferSlip;

public class TransferSlipMapper extends BaseMapper implements RowMapper<TransferSlip> {
    @Override
    public TransferSlip mapRow(ResultSet rs, int rowNum) throws SQLException {
	TransferSlip ts=new TransferSlip();
	
	if (findColumnNames(rs, "department_code")) {
	    ts.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "department_name")) {
	    ts.setDepartment_name(rs.getString("department_name"));
	}
	
	if (findColumnNames(rs, "journal_number")) {
	    ts.setJournal_number(rs.getInt("journal_number"));
	}
	
	if (findColumnNames(rs, "slip_number")) {
	    ts.setSlip_number(rs.getInt("slip_number"));
	}
	
	if (findColumnNames(rs, "recorded_year")) {
	    ts.setRecorded_year(rs.getInt("recorded_year"));
	}
	
	if (findColumnNames(rs, "recorded_month")) {
	    ts.setRecorded_month(rs.getInt("recorded_month"));
	}
	
	if (findColumnNames(rs, "recorded_date")) {
	    ts.setRecorded_date(rs.getInt("recorded_date"));
	}
	
	if (findColumnNames(rs, "abstract_name")) {
	    ts.setAbstract_name(rs.getString("abstract_name"));
	}
	
	return ts;
    }
    

}
