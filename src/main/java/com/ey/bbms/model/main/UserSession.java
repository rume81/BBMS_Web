package com.ey.bbms.model.main;

public class UserSession {
    private int sessionId;
    private String clientIpAddress;
    private String bidDate;
    private String bidAmountPland;
    private String footCutInterestRates;
    private String borrowingDate;
    private String maturityDate;
    
    protected HUser user = null;
    
    public int getSessionId() {
        return sessionId;
    }
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    public String getClientIpAddress() {
        return clientIpAddress;
    }
    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }
    public HUser getUser() {
        return user;
    }
    public void setUser(HUser user) {
        this.user = user;
    }
    public String getBidDate() {
        return bidDate;
    }
    public void setBidDate(String bidDate) {
        this.bidDate = bidDate;
    }
	public String getBidAmountPland() {
		return bidAmountPland;
	}
	public void setBidAmountPland(String bidAmountPland) {
		this.bidAmountPland = bidAmountPland;
	}
	public String getFootCutInterestRates() {
		return footCutInterestRates;
	}
	public void setFootCutInterestRates(String footCutInterestRates) {
		this.footCutInterestRates = footCutInterestRates;
	}
	public String getBorrowingDate() {
		return borrowingDate;
	}
	public void setBorrowingDate(String borrowingDate) {
		this.borrowingDate = borrowingDate;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
}
