package com.ey.bbms.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.ey.bbms.model.main.HBase;
import com.ey.bbms.model.main.HBidData;
import com.ey.bbms.model.main.HBidDataRateOrder;
import com.ey.bbms.model.main.HBidStatus;
import com.ey.bbms.model.main.HBillCreditApplicationDatas;
import com.ey.bbms.model.main.HCommon;
import com.ey.bbms.model.main.HDivisions;
import com.ey.bbms.model.main.HFinancialInstitutionsMasters;
import com.ey.bbms.model.main.HProrationDataInterestRateOrder;
import com.ey.bbms.model.main.HSuccessfulBidData;
import com.ey.bbms.model.main.HSuccessfulBidData3;
import com.ey.bbms.model.main.HSuccessfulBidSituations;
import com.ey.bbms.model.main.HUser;
import com.ey.bbms.model.rpt.HFinancialInstituteBy;
import com.ey.bbms.model.rpt.JournalSearchResult;
import com.ey.bbms.model.rpt.BillApplicationFormListReport;
import com.ey.bbms.model.rpt.BillCreditApplicationIntermediateInterestPaymentsReport;
import com.ey.bbms.model.rpt.CreditApplicationPrintReport;
import com.ey.bbms.model.rpt.Financialinstitute;
import com.ey.bbms.model.rpt.NoSpecification;
import com.ey.bbms.model.rpt.SuccessfulBidDocument;
import com.ey.bbms.model.rpt.SuccessfulBidDocumentSub;
import com.ey.bbms.model.rpt.ThereSpecification;
import com.ey.bbms.model.rpt.TransferSlip;
import com.ey.bbms.model.support.HFlgByBankProcessing;
import com.ey.bbms.model.support.HProrationdataprocessing;

public interface IBbmsDAO {
    
    public HUser getEmployeeByPassword(boolean isDeleted,String userName, String pass);
    
    public List<HUser> getAllUser();
    
    public List<Financialinstitute> getFinancialInstituteList(String date);
    
    public List<HFinancialInstitutionsMasters> getHFinancialInstitutionsMasters(String bankCode);
    
    public List<HDivisions> getDivision();
    
    public HBase getBase();
    
    public List<HBidData> getBidData(String bidDate,String bankcode,String deptcode);
    
    public List<HBidData> getBidData();
    
    public Integer getRowCountOfBidDataForABank(String bidDate,String bankcode,String deptcode);
    
    public boolean deleteRowOfBidDataForABank(String bidDate,String bankcode,String deptcode);
    
    public boolean addRowOfBidDataForABank(List<HBidData> biddatas);
    
    public boolean updateFlagOfBidDataForABank(String bidDate,String bankcode,String deptcode,String flg);
    
    public boolean BidInterestRateOrderClear();
    
    public boolean AddBidInterestRateOrder(String deptcode, String bidDate);
    
    public boolean BidStatusTableClear();
    
    public boolean AddToBidStatusTable();
    
    public boolean BidSituationInterestRateCumulativeCalculationClear();
    
    public boolean BidSituationInterestRateCumulativeCalculation();
    
    public void UpdateBidStatusTable();
    
    public List<HBidStatus> getBidStatus();
    
    public boolean UpdateBidStatus2(HBidStatus obj);
    
    public HCommon getLargestTotalFromBidStatus();
    
    public List<ThereSpecification> getThereSpecification(String date);
    
    public List<NoSpecification> getNoSpecification(String date);
    
    public boolean DropViewIfExist(String viewName);
    
    public boolean DataClear(String tableName);
    
    public boolean getForMinimumAmountDetermined();
    
    public boolean AddBidInterestRateOrder(double GENDO_RI, String deptcode, String bidDate);
    
    public List<HBidDataRateOrder> getBidDataRateOrder();
    
    public boolean UpdateBidInterestRateOrder(HBidDataRateOrder obj);
    
    public boolean addProrationData(HBidDataRateOrder obj);
    
    public List<HProrationDataInterestRateOrder> getProrationDataInterestRateOrder();
    
