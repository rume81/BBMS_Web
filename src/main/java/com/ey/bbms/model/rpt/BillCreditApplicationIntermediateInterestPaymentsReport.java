package com.ey.bbms.model.rpt;

public class BillCreditApplicationIntermediateInterestPaymentsReport extends CreditApplicationPrintReport {
    String financial_institutions;
    String drawer_date;
    String maturity_date;
    double expr1;
    String expr2;
    int expr3;
    
    public String getFinancial_institutions() {
        return financial_institutions;
    }
    public void setFinancial_institutions(String financial_institutions) {
        this.financial_institutions = financial_institutions;
    }
    public String getDrawer_date() {
        return drawer_date;
    }
    public void setDrawer_date(String drawer_date) {
        this.drawer_date = drawer_date;
    }
    public String getMaturity_date() {
        return maturity_date;
    }
    public void setMaturity_date(String maturity_date) {
        this.maturity_date = maturity_date;
    }
    public double getExpr1() {
        return expr1;
    }
    public void setExpr1(double expr1) {
        this.expr1 = expr1;
    }
    public String getExpr2() {
        return expr2;
    }
    public void setExpr2(String expr2) {
        this.expr2 = expr2;
    }
    public int getExpr3() {
        return expr3;
    }
    public void setExpr3(int expr3) {
        this.expr3 = expr3;
    }
}
