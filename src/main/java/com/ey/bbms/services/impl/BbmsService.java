package com.ey.bbms.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ey.bbms.dao.interfaces.IBbmsDAO;
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
import com.ey.bbms.model.support.HFlgByBankProcessing;
import com.ey.bbms.model.support.HProrationdataprocessing;
import com.ey.bbms.services.interfaces.IBbmsService;

public class BbmsService implements IBbmsService {
	private IBbmsDAO bbmsDao;

	// ==================================================================Local_Function=================================================================
	public float Nz(Float v) {
		float val = 0;
		if (null != v)
			val = v;
		else
			val = 0;

		return val;
	}

	public double Nz(Double v) {
		Double val = 0d;
		if (null != v)
			val = v;
		else
			val = 0d;

		return val;
	}

	public boolean Like(Double toCompare, String by) {
		if (by != null) {
			if (toCompare != null) {
				String toBeCompare = toCompare.toString();
				if (by.startsWith("*") && by.endsWith("*")) {
					int index = toBeCompare.toLowerCase().indexOf(by.replace("*", "").toLowerCase());
					if (index < 0) {
						return false;
					} else {
						return true;
					}
				} else if (by.startsWith("*")) {
					return toBeCompare.endsWith(by.replace("*", ""));
				} else if (by.endsWith("*")) {
					return toBeCompare.startsWith(by.replace("*", ""));
				} else {
					return toBeCompare.equals(by.replace("*", ""));
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public double INT(double ri) {
		double integer = 0;
		if (ri == 0.0)
			return integer;
		double num = ri;

		String[] splitter = String.valueOf(num).split("\\.");
		int decimalPlaces = splitter[1].length(); // After Decimal Count

		integer = (int) num;

		return integer;
	}

	public double Format(double val, String pattern) {
		String formatedVal = "";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		formatedVal = decimalFormat.format(val);

		return Double.parseDouble(formatedVal);
	}

	public double Round(double val, long pattern) {
		double roundOff = (double) Math.round(val * pattern) / 100;
		return roundOff;
	}

	public Date custingStringToDate(String date, String formate) {
		// String testDate = "29-Apr-2010,13:00:14 PM";
		DateFormat formatter = new SimpleDateFormat(formate);// "d-MMM-yyyy,HH:mm:ss
		// aaa"
		Date covertedDate = null;
		if (date.equals(""))
			return covertedDate;
		try {
			covertedDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return covertedDate;
	}

	public String FormatSt(long n, String pattern) {
		String num = "";
		if (pattern.equals("0000"))
			num = StringUtils.leftPad(String.valueOf(n), 4, "0");

		return num;
	}

	public float Int(double val) {
		double myDub;
		myDub = val;
		long myLong;
		myLong = (long) myDub;
		// myDub=(myDub%1)*1000000000;
		// int myInt=(int)myDub;
		return (float) myLong;
	}
	// ==================================================================Local_Function_End=============================================================

	public void setBbmsDao(IBbmsDAO bbmsDao) {
		this.bbmsDao = bbmsDao;
	}

	public HUser getUserValidation(HUser user) {
		HUser returnUser = bbmsDao.getEmployeeByPassword(false, user.getUser_name(), user.getPassword());
		return returnUser;
	}

	@Override
	public List<Financialinstitute> getFinancialInstituteList(String date) {
		return bbmsDao.getFinancialInstituteList(date);
	}

	@Override
	public List<HUser> getAllUser() {
		return bbmsDao.getAllUser();
	}

	@Override
	public List<HFinancialInstitutionsMasters> getHFinancialInstitutionsMasters(String bankCode) {
		return bbmsDao.getHFinancialInstitutionsMasters(bankCode);
	}

	@Override
	public List<HDivisions> getDivision() {
		return bbmsDao.getDivision();
	}

	@Override
	public HBase getBase() {
		return bbmsDao.getBase();
	}

	@Override
	public List<HBidData> getBidData(String bidDate, String bankcode, String deptcode) {
		return bbmsDao.getBidData(bidDate, bankcode, deptcode);
	}

	@Override
	public Integer getRowCountOfBidDataForABank(String bidDate, String bankcode, String deptcode) {
		return bbmsDao.getRowCountOfBidDataForABank(bidDate, bankcode, deptcode);
	}

	@Override
	public boolean deleteRowOfBidDataForABank(String bidDate, String bankcode, String deptcode) {
		return bbmsDao.deleteRowOfBidDataForABank(bidDate, bankcode, deptcode);
	}

	@Override
	public boolean addRowOfBidDataForABank(List<HBidData> biddatas) {
		return bbmsDao.addRowOfBidDataForABank(biddatas);
	}

	@Override
	public boolean updateFlagOfBidDataForABank(String bidDate, String bankcode, String deptcode, String flg) {
		return bbmsDao.updateFlagOfBidDataForABank(bidDate, bankcode, deptcode, flg);
	}

	@Override
	public List<ThereSpecification> getThereSpecification(String date) {
		return bbmsDao.getThereSpecification(date);
	}

	@Override
	public List<NoSpecification> getNoSpecification(String date) {
		return bbmsDao.getNoSpecification(date);
	}

	@Override
	public List<HSuccessfulBidSituations> getInterestRateBy(String bidDate, String dept_code) {
		return bbmsDao.getInterestRateBy(bidDate, dept_code);
	}

	@Override
	public List<HSuccessfulBidData3> getFinancialInstituteBy(String bidDate, String dept_code) {
		return bbmsDao.getFinancialInstituteBy(bidDate, dept_code);
	}

	@Override
	public List<HSuccessfulBidData3> loadBidMaintenanceData(String bidDate, String deptCode) {
		return bbmsDao.loadBidMaintenanceData(bidDate, deptCode);
	}

	@Override
	public boolean UpdateSucessfullBidData(HSuccessfulBidData3 obj, String deptCode) {
		return bbmsDao.UpdateSucessfullBidData(obj, deptCode);
	}

	@Override
	public List<SuccessfulBidDocument> getSuccessfulBidDocument(String bidDate, String deptCode) {
		return bbmsDao.getSuccessfulBidDocument(bidDate, deptCode);
	}

	@Override
	public List<SuccessfulBidDocumentSub> getSuccessfulBidDocumentSub(String bidDate, String deptCode,
			String bankCode) {
		return bbmsDao.getSuccessfulBidDocumentSub(bidDate, deptCode, bankCode);
	}

	@Override
	public String NRJ_T_MAKE(int SHORI, String dept, String bidDate) {
		// Make InterestRateOrderBidStatusTable
		double MAE_RITSU;
		String MAE_KEY;
		double MAE_KIN;
		double RUIKEI;
		double RUIKEI_RI;
		long COUNT01;

		bbmsDao.BidInterestRateOrderClear();

		bbmsDao.AddBidInterestRateOrder(dept, bidDate);

		bbmsDao.BidStatusTableClear();

		bbmsDao.AddToBidStatusTable();

		bbmsDao.BidSituationInterestRateCumulativeCalculationClear();

		bbmsDao.BidSituationInterestRateCumulativeCalculation();

		bbmsDao.UpdateBidStatusTable();

		MAE_RITSU = 0;
		MAE_KEY = "0";
		RUIKEI_RI = 0;
		RUIKEI = 0;
		COUNT01 = 1;

		List<HBidStatus> bdrst = bbmsDao.getBidStatus();

		for (HBidStatus rst : bdrst) {
			RUIKEI = RUIKEI + Nz(rst.getBid_amount_of_money());
			rst.setTotal_amount_of_money(RUIKEI);
			RUIKEI_RI = (float) (RUIKEI_RI
					+ Nz(rst.getBid_amount_of_money()) * Nz(rst.getBid_interest_rate()) * 100000000);
			rst.setCumulative_interest(RUIKEI_RI);
			if (Like((double) (RUIKEI_RI / (RUIKEI * 100000000) * 100000), "*.*")) {
				rst.setAverage_rate(INT(RUIKEI_RI / (RUIKEI * 100000000) * 100000) / 100000);
			} else {
				rst.setAverage_rate((double) (RUIKEI_RI / (RUIKEI * 100000000)));
			}
			rst.setSno(COUNT01);
			COUNT01 = COUNT01 + 1;
			if ((rst.getBid_interest_rate() == MAE_RITSU) && (rst.getBank_code() == MAE_KEY)) {
				rst.setBid_interest_rate2(0.0);
				rst.setInterest_rate_by_bid_amount_of_money(0.0);
			} else {
				rst.setBid_interest_rate2(rst.getBid_interest_rate());
			}

			MAE_RITSU = rst.getBid_interest_rate();
			MAE_KEY = rst.getBank_code();
			bbmsDao.UpdateBidStatus2(rst);
		}

		if (SHORI == 1) {
			return "ThereSpecification";
		} else if (SHORI == 2) {
			double InterestRateByBasicPriceTotal = 0.0;
			double largestTotalAmount = 0.0;

			HCommon obj = bbmsDao.getLargestTotalFromBidStatus();
			List<String> datas = obj.getDatas();

			if (!datas.get(0).equals(""))
				InterestRateByBasicPriceTotal = Double.parseDouble(datas.get(0));
			if (!datas.get(1).equals(""))
				largestTotalAmount = Double.parseDouble(datas.get(1));

			if (largestTotalAmount != InterestRateByBasicPriceTotal) {
				return "-2";
			}

			return "NoSpecification~" + String.valueOf(largestTotalAmount);
		}

		return "-1";
	}

	@Override
	public String RI_T_MAKE(int SHORI, String cmbDataUpdated, String txtBidAmountPland, String txtFootCutInterestRates,
			String txtMinimumAmount, String deptcode, String bidDate) {
		double GENDO;
		float saitei;
		double GENDO_RI;
		double BUF01;
		double MAE_BUF01;
		double BUF02;
		double BUF03;
		double BUF04;
		double BUF05;
		double BUF06;
		double ICHIGYOME = 0;
		double ICHIGYOMERI = 0;
		double ANBUN_GOKEI;
		double MAE_RITSU;
		double RUIKEI;
		double RUIKEI_RI;
		long COUNT01;
		String MAE_KINYU;
		String MAE_KINYU_N = "";
		String MAE_BUMON;
		int RAKU_COUNT;
		String dum;
		int han01 = 0;
		int han02;
		int SHORI01;
		int co01;
		int co02;
		int endco;
		int Fco;
		int Page01;
		String LBL_C; // Label caption
		String DAT_S; // Data source

		String returnValue = "-1";

		SHORI01 = 0;
		if (String.valueOf(cmbDataUpdated).equals("しない"))
			SHORI01 = 1;

		if (SHORI01 == 0) {
			if (han01 == 7)
				return "";
			if (txtBidAmountPland.equals("")) {
				GENDO = 1000000;
			} else {
				GENDO = Double.parseDouble(txtBidAmountPland);
			}

			if (txtFootCutInterestRates.equals("")) {
				GENDO_RI = 100;
			} else {
				GENDO_RI = Double.parseDouble(txtFootCutInterestRates);
			}

			if (txtMinimumAmount.equals("")) {
				saitei = 0;
			} else {
				saitei = Float.parseFloat(txtMinimumAmount);
			}

			bbmsDao.getForMinimumAmountDetermined();

			// prorationDataClear();
			bbmsDao.DataClear("proration_datas");

			// bidInterestRateOrderClear();
			bbmsDao.DataClear("bid_data_rate_orders");

			bbmsDao.AddBidInterestRateOrder(GENDO_RI, deptcode, bidDate);

			// Select up to required procurement Price from Sorting table in the
			// order of InterestRate.
			// Also to create data for apportioning together.
			BUF01 = 0;
			MAE_RITSU = 0;
			List<HBidDataRateOrder> rsts = bbmsDao.getBidDataRateOrder();

			han01 = 0;
			for (HBidDataRateOrder rst : rsts) {
				MAE_BUF01 = BUF01;
				double BidPrice = rst.getBid_amount_money();
				if (BidPrice == 0)
					BidPrice = 0;
				BUF01 = BUF01 + BidPrice;
				if (BUF01 <= GENDO) {
					rst.setTotal_amount_money(BUF01);
					bbmsDao.UpdateBidInterestRateOrder(rst);
				} else {
					if (MAE_BUF01 == GENDO && han01 == 0) {
						// In the case of just limit in front of the data
						if (rst.getBid_interest_rate() == MAE_RITSU) {
							MAE_RITSU = rst.getBid_interest_rate();
							bbmsDao.addProrationData(rst);
							han01 = 1;
						} else {
							han01 = 1;
						}
					} else if (han01 == 0) {
						// If below the limit in front of the data
						MAE_RITSU = rst.getBid_interest_rate();
						bbmsDao.addProrationData(rst);
						if (BUF01 >= GENDO) {
							han01 = 1;
						}
					}
					if ((MAE_RITSU == 0 || MAE_RITSU == rst.getBid_interest_rate()) && han01 == 0) {
						MAE_RITSU = rst.getBid_interest_rate();
						bbmsDao.addProrationData(rst);
						han01 = 1;
					}
				}
				MAE_RITSU = rst.getBid_interest_rate();
			}

			MAE_RITSU = 0;
			//////////////////////////////////////////////////
			// updateProrationData();
			List<HBidDataRateOrder> allObj = new ArrayList<HBidDataRateOrder>();

			List<HProrationDataInterestRateOrder> rst = bbmsDao.getProrationDataInterestRateOrder();
			List<HBidDataRateOrder> rst2s = bbmsDao.getBidDataInterestRateOrderLastTotalAmount();

			if (rst.size() > 0) {
				for (HBidDataRateOrder rst2 : rst2s) {
					if (rst.get(0).getBid_interest_rate() == rst2.getBid_interest_rate()) {
						rst2.setTotal_amount_money(0.0);
						
						allObj.add(rst2);
					}
				}
			}

			for (HBidDataRateOrder obj : allObj) {
				bbmsDao.addProrationData(obj);

				bbmsDao.UpdateBidInterestRateOrder(obj);
			}
			// Duplication elimination of data
			bbmsDao.deleteProrationData();

			BUF02 = GENDO - bbmsDao.getTotalAmount();

			ANBUN_GOKEI = bbmsDao.getProratedBidPriceTotal();

			List<HBidDataRateOrder> ProrationData = bbmsDao.getProrationData(1);

			for (HBidDataRateOrder pro : ProrationData) {
				if (Like(BUF02 * pro.getBid_amount_money() / ANBUN_GOKEI + 0.5, "*.*")) {
					pro.setTotal_amount_money(Format(((BUF02 * pro.getBid_amount_money()) / ANBUN_GOKEI), "0.000000"));
				} else {
					// rst!TotalAmount = Format(BUF02 * rst!BidPrice /
					// ANBUN_GOKEI + 0.5, "0.000000")
					pro.setTotal_amount_money(Round(((BUF02 * pro.getBid_amount_money()) / ANBUN_GOKEI), 1000000));
				}

				// rst!TotalAmount = BUF02 * rst!BidPrice / ANBUN_GOKEI
				if (((long) (BUF02 * pro.getBid_amount_money() / ANBUN_GOKEI + 0.5)) == 0) {
					pro.setTotal_amount_money(0.0);
				}

				bbmsDao.UpdateProrationData(pro);
			}

			HProrationdataprocessing rst1 = bbmsDao.getProrationDataProcessing1();
			HProrationdataprocessing rst2 = bbmsDao.getProrationDataProcessing2();

			double total_amount = rst2.getSum_of_total_amount();
			if (total_amount > BUF02) {
				dum = rst1.getBank_code();
				BUF05 = rst1.getTotal_amount_of_money();
			}

			han02 = 10;
			// Set the total amount of the amount subscribed to proration data

			boolean fo = bbmsDao.DataClear("bid_data_by_bank_totals");// DeleteBidDataByBankTotals()
			boolean fo1 = bbmsDao.InsertBidDataByBankTotals();
			boolean fo2 = bbmsDao.UpdateProrationDatas();

			List<HBidDataRateOrder> prorationDatas = bbmsDao.getProrationData(2);
			HBidDataRateOrder obj = new HBidDataRateOrder();
			if (prorationDatas.size() > 0) {
				obj = prorationDatas.get(0);
			}

			HProrationdataprocessing hprst2 = bbmsDao.getProrationDataProcessing2();

			double sum_total_amount = hprst2.getSum_of_total_amount();
			if (sum_total_amount > BUF02) {
				obj.setTotal_amount_money(obj.getTotal_amount_money() - (int) total_amount - BUF02);
				han02 = 20;
				bbmsDao.UpdateProrationData(obj);
			}
			if (han02 == 10) {

				double TotalAmountsTotal = bbmsDao.getProratedTotal();

				List<HBidDataRateOrder> prorationDatas1 = bbmsDao.getProrationData(2);
				HBidDataRateOrder hbro = new HBidDataRateOrder();
				if (prorationDatas1.size() > 0) {
					hbro = prorationDatas1.get(0);
				}

				if (TotalAmountsTotal <= BUF02) {
					hbro.setTotal_amount_money(hbro.getTotal_amount_money() + BUF02 - TotalAmountsTotal);
					han02 = 20;
					bbmsDao.UpdateProrationData(hbro);
				}
			}

			List<HBidDataRateOrder> proratioDatas = bbmsDao.getProrationData(1);
			for (HBidDataRateOrder pro : proratioDatas) {
				double t_amount = pro.getTotal_amount_money();
				pro.setBid_amount_money(t_amount);
				bbmsDao.UpdateProrationDataPrice(pro);
			}

			bbmsDao.DataClear("successful_bid_datas");

			bbmsDao.InsertSuccessfulBidDatas(1, bidDate);

			bbmsDao.InsertSuccessfulBidDatas(2, bidDate);

			bbmsDao.DeleteSuccessfulBidDatas3(deptcode, bidDate);

			BUF01 = 0;
			BUF02 = 0;
			BUF03 = 0;
			BUF04 = 0;
			BUF05 = 0;
			BUF06 = 0;
			RAKU_COUNT = 0;
			MAE_KINYU = "    ";
			MAE_BUMON = "    ";

			List<HSuccessfulBidData> SuccessfulBidDatas2 = bbmsDao.SuccessfulBidData2(bidDate);
			bbmsDao.writeSuccessfulBidData2(SuccessfulBidDatas2);
			List<HSuccessfulBidData> SuccessfulBidDatas = bbmsDao.SuccessfulBidData3();

			for (HSuccessfulBidData hsbd : SuccessfulBidDatas) {
				if (hsbd.getDepartment_code().equals(String.valueOf(deptcode))) {
					if ((!hsbd.getBank_code().equals(MAE_KINYU)) && (!MAE_KINYU.equals("    "))) {
						HSuccessfulBidData3 hsbd2 = new HSuccessfulBidData3();
						hsbd2.setAuction_date(bidDate);
						hsbd2.setDepartment_code(MAE_BUMON);
						hsbd2.setBank_code(MAE_KINYU);
						hsbd2.setFinancial_institution_name(MAE_KINYU_N + " 計");
						hsbd2.setBid_number(98);
						hsbd2.setBid_amount_money(BUF01);
						hsbd2.setBusiness_category("");
						if (BUF02 != 0 && ICHIGYOME != BUF02) {
							if (Like((BUF03 / BUF02 * 100000), "*.*")) {
								String strInterestRate = new DecimalFormat("##.####")
										.format((((BUF03 / BUF02) * 100000) / 100000));
								double interstRate = ((BUF03 / BUF02 * 100000) / 100000);
								if ((!strInterestRate.equals("")) && (!strInterestRate.equals("0")))
									interstRate = Double.parseDouble(strInterestRate);
								hsbd2.setSuccessful_bid_interest_rate(interstRate);
							} else {
								hsbd2.setSuccessful_bid_interest_rate(BUF03 / BUF02);
							}
						} else if (BUF02 != 0 && ICHIGYOME == BUF02) {
							hsbd2.setSuccessful_bid_interest_rate(ICHIGYOMERI);
						} else {
							hsbd2.setSuccessful_bid_interest_rate(0);
						}
						if (BUF02 != 0)
							hsbd2.setSuccessful_bid_price((float) BUF02);
						else {
							hsbd2.setSuccessful_bid_price(0);
							hsbd2.setSuccessful_bid_interest_rate(0);
						}

						bbmsDao.insertSuccessfulBidData3(hsbd2, 1);

						BUF01 = 0;
						BUF02 = 0;
						BUF03 = 0;
					}

					HSuccessfulBidData3 hsbd2 = new HSuccessfulBidData3();
					hsbd2.setAuction_date(bidDate);
					hsbd2.setDepartment_code(hsbd.getDepartment_code());
					hsbd2.setBusiness_category(hsbd.getBusiness_category());
					hsbd2.setBank_code(hsbd.getBank_code());
					MAE_BUMON = hsbd.getDepartment_code();
					MAE_KINYU = hsbd.getBank_code();
					hsbd2.setFinancial_institution_name(hsbd.getFinancial_institution_name());
					hsbd2.setBid_number(hsbd.getBid_number());
					hsbd2.setBid_interest_rate(hsbd.getBid_interest_rate());
					hsbd2.setBid_amount_money(hsbd.getBid_amount_money());
					BUF01 = BUF01 + Nz(hsbd.getBid_amount_money());
					BUF04 = BUF04 + Nz(hsbd.getBid_amount_money());
					hsbd2.setSuccessful_bid_interest_rate(Nz(hsbd.getSuccessful_bid_interest_rate()));
					BUF03 = BUF03
							+ (Nz(hsbd.getSuccessful_bid_interest_rate()) * Nz(hsbd.getSuccessful_bid_amount_money()));
					BUF06 = BUF06
							+ (Nz(hsbd.getSuccessful_bid_interest_rate()) * Nz(hsbd.getSuccessful_bid_amount_money()));
					if (hsbd.getBid_number() == 1) {
						ICHIGYOME = Nz(hsbd.getSuccessful_bid_amount_money());
						ICHIGYOMERI = Nz(hsbd.getSuccessful_bid_interest_rate());
					}
					if (Nz(hsbd.getSuccessful_bid_amount_money()) == 0) {
						hsbd2.setSuccessful_bid_price(0);
					} else {
						hsbd2.setSuccessful_bid_price(Nz(hsbd.getSuccessful_bid_amount_money()));
					}

					BUF02 = BUF02 + Nz(hsbd.getSuccessful_bid_amount_money());
					BUF05 = BUF05 + Nz(hsbd.getSuccessful_bid_amount_money());
					if (hsbd.getSuccessful_bid_amount_money() != 0) {
						RAKU_COUNT = RAKU_COUNT + 1;
					}
					MAE_BUMON = hsbd.getDepartment_code();
					MAE_KINYU = hsbd.getBank_code();
					MAE_KINYU_N = hsbd.getFinancial_institution_name();

					bbmsDao.insertSuccessfulBidData3(hsbd2, 2);

				}
			}

			HSuccessfulBidData3 hsbd2 = new HSuccessfulBidData3();
			hsbd2.setAuction_date(bidDate);
			hsbd2.setDepartment_code(MAE_BUMON);
			hsbd2.setBank_code(MAE_KINYU);
			hsbd2.setFinancial_institution_name(MAE_KINYU_N + " 計");
			hsbd2.setBid_number(98);
			hsbd2.setBid_amount_money(BUF01);
			hsbd2.setBusiness_category("");
			if (BUF02 != 0 && ICHIGYOME != BUF02) {
				if (Like(((BUF03 / BUF02) * 100000), "*.*")) {
					String strInterestRate = new DecimalFormat("##.####").format(((BUF03 / BUF02) * 100000) / 100000);
					double interstRate = (((BUF03 / BUF02) * 100000) / 100000);
					if ((!strInterestRate.equals("")) && (!strInterestRate.equals("0")))
						interstRate = Double.parseDouble(strInterestRate);
					hsbd2.setSuccessful_bid_interest_rate(interstRate);
				} else {
					hsbd2.setSuccessful_bid_interest_rate(BUF03 / BUF02);
				}
			} else if (BUF02 != 0 && ICHIGYOME == BUF02) {
				hsbd2.setSuccessful_bid_interest_rate(ICHIGYOMERI);
			} else {
				hsbd2.setSuccessful_bid_interest_rate(0);
			}
			hsbd2.setSuccessful_bid_price((float) BUF02);
			bbmsDao.insertSuccessfulBidData3(hsbd2, 3);

			HSuccessfulBidData3 rst3 = new HSuccessfulBidData3();
			rst3.setAuction_date(bidDate);
			rst3.setDepartment_code(deptcode);
			rst3.setBank_code(MAE_KINYU);
			rst3.setFinancial_institution_name("合  計");
			rst3.setBid_number(99);
			rst3.setBid_amount_money(BUF04);
			rst3.setBusiness_category("");
			if (BUF05 != 0) {
				if (Like(((BUF06 / BUF05) * 100000), "*.*")) {
					double a = ((int) ((BUF06 / BUF05) * 100000));
					double b = a / 100000;
					rst3.setSuccessful_bid_interest_rate(b);
				} else {
					rst3.setSuccessful_bid_interest_rate(BUF06 / BUF05);
				}
			} else {
				rst3.setSuccessful_bid_interest_rate(0);
			}
			rst3.setSuccessful_bid_price((float) BUF05);
			bbmsDao.insertSuccessfulBidData3(rst3, 4);

			List<HFlgByBankProcessing> frst = new ArrayList<HFlgByBankProcessing>();
			frst = bbmsDao.getFlgByBankProcessing();
			han01 = 0;
			MAE_KINYU = "    ";

			for (HFlgByBankProcessing flbb : frst) {
				HBidDataRateOrder objbiddata = new HBidDataRateOrder();
				objbiddata.setBank_code(flbb.getBank_code());
				objbiddata.setBid_number(flbb.getBid_number());
				objbiddata.setDepartment_code(flbb.getDepartment_code());

				if ((!MAE_KINYU.equals("    ")) && (!objbiddata.getBank_code().equals(MAE_KINYU)))
					han01 = 0;
				if (objbiddata.getBid_number() == 1) {
					double bid_price = flbb.getSuccessful_bid_price();
					if (bid_price == -1)
						han01 = 1;
				}
				int flg_ByBank = 1;
				if (han01 == 1) {
					flg_ByBank = 2;
				} else {
					flg_ByBank = 1;
				}
				bbmsDao.Updateflg_ByBankProcessing(objbiddata, flg_ByBank);
				MAE_KINYU = objbiddata.getBank_code();
			}

			if (SHORI == 1) {
				String params = deptcode + "~" + (int) GENDO;
				returnValue = params;

			} else if (SHORI == 2 || SHORI == 3) {
				// ClearSuccessfulBidSituations();
				bbmsDao.DataClear("successful_bid_situations");

				bbmsDao.AddSuccessfulBidStatusTable(deptcode, bidDate);

				bbmsDao.DataClear("successful_bid_situation_interests");

				bbmsDao.MakeSuccessfulBidSituationInterestRateCumulativeCalculation();

				List<HSuccessfulBidSituations> SuccessfulBidSituations_datas = bbmsDao.getSuccessfulBidSituations();

				bbmsDao.DataClear("successful_bid_situations");

				MAE_RITSU = 0;
				RUIKEI_RI = 0;
				RUIKEI = 0;
				COUNT01 = 1;
				for (HSuccessfulBidSituations hsrst : SuccessfulBidSituations_datas) {
					RUIKEI = RUIKEI + Nz(hsrst.getBid_amount_of_money());
					hsrst.setTotal_amount_of_money(RUIKEI);
					RUIKEI_RI = (RUIKEI_RI
							+ Nz(hsrst.getBid_amount_of_money()) * hsrst.getBid_interest_rate() * 100000000);
					hsrst.setCumulative_interest(RUIKEI_RI);
					if (Like((double) (RUIKEI_RI / (RUIKEI * 100000000) * 100000), "*.*")) {
						double rate = (int) (RUIKEI_RI / (RUIKEI * 100000000) * 100000);
						double avRate = (rate / 100000);
						hsrst.setAverage_rate(avRate);
					} else {
						hsrst.setAverage_rate((double) (RUIKEI_RI / (RUIKEI * 100000000)));
					}
					hsrst.setSno(COUNT01);
					COUNT01 = COUNT01 + 1;
					if (hsrst.getBid_interest_rate() == MAE_RITSU) {
						hsrst.setBid_interest_rate2(0D);
						hsrst.setInterest_rate_by_bid_amount_of_money(0.0);
					} else {
						hsrst.setBid_interest_rate2(hsrst.getBid_interest_rate());
					}
					MAE_RITSU = hsrst.getBid_interest_rate();

					bbmsDao.InsertSuccessfulBidStatusTable(hsrst);
				}

				if (SHORI == 2) {
					returnValue = "2";

				}
			} else {
				if (SHORI == 1) {
					returnValue = "3";
					// OPEN REPORT Financial institutions by

				} else if (SHORI == 2) {
					returnValue = "2";
					// OPEN REPORT Interest rate by

				}
			}

		}
		return returnValue;
	} // end RI_T_MAKE

	@Override
	public String RAKU_PRINT(int SHORI, String deptcode, String bidDate) {
		// Print of a successful bid document
		String returnValue = "-1";
		bbmsDao.UpdateSuccessfulBidData3(bidDate);

		if (SHORI == 1) {
			returnValue = "SuccessfulBidDocument";

		} else if (SHORI == 2) {

			returnValue = "ProvisionalNotice";

		}
		return returnValue;
	} // end RAKU_PRINT

	@Override
	public String KM_PRINT(int SHORI, HCommon obj,int user_id) {
		String returnValue = "-1";
		int han = 0;
		long TNo = 0;
		String datePattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		List<String> datas = obj.getDatas();
		String bidDate = datas.get(0);
		String dept = datas.get(1);
		String deptname = datas.get(2);
		String processing = datas.get(3);
		String unit = datas.get(4);
		String rearrangement = datas.get(5);
		String billRearrangement = datas.get(6);
		String borrowingDate = datas.get(7);
		String maturityDate = datas.get(8);
		String intermidiateInterestPayment = datas.get(9);

		if (SHORI == 1) {
			// System.out.println("Data of ReportBillCreditApplication of
			// accounting that is currently selected and make this Processing
			// are all Clear. BillNumber you enter also all will be erased. is
			// it OK?, 4");
			if (han == 7)
				return returnValue;
			Date DrawerDate = custingStringToDate(borrowingDate, "dd-MM-yyyy");
			Date MaturityDate = custingStringToDate(maturityDate, "dd-MM-yyyy");

			if ((null == DrawerDate) || (null == MaturityDate)) {
				return returnValue;
			}

			TNo = bbmsDao.manageBillCreditApplicationData(dept, bidDate);

			boolean fo = bbmsDao.insertBillCreditApplicationData(dept, bidDate);

			List<HBillCreditApplicationDatas> billCredits = bbmsDao.getBillCreditApplicationData(dept, bidDate);

			for (HBillCreditApplicationDatas rst : billCredits) {
				if (rst.getDepartment_code().equals(dept)) {
					if (Long.parseLong(rst.getBank_code()) < 0100000) {
						String BankName = rst.getFinancial_institution_name();
						rst.setFinancial_institution_name(BankName);
					}

					rst.setDrawer_date(dateFormatter.format(DrawerDate));
					rst.setMaturity_date(dateFormatter.format(MaturityDate));
					rst.setBill_number(FormatSt(TNo, "0000"));

					// rst.setAmount_of_money((float)
					// Math.floor(rst.getSuccessful_bid_price()+0.5) *
					// 100000000);
					double bid_price = rst.getSuccessful_bid_price();
					rst.setAmount_of_money(bid_price * 100000000);

					long duration = MaturityDate.getTime() - DrawerDate.getTime();
					long daysbetween = (duration / (24 * 60 * 60 * 1000));
					rst.setNumber_of_days(daysbetween);
					rst.setInterest_rate(rst.getSuccessful_bid_interest_rate());

					float interest = 0;
					BigDecimal bg = new BigDecimal(rst.getSuccessful_bid_interest_rate());
					BigDecimal bg1;
					bg1 = bg.setScale(3, RoundingMode.FLOOR);
					double successful_bid_rate = Double.parseDouble(bg1.toString());
					rst.setSuccessful_bid_interest_rate(successful_bid_rate);

					if (Like(rst.getSuccessful_bid_price() * 100000000 * (rst.getSuccessful_bid_interest_rate() / 100)
							* daysbetween / 365, "*.*")) {
						interest = Int((((rst.getSuccessful_bid_price() * 100000000)
								* (rst.getSuccessful_bid_interest_rate() / 100)) * daysbetween) / 365);
					} else {
						interest = (float) (((rst.getSuccessful_bid_price() * 100000000)
								* (rst.getSuccessful_bid_interest_rate() / 100)) * daysbetween / 365);
					}

					rst.setInterest_amount(interest);

					boolean upOk = bbmsDao.updateBillCreditApplicationData(rst);
					if (upOk)
						TNo = TNo + 1;
				}
			}
			returnValue = "1~" + "手形申込書データ作成が完了しました";
		} // end SHORI == 1
		else if (SHORI == 2) {
			String interdate = "";
			if (!intermidiateInterestPayment.equals(""))
				interdate = intermidiateInterestPayment;
			Date IntermediateInterestPaymentDate = custingStringToDate(interdate, "dd-MM-yyyy");
			// Set qdf = dbs.QueryDefs("ReportBillCreditApplicationData2")
			if (null == IntermediateInterestPaymentDate) {
				bbmsDao.DropViewIfExist("bill_credit_application_datas2_"+user_id);

				bbmsDao.createViewBill_credit_application_datas2(1, dept, bidDate, null, null);
			} else {
				bbmsDao.DropViewIfExist("bill_credit_application_datas2_"+user_id);

				Date DrawerDate = custingStringToDate(borrowingDate, "dd-MM-yyyy");
				bbmsDao.createViewBill_credit_application_datas2(2, dept, bidDate, DrawerDate,
						IntermediateInterestPaymentDate);
			}
			if (null == IntermediateInterestPaymentDate) {
				// OPEN REPORT CreditApplicationPrint
				returnValue = "2~" + "CreditApplicationPrint";
			} else {
				// OPEN REPORT BillCreditApplicationIntermediateInterestPayments
				returnValue = "2~" + "BillCreditApplicationIntermediateInterestPayments";
			}
		} // end SHORI == 2
		else if (SHORI == 3) {
			bbmsDao.DropViewIfExist("bill_credit_application_datas2_"+user_id);

			bbmsDao.createViewBill_credit_application_datas2(3, dept, bidDate, null, null);

			if (billRearrangement.equals("利率・手 形番号")) {
				if (!processing.equals("債権")) {
					// OPEN REPORT BillApplicationFormList3
					returnValue = "3~" + "BillApplicationFormList3";
				} else {
					// OPEN REPORT CreditorManagementBook3
					returnValue = "3~" + "CreditorManagementBook3";
				}
			} else if (billRearrangement.equals("金融機関")) {
				if (!processing.equals("債権")) {
					// OPEN REPORT BillApplicationFormList
					returnValue = "3~" + "BillApplicationFormList";
				} else {
					// OPEN REPORT CreditorManagementBook
					returnValue = "3~" + "CreditorManagementBook";
				}
			} else {
				if (!processing.equals("債権")) {
					// OPEN REPORT BillApplicationFormList2
					returnValue = "3~" + "BillApplicationFormList2";
				} else {
					// OPEN REPORT CreditorManagementBook2
					returnValue = "3~" + "CreditorManagementBook2";
				}
			}
		} // end SHORI == 3
		else if (SHORI == 4) {
			bbmsDao.DropViewIfExist("bill_credit_application_datas2_"+user_id);

			bbmsDao.createViewBill_credit_application_datas2(4, dept, bidDate, null, null);

			if (rearrangement.equals("金融機関")) {
				// OPEN REPORT ApprovalApplicationFormAttachment
				returnValue = "4~" + "ApprovalApplicationFormAttachment";
			} else if (rearrangement.equals("借入金額")) {
				// OPEN REPORT ApprovalApplicationFormAttachment2
				returnValue = "4~" + "ApprovalApplicationFormAttachment2";
			} else if (rearrangement.equals("利率")) {
				// OPEN REPORT ApprovalApplicationFormAttachment3
				returnValue = "4~" + "ApprovalApplicationFormAttachment3";

			}
		} // end SHORI == 4
		else if (SHORI == 5) {

		} // end SHORI == 5
		return returnValue;
	}// end KM_PRINT

	@Override
	public List<CreditApplicationPrintReport> getCreditApplicationPrint() {
		return bbmsDao.getCreditApplicationPrint();
	}

	@Override
	public List<BillCreditApplicationIntermediateInterestPaymentsReport> getBillCreditApplicationIntermediateInterestPayments() {
		return bbmsDao.getBillCreditApplicationIntermediateInterestPayments();
	}

	@Override
	public List<BillApplicationFormListReport> getBillApplicationFormList(int rpt) {
		return bbmsDao.getBillApplicationFormList(rpt);
	}

	@Override
	public boolean TransferSlip(String bidDate, String deptCode) {
		return bbmsDao.TransferSlip(bidDate, deptCode);
	}

	@Override
	public List<com.ey.bbms.model.rpt.TransferSlip> getTransferSlip() {
	    return bbmsDao.getTransferSlip();
	}

	@Override
	public List<JournalSearchResult> getJournalSearchResult(int journal_no, int slip_no) {
	    return bbmsDao.getJournalSearchResult(journal_no, slip_no);
	}

	@Override
	public List<BillApplicationFormListReport> getLoanAgreementDeed() {
	    return bbmsDao.getLoanAgreementDeed();
	}

	@Override
	public List<HBidData> getBidData() {
	    return bbmsDao.getBidData();
	}

	@Override
	public List<HBillCreditApplicationDatas> getAllBillCreditApplicationData() {
	    return bbmsDao.getAllBillCreditApplicationData();
	}

	@Override
	public List<HSuccessfulBidData3> getSuccessfulBidDocumentAll() {
	    return bbmsDao.getSuccessfulBidDocumentAll();
	}

	@Override
	public boolean addNewUser(HUser user) {
	    return bbmsDao.addNewUser(user);
	}
	
	@Override
	public boolean editUser(HUser user) {
	    return bbmsDao.editUser(user);
	}

	@Override
	public boolean ClearTables(String bidDate, String DepartmentCode) {
	    return bbmsDao.ClearTables(bidDate, DepartmentCode);
	}

}
