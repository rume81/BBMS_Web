package com.ey.bbms.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ey.bbms.dao.interfaces.IBbmsDAO;
import com.ey.bbms.mapper.DivisionsMapper;
import com.ey.bbms.mapper.HBaseMapper;
import com.ey.bbms.mapper.HBidDataByBankTotalsMapper;
import com.ey.bbms.mapper.HBidDataMapper;
import com.ey.bbms.mapper.HBidDataRateOrderMapper;
import com.ey.bbms.mapper.HBidSituationInterestRateCumulativeCalculationMapper;
import com.ey.bbms.mapper.HBidStatusMapper;
import com.ey.bbms.mapper.HBillCreditApplicationDatasMapper;
import com.ey.bbms.mapper.HCommonMapper;
import com.ey.bbms.mapper.HFinancialInstitutionsMastersMapper;
import com.ey.bbms.mapper.HFlgByBankProcessingMapper;
import com.ey.bbms.mapper.HProrationDataInterestRateOrderMapper;
import com.ey.bbms.mapper.HProrationdataprocessingMapper;
import com.ey.bbms.mapper.HSuccessfulBidDataMapper;
import com.ey.bbms.mapper.HSuccessfulBidSituationsMapper;
import com.ey.bbms.mapper.UserMapper;
import com.ey.bbms.mapper.rpt.HSuccessfulBidData3Mapper;
import com.ey.bbms.mapper.rpt.JournalSearchResultMapper;
import com.ey.bbms.mapper.rpt.BillApplicationFormListReportMapper;
import com.ey.bbms.mapper.rpt.BillCreditApplicationIntermediateInterestPaymentsReportMapper;
import com.ey.bbms.mapper.rpt.CreditApplicationPrintReportMapper;
import com.ey.bbms.mapper.rpt.FinancialinstituteMapper;
import com.ey.bbms.mapper.rpt.NoSpecificationMapper;
import com.ey.bbms.mapper.rpt.SuccessfulBidDocumentMapper;
import com.ey.bbms.mapper.rpt.SuccessfulBidDocumentSubMapper;
import com.ey.bbms.mapper.rpt.SuccessfulBidSituationsMapper;
import com.ey.bbms.mapper.rpt.ThereSpecificationMapper;
import com.ey.bbms.mapper.rpt.TransferSlipMapper;
import com.ey.bbms.model.main.HBase;
import com.ey.bbms.model.main.HBidData;
import com.ey.bbms.model.main.HBidDataByBankTotals;
import com.ey.bbms.model.main.HBidDataRateOrder;
import com.ey.bbms.model.main.HBidSituationInterestRateCumulativeCalculation;
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
import com.ey.bbms.services.interfaces.ISessionService;

public class BbmsDAO extends BaseDAO implements IBbmsDAO {
    private final Logger logger = LoggerFactory.getLogger(BbmsDAO.class);

    private ISessionService sessionService;

    public void setSessionService(ISessionService sessionService) {
	this.sessionService = sessionService;
    }

    public Integer getUserId() {
	Integer user_id = sessionService.getUserSession().getUser().getUser_id();
	logger.info("User Id: " + user_id);
	return user_id;
    }

