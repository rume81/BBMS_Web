package com.ey.bbms.model.main;

public class HBidSituationInterestRateCumulativeCalculation extends HObject {
    private String groups;
    private Double bid_interest_rate;
    private Double sum_of_bid_amount_of_money;
    
    public String getGroups() {
        return groups;
    }
    public void setGroups(String groups) {
        this.groups = groups;
    }
    public Double getBid_interest_rate() {
        return bid_interest_rate;
    }
    public void setBid_interest_rate(Double bid_interest_rate) {
        this.bid_interest_rate = bid_interest_rate;
    }
    public Double getSum_of_bid_amount_of_money() {
        return sum_of_bid_amount_of_money;
    }
    public void setSum_of_bid_amount_of_money(Double sum_of_bid_amount_of_money) {
        this.sum_of_bid_amount_of_money = sum_of_bid_amount_of_money;
    }
}
