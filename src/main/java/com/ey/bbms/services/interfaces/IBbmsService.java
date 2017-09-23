package com.ey.bbms.services.interfaces;

import java.util.List;

import com.ey.bbms.model.main.HBase;
import com.ey.bbms.model.main.HBidData;
import com.ey.bbms.model.main.HBillCreditApplicationDatas;
import com.ey.bbms.model.main.HCommon;
import com.ey.bbms.model.main.HDivisions;
import com.ey.bbms.model.main.HFinancialInstitutionsMasters;
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

public interface IBbmsService {
    
    public HUser getUserValidation(HUser user);
    
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
    
    public List<ThereSpecification> getThereSpecification(String date);
    
    public List<NoSpecification> getNoSpecification(String date);
    
    public String NRJ_T_MAKE(int SHORI,String dept,String bidDate);
    
    public String RI_T_MAKE(int SHORI,String cmbDataUpdated,String txtBidAmountPland,String txtFootCutInterestRates,String txtMinimumAmount,String deptcode,String bidDate);
    
    public List<HSuccessfulBidSituations> getInterestRateBy(String bidDate, String dept_code);
    
    public List<HSuccessfulBidData3> getFinancialInstituteBy(String bidDate, String dept_code);
    
    public List<HSuccessfulBidData3> loadBidMaintenanceData(String bidDate, String deptCode);
    
    public boolean UpdateSucessfullBidData(HSuccessfulBidData3 obj, String deptCode);
    
    public String RAKU_PRINT(int SHORI,String deptcode, String bidDate);
    
    public List<SuccessfulBidDocument> getSuccessfulBidDocument(String bidDate, String deptCode);
    
    public List<SuccessfulBidDocumentSub> getSuccessfulBidDocumentSub(String bidDate, String deptCode, String bankCode);
  
    public String KM_PRINT(int SHORI,HCommon obj,int user_id);
    
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
