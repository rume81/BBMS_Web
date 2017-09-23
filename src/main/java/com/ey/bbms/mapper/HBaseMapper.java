package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HBase;

public class HBaseMapper extends BaseMapper implements RowMapper<HBase> {

    @Override
    public HBase mapRow(ResultSet rs, int rowNum) throws SQLException {
	HBase base = new HBase();

	if (findColumnNames(rs, "wrkdirs")) {
	    base.setWrkdirs(rs.getString("wrkdirs"));
	}
	if (findColumnNames(rs, "department_code")) {
	    base.setDepartment_code(rs.getString("department_code"));
	}
	if (findColumnNames(rs, "department_name")) {
	    base.setDepartment_name(rs.getString("department_name"));
	}
	if (findColumnNames(rs, "chairman_name")) {
	    base.setChairman_name(rs.getString("chairman_name"));
	}
	if (findColumnNames(rs, "chairman_name_2")) {
	    base.setChairman_name_2(rs.getString("chairman_name_2"));
	}
	if (findColumnNames(rs, "minister_of_finance")) {
	    base.setMinister_of_finance(rs.getString("minister_of_finance"));
	}
	if (findColumnNames(rs, "street_address")) {
	    base.setStreet_address(rs.getString("street_address"));
	}
	if (findColumnNames(rs, "financial_institution_name_1")) {
	    base.setFinancial_institution_name_1(rs.getString("financial_institution_name_1"));
	}
	if (findColumnNames(rs, "financial_institution_name_2")) {
	    base.setFinancial_institution_name_2(rs.getString("financial_institution_name_2"));
	}
	if (findColumnNames(rs, "division_name")) {
	    base.setDivision_name(rs.getString("division_name"));
	}
	if (findColumnNames(rs, "provisional_successful_bid_document_wording")) {
	    base.setProvisional_successful_bid_document_wording(
		    rs.getString("provisional_successful_bid_document_wording"));
	}
	if (findColumnNames(rs, "successful_bid_document_wording")) {
	    base.setSuccessful_bid_document_wording(rs.getString("successful_bid_document_wording"));
	}
	if (findColumnNames(rs, "provisional_successful_bid_document_wording_2")) {
	    base.setProvisional_successful_bid_document_wording_2(
		    rs.getString("provisional_successful_bid_document_wording_2"));
	}
	if (findColumnNames(rs, "successful_bid_document_wording_2")) {
	    base.setSuccessful_bid_document_wording_2(rs.getString("successful_bid_document_wording_2"));
	}
	if (findColumnNames(rs, "credit_application_wording")) {
	    base.setCredit_application_wording(rs.getString("credit_application_wording"));
	}
	if (findColumnNames(rs, "payees_bank_name")) {
	    base.setPayees_bank_name(rs.getString("payees_bank_name"));
	}
	return base;
    }

}
