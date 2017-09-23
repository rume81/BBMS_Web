package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HBidSituationInterestRateCumulativeCalculation;

public class HBidSituationInterestRateCumulativeCalculationMapper extends BaseMapper
	implements RowMapper<HBidSituationInterestRateCumulativeCalculation> {
    @Override
    public HBidSituationInterestRateCumulativeCalculation mapRow(ResultSet rs, int rowNum) throws SQLException {
	HBidSituationInterestRateCumulativeCalculation bs = new HBidSituationInterestRateCumulativeCalculation();

	if (findColumnNames(rs, "groups")) {
	    bs.setGroups(rs.getString("groups"));
	}

	if (findColumnNames(rs, "bid_interest_rate")) {
	    bs.setBid_interest_rate(rs.getDouble("bid_interest_rate"));
	}

	if (findColumnNames(rs, "sum_of_bid_amount_of_money")) {
	    bs.setSum_of_bid_amount_of_money(rs.getDouble("sum_of_bid_amount_of_money"));
	}

	return bs;
    }

}
