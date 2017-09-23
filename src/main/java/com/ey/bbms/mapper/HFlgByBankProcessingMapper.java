package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.support.HFlgByBankProcessing;

/**
 * @author OS-10 Monsur
 *
 */
public class HFlgByBankProcessingMapper extends BaseMapper implements RowMapper<HFlgByBankProcessing>{
    @Override
    public HFlgByBankProcessing mapRow(ResultSet rs, int rowNum) throws SQLException {
	HFlgByBankProcessing obj = new HFlgByBankProcessing();
	
	if (findColumnNames(rs, "bank_code")) {
	    obj.setBank_code(rs.getString("bank_code"));
	}
	
	if (findColumnNames(rs, "bid_number")) {
	    obj.setBid_number(rs.getInt("bid_number"));
	}
	
	if (findColumnNames(rs, "department_code")) {
	    obj.setDepartment_code(rs.getString("department_code"));
	}
	
	if (findColumnNames(rs, "financial_institutions_by_flag")) {
	    obj.setFinancial_institutions_by_flag(rs.getInt("financial_institutions_by_flag"));
	}
	
	if (findColumnNames(rs, "successful_bid_price")) {
	    double sbp = rs.getDouble("successful_bid_price");
	    if(rs.wasNull()){
		obj.setSuccessful_bid_price(-1);
	    } else{
		obj.setSuccessful_bid_price(sbp);
	    }
	}
	
	return obj;
    }

}
