package com.ey.bbms.dao.mastermaintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ey.bbms.dao.impl.BaseDAO;
import com.ey.bbms.dao.impl.BbmsDAO;
import com.ey.bbms.dao.mastermaintenance.interfaces.IMasterMaintenanceDAO;
import com.ey.bbms.mapper.BusinessCategoryMapper;
import com.ey.bbms.mapper.DivisionsMapper;
import com.ey.bbms.mapper.HBaseMapper;
import com.ey.bbms.mapper.HContactsWordingMapper;
import com.ey.bbms.mapper.HFinancialInstitutionsMastersMapper;
import com.ey.bbms.mapper.HNameConversionMapper;
import com.ey.bbms.mapper.HSystemSelectionMapper;
import com.ey.bbms.mapper.HSytemSpecificMaintenanceMapper;
import com.ey.bbms.model.main.HBase;
import com.ey.bbms.model.main.HBusinessCategory;
import com.ey.bbms.model.main.HContactsWording;
import com.ey.bbms.model.main.HDivisions;
import com.ey.bbms.model.main.HFinancialInstitutionsMasters;
import com.ey.bbms.model.main.HNameConversion;
import com.ey.bbms.model.main.HSystemSelection;
import com.ey.bbms.model.main.HSytemSpecificMaintenance;

public class MasterMaintenanceDAO extends BaseDAO implements IMasterMaintenanceDAO {

	private final Logger logger = LoggerFactory.getLogger(MasterMaintenanceDAO.class);

