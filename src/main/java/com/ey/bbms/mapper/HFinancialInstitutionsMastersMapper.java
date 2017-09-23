package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HFinancialInstitutionsMasters;

public class HFinancialInstitutionsMastersMapper extends BaseMapper implements RowMapper<HFinancialInstitutionsMasters> {
    @Override
    public HFinancialInstitutionsMasters mapRow(ResultSet rs, int rowNum) throws SQLException {
	HFinancialInstitutionsMasters inst = new HFinancialInstitutionsMasters();
	
	if (findColumnNames(rs, "bank_code")) {
	    inst.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "financial_institution_name")) {
	    inst.setFinancial_institution_name(rs.getString("financial_institution_name"));
	}
	
	if (findColumnNames(rs, "store")) {
	    inst.setStore(rs.getString("store"));
	}
	
	if (findColumnNames(rs, "business_category")) {
	    inst.setBusiness_category(rs.getString("business_category"));
	}
	
	if (findColumnNames(rs, "f11")) {
	    inst.setF11(rs.getString("f11"));
	}
	
	if (findColumnNames(rs, "f12")) {
	    inst.setF12(rs.getString("f12"));
	}
	
	if (findColumnNames(rs, "f13")) {
	    inst.setF13(rs.getString("f13"));
	}
	
	if (findColumnNames(rs, "f14")) {
	    inst.setF14(rs.getString("f14"));
	}
	
	if (findColumnNames(rs, "f15")) {
	    inst.setF15(rs.getString("f15"));
	}
	
	if (findColumnNames(rs, "f16")) {
	    inst.setF16(rs.getString("f16"));
	}
	
	if (findColumnNames(rs, "f17")) {
	    inst.setF17(rs.getString("f17"));
	}
	
	if (findColumnNames(rs, "f18")) {
	    inst.setF18(rs.getString("f18"));
	}
	
	if (findColumnNames(rs, "f19")) {
	    inst.setF19(rs.getString("f19"));
	}
	
	if (findColumnNames(rs, "f20")) {
	    inst.setF20(rs.getString("f20"));
	}
	
	if (findColumnNames(rs, "f21")) {
	    inst.setF21(rs.getString("f21"));
	}
	
	if (findColumnNames(rs, "f22")) {
	    inst.setF22(rs.getString("f22"));
	}
	
	if (findColumnNames(rs, "f23")) {
	    inst.setF23(rs.getString("f23"));
	}
	
	if (findColumnNames(rs, "f24")) {
	    inst.setF24(rs.getString("f24"));
	}
	
	if (findColumnNames(rs, "f25")) {
	    inst.setF25(rs.getString("f25"));
	}
	
	if (findColumnNames(rs, "f26")) {
	    inst.setF26(rs.getString("f26"));
	}
	
	if (findColumnNames(rs, "f27")) {
	    inst.setF27(rs.getString("f27"));
	}
	
	if (findColumnNames(rs, "f28")) {
	    inst.setF28(rs.getString("f28"));
	}
	
	if (findColumnNames(rs, "f29")) {
	    inst.setF29(rs.getString("f29"));
	}
	
	if (findColumnNames(rs, "f30")) {
	    inst.setF30(rs.getString("f30"));
	}
	
	if (findColumnNames(rs, "updated")) {
	    inst.setUpdated(rs.getString("updated"));
	}
	
	if (findColumnNames(rs, "DelF")) {
	    inst.setDelF(rs.getInt("DelF"));
	}
	
	return inst;
    }

}
