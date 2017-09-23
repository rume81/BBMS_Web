package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.JournalSearchResult;

public class JournalSearchResultMapper extends BaseMapper implements RowMapper<JournalSearchResult> {
    @Override
    public JournalSearchResult mapRow(ResultSet rs, int rowNum) throws SQLException {
	JournalSearchResult ts = new JournalSearchResult();
	
	if (findColumnNames(rs, "journal_number")) {
	    ts.setJournal_number(rs.getInt("journal_number"));
	}
	
	if (findColumnNames(rs, "slip_number")) {
	    ts.setSlip_number(rs.getInt("slip_number"));
	}
	
	if (findColumnNames(rs, "row_number")) {
	    ts.setRow_number(rs.getInt("row_number"));
	}
	
	if (findColumnNames(rs, "department_code")) {
	    ts.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "department_name")) {
	    ts.setDepartment_name(rs.getString("department_name"));
	}
	
	if (findColumnNames(rs, "debit_item_code")) {
	    ts.setDebit_item_code(rs.getString("debit_item_code"));
	}
	
	if (findColumnNames(rs, "debit_item_name")) {
	    ts.setDebit_item_name(rs.getString("debit_item_name"));
	}
	
	if (findColumnNames(rs, "debit_details_code")) {
	    ts.setDebit_details_code(rs.getString("debit_details_code"));
	}	
	
	if (findColumnNames(rs, "debit_details_name")) {
	    ts.setDebit_details_name(rs.getString("debit_details_name"));
	}
	
	if (findColumnNames(rs, "credit_course_code")) {
	    ts.setCredit_course_code(rs.getString("credit_course_code"));
	}
	
	if (findColumnNames(rs, "credit_course_name")) {
	    ts.setCredit_course_name(rs.getString("credit_course_name"));
	}
	
	if (findColumnNames(rs, "credit_specific_code")) {
	    ts.setCredit_specific_code(rs.getString("credit_specific_code"));
	}
	
	if (findColumnNames(rs, "credit_specific_name")) {
	    ts.setCredit_specific_name(rs.getString("credit_specific_name"));
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
	
	if (findColumnNames(rs, "debit_amount")) {
	    ts.setDebit_amount(rs.getDouble("debit_amount"));
	}
	
	if (findColumnNames(rs, "credit_amount")) {
	    ts.setCredit_amount(rs.getDouble("credit_amount"));
	}
	
	if (findColumnNames(rs, "balance_amount")) {
	    ts.setBalance_amount(rs.getDouble("balance_amount"));
	}
	
	if (findColumnNames(rs, "abstract_code")) {
	    ts.setAbstract_code(rs.getString("abstract_code"));
	}
	
	if (findColumnNames(rs, "abstract_name")) {
	    ts.setAbstract_name(rs.getString("abstract_name"));
	}
	
	if (findColumnNames(rs, "processing_time")) {
	    ts.setProcessing_time(rs.getInt("processing_time"));
	}
	
	if (findColumnNames(rs, "debit_consumption_tax_classification")) {
	    ts.setDebit_consumption_tax_classification(rs.getString("debit_consumption_tax_classification"));
	}
	
	if (findColumnNames(rs, "credit_consumption_tax_classification")) {
	    ts.setCredit_consumption_tax_classification(rs.getString("credit_consumption_tax_classification"));
	}
	
	if (findColumnNames(rs, "customer_code")) {
	    ts.setCustomer_code(rs.getString("customer_code"));
	}
	
	if (findColumnNames(rs, "customer_name")) {
	    ts.setCustomer_name(rs.getString("customer_name"));
	}
	
	if (findColumnNames(rs, "type_size")) {
	    ts.setType_size(rs.getString("type_size"));
	}
	
	
	return ts;
    }

}
