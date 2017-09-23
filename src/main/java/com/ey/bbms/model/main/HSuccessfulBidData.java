package com.ey.bbms.model.main;

import java.util.Date;

public class HSuccessfulBidData extends HBidDataRateOrder{

	private double successful_bid_interest_rate;
	private double successful_bid_amount_money;
	private String auction_date;
	
	public double getSuccessful_bid_interest_rate() {
		return successful_bid_interest_rate;
	}
	public void setSuccessful_bid_interest_rate(double successful_bid_interest_rate) {
		this.successful_bid_interest_rate = successful_bid_interest_rate;
	}
	public double getSuccessful_bid_amount_money() {
		return successful_bid_amount_money;
	}
	public void setSuccessful_bid_amount_money(double successful_bid_amount_money) {
		this.successful_bid_amount_money = successful_bid_amount_money;
	}
	public String getAuction_date() {
		return auction_date;
	}
	public void setAuction_date(String auction_date) {
		this.auction_date = auction_date;
	}
	
	 
}

