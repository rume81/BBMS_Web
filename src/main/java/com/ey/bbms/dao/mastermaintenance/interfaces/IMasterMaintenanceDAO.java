package com.ey.bbms.dao.mastermaintenance.interfaces;

import java.util.List;

import com.ey.bbms.model.main.HBase;
import com.ey.bbms.model.main.HBusinessCategory;
import com.ey.bbms.model.main.HContactsWording;
import com.ey.bbms.model.main.HDivisions;
import com.ey.bbms.model.main.HFinancialInstitutionsMasters;
import com.ey.bbms.model.main.HNameConversion;
import com.ey.bbms.model.main.HSystemSelection;
import com.ey.bbms.model.main.HSytemSpecificMaintenance;

public interface IMasterMaintenanceDAO {

	public List<HFinancialInstitutionsMasters> getFinancialInstitutionMaster();
	
	public List<HBusinessCategory> getBusinessCategory();
	
	public List<HContactsWording> getContactsWording();
	
	public List<HNameConversion> getNameConversion();
	
	public HBase getBase();
	
	public boolean editFinancialInstitution(HFinancialInstitutionsMasters obj);
	
	public boolean insertFinancialInstitution(HFinancialInstitutionsMasters obj);
	
	public boolean saveFinancialInstitution(String delValue);
	
	public List<HFinancialInstitutionsMasters> getFinancialInstitutionMasterTemp();
	
	public List<HDivisions> getDivisions();
	
	public boolean insertCertificateWording(HContactsWording obj);
	
	public boolean updateCertificateWording(HContactsWording obj);
	
	public boolean updateVariousParameters(HBase obj);
	
	public boolean updateOfficianlNameConversion(HNameConversion obj);
	
	public boolean saveOfficialNameConversion(String delValue);
	
	public List<HNameConversion> getNameConversionWrk();
	
	public List<HSystemSelection> getSystemSelection();
	
	public List<HSytemSpecificMaintenance> getSystemSpecificiMaintenance();
	
	public boolean updateFinancialInstitutionMasterTemp(String fieldname, String usestatus, String bankcode);
	
	public HSystemSelection updateFieldName(HSystemSelection obj);
	
	public boolean DropViewIfExist(String viewName);
	
	public List<HSytemSpecificMaintenance> getSystemSpecificiMaintenanceTemp();
	
	public boolean saveSystemMaintenance(String fieldname);
	
}
