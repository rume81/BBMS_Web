package com.ey.bbms.mapper.rpt;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.mapper.BaseMapper;
import com.ey.bbms.model.rpt.SuccessfulBidDocumentSub;

public class SuccessfulBidDocumentSubMapper extends BaseMapper implements RowMapper<SuccessfulBidDocumentSub> {
    @Override
    public SuccessfulBidDocumentSub mapRow(ResultSet rs, int rowNum) throws SQLException {
	SuccessfulBidDocumentSub sbd = new SuccessfulBidDocumentSub();
	
	if (findColumnNames(rs, "department_code")) {
	    sbd.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "department_name")) {
	    sbd.setDepartment_name(rs.getString("department_name"));
	}
	
	if (findColumnNames(rs, "business_category")) {
	    sbd.setBusiness_category(rs.getString("business_category"));
	}
	
	if (findColumnNames(rs, "bank_code")) {
	    sbd.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    sbd.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	int colindx = findColumnAlias(rs, "bid_number1");
	if (colindx>0) {
	    sbd.setBid_number1(rs.getString(colindx));
	}
	
	if (findColumnNames(rs, "bid_interest_rate")) {
	    sbd.setBid_interest_rate(rs.getDouble("bid_interest_rate"));
	}
	
	if (findColumnNames(rs, "bid_amount_money")) {
	    sbd.setBid_amount_money(rs.getDouble("bid_amount_money"));
	}
	
	if (findColumnNames(rs, "successful_bid_interest_rate")) {
	    sbd.setSuccessful_bid_interest_rate(rs.getDouble("successful_bid_interest_rate"));
	}
	
	if (findColumnNames(rs, "successful_bid_price")) {
	    sbd.setSuccessful_bid_price(rs.getDouble("successful_bid_price"));
	}	
	
	return sbd;
    }

}