    public List<HBidDataRateOrder> getBidDataInterestRateOrderLastTotalAmount();
    
    public boolean deleteProrationData();
    
    public double getTotalAmount();
    
    public double getProratedBidPriceTotal();
    
    public List<HBidDataRateOrder> getProrationData(int sq);
    
    public void UpdateProrationData(HBidDataRateOrder obj);
    
    public HProrationdataprocessing getProrationDataProcessing1();
    
    public HProrationdataprocessing getProrationDataProcessing2();
    
    public boolean InsertBidDataByBankTotals();
    
    public boolean UpdateProrationDatas();
    
    public double getProratedTotal();
    
    public boolean UpdateProrationDataPrice(HBidDataRateOrder obj);
    
    public boolean InsertSuccessfulBidDatas(int sq,String bidDate);
    
    public boolean DeleteSuccessfulBidDatas3(String dept,String bidDate);
    
    public List<HSuccessfulBidData> SuccessfulBidData2(String bidDate);
    
    public void writeSuccessfulBidData2(List<HSuccessfulBidData> SuccessfulBidDatas);
    
    public List<HSuccessfulBidData> SuccessfulBidData3();
    
    public void insertSuccessfulBidData3(HSuccessfulBidData3 hsbd2,int sq);
    
    public List<HFlgByBankProcessing> getFlgByBankProcessing();
    
    public boolean Updateflg_ByBankProcessing(HBidDataRateOrder obj, int flag);
    
    public boolean AddSuccessfulBidStatusTable(String dept,String bidDate);
    
    public boolean MakeSuccessfulBidSituationInterestRateCumulativeCalculation();
    
    public List<HSuccessfulBidSituations> getSuccessfulBidSituations();
    
    public boolean InsertSuccessfulBidStatusTable(HSuccessfulBidSituations hsrst);
    
    public List<HSuccessfulBidSituations> getInterestRateBy(String bidDate, String dept_code);
    
    public List<HSuccessfulBidData3> getFinancialInstituteBy(String bidDate, String dept_code);
    
    public List<HSuccessfulBidData3> loadBidMaintenanceData(String bidDate, String deptCode);
    
    public boolean UpdateSucessfullBidData(HSuccessfulBidData3 obj, String deptCode);
    
    public boolean UpdateSuccessfulBidData3(String bidDate);
    
    public List<SuccessfulBidDocument> getSuccessfulBidDocument(String bidDate, String deptCode);
    
    public List<SuccessfulBidDocumentSub> getSuccessfulBidDocumentSub(String bidDate, String deptCode, String bankCode);
    
    public long manageBillCreditApplicationData(String dept,String bidDate);
    
    public boolean insertBillCreditApplicationData(String dept, String bidDate);
    
    public List<HBillCreditApplicationDatas> getBillCreditApplicationData(String dept, String bidDate);
    
    public boolean updateBillCreditApplicationData(HBillCreditApplicationDatas rst);
    
    public boolean createViewBill_credit_application_datas2(int str,String dept,String bidDate,Date drDt,Date IntDate);
    
    public List<CreditApplicationPrintReport> getCreditApplicationPrint();
    
    public List<BillCreditApplicationIntermediateInterestPaymentsReport> getBillCreditApplicationIntermediateInterestPayments();
    
    public List<BillApplicationFormListReport> getBillApplicationFormList(int rpt);
    
    public boolean TransferSlip(String bidDate, String deptCode);
    
    public List<TransferSlip> getTransferSlip();
    
    public List<JournalSearchResult> getJournalSearchResult(int journal_no, int slip_no);
    
    public List<BillApplicationFormListReport> getLoanAgreementDeed();
    
    public List<HBillCreditApplicationDatas> getAllBillCreditApplicationData();
    
    public List<HSuccessfulBidData3> getSuccessfulBidDocumentAll();
    
    public boolean addNewUser(HUser user);
    
    public boolean editUser(HUser user);
        
    public boolean ClearTables(String bidDate,String DepartmentCode);
}