	@Override
	public List<HFinancialInstitutionsMasters> getFinancialInstitutionMaster() {

		try {
			StringBuffer strSql = new StringBuffer("DELETE FROM financial_institutions_masters_temp");

			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			StringBuffer strSql = new StringBuffer(
					"INSERT INTO financial_institutions_masters_temp(bank_code, financial_institution_name, "
							+ "store, business_category, F11, F12, F13, F14, F15, F16, F17, F18, F19, F20, F21, F22, F23, F24, F25, F26 , F27, F28, F29, F30, updated) "
							+ "SELECT DISTINCT financial_institutions_masters.bank_code, financial_institutions_masters.financial_institution_name, "
							+ "financial_institutions_masters.store, financial_institutions_masters.business_category, financial_institutions_masters.F11, "
							+ "financial_institutions_masters.F12, financial_institutions_masters.F13, financial_institutions_masters.F14, "
							+ "financial_institutions_masters.F15, financial_institutions_masters.F16, financial_institutions_masters.F17, "
							+ "financial_institutions_masters.F18, financial_institutions_masters.F19, financial_institutions_masters.F20, "
							+ "financial_institutions_masters.F21, financial_institutions_masters.F22, financial_institutions_masters.F23, "
							+ "financial_institutions_masters.F24, financial_institutions_masters.F25, financial_institutions_masters.F26, "
							+ "financial_institutions_masters.F27, financial_institutions_masters.F28, financial_institutions_masters.F29, "
							+ "financial_institutions_masters.F30, financial_institutions_masters.updated FROM financial_institutions_masters");

			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<HFinancialInstitutionsMasters> financialInstitutionMaster = new ArrayList<HFinancialInstitutionsMasters>();
		try {
			financialInstitutionMaster = getJdbcService().getJdbcTemplate().query(
					"SELECT * FROM financial_institutions_masters_temp", new Object[] {},
					new HFinancialInstitutionsMastersMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return financialInstitutionMaster;
	}
	
	@Override
	public List<HFinancialInstitutionsMasters> getFinancialInstitutionMasterTemp() {
		
		List<HFinancialInstitutionsMasters> financialInstitutionMaster = new ArrayList<HFinancialInstitutionsMasters>();
		try {
			financialInstitutionMaster = getJdbcService().getJdbcTemplate().query(
					"SELECT * FROM financial_institutions_masters_temp", new Object[] {},
					new HFinancialInstitutionsMastersMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return financialInstitutionMaster;
	}
	
	
	@Override
	public List<HBusinessCategory> getBusinessCategory() {
		List<HBusinessCategory> businesscategory = new ArrayList<HBusinessCategory>();

		try {
			businesscategory = getJdbcService().getJdbcTemplate().query("SELECT * FROM business_categorys",
					new Object[] {}, new BusinessCategoryMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return businesscategory;
	}

	@Override
	public List<HContactsWording> getContactsWording() {

		List<HContactsWording> contactsWording = new ArrayList<HContactsWording>();
		try {
			contactsWording = getJdbcService().getJdbcTemplate().query("SELECT * FROM contracts_wording",
					new Object[] {}, new HContactsWordingMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return contactsWording;
	}

	@Override
	public List<HNameConversion> getNameConversion() {

/*		List<HNameConversion> nameConversion = new ArrayList<HNameConversion>();

		try {
			nameConversion = getJdbcService().getJdbcTemplate().query(
					"SELECT * FROM name_conversions_wrk order by bank_code", new Object[] {},
					new HNameConversionMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nameConversion;*/
		
		try {
			StringBuffer strSql = new StringBuffer("DELETE FROM name_conversions_wrk");

			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			StringBuffer strSql = new StringBuffer("INSERT INTO name_conversions_wrk(bank_code, abbreviation, financial_institution_name, business_category, DelF) "+  
										"SELECT financial_institutions_masters.bank_code, "+
										"financial_institutions_masters.financial_institution_name, "+ 
										"name_conversions.financial_institution_name, "+ 
										"financial_institutions_masters.business_category, "+ 
										"0 as DelF "+ 
										"FROM financial_institutions_masters "+ 
										"INNER JOIN name_conversions ON financial_institutions_masters.bank_code = name_conversions.bank_code");

			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<HNameConversion> officialNameConversion = new ArrayList<HNameConversion>();
		try {
			officialNameConversion = getJdbcService().getJdbcTemplate().query(
					"SELECT * FROM name_conversions_wrk ORDER BY bank_code", new Object[] {},
					new HNameConversionMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return officialNameConversion;
	}

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

	public boolean editFinancialInstitution(HFinancialInstitutionsMasters obj) {
		boolean fo = false;
		try {
			StringBuffer strSql = new StringBuffer(
					"UPDATE financial_institutions_masters_temp SET " + "financial_institution_name='"
							+ obj.getFinancial_institution_name() + "',store='" + obj.getStore()
							+ "',business_category='" + obj.getBusiness_category() + "',DelF=" + obj.getUpdated()
							+ " WHERE financial_institutions_masters_temp.bank_code='" + obj.getBank_code() + "'");

			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());
			fo = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fo;
	}

	public boolean insertFinancialInstitution(HFinancialInstitutionsMasters obj) {
		boolean fo = false;
		try {
			StringBuffer strSql = new StringBuffer(
					"INSERT INTO financial_institutions_masters_temp (bank_code,financial_institution_name,store,business_category,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30,DelF) VALUES('"
							+ obj.getBank_code() + "','" + obj.getFinancial_institution_name() + "','" + obj.getStore()
							+ "','" + obj.getBusiness_category()
							+ "','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',1)");

			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());
			fo = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fo;
	}
	
	@Override
	public boolean saveFinancialInstitution(String delValue) {

		boolean fo = false;
		if(delValue.equals("1")){
			try {
	
				List<HFinancialInstitutionsMasters> bankCodes = new ArrayList<HFinancialInstitutionsMasters>();
	
				StringBuffer strSql = new StringBuffer("SELECT * from financial_institutions_masters_temp where DelF = -1");
	
				bankCodes = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
						new HFinancialInstitutionsMastersMapper());
	
				for (HFinancialInstitutionsMasters Bcode : bankCodes) {
					StringBuffer strSqldel = new StringBuffer(
							"delete from financial_institutions_masters where bank_code ='" + Bcode.getBank_code() + "'");
	
					logger.info("AddToBidStatusTable Query - > " + strSqldel.toString());
					getJdbcService().getJdbcTemplate().execute(strSqldel.toString());
					// fo = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<HFinancialInstitutionsMasters> finstitute = new ArrayList<HFinancialInstitutionsMasters>();
		try {

			StringBuffer strSql = new StringBuffer("SELECT * from financial_institutions_masters_temp where DelF = 0");

			finstitute = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
					new HFinancialInstitutionsMastersMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			for (HFinancialInstitutionsMasters obj : finstitute) {
				StringBuffer strSql = new StringBuffer("UPDATE financial_institutions_masters SET"
						+ " financial_institution_name='" + obj.getFinancial_institution_name() + "',store='"
						+ obj.getStore() + "',business_category='" + obj.getBusiness_category()
						+ "' WHERE financial_institutions_masters.bank_code='" + obj.getBank_code() + "'");

				// System.out.println(sb);
				// boolean fo = db.doQuery(sb.toString());
				logger.info("AddToBidStatusTable Query - > " + strSql.toString());
				getJdbcService().getJdbcTemplate().execute(strSql.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<HFinancialInstitutionsMasters> finstituteNew = new ArrayList<HFinancialInstitutionsMasters>();
		try {

			StringBuffer strSql = new StringBuffer("SELECT * from financial_institutions_masters_temp where DelF = 1");

			finstituteNew = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
					new HFinancialInstitutionsMastersMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			for (HFinancialInstitutionsMasters obj : finstituteNew) {
				StringBuffer sb = new StringBuffer(
						"INSERT INTO financial_institutions_masters (bank_code,financial_institution_name,store,business_category,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30) VALUES('"
								+ obj.getBank_code() + "','" + obj.getFinancial_institution_name() + "','"
								+ obj.getStore() + "','" + obj.getBusiness_category()
								+ "','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1')");

				// boolean fo = db.doQuery(sb.toString());
				logger.info("AddToBidStatusTable Query - > " + sb.toString());
				getJdbcService().getJdbcTemplate().execute(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		fo= true;
		return fo;
		// JOptionPane.showMessageDialog(null, "更新しました");
	}
	
	@Override
	public List<HDivisions> getDivisions() {

		List<HDivisions> divisions = new ArrayList<HDivisions>();
		try {
			divisions = getJdbcService().getJdbcTemplate().query("SELECT * FROM divisions",
					new Object[] {}, new DivisionsMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return divisions;
	}
	
	@Override
	public boolean insertCertificateWording(HContactsWording obj) {
		boolean fo = false;
		try {
			StringBuffer strSql = new StringBuffer("INSERT INTO contracts_wording (department_code,horei01,horei02) VALUES('"
						+obj.getDepartment_code()+"','"+obj.getHorei01()+ "','"+obj.getHorei02()+"')");
			
			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());
			fo = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fo;
	}
	
	@Override
	public boolean updateCertificateWording(HContactsWording obj) {
		boolean fo = false;
		try {
			StringBuffer strSql = new StringBuffer("UPDATE contracts_wording SET "
						+ "horei01='"+obj.getHorei01()
						+ "',horei02='"+obj.getHorei02()
						+ "' WHERE contracts_wording.department_code='"+obj.getDepartment_code()+"'");
			
			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());
			fo = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fo;
	}
	@Override
	public boolean updateVariousParameters(HBase obj) {
		boolean fo = false;
		try {
			StringBuffer strSql = new StringBuffer("UPDATE base SET "
						+ "chairman_name='"+obj.getChairman_name()
						+ "',chairman_name_2='"+obj.getChairman_name_2()
						+ "',minister_of_finance='"+obj.getMinister_of_finance()
						+ "',street_address='"+obj.getStreet_address()
						+ "',department_name='"+obj.getDivision_name()
						+ "',provisional_successful_bid_document_wording='"+obj.getProvisional_successful_bid_document_wording()
						+ "',successful_bid_document_wording='"+obj.getSuccessful_bid_document_wording()
						+ "',provisional_successful_bid_document_wording_2='"+obj.getProvisional_successful_bid_document_wording_2()
						+ "',successful_bid_document_wording_2='"+obj.getSuccessful_bid_document_wording_2()
						+ "',credit_application_wording='"+obj.getCredit_application_wording()
						+ "' WHERE base.department_code='"+obj.getDepartment_code()+"'");
			
			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());
			fo = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fo;
	}
	
	@Override
	public boolean updateOfficianlNameConversion(HNameConversion obj) {
		boolean fo = false;
		try {
			StringBuffer strSql = new StringBuffer("UPDATE name_conversions_wrk SET "
						+ "financial_institution_name='"+obj.getFinancial_institution_name()
						+ "',DelF="+obj.getDelF()
						+ " WHERE name_conversions_wrk.bank_code='"+obj.getBank_code()+"'");
			
			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());
			fo = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fo;
	}
	@Override
	public boolean saveOfficialNameConversion(String delValue) {
		boolean fo = false;
		if(delValue.equals("1")){
			try {
				List<HNameConversion> bankCodes = new ArrayList<HNameConversion>();
	
				StringBuffer strSql = new StringBuffer("SELECT * from name_conversions_wrk where DelF = -1");
	
				bankCodes = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
						new HNameConversionMapper());
	
				for (HNameConversion Bcode : bankCodes) {
					StringBuffer strSqldel = new StringBuffer(
							"delete from name_conversions where bank_code ='" + Bcode.getBank_code() + "'");
	
					logger.info("AddToBidStatusTable Query - > " + strSqldel.toString());
					getJdbcService().getJdbcTemplate().execute(strSqldel.toString());
					// fo = true;
					//System.out.println(strSql);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		List<HNameConversion> finstitute = new ArrayList<HNameConversion>();
		try {

			StringBuffer strSql = new StringBuffer("SELECT * from name_conversions_wrk where DelF = 0");

			finstitute = getJdbcService().getJdbcTemplate().query(strSql.toString(), new Object[] {},
					new HNameConversionMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			for (HNameConversion obj : finstitute) {
				StringBuffer strSql = new StringBuffer("UPDATE name_conversions SET"
					+ " financial_institution_name='"+obj.getFinancial_institution_name()
					+ "' WHERE name_conversions.bank_code='"+obj.getBank_code()+"'");

				logger.info("AddToBidStatusTable Query - > " + strSql.toString());
				getJdbcService().getJdbcTemplate().execute(strSql.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		fo= true;
		return fo;
	}
	
	@Override
	public List<HNameConversion> getNameConversionWrk() {
		
		List<HNameConversion> nameConversion = new ArrayList<HNameConversion>();
		try {
			nameConversion = getJdbcService().getJdbcTemplate().query(
					"SELECT * FROM name_conversions_wrk", new Object[] {},
					new HNameConversionMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return nameConversion;
	}
	@Override
	public List<HSystemSelection> getSystemSelection() {
		List<HSystemSelection> systemSelection = new ArrayList<HSystemSelection>();
		
		try {
			systemSelection = getJdbcService().getJdbcTemplate().query("SELECT DISTINCT systems_name.system_name FROM systems_name WHERE field_name IS NOT NULL", 
					new Object[] {}, new HSystemSelectionMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return systemSelection;
	}
	//dfasf
	@Override
	public List<HSytemSpecificMaintenance> getSystemSpecificiMaintenance() {
		try {
			StringBuffer strSql = new StringBuffer("DELETE FROM financial_institutions_masters_temp");

			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			StringBuffer strSql = new StringBuffer(
					"INSERT INTO financial_institutions_masters_temp(bank_code, financial_institution_name, "+ 
										"store, business_category, F11, F12, F13, F14, F15, F16, F17, F18, F19, F20, F21, F22, F23, F24, F25, F26 , F27, F28, F29, F30, updated) "+ 
										"SELECT DISTINCT financial_institutions_masters.bank_code, financial_institutions_masters.financial_institution_name, "+ 
										"financial_institutions_masters.store, financial_institutions_masters.business_category, financial_institutions_masters.F11, "+ 
										"financial_institutions_masters.F12, financial_institutions_masters.F13, financial_institutions_masters.F14, "+ 
										"financial_institutions_masters.F15, financial_institutions_masters.F16, financial_institutions_masters.F17, "+ 
										"financial_institutions_masters.F18, financial_institutions_masters.F19, financial_institutions_masters.F20, "+ 
										"financial_institutions_masters.F21, financial_institutions_masters.F22, financial_institutions_masters.F23, "+ 
										"financial_institutions_masters.F24, financial_institutions_masters.F25, financial_institutions_masters.F26, "+ 
										"financial_institutions_masters.F27, financial_institutions_masters.F28, financial_institutions_masters.F29, "+ 
										"financial_institutions_masters.F30, financial_institutions_masters.updated FROM financial_institutions_masters");

			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<HSytemSpecificMaintenance> systemMaintenance = new ArrayList<HSytemSpecificMaintenance>();
		try {
			systemMaintenance = getJdbcService().getJdbcTemplate().query("SELECT * FROM SystemSpecificMaintenance", 
					new Object[] {}, new HSytemSpecificMaintenanceMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return systemMaintenance;
		
	}
	
	
	public HSystemSelection updateFieldName(HSystemSelection obj) {
		boolean fo = DropViewIfExist("SystemSpecificMaintenance");
		
		HSystemSelection systemSelection = new HSystemSelection();
		
		try {
			StringBuffer strSql=new StringBuffer("SELECT * FROM systems_name WHERE system_name='"+obj.getSystem_name()+"' AND field_name IS NOT NULL");
			logger.info("updateFieldName querey:"+strSql.toString());
			systemSelection = getJdbcService().getJdbcTemplate().queryForObject(strSql.toString(),new HSystemSelectionMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			StringBuffer strSql = new StringBuffer("CREATE VIEW SystemSpecificMaintenance AS "+
						"SELECT DISTINCT financial_institutions_masters_temp.bank_code, "+ 
						"financial_institutions_masters_temp.financial_institution_name, "+
						"financial_institutions_masters_temp.store, "+ 
						"financial_institutions_masters_temp.business_category, "+ 
						"financial_institutions_masters_temp."+systemSelection.getField_name()+" AS FN, "+ 
						"financial_institutions_masters_temp.Updated "+
						"FROM financial_institutions_masters_temp");
			logger.info("DropView Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());
			fo = true;
		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		
		return systemSelection;
	}
	public boolean updateFinancialInstitutionMasterTemp(String fieldname, String usestatus, String bankcode) {
		boolean fo = false;
		
		try {
			StringBuffer strSql = new StringBuffer("UPDATE financial_institutions_masters_temp SET "
					+ fieldname+"='"+usestatus
					+ "' WHERE financial_institutions_masters_temp.bank_code='"+bankcode+"'");
			
			logger.info("AddToBidStatusTable Query - > " + strSql.toString());
			getJdbcService().getJdbcTemplate().execute(strSql.toString());
			fo = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fo;
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
	public List<HSytemSpecificMaintenance> getSystemSpecificiMaintenanceTemp() {
		
		List<HSytemSpecificMaintenance> systemMaintenance = new ArrayList<HSytemSpecificMaintenance>();
		try {
			systemMaintenance = getJdbcService().getJdbcTemplate().query("SELECT * FROM SystemSpecificMaintenance", 
					new Object[] {}, new HSytemSpecificMaintenanceMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return systemMaintenance;

		//return systemMaintenance;
	}

	@Override
	public boolean saveSystemMaintenance(String fieldname) {
		boolean fo = false;
		List<HFinancialInstitutionsMasters> finstitute = new ArrayList<HFinancialInstitutionsMasters>();
			
			try {
				StringBuffer strSql=new StringBuffer("SELECT bank_code,"+fieldname+" from financial_institutions_masters_temp");
				//logger.info("saveSystemMaintenance querey:"+strSql.toString());
				finstitute =getJdbcService().getJdbcTemplate().query(strSql.toString(), 
						new Object[] {}, new HFinancialInstitutionsMastersMapper());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
				
			for(HFinancialInstitutionsMasters obj:finstitute){
				String value= "";
				if(fieldname.equals("f11")) {
					value = obj.getF11();
				} else if(fieldname.equalsIgnoreCase("f12")){
					value = obj.getF12();
				} else if(fieldname.equalsIgnoreCase("f13")) {
					value = obj.getF13();
				}else if(fieldname.equalsIgnoreCase("f14")) {
					value = obj.getF14();
				}else if(fieldname.equalsIgnoreCase("f15")) {
					value = obj.getF15();
				}else if(fieldname.equalsIgnoreCase("f16")) {
					value = obj.getF16();
				}else if(fieldname.equalsIgnoreCase("f17")) {
					value = obj.getF17();
				}else if(fieldname.equalsIgnoreCase("f18")) {
					value = obj.getF18();
				}else if(fieldname.equalsIgnoreCase("f19")) {
					value = obj.getF19();
				}else if(fieldname.equalsIgnoreCase("f20")) {
					value = obj.getF20();
				}else if(fieldname.equalsIgnoreCase("f21")) {
					value = obj.getF21();
				}else if(fieldname.equalsIgnoreCase("f22")) {
					value = obj.getF22();
				}else if(fieldname.equalsIgnoreCase("f23")) {
					value = obj.getF23();
				}else if(fieldname.equalsIgnoreCase("f24")) {
					value = obj.getF24();
				}else if(fieldname.equalsIgnoreCase("f25")) {
					value = obj.getF25();
				}else if(fieldname.equalsIgnoreCase("f26")) {
					value = obj.getF26();
				}else if(fieldname.equalsIgnoreCase("f27")) {
					value = obj.getF27();
				}else if(fieldname.equalsIgnoreCase("f28")) {
					value = obj.getF28();
				}else if(fieldname.equalsIgnoreCase("f29")) {
					value = obj.getF29();
				}else if(fieldname.equalsIgnoreCase("f30")) {
					value = obj.getF30();
				}
				
				StringBuffer sb = new StringBuffer("UPDATE financial_institutions_masters SET "
					+ fieldname+"='"+value+ "' WHERE financial_institutions_masters.bank_code='"+obj.getBank_code()+"'");
					
					//System.out.println(sb);
				getJdbcService().getJdbcTemplate().execute(sb.toString());
			
				
			} 
			fo = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	   	
    	//JOptionPane.showMessageDialog(null, "更新しました");
		return fo;
	}

}