    @Override
    public HUser getEmployeeByPassword(boolean isDeleted, String userName, String pass) {
	HUser profile = null;
	try {
	    profile = getJdbcService().getJdbcTemplate().queryForObject(
		    "SELECT * FROM user WHERE user_name ='" + userName + "' AND password=MD5('" + pass + "')",
		    new UserMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return profile;
    }

    @Override
    public List<HUser> getAllUser() {

	List<HUser> allUser = new ArrayList<HUser>();
	try {
	    allUser = getJdbcService().getJdbcTemplate().query("SELECT * FROM user", new Object[] {}, new UserMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return allUser;
    }

    @Override
    public List<Financialinstitute> getFinancialInstituteList(String date) {
	List<Financialinstitute> allFinancialinstitute = new ArrayList<Financialinstitute>();
	try {
	    allFinancialinstitute = getJdbcService().getJdbcTemplate()
		    .query("SELECT bid_datas2.department_code, bid_datas2.department_name, bid_datas2.business_category, name_conversions.financial_institution_name bank_name,"
			    + "bid_datas2.financial_institution_name, bid_datas2.bank_code,bid_datas2.bid_number,bid_datas2.bid_interest_rate,bid_datas2.bid_amount_money"
			    + " FROM bid_datas2 LEFT JOIN name_conversions ON bid_datas2.bank_code = name_conversions.bank_code "
			    + "WHERE bid_datas2.auction_date = '" + date + "' AND bid_datas2.user_id =" + getUserId()
			    + " order by bid_datas2.bank_code", new Object[] {}, new FinancialinstituteMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return allFinancialinstitute;
    }

    // ========================>
    @Override
    public List<HFinancialInstitutionsMasters> getHFinancialInstitutionsMasters(String bankCode) {

	List<HFinancialInstitutionsMasters> allFinancialinstitute = new ArrayList<HFinancialInstitutionsMasters>();

	try {
	    if (bankCode.equals(""))
		allFinancialinstitute = getJdbcService().getJdbcTemplate().query(
			"SELECT DISTINCT * FROM financial_institutions_masters", new Object[] {},
			new HFinancialInstitutionsMastersMapper());
	    else {
		HFinancialInstitutionsMasters inst = getJdbcService().getJdbcTemplate().queryForObject(
			"SELECT DISTINCT * FROM financial_institutions_masters WHERE bank_code=" + bankCode,
			new HFinancialInstitutionsMastersMapper());
		if (null != inst)
		    allFinancialinstitute.add(inst);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return allFinancialinstitute;
    }

    @Override
    public List<HBidData> getBidData(String bidDate, String bankcode, String deptcode) {

	List<HBidData> allBidData = new ArrayList<HBidData>();
	try {
	    allBidData = getJdbcService().getJdbcTemplate()
		    .query("SELECT DISTINCT bid_datas.* FROM bid_datas WHERE bid_datas.auction_date='" + bidDate
			    + "' AND bid_datas.department_code='" + deptcode + "' AND bid_datas.bank_code='" + bankcode
			    + "' AND bid_datas.user_id =" + getUserId()
			    + " ORDER BY bid_datas.department_code, bid_datas.bank_code, bid_datas.bid_number",
			    new Object[] {}, new HBidDataMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return allBidData;
    }

    @Override
    public List<HBidData> getBidData() {

	List<HBidData> allBidData = new ArrayList<HBidData>();
	try {
	    allBidData = getJdbcService().getJdbcTemplate().query(
		    "SELECT * FROM bid_datas WHERE bid_datas.user_id =" + getUserId(), new Object[] {},
		    new HBidDataMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return allBidData;
    }

    // ==============================>
    @Override
    public List<HDivisions> getDivision() {

	List<HDivisions> allDivision = new ArrayList<HDivisions>();
	try {
	    allDivision = getJdbcService().getJdbcTemplate().query("SELECT DISTINCT * FROM divisions", new Object[] {},
		    new DivisionsMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return allDivision;
    }

    // ==============================>
    @Override
    public HBase getBase() {
	HBase base = null;
	try {
	    base = getJdbcService().getJdbcTemplate().queryForObject("SELECT * FROM base", new HBaseMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return base;
    }

    @Override
    public Integer getRowCountOfBidDataForABank(String bidDate, String bankcode, String deptcode) {
	Integer count = 0;
	try {
	    count = getJdbcService().getJdbcTemplate()
		    .queryForInt("SELECT Count(*) from bid_datas WHERE bid_datas.bank_code='" + bankcode
			    + "' AND bid_datas.department_code='" + deptcode + "' AND bid_datas.auction_date='"
			    + bidDate + "' AND bid_datas.user_id =" + getUserId());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return count;
    }

    @Override
    public boolean deleteRowOfBidDataForABank(String bidDate, String bankcode, String deptcode) {
	boolean Fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM bid_datas WHERE bid_datas.bank_code='" + bankcode
		    + "' AND bid_datas.department_code='" + deptcode + "' AND bid_datas.auction_date='" + bidDate
		    + "' AND bid_datas.user_id =" + getUserId());
	    logger.info("HBiddata Delete Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    Fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return Fo;
    }

    @Override
    public boolean addRowOfBidDataForABank(List<HBidData> biddatas) {
	boolean Fo = false;
	try {
	    for (HBidData biddata : biddatas) {
		StringBuffer strSql = new StringBuffer(
			"INSERT INTO bid_datas (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money,auction_date,user_id) VALUES('"
				+ biddata.getDepartment_code() + "','" + biddata.getBusiness_category() + "','"
				+ biddata.getBank_code() + "','" + biddata.getFinancial_institution_name() + "',"
				+ biddata.getBid_number() + "," + biddata.getBid_interest_rate() + ","
				+ biddata.getBid_amount_money() + ",'" + biddata.getAuction_date() + "'," + getUserId()
				+ ")");
		logger.info("HBiddata Add Query - > " + strSql.toString());

		getJdbcService().getJdbcTemplate().execute(strSql.toString());
		Fo = true;
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return Fo;
    }

    @Override
    public boolean updateFlagOfBidDataForABank(String bidDate, String bankcode, String deptcode, String flg) {
	boolean Fo = false;
	try {

	    StringBuffer strSql = new StringBuffer("UPDATE bid_datas SET flag1 = '" + flg
		    + "' WHERE bid_datas.bank_code='" + bankcode + "' AND bid_datas.department_code='" + deptcode
		    + "' AND bid_datas.auction_date='" + bidDate + "' AND bid_datas.user_id =" + getUserId());
	    logger.info("HBiddata Update Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    Fo = true;

	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return Fo;
    }

    @Override
    public boolean BidInterestRateOrderClear() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE  FROM bid_data_rate_orders WHERE user_id=" + getUserId());
	    logger.info("BidInterestRateOrderClear Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean AddBidInterestRateOrder(String deptcode, String bidDate) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO bid_data_rate_orders (department_code, business_categorie, bank_code, financial_institution_name, bid_number, bid_interest_rate, bid_amount_money, user_id) "
			    + "SELECT DISTINCT bid_datas.department_code, bid_datas.business_category, bid_datas.bank_code, "
			    + "CASE WHEN (substr(bid_datas.bank_code, 1, 1 ) = '0') THEN bid_datas.financial_institution_name "
			    + "WHEN bid_datas.bank_code ='1000004' THEN '全国信用金庫連合会' "
			    + "WHEN bid_datas.bank_code ='2010100' THEN '全国信用協同組合連合会' "
			    + "WHEN bid_datas.bank_code ='2950002' THEN '全国労働金庫連合会' "
			    + "ELSE bid_datas.financial_institution_name END AS financial_institution_name_1, "
			    + "bid_datas.bid_number,bid_datas.bid_interest_rate,bid_datas.bid_amount_money,bid_datas.user_id FROM bid_datas WHERE (((bid_datas.department_code) = '"
			    + deptcode + "') AND ((bid_datas.bid_interest_rate) <> 0) AND ((bid_datas.auction_date)= '"
			    + bidDate + "') AND (bid_datas.user_id=" + getUserId()
			    + ")) ORDER BY bid_datas.bid_interest_rate");

	    logger.info("AddBidInterestRateOrder Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean BidStatusTableClear() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM bid_status WHERE user_id=" + getUserId());

	    logger.info("BidStatusTableClear Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean AddToBidStatusTable() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO bid_status (bid_interest_rate,bank_code,bid_number,department_code,business_category,financial_institution_name,bid_amount_of_money,bid_interest_rate_key,user_id) "
			    + "SELECT DISTINCT bid_data_rate_orders.bid_interest_rate, bid_data_rate_orders.bank_code, bid_data_rate_orders.bid_number, "
			    + "bid_data_rate_orders.department_code, bid_data_rate_orders.business_categorie, bid_data_rate_orders.financial_institution_name, "
			    + "bid_data_rate_orders.bid_amount_money, bid_data_rate_orders.bid_interest_rate AS Process1, bid_data_rate_orders.user_id FROM bid_data_rate_orders WHERE bid_data_rate_orders.user_id="
			    + getUserId()
			    + " ORDER BY bid_data_rate_orders.bid_interest_rate, bid_data_rate_orders.bank_code");
	    logger.info("AddToBidStatusTable Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean BidSituationInterestRateCumulativeCalculationClear() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "DELETE FROM  bid_situation_interest_rate_cumulative_calculation WHERE user_id=" + getUserId());
	    logger.info("BidSituationInterestRateCumulativeCalculationClear Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean BidSituationInterestRateCumulativeCalculation() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO bid_situation_interest_rate_cumulative_calculation (groups,bid_interest_rate,sum_of_bid_amount_of_money,user_id) "
			    + "SELECT DISTINCT bid_status.bid_interest_rate AS g, Max(bid_status.bid_interest_rate) AS BidInterestRate, Sum(bid_status.bid_amount_of_money) AS BasicPriceTotal, bid_status.user_id "
			    + "FROM bid_status WHERE bid_status.user_id=" + getUserId()
			    + " GROUP BY bid_status.bid_interest_rate  ORDER BY bid_status.bid_interest_rate");
	    logger.info("BidSituationInterestRateCumulativeCalculation Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public void UpdateBidStatusTable() {
	List<HBidSituationInterestRateCumulativeCalculation> bdirs = new ArrayList<HBidSituationInterestRateCumulativeCalculation>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT bid_situation_interest_rate_cumulative_calculation.groups, bid_situation_interest_rate_cumulative_calculation.sum_of_bid_amount_of_money FROM bid_situation_interest_rate_cumulative_calculation WHERE user_id="
			    + getUserId());
	    bdirs = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBidSituationInterestRateCumulativeCalculationMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	List<HBidStatus> bdstatus = new ArrayList<HBidStatus>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT bid_status.bid_interest_rate_key FROM bid_status WHERE user_id=" + getUserId());
	    bdstatus = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBidStatusMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	for (HBidStatus ob : bdstatus) {
	    for (HBidSituationInterestRateCumulativeCalculation ths : bdirs) {
		if (ob.getBid_interest_rate_key().equals(ths.getGroups())) {
		    try {
			StringBuffer strSql = new StringBuffer(
				"UPDATE bid_status SET interest_rate_by_bid_amount_of_money ="
					+ ths.getSum_of_bid_amount_of_money()
					+ " WHERE bid_status.bid_interest_rate_key='" + ths.getGroups()
					+ "' AND bid_status.user_id=" + getUserId());
			logger.info("UpdateToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());

		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		    break;
		}
	    }
	}
    }

    @Override
    public List<HBidStatus> getBidStatus() {
	List<HBidStatus> bdstatus = new ArrayList<HBidStatus>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM bid_status WHERE user_id=" + getUserId());
	    bdstatus = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBidStatusMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return bdstatus;
    }

    @Override
    public boolean UpdateBidStatus2(HBidStatus obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE bid_status SET " + "total_amount_of_money ="
		    + obj.getTotal_amount_of_money() + ", cumulative_interest =" + obj.getCumulative_interest()
		    + ", average_rate =" + obj.getAverage_rate() + ", sno =" + obj.getSno() + ", bid_interest_rate2 ="
		    + obj.getBid_interest_rate2() + ", interest_rate_by_bid_amount_of_money ="
		    + obj.getInterest_rate_by_bid_amount_of_money() + " WHERE bid_status.bid_interest_rate_key='"
		    + obj.getBid_interest_rate_key() + "' AND bid_status.bank_code='" + obj.getBank_code()
		    + "' AND bid_status.user_id=" + getUserId());
	    logger.info("AddToBidStatusTable Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public HCommon getLargestTotalFromBidStatus() {
	HCommon obj = new HCommon();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT Sum(bid_status.interest_rate_by_bid_amount_of_money) AS InterestRateByBasicPriceTotal, Max(bid_status.total_amount_of_money) AS TotalAmountOflargest FROM bid_status WHERE user_id="
			    + getUserId());
	    obj = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), new HCommonMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return obj;
    }

    @Override
    public List<ThereSpecification> getThereSpecification(String date) {

	List<ThereSpecification> thereSpecification = new ArrayList<ThereSpecification>();
	try {
	    thereSpecification = getJdbcService().getJdbcTemplate()
		    .query("SELECT bid_status.SNO, bid_status.department_code,bid_status.bid_interest_rate,bid_status.bid_interest_rate2,bid_status.interest_rate_by_bid_amount_of_money,bid_status.average_rate,bid_status.total_amount_of_money,bid_status.cumulative_interest,bid_status.business_category,bid_status.bank_code,bid_status.financial_institution_name,name_conversions.financial_institution_name AS bank_name,bid_status.bid_number,bid_status.bid_amount_of_money,bid_status.bid_interest_rate_key FROM bid_status LEFT JOIN name_conversions ON bid_status.bank_code = name_conversions.bank_code WHERE user_id="
			    + getUserId(), new Object[] {}, new ThereSpecificationMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return thereSpecification;
    }

    @Override
    public List<NoSpecification> getNoSpecification(String date) {

	List<NoSpecification> noSpecification = new ArrayList<NoSpecification>();
	try {
	    noSpecification = getJdbcService().getJdbcTemplate()
		    .query("SELECT DISTINCT bid_status.bid_interest_rate,Max(bid_status.interest_rate_by_bid_amount_of_money) AS interest_rate_by_bid_amount_of_money,Max(bid_status.average_rate) AS average_interest_rate,Max(bid_status.total_amount_of_money) AS total_amount_of_money FROM bid_status WHERE bid_status.user_id="
			    + getUserId()
			    + " GROUP BY bid_status. bid_interest_rate HAVING bid_status.bid_interest_rate IS NOT NULL",
			    new Object[] {}, new NoSpecificationMapper());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return noSpecification;
    }

    @Override
    public boolean DropViewIfExist(String viewName) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DROP VIEW IF EXISTS " + viewName + "");
	    logger.info("DropView Query " + viewName + " - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean DataClear(String tableName) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("DELETE FROM " + tableName + " WHERE user_id=" + getUserId());
	    logger.info(
		    "DataClear Query for " + tableName + " WHERE user_id=" + getUserId() + " - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean getForMinimumAmountDetermined() {
	boolean fo = DropViewIfExist("ForMinimumAmountDetermined_" + getUserId());
	if (fo) {
	    fo = false;
	    float minAmount = 0;
	    try {
		StringBuffer strSql = new StringBuffer("CREATE VIEW ForMinimumAmountDetermined_" + getUserId()
			+ " AS SELECT DISTINCT bid_datas.bank_code, Sum(bid_datas.bid_amount_money) AS BasicPriceTotal FROM bid_datas WHERE bid_datas.user_id="
			+ getUserId() + " GROUP BY bid_datas.bank_code HAVING Sum(bid_datas.bid_amount_money)>="
			+ minAmount);
		logger.info("getForMinimumAmountDetermined_" + getUserId() + " Query - > " + strSql.toString());
		getJdbcService().getJdbcTemplate().execute(strSql.toString());
		fo = true;
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
	return fo;
    }

    @Override
    public boolean AddBidInterestRateOrder(double GENDO_RI, String deptcode, String bidDate) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO bid_data_rate_orders (department_code, business_categorie, bank_code, financial_institution_name, bid_number, bid_interest_rate, bid_amount_money, user_id) "
			    + "SELECT DISTINCT bid_datas.department_code, bid_datas.business_category, bid_datas.bank_code, "
			    + "bid_datas.financial_institution_name, bid_datas.bid_number, bid_datas.bid_interest_rate, "
			    + "bid_datas.bid_amount_money,bid_datas.user_id FROM bid_datas INNER JOIN ForMinimumAmountDetermined_"
			    + getUserId() + " ON bid_datas.bank_code = ForMinimumAmountDetermined_" + getUserId()
			    + ".bank_code " + "WHERE bid_datas.auction_date='" + bidDate
			    + "' AND bid_datas.department_code='" + deptcode + "' AND bid_datas.bid_interest_rate<="
			    + GENDO_RI / 100 + " AND bid_datas.flag1='1' AND bid_datas.user_id=" + getUserId()
			    + " ORDER BY bid_datas.bid_interest_rate");

	    logger.info("AddBidInterestRateOrder Query - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HBidDataRateOrder> getBidDataRateOrder() {
	List<HBidDataRateOrder> bdirs = new ArrayList<HBidDataRateOrder>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM bid_data_rate_orders WHERE user_id=" + getUserId());
	    bdirs = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBidDataRateOrderMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return bdirs;
    }

    @Override
    public boolean UpdateBidInterestRateOrder(HBidDataRateOrder obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE bid_data_rate_orders SET total_amount_money ="
		    + obj.getTotal_amount_money() + " WHERE department_code ='" + obj.getDepartment_code()
		    + "' AND bank_code = '" + obj.getBank_code() + "' AND bid_number =" + obj.getBid_number()
		    + " AND user_id=" + getUserId());
	    logger.info("UpdateBidInterestRateOrder Query for  - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean addProrationData(HBidDataRateOrder obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("INSERT into proration_datas VALUES('" + obj.getDepartment_code()
		    + "','" + obj.getBusiness_category() + "','" + obj.getBank_code() + "','"
		    + obj.getFinancial_institution_name() + "'," + obj.getBid_number() + ","
		    + obj.getBid_interest_rate() + "," + obj.getBid_amount_money() + "," + obj.getTotal_amount_money()
		    + "," + getUserId() + ")");

	    logger.info("addProrationData Query for  - > " + strSql.toString());
	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public List<HProrationDataInterestRateOrder> getProrationDataInterestRateOrder() {
	List<HProrationDataInterestRateOrder> bdirs = new ArrayList<HProrationDataInterestRateOrder>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT * FROM ProrationDataInterestRateOrder WHERE user_id=" + getUserId());
	    logger.info("getProrationDataInterestRateOrder Query for  - > " + strSql.toString());
	    bdirs = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HProrationDataInterestRateOrderMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return bdirs;
    }

    @Override
    public List<HBidDataRateOrder> getBidDataInterestRateOrderLastTotalAmount() {
	List<HBidDataRateOrder> bdirs = new ArrayList<HBidDataRateOrder>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT * FROM BidDataInterestRateOrderLastTotalAmount WHERE user_id=" + getUserId());
	    logger.info("getBidDataInterestRateOrderLastTotalAmount Query for  - > " + strSql.toString());
	    bdirs = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBidDataRateOrderMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return bdirs;
    }

    /*
     * @Override public boolean deleteProrationData() { boolean fo = false;
     * List<HCommon> rowNum = new ArrayList<HCommon>(); try { StringBuffer
     * strSql = new StringBuffer("SELECT * FROM ProrationDataDupulicated");
     * rowNum = getJdbcService().getJdbcTemplate().query(strSql.toString(), new
     * HCommonMapper());
     * 
     * for (HCommon datas : rowNum) { List<String> rd = datas.getDatas(); String
     * row = rd.get(0); StringBuffer delSql = new StringBuffer(
     * "DELETE FROM proration_datas WHERE rowid=" + row);
     * 
     * logger.info("deleteProrationData Delete Query - > " + strSql.toString());
     * 
     * getJdbcService().getJdbcTemplate().execute(strSql.toString());
     * 
     * fo = true; }
     * 
     * } catch (Exception e) { e.printStackTrace(); }
     * 
     * return fo; }
     */

    @Override
    public boolean deleteProrationData() {
	boolean ff = true;
	List<HBidDataRateOrder> withduplicat = new ArrayList<HBidDataRateOrder>();
	List<HBidDataRateOrder> noduplicat = new ArrayList<HBidDataRateOrder>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM proration_datas WHERE user_id=" + getUserId());
	    withduplicat = getJdbcService().getJdbcTemplate().query(strSql.toString(), new HBidDataRateOrderMapper());

	    for (HBidDataRateOrder datas : withduplicat) {
		boolean fo = false;

		for (HBidDataRateOrder freshdatas : noduplicat) {
		    if (freshdatas.getDepartment_code().equals(datas.getDepartment_code())
			    && freshdatas.getBank_code().equals(datas.getBank_code())
			    && freshdatas.getBid_number() == datas.getBid_number()) {
			fo = true;
			break;
		    }
		}
		if (!fo) {
		    noduplicat.add(datas);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (noduplicat.size() > 0) {
	    try {
		StringBuffer delSql = new StringBuffer("DELETE FROM proration_datas WHERE user_id=" + getUserId());

		logger.info("deleteProrationData Delete Query - > " + delSql.toString());

		getJdbcService().getJdbcTemplate().execute(delSql.toString());

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	for (HBidDataRateOrder freshdatas : noduplicat) {
	    addProrationData(freshdatas);
	}

	return ff;
    }

    @Override
    public double getTotalAmount() {
	List<HCommon> datas = new ArrayList<HCommon>();
	double totalAmount = 0;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT * FROM TotalAmount WHERE user_id=" + getUserId() + " order by total_amount_money desc");
	    datas = getJdbcService().getJdbcTemplate().query(strSql.toString(), new HCommonMapper());
	    if (null != datas.get(0).getDatas().get(0)) {
		totalAmount = Double.parseDouble(datas.get(0).getDatas().get(0));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return totalAmount;
    }

    @Override
    public double getProratedBidPriceTotal() {
	double BasicPriceTotal = 0;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT sum_of_bid_amount FROM ProratedTotal WHERE user_id=" + getUserId());
	    BasicPriceTotal = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), Double.class);

	} catch (Exception e) {
	    // e.printStackTrace();
	}

	return BasicPriceTotal;
    }

    @Override
    public List<HBidDataRateOrder> getProrationData(int sq) {
	List<HBidDataRateOrder> bdirs = new ArrayList<HBidDataRateOrder>();
	try {
	    StringBuffer strSql = new StringBuffer("");
	    if (sq == 1)
		strSql = new StringBuffer("SELECT * FROM proration_datas WHERE user_id=" + getUserId());
	    else if (sq == 2)
		strSql = new StringBuffer("SELECT * FROM proration_datas WHERE user_id=" + getUserId()
			+ " order by total_amount_of_money,bid_amount_of_money,bank_code");

	    logger.info("getProrationData Query - > " + strSql.toString());
	    bdirs = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBidDataRateOrderMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return bdirs;
    }

    @Override
    public void UpdateProrationData(HBidDataRateOrder obj) {
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE proration_datas SET total_amount_of_money ="
		    + obj.getTotal_amount_money() + " WHERE department_code ='" + obj.getDepartment_code()
		    + "' AND bank_code = '" + obj.getBank_code() + "' AND bid_number =" + obj.getBid_number()
		    + " AND user_id=" + getUserId());
	    logger.info("UpdateProrationData Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public HProrationdataprocessing getProrationDataProcessing1() {
	HProrationdataprocessing pro = new HProrationdataprocessing();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT * FROM ProrationDataProcessing1 WHERE user_id=" + getUserId());
	    logger.info("getProrationDataProcessing1 Query - > " + strSql.toString());
	    pro = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(),
		    new HProrationdataprocessingMapper());
	} catch (Exception e) {
	    // e.printStackTrace();
	}
	return pro;
    }

    @Override
    public HProrationdataprocessing getProrationDataProcessing2() {
	HProrationdataprocessing pro = new HProrationdataprocessing();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT * FROM ProrationDataProcessing2 WHERE user_id=" + getUserId());
	    logger.info("getProrationDataProcessing1 Query - > " + strSql.toString());
	    pro = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(),
		    new HProrationdataprocessingMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return pro;
    }

    @Override
    public boolean InsertBidDataByBankTotals() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO bid_data_by_bank_totals(department_code, bank_code, financial_institution_name, sum_of_bid_amount_money, user_id) SELECT department_code, bank_code, financial_institution_name, Sum(bid_amount_money) AS sum_of_bid_amount_money, user_id FROM bid_data_rate_orders WHERE user_id="
			    + getUserId()
			    + " GROUP BY department_code, bank_code, financial_institution_name, user_id");
	    logger.info("InsertBidDataByBankTotals Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    /*
     * @Override public boolean UpdateProrationDatas() { boolean fo = false; try
     * { StringBuffer strSql = new StringBuffer(
     * "UPDATE proration_datas SET bid_amount_of_money = (Select bid_data_by_bank_totals.sum_of_bid_amount_money from bid_data_by_bank_totals INNER JOIN proration_datas ON (bid_data_by_bank_totals.bank_code = proration_datas.bank_code) AND (bid_data_by_bank_totals.department_code = proration_datas.department_code))"
     * ); logger.info("UpdateProrationDatas Query - > " + strSql.toString());
     * 
     * getJdbcService().getJdbcTemplate().execute(strSql.toString()); fo = true;
     * } catch (Exception e) { e.printStackTrace(); } return fo; }
     */

    @Override
    public boolean UpdateProrationDatas() {
	boolean fo = false;

	List<HBidDataByBankTotals> bankTotal = new ArrayList<HBidDataByBankTotals>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "Select bid_data_by_bank_totals.sum_of_bid_amount_money, bid_data_by_bank_totals.bank_code, bid_data_by_bank_totals.department_code from bid_data_by_bank_totals INNER JOIN proration_datas ON (bid_data_by_bank_totals.bank_code = proration_datas.bank_code) AND (bid_data_by_bank_totals.department_code = proration_datas.department_code) AND (bid_data_by_bank_totals.user_id = proration_datas.user_id) WHERE bid_data_by_bank_totals.user_id="
			    + getUserId());
	    logger.info("UpdateProrationDatas Query - > " + strSql.toString());
	    bankTotal = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBidDataByBankTotalsMapper());
	} catch (Exception e) {
	    // e.printStackTrace();
	}

	try {
	    for (HBidDataByBankTotals ubt : bankTotal) {
		StringBuffer strSql = new StringBuffer("UPDATE proration_datas SET bid_amount_of_money ="
			+ ubt.getSum_of_bid_amount_money() + " WHERE proration_datas.bank_code='" + ubt.getBank_code()
			+ "' AND proration_datas.department_code='" + ubt.getDepartment_code()
			+ "' AND proration_datas.user_id=" + getUserId());
		logger.info("UpdateProrationDatas Query - > " + strSql.toString());

		getJdbcService().getJdbcTemplate().execute(strSql.toString());
		fo = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public double getProratedTotal() {
	double BasicPriceTotal = 0;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT sum_of_total_amount FROM ProratedTotal WHERE user_id=" + getUserId());
	    logger.info("getProratedTotal Query - > " + strSql.toString());

	    BasicPriceTotal = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(), Double.class);
	} catch (Exception e) {
	    // e.printStackTrace();
	}

	return BasicPriceTotal;
    }

    @Override
    public boolean UpdateProrationDataPrice(HBidDataRateOrder obj) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE proration_datas SET bid_amount_of_money ="
		    + obj.getBid_amount_money() + " WHERE department_code ='" + obj.getDepartment_code()
		    + "' AND bank_code = '" + obj.getBank_code() + "' AND bid_number =" + obj.getBid_number()
		    + " AND user_id=" + getUserId());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean InsertSuccessfulBidDatas(int sq, String bidDate) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("");
	    if (sq == 1) {
		strSql = new StringBuffer(
			"INSERT INTO successful_bid_datas (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, total_amount_money, auction_date, user_id) "
				+ "SELECT DISTINCT bid_data_rate_orders.department_code, bid_data_rate_orders.business_categorie, bid_data_rate_orders.bank_code, "
				+ "bid_data_rate_orders.financial_institution_name, bid_data_rate_orders.bid_number, bid_data_rate_orders.bid_interest_rate, bid_data_rate_orders.bid_amount_money, "
				+ "bid_data_rate_orders.total_amount_money, '" + bidDate
				+ "' AS Process1, bid_data_rate_orders.user_id FROM bid_data_rate_orders "
				+ " WHERE bid_data_rate_orders.total_amount_money>0 AND bid_data_rate_orders.user_id="
				+ getUserId());
	    } else if (sq == 2) {
		strSql = new StringBuffer(
			"INSERT INTO successful_bid_datas (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, total_amount_money, auction_date, user_id) "
				+ "SELECT DISTINCT proration_datas.department_code, proration_datas.business_category, proration_datas.bank_code, proration_datas.financial_institution_name, proration_datas.bid_number, "
				+ "proration_datas.bid_interest_rate, proration_datas.bid_amount_of_money, proration_datas.total_amount_of_money, '"
				+ bidDate + "' AS Process1, proration_datas.user_id FROM proration_datas "
				+ " WHERE proration_datas.user_id=" + getUserId());
	    }
	    logger.info("InsertSuccessfulBidDatas Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean DeleteSuccessfulBidDatas3(String dept, String bidDate) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "DELETE FROM successful_bid_datas_3 WHERE successful_bid_datas_3.auction_date='" + bidDate
			    + "' and successful_bid_datas_3.department_code='" + dept
			    + "' AND successful_bid_datas_3.user_id=" + getUserId());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HSuccessfulBidData> SuccessfulBidData2(String bidDate) {
	List<HSuccessfulBidData> SuccessfulBidDatas = new ArrayList<HSuccessfulBidData>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT bid_datas.department_code, bid_datas.business_category,bid_datas.bank_code,bid_datas.financial_institution_name,bid_datas.bid_number,bid_datas.bid_interest_rate,bid_datas.bid_amount_money, successful_bid_datas.bid_interest_rate AS successful_bid_interest_rate,successful_bid_datas.bid_amount_money AS successful_bid_amount_money,successful_bid_datas.auction_date FROM successful_bid_datas LEFT JOIN bid_datas ON (successful_bid_datas.bank_code = bid_datas.bank_code) AND (successful_bid_datas.bid_number = bid_datas.bid_number) AND (successful_bid_datas.user_id = bid_datas.user_id) WHERE bid_datas.auction_date='"
			    + bidDate + "' AND bid_datas.user_id=" + getUserId()
			    + " ORDER BY bid_datas.department_code,bid_datas.bank_code,bid_datas.bid_number");

	    logger.info("SuccessfulBidData2 Query - > " + strSql.toString());
	    SuccessfulBidDatas = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HSuccessfulBidDataMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	List<HBidData> rst = new ArrayList<HBidData>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT bid_datas.department_code, bid_datas.business_category,bid_datas.bank_code,bid_datas.financial_institution_name,bid_datas.bid_number, "
			    + "bid_datas.bid_interest_rate,bid_datas.bid_amount_money FROM bid_datas WHERE bid_datas.auction_date = '"
			    + bidDate + "' AND bid_datas.user_id=" + getUserId());
	    logger.info("SuccessfulBidData2 Query - > " + strSql.toString());
	    rst = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new HBidDataMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	for (HBidData rst1 : rst) {
	    HSuccessfulBidData obj = new HSuccessfulBidData();
	    String deptCode = rst1.getDepartment_code();
	    String bankCode = rst1.getBank_code();
	    int bidNum = rst1.getBid_number();
	    obj.setDepartment_code(deptCode);
	    obj.setBusiness_category(rst1.getBusiness_category());
	    obj.setBank_code(bankCode);
	    obj.setFinancial_institution_name(rst1.getFinancial_institution_name());
	    obj.setBid_number(bidNum);
	    obj.setBid_interest_rate(rst1.getBid_interest_rate());
	    obj.setBid_amount_money(rst1.getBid_amount_money());

	    boolean Fo = false;
	    for (HSuccessfulBidData bid : SuccessfulBidDatas) {
		if (bid.getDepartment_code().equals(obj.getDepartment_code())
			&& bid.getBank_code().equals(obj.getBank_code())
			&& bid.getBid_number() == obj.getBid_number()) {
		    Fo = true;
		    break;
		}
	    }
	    if (!Fo)
		SuccessfulBidDatas.add(obj);
	}

	return SuccessfulBidDatas;
    }

    @Override
    public void writeSuccessfulBidData2(List<HSuccessfulBidData> SuccessfulBidDatas) {

	DataClear("successful_bid_datas_2");

	try {
	    for (HSuccessfulBidData rst2 : SuccessfulBidDatas) {
		StringBuffer strSql = new StringBuffer("");
		if (rst2.getAuction_date() != null) {
		    strSql = new StringBuffer(
			    "INSERT INTO successful_bid_datas_2 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, successful_bid_interest_rate, successful_bid_price, auction_date, user_id) VALUES('"
				    + rst2.getDepartment_code() + "','" + rst2.getBusiness_category() + "','"
				    + rst2.getBank_code() + "','" + rst2.getFinancial_institution_name() + "',"
				    + rst2.getBid_number() + "," + rst2.getBid_interest_rate() + ","
				    + rst2.getBid_amount_money() + "," + rst2.getSuccessful_bid_interest_rate() + ","
				    + rst2.getSuccessful_bid_amount_money() + ",'" + rst2.getAuction_date() + "',"
				    + getUserId() + ")");
		} else {
		    strSql = new StringBuffer(
			    "INSERT INTO successful_bid_datas_2 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, successful_bid_interest_rate, successful_bid_price, user_id) VALUES('"
				    + rst2.getDepartment_code() + "','" + rst2.getBusiness_category() + "','"
				    + rst2.getBank_code() + "','" + rst2.getFinancial_institution_name() + "',"
				    + rst2.getBid_number() + "," + rst2.getBid_interest_rate() + ","
				    + rst2.getBid_amount_money() + "," + rst2.getSuccessful_bid_interest_rate() + ","
				    + rst2.getSuccessful_bid_amount_money() + "," + getUserId() + ")");
		}

		getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Override
    public List<HSuccessfulBidData> SuccessfulBidData3() {
	List<HSuccessfulBidData> SuccessfulBidDatas = new ArrayList<HSuccessfulBidData>();

	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM successful_bid_datas_2 WHERE user_id=" + getUserId()
		    + " ORDER BY department_code,bank_code,bid_number");

	    logger.info("SuccessfulBidData2 Query - > " + strSql.toString());
	    SuccessfulBidDatas = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HSuccessfulBidDataMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return SuccessfulBidDatas;
    }

    @Override
    public void insertSuccessfulBidData3(HSuccessfulBidData3 hsbd2, int sq) {
	try {
	    StringBuffer strSql = new StringBuffer("");

	    if (sq == 1) {
		strSql = new StringBuffer(
			"INSERT INTO successful_bid_datas_3 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_amount_money, successful_bid_interest_rate,successful_bid_price,auction_date,user_id) VALUES('"
				+ hsbd2.getDepartment_code() + "','" + hsbd2.getBusiness_category() + "','"
				+ hsbd2.getBank_code() + "','" + hsbd2.getFinancial_institution_name() + "',"
				+ hsbd2.getBid_number() + "," + hsbd2.getBid_amount_money() + ","
				+ hsbd2.getSuccessful_bid_interest_rate() + "," + hsbd2.getSuccessful_bid_price() + ",'"
				+ hsbd2.getAuction_date() + "'," + getUserId() + ")");
	    } else if (sq == 2) {
		strSql = new StringBuffer(
			"INSERT INTO successful_bid_datas_3 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, successful_bid_interest_rate,successful_bid_price,auction_date,user_id) VALUES('"
				+ hsbd2.getDepartment_code() + "','" + hsbd2.getBusiness_category() + "','"
				+ hsbd2.getBank_code() + "','" + hsbd2.getFinancial_institution_name() + "',"
				+ hsbd2.getBid_number() + "," + hsbd2.getBid_interest_rate() + ","
				+ hsbd2.getBid_amount_money() + "," + hsbd2.getSuccessful_bid_interest_rate() + ","
				+ hsbd2.getSuccessful_bid_price() + ",'" + hsbd2.getAuction_date() + "'," + getUserId()
				+ ")");
	    } else if (sq == 3) {
		strSql = new StringBuffer(
			"INSERT INTO successful_bid_datas_3 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_amount_money, successful_bid_interest_rate,successful_bid_price,auction_date,user_id) VALUES('"
				+ hsbd2.getDepartment_code() + "','" + hsbd2.getBusiness_category() + "','"
				+ hsbd2.getBank_code() + "','" + hsbd2.getFinancial_institution_name() + "',"
				+ hsbd2.getBid_number() + "," + hsbd2.getBid_amount_money() + ","
				+ hsbd2.getSuccessful_bid_interest_rate() + "," + hsbd2.getSuccessful_bid_price() + ",'"
				+ hsbd2.getAuction_date() + "'," + getUserId() + ")");
	    } else if (sq == 4) {
		strSql = new StringBuffer(
			"INSERT INTO successful_bid_datas_3 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_amount_money, successful_bid_interest_rate,successful_bid_price,auction_date,user_id) VALUES('"
				+ hsbd2.getDepartment_code() + "','" + hsbd2.getBusiness_category() + "','"
				+ hsbd2.getBank_code() + "','" + hsbd2.getFinancial_institution_name() + "',"
				+ hsbd2.getBid_number() + "," + hsbd2.getBid_amount_money() + ","
				+ hsbd2.getSuccessful_bid_interest_rate() + "," + hsbd2.getSuccessful_bid_price() + ",'"
				+ hsbd2.getAuction_date() + "'," + getUserId() + ")");
	    }

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Override
    public List<HFlgByBankProcessing> getFlgByBankProcessing() {
	List<HFlgByBankProcessing> flgByBankProcessing = new ArrayList<HFlgByBankProcessing>();

	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM flg_ByBankProcessing WHERE user_id=" + getUserId());

	    logger.info("getFlgByBankProcessing Query - > " + strSql.toString());
	    flgByBankProcessing = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HFlgByBankProcessingMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return flgByBankProcessing;
    }

    @Override
    public boolean Updateflg_ByBankProcessing(HBidDataRateOrder obj, int flag) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE successful_bid_datas_3 SET financial_institutions_by_flag ="
		    + flag + " WHERE department_code ='" + obj.getDepartment_code() + "' AND bank_code = '"
		    + obj.getBank_code() + "' AND bid_number =" + obj.getBid_number() + " AND user_id=" + getUserId());
	    logger.info("Updateflg_ByBankProcessing Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean AddSuccessfulBidStatusTable(String dept, String bidDate) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO successful_bid_situations(department_code, business_category, bid_interest_rate, bid_amount_of_money, bank_code, financial_institution_name, bid_number,user_id) "
			    + "SELECT DISTINCT successful_bid_datas_3.department_code, successful_bid_datas_3.business_category, successful_bid_datas_3.successful_bid_interest_rate, "
			    + "successful_bid_datas_3.successful_bid_price, successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name, successful_bid_datas_3.bid_number, successful_bid_datas_3.user_id "
			    + "FROM successful_bid_datas_3 WHERE successful_bid_datas_3.auction_date='" + bidDate
			    + "' AND successful_bid_datas_3.department_code='" + dept
			    + "' AND successful_bid_datas_3.user_id=" + getUserId()
			    + " AND successful_bid_datas_3.successful_bid_interest_rate<>0 AND successful_bid_datas_3.bid_number<98 "
			    + "ORDER BY successful_bid_datas_3.successful_bid_interest_rate,successful_bid_datas_3.bank_code");
	    logger.info("AddSuccessfulBidStatusTable Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean MakeSuccessfulBidSituationInterestRateCumulativeCalculation() {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO successful_bid_situation_interests (bid_interest_rate,sum_of_bid_amount_money,user_id) "
			    + "SELECT DISTINCT successful_bid_situations.bid_interest_rate, Sum(successful_bid_situations.bid_amount_of_money)  AS BasicPriceTotal, successful_bid_situations.user_id"
			    + " FROM successful_bid_situations WHERE successful_bid_situations.user_id=" + getUserId()
			    + " GROUP BY successful_bid_situations.bid_interest_rate");
	    logger.info("MakeSuccessfulBidSituationInterestRateCumulativeCalculation Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HSuccessfulBidSituations> getSuccessfulBidSituations() {
	List<HSuccessfulBidSituations> successfulBidSituations = new ArrayList<HSuccessfulBidSituations>();

	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT * FROM successful_bid_situations LEFT JOIN successful_bid_situation_interests ON "
			    + "successful_bid_situations.bid_interest_rate = successful_bid_situation_interests.bid_interest_rate AND successful_bid_situations.user_id = successful_bid_situation_interests.user_id "
			    + " WHERE successful_bid_situations.user_id =" + getUserId());

	    logger.info("getSuccessfulBidSituations Query - > " + strSql.toString());
	    successfulBidSituations = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HSuccessfulBidSituationsMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return successfulBidSituations;
    }

    @Override
    public boolean InsertSuccessfulBidStatusTable(HSuccessfulBidSituations hsrst) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("INSERT INTO successful_bid_situations("
		    + "sno,department_code,bid_interest_rate,bid_interest_rate2,interest_rate_by_bid_amount_of_money,average_rate,total_amount_of_money,cumulative_interest,business_category,bank_code,financial_institution_name,bid_number,bid_amount_of_money,user_id) VALUES("
		    + hsrst.getSno() + ",'" + hsrst.getDepartment_code() + "'," + hsrst.getBid_interest_rate() + ","
		    + hsrst.getBid_interest_rate2() + "," + hsrst.getInterest_rate_by_bid_amount_of_money() + ","
		    + hsrst.getAverage_rate() + "," + hsrst.getTotal_amount_of_money() + ","
		    + hsrst.getCumulative_interest() + ",'" + hsrst.getBusiness_category() + "','"
		    + hsrst.getBank_code() + "','" + hsrst.getFinancial_institution_name() + "',"
		    + hsrst.getBid_number() + "," + hsrst.getBid_amount_of_money() + "," + getUserId() + ")");

	    logger.info("InsertSuccessfulBidStatusTable====>> Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public List<HSuccessfulBidSituations> getInterestRateBy(String bidDate, String dept_code) {
	List<HSuccessfulBidSituations> dataBeanList = new ArrayList<HSuccessfulBidSituations>();

	try {

	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT  successful_bid_situations.department_code,divisions.department_name,successful_bid_situations.bid_interest_rate,successful_bid_situations.bid_interest_rate2, "
			    + "successful_bid_situations.interest_rate_by_bid_amount_of_money, successful_bid_situations.average_rate,successful_bid_situations.total_amount_of_money,successful_bid_situations.cumulative_interest,successful_bid_situations.business_category, "
			    + "successful_bid_situations.bank_code,successful_bid_situations.financial_institution_name, successful_bid_situations.bid_number, successful_bid_situations.bid_amount_of_money "
			    + "FROM successful_bid_situations LEFT JOIN divisions ON successful_bid_situations.department_code = divisions.department_code "
			    + "WHERE successful_bid_situations.department_code = '" + dept_code
			    + "' AND successful_bid_situations.user_id=" + getUserId()
			    + " ORDER BY successful_bid_situations.bid_interest_rate,successful_bid_situations.bank_code");

	    logger.info("getInterestRateBy Query - > " + strSql.toString());
	    dataBeanList = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new SuccessfulBidSituationsMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return dataBeanList;
    }

    @Override
    public List<HSuccessfulBidData3> getFinancialInstituteBy(String bidDate, String dept_code) {
	List<HSuccessfulBidData3> dataBeanList = new ArrayList<HSuccessfulBidData3>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT successful_bid_datas_3.department_code, divisions.department_name, successful_bid_datas_3.business_category,"
			    + "successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name, successful_bid_datas_3.bid_number,"
			    + "successful_bid_datas_3.bid_interest_rate, successful_bid_datas_3.bid_amount_money, successful_bid_datas_3.successful_bid_interest_rate,"
			    + "successful_bid_datas_3.successful_bid_price FROM successful_bid_datas_3 LEFT JOIN divisions ON successful_bid_datas_3.department_code = divisions.department_code"
			    + " WHERE (((successful_bid_datas_3.department_code) = '" + dept_code
			    + "') AND ((successful_bid_datas_3.auction_date) = '" + bidDate + "') "
			    + "AND ((successful_bid_datas_3.user_id) = " + getUserId() + "))");

	    logger.info("getFinancialInstituteBy Query - > " + strSql.toString());
	    dataBeanList = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HSuccessfulBidData3Mapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return dataBeanList;
    }

    @Override
    public List<HSuccessfulBidData3> loadBidMaintenanceData(String bidDate, String deptCode) {

	List<HSuccessfulBidData3> hsbd3 = new ArrayList<HSuccessfulBidData3>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT successful_bid_datas_3.department_code, successful_bid_datas_3.business_category, successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name, successful_bid_datas_3.bid_number, successful_bid_datas_3.bid_interest_rate, successful_bid_datas_3.bid_amount_money, successful_bid_datas_3.successful_bid_interest_rate, successful_bid_datas_3.successful_bid_price FROM successful_bid_datas_3 WHERE (successful_bid_datas_3.department_code = '"
			    + deptCode + "') AND (successful_bid_datas_3.auction_date = '" + bidDate
			    + "') AND (successful_bid_datas_3.user_id =" + getUserId()
			    + ") ORDER BY successful_bid_datas_3.department_code, successful_bid_datas_3.bank_code, successful_bid_datas_3.bid_number");
	    logger.info("loadBidMaintenanceData Query - > " + strSql.toString());
	    hsbd3 = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HSuccessfulBidData3Mapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return hsbd3;
    }

    @Override
    public boolean UpdateSucessfullBidData(HSuccessfulBidData3 obj, String deptCode) {
	boolean fo = false;
	try {

	    StringBuffer strSql = new StringBuffer("UPDATE successful_bid_datas_3 SET ");
	    if (!obj.getDepartment_code().equals(""))
		strSql.append("department_code ='" + obj.getDepartment_code() + "',");
	    if (!obj.getBusiness_category().equals(""))
		strSql.append("business_category ='" + obj.getBusiness_category() + "',");
	    if (!obj.getBank_code().equals(""))
		strSql.append("bank_code ='" + obj.getBank_code() + "',");
	    if (!obj.getFinancial_institution_name().equals(""))
		strSql.append("financial_institution_name ='" + obj.getFinancial_institution_name() + "',");
	    if (obj.getBid_number() > 0)
		strSql.append("bid_number =" + obj.getBid_number() + ",");

	    strSql.append("bid_interest_rate =" + obj.getBid_interest_rate() + ",bid_amount_money ="
		    + obj.getBid_amount_money() + ",successful_bid_interest_rate ="
		    + obj.getSuccessful_bid_interest_rate() + ",successful_bid_price =" + obj.getSuccessful_bid_price()
		    + " WHERE department_code ='" + deptCode + "' AND bank_code = '" + obj.getBank_code()
		    + "' AND bid_number =" + obj.getBid_number() + " AND user_id=" + getUserId());

	    logger.info("UpdateSucessfullBidData====>> Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fo;
    }

    @Override
    public boolean UpdateSuccessfulBidData3(String bidDate) {
	boolean fo = false;
	List<HSuccessfulBidData3> hsbd3 = new ArrayList<HSuccessfulBidData3>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM successful_bid_datas_3 WHERE auction_date='" + bidDate
		    + "' AND user_id=" + getUserId());
	    logger.info("UpdateSuccessfulBidData3 Select Query - > " + strSql.toString());
	    hsbd3 = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HSuccessfulBidData3Mapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	try {
	    for (HSuccessfulBidData3 rs : hsbd3) {
		if ('\0' == (rs.getBid_amount_money())) {
		    String department = rs.getDepartment_code();
		    String bank = rs.getBank_code();
		    int bid = rs.getBid_number();

		    StringBuffer strSql = new StringBuffer("UPDATE successful_bid_datas_3 SET "
			    + "successful_bid_interest_rate = 0" + " WHERE department_code ='" + department
			    + "' AND bank_code = '" + bank + "' AND bid_number =" + bid + " AND auction_date='"
			    + bidDate + "' AND user_id=" + getUserId());

		    logger.info("UpdateSuccessfulBidData3 Update Query - > " + strSql.toString());

		    getJdbcService().getJdbcTemplate().execute(strSql.toString());
		    fo = true;
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public List<SuccessfulBidDocument> getSuccessfulBidDocument(String bidDate, String deptCode) {

	List<SuccessfulBidDocument> hsbd = new ArrayList<SuccessfulBidDocument>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name,name_conversions.financial_institution_name AS name_conversion_fi_name "
			    + "FROM (successful_bid_datas_3 LEFT JOIN name_conversions ON successful_bid_datas_3.bank_code = name_conversions.bank_code) INNER JOIN bid_datas ON (successful_bid_datas_3.department_code = bid_datas.department_code) AND (successful_bid_datas_3.bank_code = bid_datas.bank_code) AND (successful_bid_datas_3.auction_date = bid_datas.auction_date) "
			    + "WHERE (((successful_bid_datas_3.department_code) = '" + deptCode
			    + "') AND ((successful_bid_datas_3.user_id)=" + getUserId()
			    + ") AND ((successful_bid_datas_3.financial_institution_name) NOT LIKE '%計%') AND ((successful_bid_datas_3.auction_date) = '"
			    + bidDate + "') AND ((bid_datas.flag1) = '1'))");

	    logger.info("getSuccessfulBidDocument Query - > " + strSql.toString());

	    hsbd = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new SuccessfulBidDocumentMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return hsbd;
    }

    @Override
    public List<HSuccessfulBidData3> getSuccessfulBidDocumentAll() {

	List<HSuccessfulBidData3> hsbd = new ArrayList<HSuccessfulBidData3>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM successful_bid_datas_3 WHERE user_id=" + getUserId());

	    logger.info("getSuccessfulBidDocument Query - > " + strSql.toString());

	    hsbd = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HSuccessfulBidData3Mapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return hsbd;
    }

    @Override
    public List<SuccessfulBidDocumentSub> getSuccessfulBidDocumentSub(String bidDate, String deptCode,
	    String bankCode) {

	List<SuccessfulBidDocumentSub> hsbd = new ArrayList<SuccessfulBidDocumentSub>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT DISTINCT successful_bid_datas_3.department_code,divisions.department_name, successful_bid_datas_3.business_category, successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name,"
			    + "CASE WHEN successful_bid_datas_3.bid_number > 10 THEN '合計' ELSE successful_bid_datas_3.bid_number END AS bid_number1,"
			    + "successful_bid_datas_3.bid_interest_rate, successful_bid_datas_3.bid_amount_money, successful_bid_datas_3.successful_bid_interest_rate,"
			    + "successful_bid_datas_3.successful_bid_price"
			    + " FROM (successful_bid_datas_3 LEFT JOIN divisions ON successful_bid_datas_3.department_code = divisions.department_code) INNER JOIN bid_datas ON (successful_bid_datas_3.department_code = bid_datas.department_code) AND (successful_bid_datas_3.bank_code = bid_datas.bank_code) AND (successful_bid_datas_3.auction_date = bid_datas.auction_date)"
			    + " WHERE (((successful_bid_datas_3.department_code) = '" + deptCode
			    + "') AND ((successful_bid_datas_3.user_id)=" + getUserId()
			    + ") AND ((successful_bid_datas_3.financial_institution_name) <> '合  計') AND ((successful_bid_datas_3.auction_date) = '"
			    + bidDate + "') AND ((bid_datas.flag1) = '1') AND successful_bid_datas_3.bank_code ='"
			    + bankCode + "')");

	    logger.info("getSuccessfulBidDocumentSub Query - > " + strSql.toString());

	    hsbd = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new SuccessfulBidDocumentSubMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return hsbd;
    }

    @Override
    public long manageBillCreditApplicationData(String dept, String bidDate) {
	long TNo = 0;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "DELETE FROM bill_credit_application_datas WHERE bill_credit_application_datas.department_code ='"
			    + dept + "' AND auction_date = '" + bidDate + "' AND user_id=" + getUserId());

	    logger.info("manageBillCreditApplicationData Update Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM bill_credit_application_datas WHERE user_id="
		    + getUserId() + " ORDER BY bill_number DESC");

	    logger.info("manageBillCreditApplicationData Query - > " + strSql.toString());

	    List<HBillCreditApplicationDatas> bcad = getJdbcService().getJdbcTemplate().query(strSql.toString(),
		    new Object[] {}, new HBillCreditApplicationDatasMapper());

	    boolean tFo = false;
	    for (HBillCreditApplicationDatas rsData : bcad) {
		Long trn = null;
		if (!rsData.getBill_number().equals("")) {
		    trn = Long.parseLong(rsData.getBill_number());
		}
		if (null != trn) {
		    TNo = trn + 1;
		    tFo = true;
		}
	    }
	    if (!tFo) {
		TNo = 1;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return TNo;
    }

    @Override
    public boolean insertBillCreditApplicationData(String dept, String bidDate) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "INSERT INTO bill_credit_application_datas(department_code, bank_code, financial_institution_name, successful_bid_interest_rate, successful_bid_price, auction_date, user_id) "
			    + "SELECT DISTINCT successful_bid_datas_3.department_code, successful_bid_datas_3.bank_code,financial_institutions_masters.financial_institution_name, successful_bid_datas_3.successful_bid_interest_rate, successful_bid_datas_3.successful_bid_price, successful_bid_datas_3.auction_date, successful_bid_datas_3.user_id "
			    + "FROM successful_bid_datas_3 LEFT JOIN financial_institutions_masters ON successful_bid_datas_3.bank_code = financial_institutions_masters.bank_code "
			    + "WHERE (((successful_bid_datas_3.department_code) = '" + dept
			    + "') AND ((successful_bid_datas_3.user_id)=" + getUserId()
			    + ") AND (Not (successful_bid_datas_3.bank_code) Is Null) AND ((successful_bid_datas_3.successful_bid_price)> 0) AND ((successful_bid_datas_3.bid_number) = 98) AND ((successful_bid_datas_3.auction_date) = '"
			    + bidDate + "'))");
	    logger.info("insertBillCreditApplicationData Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public List<HBillCreditApplicationDatas> getBillCreditApplicationData(String dept, String bidDate) {
	List<HBillCreditApplicationDatas> billCredits = new ArrayList<HBillCreditApplicationDatas>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT * FROM bill_credit_application_datas  WHERE bill_credit_application_datas.department_code='"
			    + dept + "' AND auction_date='" + bidDate + "' AND user_id=" + getUserId());

	    logger.info("getBillCreditApplicationData Query - > " + strSql.toString());

	    billCredits = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBillCreditApplicationDatasMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return billCredits;
    }

    @Override
    public List<HBillCreditApplicationDatas> getAllBillCreditApplicationData() {
	List<HBillCreditApplicationDatas> billCredits = new ArrayList<HBillCreditApplicationDatas>();
	try {
	    StringBuffer strSql = new StringBuffer(
		    "SELECT * FROM bill_credit_application_datas WHERE user_id=" + getUserId());

	    logger.info("getBillCreditApplicationData Query - > " + strSql.toString());

	    billCredits = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new HBillCreditApplicationDatasMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return billCredits;
    }

    @Override
    public boolean updateBillCreditApplicationData(HBillCreditApplicationDatas rst) {
	boolean fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("UPDATE bill_credit_application_datas SET " + "department_code='"
		    + rst.getDepartment_code() + "',bank_code='" + rst.getBank_code() + "',financial_institution_name='"
		    + rst.getFinancial_institution_name() + "',successful_bid_interest_rate="
		    + rst.getSuccessful_bid_interest_rate() + ",successful_bid_price=" + rst.getSuccessful_bid_price()
		    + ",bill_number='" + rst.getBill_number() + "',drawer_date='" + rst.getDrawer_date()
		    + "',maturity_date='" + rst.getMaturity_date() + "',number_of_days=" + rst.getNumber_of_days()
		    + ",interest_rate=" + rst.getInterest_rate() + ",amount_of_money=" + rst.getAmount_of_money()
		    + ",interest_amount=" + rst.getInterest_amount() + ",remarks='" + rst.getRemarks()
		    + "',auction_date='" + rst.getAuction_date() + "' WHERE department_code='"
		    + rst.getDepartment_code() + "' AND bank_code='" + rst.getBank_code() + "' AND auction_date='"
		    + rst.getAuction_date() + "' AND user_id=" + getUserId());

	    logger.info("updateBillCreditApplicationData Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public boolean createViewBill_credit_application_datas2(int str, String dept, String bidDate, Date drDt,
	    Date IntDate) {
	boolean fo = false;
	String datePattern = "yyyy-MM-dd";
	SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
	try {

	    StringBuffer strSql = new StringBuffer("");
	    if (str == 1) {
		strSql = new StringBuffer("CREATE VIEW bill_credit_application_datas2_" + getUserId() + " AS "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code,divisions.department_name,bill_credit_application_datas.bank_code, "
			+ "bill_credit_application_datas.financial_institution_name,bill_credit_application_datas.successful_bid_interest_rate, "
			+ "bill_credit_application_datas.successful_bid_price,bill_credit_application_datas.bill_number,bill_credit_application_datas.drawer_date, "
			+ "bill_credit_application_datas.maturity_date,bill_credit_application_datas.number_of_days,bill_credit_application_datas.interest_rate, "
			+ "bill_credit_application_datas.amount_of_money,bill_credit_application_datas.interest_amount,bill_credit_application_datas.remarks, "
			+ "accounts.account_number, bill_credit_application_datas.user_id FROM bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "WHERE bill_credit_application_datas.department_code = '" + dept
			+ "' AND bill_credit_application_datas.auction_date = '" + bidDate
			+ "' AND bill_credit_application_datas.user_id=" + getUserId());
	    } else if (str == 2) {
		strSql = new StringBuffer("CREATE VIEW bill_credit_application_datas2_" + getUserId()
			+ " AS SELECT DISTINCT bill_credit_application_datas.department_code, "
			+ "divisions.department_name,bill_credit_application_datas.bank_code,bill_credit_application_datas.financial_institution_name, "
			+ "bill_credit_application_datas.successful_bid_interest_rate,bill_credit_application_datas.successful_bid_price,bill_credit_application_datas.bill_number, "
			+ "bill_credit_application_datas.drawer_date,bill_credit_application_datas.maturity_date,bill_credit_application_datas.number_of_days, "
			+ "bill_credit_application_datas.interest_rate,bill_credit_application_datas.amount_of_money,bill_credit_application_datas.interest_amount, "
			+ "bill_credit_application_datas.remarks,accounts.account_number, " + "'"
			+ dateFormatter.format(IntDate) + "' AS IntermediateInterestPaymentDate, "
			+ "((TIMESTAMPDIFF(DAY, '" + dateFormatter1.format(drDt) + "','"
			+ dateFormatter1.format(IntDate)
			+ "') / bill_credit_application_datas.number_of_days) * bill_credit_application_datas.interest_amount) AS InterestPaymentDateInterest,"
			+ "(TIMESTAMPDIFF(DAY, '" + dateFormatter1.format(drDt) + "','" + dateFormatter1.format(IntDate)
			+ "')) AS InterestPaymentDay, bill_credit_application_datas.user_id"
			+ " FROM bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "WHERE bill_credit_application_datas.department_code = '" + dept
			+ "' AND bill_credit_application_datas.auction_date = '" + bidDate
			+ "' AND bill_credit_application_datas.user_id=" + getUserId());
	    } else if (str == 3) {
		strSql = new StringBuffer("CREATE VIEW bill_credit_application_datas2_" + getUserId() + " AS "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code,"
			+ "divisions.department_name,bill_credit_application_datas.bank_code,"
			+ "bill_credit_application_datas.financial_institution_name,"
			+ "bill_credit_application_datas.successful_bid_interest_rate,"
			+ "bill_credit_application_datas.successful_bid_price,"
			+ "bill_credit_application_datas.bill_number," + "bill_credit_application_datas.drawer_date,"
			+ "bill_credit_application_datas.maturity_date,"
			+ "bill_credit_application_datas.number_of_days,"
			+ "bill_credit_application_datas.interest_rate,"
			+ "bill_credit_application_datas.amount_of_money,"
			+ "bill_credit_application_datas.interest_amount," + "bill_credit_application_datas.remarks,"
			+ "accounts.account_number, bill_credit_application_datas.user_id FROM (bill_credit_application_datas "
			+ "LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "WHERE bill_credit_application_datas.department_code = '" + dept
			+ "' AND bill_credit_application_datas.auction_date = '" + bidDate
			+ "' AND bill_credit_application_datas.user_id=" + getUserId());
	    } else if (str == 4) {
		strSql = new StringBuffer("CREATE VIEW bill_credit_application_datas2_" + getUserId() + " AS "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code,divisions.department_name,bill_credit_application_datas.bank_code, "
			+ "bill_credit_application_datas.financial_institution_name,bill_credit_application_datas.successful_bid_interest_rate, "
			+ "bill_credit_application_datas.successful_bid_price,bill_credit_application_datas.bill_number,bill_credit_application_datas.drawer_date, "
			+ "bill_credit_application_datas.maturity_date,bill_credit_application_datas.number_of_days,bill_credit_application_datas.interest_rate, "
			+ "bill_credit_application_datas.amount_of_money,bill_credit_application_datas.interest_amount,bill_credit_application_datas.remarks, "
			+ "accounts.account_number, bill_credit_application_datas.user_id FROM bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "WHERE bill_credit_application_datas.department_code = '" + dept
			+ "' AND bill_credit_application_datas.auction_date = '" + bidDate
			+ "' AND bill_credit_application_datas.user_id=" + getUserId());
	    }

	    logger.info("createViewBill_credit_application_datas2_" + getUserId() + " Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());

	    fo = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return fo;
    }

    @Override
    public List<CreditApplicationPrintReport> getCreditApplicationPrint() {
	List<CreditApplicationPrintReport> dataBeanList = new ArrayList<CreditApplicationPrintReport>();

	try {
	    DropViewIfExist("credit_application_print_report_" + getUserId());
	    StringBuffer strSql = new StringBuffer("CREATE VIEW credit_application_print_report_" + getUserId()
		    + " AS select bill_credit_application_datas2_" + getUserId()
		    + ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
		    + ".department_name AS name,bill_credit_application_datas2_" + getUserId()
		    + ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
		    + ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS bankname,bill_credit_application_datas2_"
		    + getUserId()
		    + ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
		    + getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
		    + getUserId() + ".bill_number AS bill_number,substr(bill_credit_application_datas2_" + getUserId()
		    + ".drawer_date,1,4) AS drawer_yy,substr(bill_credit_application_datas2_" + getUserId()
		    + ".drawer_date,6,2) AS drawer_mm,substr(bill_credit_application_datas2_" + getUserId()
		    + ".drawer_date,9) AS drawer_dd,substr(bill_credit_application_datas2_" + getUserId()
		    + ".maturity_date,1,4) AS maturity_yy,substr(bill_credit_application_datas2_" + getUserId()
		    + ".maturity_date,6,2) AS maturity_mm,substr(bill_credit_application_datas2_" + getUserId()
		    + ".maturity_date,9) AS maturity_dd,bill_credit_application_datas2_" + getUserId()
		    + ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
		    + ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
		    + ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
		    + ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
		    + ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
		    + ".account_number AS account_number from (bill_credit_application_datas2_" + getUserId()
		    + " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
		    + ".bank_code = name_conversions.bank_code)))");
	    addData(strSql);
	    strSql = new StringBuffer("SELECT credit_application_print_report_" + getUserId()
		    + ".*,base.chairman_name,base.chairman_name_2 FROM  credit_application_print_report_" + getUserId()
		    + " LEFT JOIN base ON credit_application_print_report_" + getUserId()
		    + ".department_code = base.department_code");

	    logger.info("getCreditApplicationPrint Query - > " + strSql.toString());
	    dataBeanList = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new CreditApplicationPrintReportMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return dataBeanList;
    }

    @Override
    public List<BillCreditApplicationIntermediateInterestPaymentsReport> getBillCreditApplicationIntermediateInterestPayments() {
	List<BillCreditApplicationIntermediateInterestPaymentsReport> dataBeanList = new ArrayList<BillCreditApplicationIntermediateInterestPaymentsReport>();

	try {

	    StringBuffer strSql = new StringBuffer("Select * from bill_credit_app_report");

	    logger.info("getBillCreditApplicationIntermediateInterestPayments Query - > " + strSql.toString());
	    dataBeanList = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new BillCreditApplicationIntermediateInterestPaymentsReportMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return dataBeanList;
    }

    @Override
    public List<BillApplicationFormListReport> getBillApplicationFormList(int rpt) {
	List<BillApplicationFormListReport> dataBeanList = new ArrayList<BillApplicationFormListReport>();

	try {

	    StringBuffer strSql = new StringBuffer("");
	    if (rpt == 3) {
		/*
		 * strSql = new StringBuffer(
		 * "SELECT * FROM bill_application_form_list3_report");
		 */
		DropViewIfExist("bill_application_form_list3_report_" + getUserId());
		strSql = new StringBuffer("CREATE VIEW bill_application_form_list3_report_" + getUserId()
			+ " AS select bill_credit_application_datas2_" + getUserId()
			+ ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
			+ ".department_name AS department_name,bill_credit_application_datas2_" + getUserId()
			+ ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
			+ ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS nc_name,bill_credit_application_datas2_"
			+ getUserId()
			+ ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
			+ getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
			+ getUserId() + ".bill_number AS bill_number,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,1,4) AS drawer_yy,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,6,2) AS drawer_mm,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,9) AS drawer_dd,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,1,4) AS maturity_yy,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,6,2) AS maturity_mm,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,9) AS maturity_dd,bill_credit_application_datas2_" + getUserId()
			+ ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
			+ ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
			+ ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
			+ ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
			+ ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
			+ ".account_number AS account_number from (bill_credit_application_datas2_" + getUserId()
			+ " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
			+ ".bank_code = name_conversions.bank_code)))");
		addData(strSql);
		strSql = new StringBuffer("SELECT * FROM bill_application_form_list3_report_" + getUserId());
	    } else if (rpt == 2) {
		/*
		 * strSql = new StringBuffer(
		 * "SELECT * FROM Bill_Application_Form_List2_report");
		 */
		DropViewIfExist("Bill_Application_Form_List2_report_" + getUserId());
		strSql = new StringBuffer("CREATE VIEW Bill_Application_Form_List2_report_" + getUserId()
			+ " AS select bill_credit_application_datas2_" + getUserId()
			+ ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
			+ ".department_name AS department_name,bill_credit_application_datas2_" + getUserId()
			+ ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
			+ ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS nc_name,(case when (substr(bill_credit_application_datas2_"
			+ getUserId() + ".bank_code,1,1) = '0') then concat(bill_credit_application_datas2_"
			+ getUserId() + ".financial_institution_name,'銀行') when (bill_credit_application_datas2_"
			+ getUserId() + ".bank_code = '1000004') then '全国信用金庫連合会' when (bill_credit_application_datas2_"
			+ getUserId()
			+ ".bank_code = '2010100') then '全国信用協同組合連合会' when (bill_credit_application_datas2_"
			+ getUserId()
			+ ".bank_code = '2950002') then '全国労働金庫連合会' when (name_conversions.financial_institution_name = '') then bill_credit_application_datas2_"
			+ getUserId()
			+ ".financial_institution_name else name_conversions.financial_institution_name end) AS formated_financial_institutions,bill_credit_application_datas2_"
			+ getUserId()
			+ ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
			+ getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
			+ getUserId() + ".bill_number AS bill_number,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,1,4) AS drawer_yy,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,6,2) AS drawer_mm,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,9) AS drawer_dd,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,1,4) AS maturity_yy,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,6,2) AS maturity_mm,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,9) AS maturity_dd,bill_credit_application_datas2_" + getUserId()
			+ ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
			+ ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
			+ ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
			+ ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
			+ ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
			+ ".account_number AS account_number from (bill_credit_application_datas2_" + getUserId()
			+ " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
			+ ".bank_code = name_conversions.bank_code)))");
		addData(strSql);
		strSql = new StringBuffer("SELECT * FROM Bill_Application_Form_List2_report_" + getUserId());
	    } else if (rpt == 33) {
		/*
		 * strSql = new StringBuffer(
		 * "SELECT * FROM Creditor_Management_Book3_report");
		 */
		DropViewIfExist("Creditor_Management_Book3_report_" + getUserId());
		strSql = new StringBuffer("CREATE VIEW Creditor_Management_Book3_report_" + getUserId()
			+ " AS select bill_credit_application_datas2_" + getUserId()
			+ ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
			+ ".department_name AS department_name,bill_credit_application_datas2_" + getUserId()
			+ ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
			+ ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS nc_name,bill_credit_application_datas2_"
			+ getUserId()
			+ ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
			+ getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
			+ getUserId() + ".bill_number AS bill_number,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,1,4) AS drawer_yy,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,6,2) AS drawer_mm,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,9) AS drawer_dd,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,1,4) AS maturity_yy,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,6,2) AS maturity_mm,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,9) AS maturity_dd,bill_credit_application_datas2_" + getUserId()
			+ ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
			+ ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
			+ ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
			+ ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
			+ ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
			+ ".account_number AS account_number from (bill_credit_application_datas2_" + getUserId()
			+ " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
			+ ".bank_code = name_conversions.bank_code)))");
		addData(strSql);
		strSql = new StringBuffer("SELECT * FROM Creditor_Management_Book3_report_" + getUserId());
	    } else if (rpt == 31) {
		/*
		 * strSql = new StringBuffer(
		 * "SELECT * FROM Creditor_Management_Book_report");
		 */
		DropViewIfExist("Creditor_Management_Book_report_" + getUserId());
		strSql = new StringBuffer("CREATE VIEW Creditor_Management_Book_report_" + getUserId()
			+ " AS select bill_credit_application_datas2_" + getUserId()
			+ ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
			+ ".department_name AS department_name,bill_credit_application_datas2_" + getUserId()
			+ ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
			+ ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS nc_name,(case when (substr(bill_credit_application_datas2_"
			+ getUserId() + ".bank_code,1,1) = '0') then concat(bill_credit_application_datas2_"
			+ getUserId() + ".financial_institution_name,'銀行') when (bill_credit_application_datas2_"
			+ getUserId() + ".bank_code = '1000004') then '全国信用金庫連合会' when (bill_credit_application_datas2_"
			+ getUserId()
			+ ".bank_code = '2010100') then '全国信用協同組合連合会' when (bill_credit_application_datas2_"
			+ getUserId() + ".bank_code = '2950002') then '全国労働金庫連合会' else bill_credit_application_datas2_"
			+ getUserId()
			+ ".financial_institution_name end) AS formated_financial_institutions,bill_credit_application_datas2_"
			+ getUserId()
			+ ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
			+ getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
			+ getUserId() + ".bill_number AS bill_number,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,1,4) AS drawer_yy,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,6,2) AS drawer_mm,substr(bill_credit_application_datas2_"
			+ getUserId() + ".drawer_date,9) AS drawer_dd,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,1,4) AS maturity_yy,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,6,2) AS maturity_mm,substr(bill_credit_application_datas2_"
			+ getUserId() + ".maturity_date,9) AS maturity_dd,bill_credit_application_datas2_" + getUserId()
			+ ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
			+ ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
			+ ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
			+ ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
			+ ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
			+ ".account_number AS account_number from (bill_credit_application_datas2_" + getUserId()
			+ " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
			+ ".bank_code = name_conversions.bank_code)))");
		addData(strSql);
		strSql = new StringBuffer("SELECT * FROM Creditor_Management_Book_report_" + getUserId());
	    } else if (rpt == 41) {
		/*
		 * strSql = new StringBuffer(
		 * "SELECT * FROM approval_app_form_attachment_report");
		 */
		DropViewIfExist("approval_app_form_attachment_report_" + getUserId());
		strSql = new StringBuffer("CREATE VIEW approval_app_form_attachment_report_" + getUserId()
			+ " AS select bill_credit_application_datas2_" + getUserId()
			+ ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
			+ ".department_name AS department_name,bill_credit_application_datas2_" + getUserId()
			+ ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
			+ ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS nc_name,bill_credit_application_datas2_"
			+ getUserId()
			+ ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
			+ getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
			+ getUserId() + ".bill_number AS bill_number,bill_credit_application_datas2_" + getUserId()
			+ ".drawer_date AS drawer_date,bill_credit_application_datas2_" + getUserId()
			+ ".maturity_date AS maturity_date,bill_credit_application_datas2_" + getUserId()
			+ ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
			+ ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
			+ ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
			+ ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
			+ ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
			+ ".account_number AS account_number from (bill_credit_application_datas2_" + getUserId()
			+ " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
			+ ".bank_code = name_conversions.bank_code)))");
		addData(strSql);
		strSql = new StringBuffer("SELECT * FROM approval_app_form_attachment_report_" + getUserId());
	    } else if (rpt == 42) {
		/*
		 * strSql = new StringBuffer(
		 * "SELECT * FROM approval_app_form_attachment2_report");
		 */
		DropViewIfExist("approval_app_form_attachment2_report_" + getUserId());
		strSql = new StringBuffer("CREATE VIEW approval_app_form_attachment2_report_" + getUserId()
			+ " AS select bill_credit_application_datas2_" + getUserId()
			+ ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
			+ ".department_name AS department_name,bill_credit_application_datas2_" + getUserId()
			+ ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
			+ ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS nc_name,(case when (substr(bill_credit_application_datas2_"
			+ getUserId() + ".bank_code,1,1) = '0') then concat(bill_credit_application_datas2_"
			+ getUserId() + ".financial_institution_name,'銀行') when (bill_credit_application_datas2_"
			+ getUserId() + ".bank_code = '1000004') then '全国信用金庫連合会' when (bill_credit_application_datas2_"
			+ getUserId()
			+ ".bank_code = '2010100') then '全国信用協同組合連合会' when (bill_credit_application_datas2_"
			+ getUserId()
			+ ".bank_code = '2950002') then '全国労働金庫連合会' when (name_conversions.financial_institution_name = '') then bill_credit_application_datas2_"
			+ getUserId()
			+ ".financial_institution_name else name_conversions.financial_institution_name end) AS formated_financial_institutions,bill_credit_application_datas2_"
			+ getUserId()
			+ ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
			+ getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
			+ getUserId() + ".bill_number AS bill_number,bill_credit_application_datas2_" + getUserId()
			+ ".drawer_date AS drawer_date,bill_credit_application_datas2_" + getUserId()
			+ ".maturity_date AS maturity_date,bill_credit_application_datas2_" + getUserId()
			+ ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
			+ ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
			+ ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
			+ ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
			+ ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
			+ ".account_number AS account_number from (bill_credit_application_datas2_" + getUserId()
			+ " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
			+ ".bank_code = name_conversions.bank_code)))");
		addData(strSql);
		strSql = new StringBuffer("SELECT * FROM approval_app_form_attachment2_report_" + getUserId());
	    } else if (rpt == 43) {
		/*
		 * strSql = new StringBuffer(
		 * "SELECT * FROM approval_app_form_attachment3_report");
		 */
		DropViewIfExist("approval_app_form_attachment3_report_" + getUserId());
		strSql = new StringBuffer("CREATE VIEW approval_app_form_attachment3_report_" + getUserId()
			+ " AS select bill_credit_application_datas2_" + getUserId()
			+ ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
			+ ".department_name AS department_name,bill_credit_application_datas2_" + getUserId()
			+ ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
			+ ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS nc_name,(case when (substr(bill_credit_application_datas2_"
			+ getUserId() + ".bank_code,1,1) = '0') then concat(bill_credit_application_datas2_"
			+ getUserId() + ".financial_institution_name,'銀行') when (bill_credit_application_datas2_"
			+ getUserId() + ".bank_code = '1000004') then '全国信用金庫連合会' when (bill_credit_application_datas2_"
			+ getUserId()
			+ ".bank_code = '2010100') then '全国信用協同組合連合会' when (bill_credit_application_datas2_"
			+ getUserId()
			+ ".bank_code = '2950002') then '全国労働金庫連合会' when (name_conversions.financial_institution_name = '') then bill_credit_application_datas2_"
			+ getUserId()
			+ ".financial_institution_name else name_conversions.financial_institution_name end) AS formated_financial_institutions,bill_credit_application_datas2_"
			+ getUserId()
			+ ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
			+ getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
			+ getUserId() + ".bill_number AS bill_number,bill_credit_application_datas2_" + getUserId()
			+ ".drawer_date AS drawer_date,bill_credit_application_datas2_" + getUserId()
			+ ".maturity_date AS maturity_date,bill_credit_application_datas2_" + getUserId()
			+ ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
			+ ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
			+ ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
			+ ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
			+ ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
			+ ".account_number AS account_number from (bill_credit_application_datas2_" + getUserId()
			+ " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
			+ ".bank_code = name_conversions.bank_code)))");
		addData(strSql);
		strSql = new StringBuffer("SELECT * FROM approval_app_form_attachment3_report_" + getUserId());
	    }

	    logger.info("getBillCreditApplicationIntermediateInterestPayments Query - > " + strSql.toString());
	    dataBeanList = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new BillApplicationFormListReportMapper());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return dataBeanList;
    }

    @Override
    public boolean TransferSlip(String bidDate, String deptCode) {
	DataClear("journal_search_results");
	DataClear("transfer_slip_reports");

	boolean fo = false;

	StringBuffer strSql = new StringBuffer(
		"INSERT INTO transfer_slip_reports(department_code, department_name, journal_number, slip_number, recorded_year, recorded_month, recorded_date, user_id) "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 1 AS journal_number, 1 AS slip_number, "
			+ "substr(drawer_date,1,4) AS Year, substr(drawer_date,6,2) AS Month, substr(drawer_date,9) AS Day, bill_credit_application_datas.user_id FROM "
			+ "(bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "GROUP BY bill_credit_application_datas.department_code, divisions.department_name, 1, 1, substr(drawer_date,1,4), substr(drawer_date,6,2), substr(drawer_date,9), "
			+ "bill_credit_application_datas.auction_date HAVING bill_credit_application_datas.auction_date='"
			+ bidDate + "' AND bill_credit_application_datas.user_id=" + getUserId());

	fo = addData(strSql);

	StringBuffer strJournal = new StringBuffer(
		"INSERT INTO journal_search_results(department_code, department_name, journal_number, slip_number, row_number, abstract_name, recorded_year, recorded_month, "
			+ "recorded_date, debit_amount, credit_amount, debit_item_code, debit_item_name, credit_course_code, credit_course_name, user_id ) "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 1 AS journal_number, 1 AS slip_number, bill_credit_application_datas.bill_number, "
			+ "bill_credit_application_datas.financial_institution_name, substr(drawer_date,1,4) AS Year, substr(drawer_date,6,2) AS Month, substr(drawer_date,9) AS Day, "
			+ "bill_credit_application_datas.amount_of_money AS DebitAmount, bill_credit_application_datas.amount_of_money AS CreditAmount, '1110' AS DebitItemCode, '普通預金' AS DebitItemName, '2110' AS CreditCourseCode, "
			+ "'借入金' AS CreditCourseName, bill_credit_application_datas.user_id FROM (bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "WHERE bill_credit_application_datas.auction_date='" + bidDate
			+ "' AND bill_credit_application_datas.user_id=" + getUserId()
			+ " ORDER BY bill_credit_application_datas.bill_number");

	fo = addData(strJournal);

	StringBuffer strSql1 = new StringBuffer(
		"INSERT INTO transfer_slip_reports(department_code, department_name, journal_number, slip_number, recorded_year, recorded_month, recorded_date, user_id) "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 2 AS journal_number, 2 AS slip_number, "
			+ "substr(maturity_date,1,4) AS Year, substr(maturity_date,6,2) AS Month, substr(maturity_date,9) AS Day, bill_credit_application_datas.user_id FROM "
			+ "(bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "GROUP BY bill_credit_application_datas.department_code, divisions.department_name, 1, 1, substr(maturity_date,1,4), substr(maturity_date,6,2), substr(maturity_date,9), "
			+ "bill_credit_application_datas.auction_date HAVING bill_credit_application_datas.auction_date='"
			+ bidDate + "' AND bill_credit_application_datas.user_id=" + getUserId());
	fo = addData(strSql1);

	StringBuffer strJournal1 = new StringBuffer(
		"INSERT INTO journal_search_results(department_code, department_name, journal_number, slip_number, row_number, abstract_name, recorded_year, recorded_month, "
			+ "recorded_date, debit_amount, credit_amount, credit_course_code, credit_course_name, debit_item_code, debit_item_name, user_id ) "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 2 AS journal_number, 2 AS slip_number, bill_credit_application_datas.bill_number, "
			+ "bill_credit_application_datas.financial_institution_name, substr(maturity_date,1,4) AS Year, substr(maturity_date,6,2) AS Month, substr(maturity_date,9) AS Day, "
			+ "bill_credit_application_datas.amount_of_money AS DebitAmount, bill_credit_application_datas.amount_of_money AS CreditAmount, '1110' AS DebitItemCode, '普通預金' AS DebitItemName, '2110' AS CreditCourseCode, "
			+ "'借入金' AS CreditCourseName, bill_credit_application_datas.user_id FROM (bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "WHERE bill_credit_application_datas.auction_date='" + bidDate
			+ "' AND bill_credit_application_datas.user_id=" + getUserId()
			+ " ORDER BY bill_credit_application_datas.bill_number");

	fo = addData(strJournal1);

	StringBuffer strSql2 = new StringBuffer(
		"INSERT INTO transfer_slip_reports(department_code, department_name, journal_number, slip_number, recorded_year, recorded_month, recorded_date, user_id) "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 3 AS journal_number, 3 AS slip_number, "
			+ "substr(maturity_date,1,4) AS Year, substr(maturity_date,6,2) AS Month, substr(maturity_date,9) AS Day, bill_credit_application_datas.user_id FROM "
			+ "(bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "GROUP BY bill_credit_application_datas.department_code, divisions.department_name, 1, 1, substr(maturity_date,1,4), substr(maturity_date,6,2), substr(maturity_date,9), "
			+ "bill_credit_application_datas.auction_date HAVING bill_credit_application_datas.auction_date='"
			+ bidDate + "' AND bill_credit_application_datas.user_id=" + getUserId());
	fo = addData(strSql2);

	StringBuffer strJournal2 = new StringBuffer(
		"INSERT INTO journal_search_results(department_code, department_name, journal_number, slip_number, row_number, abstract_name, recorded_year, recorded_month, "
			+ "recorded_date, debit_amount, credit_amount, credit_course_code, credit_course_name, debit_item_code, debit_item_name, user_id ) "
			+ "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 3 AS journal_number, 3 AS slip_number, bill_credit_application_datas.bill_number, "
			+ "bill_credit_application_datas.financial_institution_name, substr(maturity_date,1,4) AS Year, substr(maturity_date,6,2) AS Month, substr(maturity_date,9) AS Day, "
			+ "bill_credit_application_datas.interest_amount AS DebitAmount, bill_credit_application_datas.interest_amount AS CreditAmount, '1110' AS DebitItemCode, '普通預金' AS DebitItemName, '5110' AS CreditCourseCode, "
			+ "'支払利息' AS CreditCourseName, bill_credit_application_datas.user_id FROM (bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "
			+ "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "
			+ "WHERE bill_credit_application_datas.auction_date='" + bidDate
			+ "' AND bill_credit_application_datas.user_id=" + getUserId()
			+ " ORDER BY bill_credit_application_datas.bill_number");

	fo = addData(strJournal2);
	return true;
    }

    @Override
    public List<TransferSlip> getTransferSlip() {

	List<TransferSlip> hsbd = new ArrayList<TransferSlip>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM transfer_slip_reports WHERE user_id=" + getUserId());

	    logger.info("getTransferSlip Query - > " + strSql.toString());

	    hsbd = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new TransferSlipMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return hsbd;
    }

    @Override
    public List<JournalSearchResult> getJournalSearchResult(int journal_no, int slip_no) {

	List<JournalSearchResult> hsbd = new ArrayList<JournalSearchResult>();
	try {
	    StringBuffer strSql = new StringBuffer("SELECT * FROM journal_search_results WHERE journal_number ="
		    + journal_no + " AND slip_number =" + slip_no + " AND user_id=" + getUserId());

	    logger.info("getJournalSearchResult Query - > " + strSql.toString());

	    hsbd = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new JournalSearchResultMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return hsbd;
    }

    @Override
    public List<BillApplicationFormListReport> getLoanAgreementDeed() {

	List<BillApplicationFormListReport> hsbd = new ArrayList<BillApplicationFormListReport>();
	try {
	    // StringBuffer strSql = new StringBuffer("SELECT * FROM
	    // loan_agreement_deed");
	    DropViewIfExist("loan_agreement_deed_" + getUserId());
	    StringBuffer strSql = new StringBuffer("CREATE VIEW loan_agreement_deed_" + getUserId()
		    + " AS select bill_credit_application_datas2_" + getUserId()
		    + ".department_code AS department_code,bill_credit_application_datas2_" + getUserId()
		    + ".department_name AS department_name,bill_credit_application_datas2_" + getUserId()
		    + ".bank_code AS bank_code,bill_credit_application_datas2_" + getUserId()
		    + ".financial_institution_name AS financial_institution_name,name_conversions.financial_institution_name AS nc_name,bill_credit_application_datas2_"
		    + getUserId()
		    + ".successful_bid_interest_rate AS successful_bid_interest_rate,bill_credit_application_datas2_"
		    + getUserId() + ".successful_bid_price AS successful_bid_price,bill_credit_application_datas2_"
		    + getUserId() + ".bill_number AS bill_number,substr(bill_credit_application_datas2_" + getUserId()
		    + ".drawer_date,1,4) AS drawer_yy,substr(bill_credit_application_datas2_" + getUserId()
		    + ".drawer_date,6,2) AS drawer_mm,substr(bill_credit_application_datas2_" + getUserId()
		    + ".drawer_date,9) AS drawer_dd,substr(bill_credit_application_datas2_" + getUserId()
		    + ".maturity_date,1,4) AS maturity_yy,substr(bill_credit_application_datas2_" + getUserId()
		    + ".maturity_date,6,2) AS maturity_mm,substr(bill_credit_application_datas2_" + getUserId()
		    + ".maturity_date,9) AS maturity_dd,bill_credit_application_datas2_" + getUserId()
		    + ".number_of_days AS number_of_days,bill_credit_application_datas2_" + getUserId()
		    + ".interest_rate AS interest_rate,bill_credit_application_datas2_" + getUserId()
		    + ".amount_of_money AS amount_of_money,bill_credit_application_datas2_" + getUserId()
		    + ".interest_amount AS interest_amount,bill_credit_application_datas2_" + getUserId()
		    + ".remarks AS remarks,bill_credit_application_datas2_" + getUserId()
		    + ".account_number AS account_number,base.chairman_name AS chairman_name,base.chairman_name_2 AS chairman_name_2,base.minister_of_finance AS minister_of_finance,base.street_address AS street_address,contracts_wording.horei01 AS horei01,contracts_wording.horei02 AS horei02 from (((bill_credit_application_datas2_"
		    + getUserId() + " left join name_conversions on((bill_credit_application_datas2_" + getUserId()
		    + ".bank_code = name_conversions.bank_code))) left join base on((bill_credit_application_datas2_"
		    + getUserId()
		    + ".department_code = base.department_code))) left join contracts_wording on((bill_credit_application_datas2_"
		    + getUserId() + ".department_code = contracts_wording.department_code)))");
	    addData(strSql);
	    strSql = new StringBuffer("SELECT * FROM loan_agreement_deed_" + getUserId());

	    logger.info("getLoanAgreementDeed Query - > " + strSql.toString());

	    hsbd = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
		    new BillApplicationFormListReportMapper());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return hsbd;
    }

    public boolean addData(StringBuffer strSql) {
	boolean Fo = false;
	try {
	    logger.info("addData Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Fo;
    }

    @Override
    public boolean addNewUser(HUser user) {
	boolean Fo = false;
	try {
	    StringBuffer strSql = new StringBuffer("INSERT INTO user (user_name, password, full_name, roles) VALUES('"
		    + user.getUser_name() + "',MD5('" + user.getPassword() + "'),'" + user.getFull_name() + "','"
		    + user.getRoles() + "')");
	    logger.info("addNewUser Add Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    Fo = true;

	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return Fo;
    }

    @Override
    public boolean editUser(HUser user) {
	boolean Fo = false;
	try {
	    StringBuffer strSql = new StringBuffer(
		    "UPDATE user SET password=MD5('" + user.getPassword() + "'),full_name='" + user.getFull_name()
			    + "',roles='" + user.getRoles() + "' WHERE user.user_id=" + user.getUser_id());
	    logger.info("editUser update Query - > " + strSql.toString());

	    getJdbcService().getJdbcTemplate().execute(strSql.toString());
	    Fo = true;

	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return Fo;
    }

    @Override
    public boolean ClearTables(String bidDate,String DepartmentCode) {
	boolean Fo = false;
	try {
	    List<HCommon> objs = new ArrayList<HCommon>();
	    try {
		StringBuffer strSql = new StringBuffer("SELECT table_name3 from link_tables");
		objs = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {}, new HCommonMapper());
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	    
	    if (objs.size() > 0) {
		for (HCommon obj : objs) {
		    String table = obj.getDatas().get(0);
		    if ((!table.equals("accounts")) && (!table.equals("name_conversions")) && (!table.equals("bid_states"))) {
			if ((table.equals("bill_credit_application_datas")) || (table.equals("bid_datas")) || (table.equals("successful_bid_datas_3"))) {
			    StringBuffer strSql = new StringBuffer("DELETE from " + table + " WHERE department_code = '"
				    + DepartmentCode + "' AND auction_date = '" + bidDate + "' AND user_id="+getUserId());
			    getJdbcService().getJdbcTemplate().execute(strSql.toString());
			    Fo= true;
			} else {
			    StringBuffer strSql = new StringBuffer("DELETE from " + table +" WHERE user_id="+getUserId());
			    getJdbcService().getJdbcTemplate().execute(strSql.toString());
			    Fo = true;
			}
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return Fo;
    }

}
