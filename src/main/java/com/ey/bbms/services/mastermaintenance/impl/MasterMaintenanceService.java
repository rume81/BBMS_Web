package com.ey.bbms.services.mastermaintenance.impl;

import java.util.List;

import com.ey.bbms.dao.mastermaintenance.interfaces.IMasterMaintenanceDAO;
import com.ey.bbms.model.main.HBase;
import com.ey.bbms.model.main.HBusinessCategory;
import com.ey.bbms.model.main.HContactsWording;
import com.ey.bbms.model.main.HDivisions;
import com.ey.bbms.model.main.HFinancialInstitutionsMasters;
import com.ey.bbms.model.main.HNameConversion;
import com.ey.bbms.model.main.HSystemSelection;
import com.ey.bbms.model.main.HSytemSpecificMaintenance;
import com.ey.bbms.services.mastermaintenance.interfaces.IMasterMaintenanceService;

public class MasterMaintenanceService implements IMasterMaintenanceService {

	private IMasterMaintenanceDAO bbmsMasterDao;

	public void setBbmsMasterDao(IMasterMaintenanceDAO bbmsMasterDao) {
		this.bbmsMasterDao = bbmsMasterDao;
	}

	@Override
	public List<HFinancialInstitutionsMasters> getHFinancialInstitutionsMasters() {
		return bbmsMasterDao.getFinancialInstitutionMaster();
	}
	
	@Override
	public List<HBusinessCategory> getBusinessCategory() {
		return bbmsMasterDao.getBusinessCategory();
	}

	@Override
	public List<HContactsWording> getContactsWording() {
		return bbmsMasterDao.getContactsWording();
	}

	@Override
	public List<HNameConversion> getNameConversion() {
		return bbmsMasterDao.getNameConversion();
	}

	@Override
	public HBase getBase() {
		return bbmsMasterDao.getBase();
	}
	
	@Override
	public boolean editFinancialInstitution(HFinancialInstitutionsMasters obj) {
		return bbmsMasterDao.editFinancialInstitution(obj);
	}
	@Override
	public boolean insertFinancailInstitution(HFinancialInstitutionsMasters obj) {
		return bbmsMasterDao.insertFinancialInstitution(obj);
	}
	@Override
	public boolean saveFinancailInstitution(String delValue){
		return bbmsMasterDao.saveFinancialInstitution(delValue);
	}

	@Override
	public List<HFinancialInstitutionsMasters> getFinancialInstitutionMasterTemp() {
		return bbmsMasterDao.getFinancialInstitutionMasterTemp();
	}
	@Override
	public List<HDivisions> getDivisions() {
		return bbmsMasterDao.getDivisions();
	}

	@Override
	public boolean insertCertificateWording(HContactsWording obj) {
		return bbmsMasterDao.insertCertificateWording(obj);
	}

	@Override
	public boolean updateCertificateWording(HContactsWording obj) {
		return bbmsMasterDao.updateCertificateWording(obj);
	}

	@Override
	public boolean updateVariousParameters(HBase obj) {
		return bbmsMasterDao.updateVariousParameters(obj);
	}

	@Override
	public boolean updateOfficianlNameConversion(HNameConversion obj) {
		return bbmsMasterDao.updateOfficianlNameConversion(obj);
	}
	@Override
	public boolean saveOfficialNameConversion(String delValue){
		return bbmsMasterDao.saveOfficialNameConversion(delValue);
	}
	
	@Override
	public List<HNameConversion> getNameConversionWrk() {
		return bbmsMasterDao.getNameConversionWrk();
	}
	@Override
	public List<HSystemSelection> getSystemSelection() {
		return bbmsMasterDao.getSystemSelection();
	}
	@Override
	public List<HSytemSpecificMaintenance> getSystemSpecificiMaintenance() {
		return bbmsMasterDao.getSystemSpecificiMaintenance();
	}
	@Override
	public boolean updateSystemSpecificMaintenance(String fieldname, String usestatus, String bankcode) {
		return bbmsMasterDao.updateFinancialInstitutionMasterTemp(fieldname, usestatus, bankcode);
	}

	@Override
	public HSystemSelection updateFieldName(HSystemSelection obj) {
		return bbmsMasterDao.updateFieldName(obj);
	}

	@Override
	public List<HSytemSpecificMaintenance> getSystemSpecificiMaintenanceTemp() {
		return bbmsMasterDao.getSystemSpecificiMaintenanceTemp();
	}

	@Override
	public boolean saveSystemMaintenance(String fieldname) {
		return bbmsMasterDao.saveSystemMaintenance(fieldname);
	}
	
}
