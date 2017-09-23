package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.SuccessfulBidDocument;

public class SuccessfulBidDocumentMapper extends BaseMapper implements RowMapper<SuccessfulBidDocument> {
    @Override
    public SuccessfulBidDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
	SuccessfulBidDocument sb = new SuccessfulBidDocument();
	
	if (findColumnNames(rs, "bank_code")) {
	    sb.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    sb.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	int colindx = findColumnAlias(rs, "name_conversion_fi_name");
	if (colindx>0) {
	    sb.setName_conversion_fi_name(rs.getString(colindx));
	}
	
	return sb;
    }

}
