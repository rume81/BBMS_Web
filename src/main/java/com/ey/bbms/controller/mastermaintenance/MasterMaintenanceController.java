package com.ey.bbms.controller.mastermaintenance;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bbms.controller.BaseController;
import com.ey.bbms.model.main.HBase;
import com.ey.bbms.model.main.HBusinessCategory;
import com.ey.bbms.model.main.HContactsWording;
import com.ey.bbms.model.main.HDivisions;
import com.ey.bbms.model.main.HFinancialInstitutionsMasters;
import com.ey.bbms.model.main.HNameConversion;
import com.ey.bbms.model.main.HSystemSelection;
import com.ey.bbms.model.main.HSytemSpecificMaintenance;
import com.ey.bbms.model.main.HUser;
import com.ey.bbms.services.mastermaintenance.interfaces.IMasterMaintenanceService;

/**
 * Handles requests for the application Master Maintenance page.
 */
@Controller
public class MasterMaintenanceController extends BaseController implements ApplicationEventPublisherAware {
	
	private static final Logger logger = LoggerFactory.getLogger(MasterMaintenanceController.class);

	private ApplicationContext applicationContext;

	private IMasterMaintenanceService bbmsMasterService;
	
	public HUser getUser(){
	    HUser user = getSessionService().getUserSession().getUser();
	    return user;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher arg0) {
		this.applicationContext = applicationContext;

	}

	public void setBbmsMasterService(IMasterMaintenanceService bbmsMasterService) {
		this.bbmsMasterService = bbmsMasterService;
	}

	@RequestMapping(value = "/mastermaintenance", method = RequestMethod.GET)
	public ModelAndView mastermaintenance(HttpServletRequest request) throws Exception {
		boolean validSession = getSessionService().isSessionValid();

		ModelMap mm = new ModelMap();
		mm.addAttribute("validSession", validSession);
		mm.addAttribute("currentuser", getUser());

		if (!validSession) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("mastermaintenance", mm);
	}

	@RequestMapping(value = "/FinancialInstitutionMaster", method = RequestMethod.GET)
	public ModelAndView FinancialInstitutionMaster(HttpServletRequest request) throws Exception {
		boolean validSession = getSessionService().isSessionValid();

		ModelMap mm = new ModelMap();
		mm.addAttribute("validSession", validSession);
		mm.addAttribute("currentuser", getUser());

		if (!validSession) {
			return new ModelAndView("redirect:/");
		}

		List<HFinancialInstitutionsMasters> finslist = bbmsMasterService.getHFinancialInstitutionsMasters();
		List<HBusinessCategory> busicat = bbmsMasterService.getBusinessCategory();

		mm.addAttribute("finacial", finslist);
		mm.addAttribute("busicat", busicat);

		return new ModelAndView("financialinstitutionmaster", mm);
	}

	@RequestMapping(value = "/ChangeofCertificateWording", method = RequestMethod.GET)
	public ModelAndView ChangeofCertificateWording(HttpServletRequest request) throws Exception {
		boolean validSession = getSessionService().isSessionValid();

		ModelMap mm = new ModelMap();
		mm.addAttribute("validSession", validSession);
		mm.addAttribute("currentuser", getUser());

		if (!validSession) {
			return new ModelAndView("redirect:/");
		}
		List<HContactsWording> contactsWording = bbmsMasterService.getContactsWording();
		List<HDivisions> divisions = bbmsMasterService.getDivisions();

		mm.addAttribute("contactsWording", contactsWording);
		mm.addAttribute("divisions", divisions);

		return new ModelAndView("changeofcertificatewording", mm);
	}

	@RequestMapping(value = "/VariousParametersChange", method = RequestMethod.GET)
	public ModelAndView VariousParametersChange(HttpServletRequest request) throws Exception {
		boolean validSession = getSessionService().isSessionValid();

		ModelMap mm = new ModelMap();
		mm.addAttribute("validSession", validSession);
		mm.addAttribute("currentuser", getUser());

		if (!validSession) {
			return new ModelAndView("redirect:/");
		}
		HBase base = bbmsMasterService.getBase();
		mm.addAttribute("base", base);

		return new ModelAndView("variousparameterschange", mm);
	}

	@RequestMapping(value = "/OfficialNameConversion", method = RequestMethod.GET)
	public ModelAndView OfficialNameConversion(HttpServletRequest request) throws Exception {
		boolean validSession = getSessionService().isSessionValid();

		ModelMap mm = new ModelMap();
		mm.addAttribute("validSession", validSession);
		mm.addAttribute("currentuser", getUser());

		if (!validSession) {
			return new ModelAndView("redirect:/");
		}

		List<HNameConversion> nameConversion = bbmsMasterService.getNameConversion();
		mm.addAttribute("nameConversion", nameConversion);

		return new ModelAndView("officialnameconversion", mm);
	}

	@RequestMapping(value = "/editfinancialinstitution", method = RequestMethod.POST)
	public ModelAndView editfinancialinstitution(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);

		String[] requestUriSplit = requestUri.split("~");

		if (requestUriSplit.length < 5) {
			logger.warn("Expecting atleast 5 arguments but received " + requestUriSplit.length);
			return null;
		}

		String bank_code = requestUriSplit[0];
		String financial_institution_name = requestUriSplit[1];
		String store = requestUriSplit[2];
		String business_category = requestUriSplit[3];
		String DelF = requestUriSplit[4];

		HFinancialInstitutionsMasters obj = new HFinancialInstitutionsMasters();
		
		obj.setBank_code(bank_code);
		obj.setFinancial_institution_name(financial_institution_name);
		obj.setStore(store);
		obj.setBusiness_category(business_category);
		obj.setUpdated(DelF);


		boolean fo = bbmsMasterService.editFinancialInstitution(obj);
		
		List<HFinancialInstitutionsMasters> finacial = new ArrayList<HFinancialInstitutionsMasters>();
		ModelMap mm = new ModelMap();
		
		if(fo){
			finacial = bbmsMasterService.getFinancialInstitutionMasterTemp();
			mm.addAttribute("finacial", finacial);
			return new ModelAndView("financialinstitutionmastersub", mm);
		}
		mm.addAttribute("msg", "-1");
		return new ModelAndView("result", mm);
	}
	
	@RequestMapping(value = "/insertfinancialinstitution", method = RequestMethod.POST)
	public ModelAndView insertfinancialinstitution(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);

		String[] requestUriSplit = requestUri.split("~");

		if (requestUriSplit.length < 4) {
			logger.warn("Expecting atleast 4 arguments but received " + requestUriSplit.length);
			return null;
		}

		String bank_code = requestUriSplit[0];
		String financial_institution_name = requestUriSplit[1];
		String store = requestUriSplit[2];
		String business_category = requestUriSplit[3];

		HFinancialInstitutionsMasters obj = new HFinancialInstitutionsMasters();
		
		obj.setBank_code(bank_code);
		obj.setFinancial_institution_name(financial_institution_name);
		obj.setStore(store);
		obj.setBusiness_category(business_category);

		boolean fo = bbmsMasterService.insertFinancailInstitution(obj);
		
		List<HFinancialInstitutionsMasters> finacial = new ArrayList<HFinancialInstitutionsMasters>();
		ModelMap mm = new ModelMap();
		if(fo){
			finacial = bbmsMasterService.getFinancialInstitutionMasterTemp();
			mm.addAttribute("finacial", finacial);
			return new ModelAndView("financialinstitutionmastersub", mm);
		}
		mm.addAttribute("msg", "1");

		return new ModelAndView("result", mm);
	}
	
	@RequestMapping(value = "/savefinancialmaster", method = RequestMethod.POST)
	public ModelAndView savefinancialmaster(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);
		String delValue = requestUri;
		boolean fo = bbmsMasterService.saveFinancailInstitution(delValue);
		
		int res = -1;
		if(fo)
			res = 1;
		
		ModelMap mm = new ModelMap();
		mm.addAttribute("msg", res);

		return new ModelAndView("result", mm);
	}
	
	@RequestMapping(value = "/insertcertificatewording", method = RequestMethod.POST)
	public ModelAndView insertcertificatewording(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);

		String[] requestUriSplit = requestUri.split("~");

		if (requestUriSplit.length < 3) {
			logger.warn("Expecting atleast 3 arguments but received " + requestUriSplit.length);
			return null;
		}

		String department_code = requestUriSplit[0];
		String horei01 = requestUriSplit[1];
		String horei02 = requestUriSplit[2];

		HContactsWording obj = new HContactsWording();
		
		obj.setDepartment_code(department_code);
		obj.setHorei01(horei01);
		obj.setHorei02(horei02);

		boolean fo = bbmsMasterService.insertCertificateWording(obj);
		
		List<HContactsWording> certificatewording = new ArrayList<HContactsWording>();
		
		ModelMap mm = new ModelMap();

		mm.addAttribute("msg", "1");

		return new ModelAndView("result", mm);
	}
	
	
	@RequestMapping(value = "/updatecertificatewording", method = RequestMethod.POST)
	public ModelAndView updatecertificatewording(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);

		String[] requestUriSplit = requestUri.split("~");

		if (requestUriSplit.length < 3) {
			logger.warn("Expecting atleast 3 arguments but received " + requestUriSplit.length);
			return null;
		}

		String department_code = requestUriSplit[0];
		String horei01 = requestUriSplit[1];
		String horei02 = requestUriSplit[2];

		HContactsWording obj = new HContactsWording();
		
		obj.setDepartment_code(department_code);
		obj.setHorei01(horei01);
		obj.setHorei02(horei02);		

		boolean fo = bbmsMasterService.updateCertificateWording(obj);
		ModelMap mm = new ModelMap();
		if(fo)
			mm.addAttribute("msg", "1");
		else
		mm.addAttribute("msg", "-1");
		
		return new ModelAndView("result", mm);
	}
	
	@RequestMapping(value = "/updatevariousparameters", method = RequestMethod.POST)
	public ModelAndView updatevariousparameters(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);

		String[] requestUriSplit = requestUri.split("~");

		if (requestUriSplit.length < 11) {
			logger.warn("Expecting atleast 11 arguments but received " + requestUriSplit.length);
			return null;
		}

		String chairman_name = requestUriSplit[0];
		String chairman_name_2 = requestUriSplit[1];
		String minister_of_finance = requestUriSplit[2];
		String division_name = requestUriSplit[3];
		String provisional_successful_bid_document_wording = requestUriSplit[4];
		String provisional_successful_bid_document_wording_2 = requestUriSplit[5];
		String successful_bid_document_wording = requestUriSplit[6];
		String successful_bid_document_wording_2 = requestUriSplit[7];
		String credit_application_wording = requestUriSplit[8];
		String street_address = requestUriSplit[9];
		String department_code = requestUriSplit[10];

		HBase obj = new HBase();
		
		obj.setChairman_name(chairman_name);
		obj.setChairman_name_2(chairman_name_2);
		obj.setMinister_of_finance(minister_of_finance);
		obj.setDivision_name(division_name);
		obj.setProvisional_successful_bid_document_wording(provisional_successful_bid_document_wording);
		obj.setProvisional_successful_bid_document_wording_2(provisional_successful_bid_document_wording_2);
		obj.setSuccessful_bid_document_wording(successful_bid_document_wording);
		obj.setSuccessful_bid_document_wording_2(successful_bid_document_wording_2);
		obj.setCredit_application_wording(credit_application_wording);
		obj.setStreet_address(street_address);
		obj.setDepartment_code(department_code);

		boolean fo = bbmsMasterService.updateVariousParameters(obj);
		ModelMap mm = new ModelMap();
		if(fo)
		mm.addAttribute("msg", "1");
		else 
			mm.addAttribute("msg", "-1");
		
		return new ModelAndView("result", mm);
	}
	
	
	@RequestMapping(value = "/updateofficialnameconversion", method = RequestMethod.POST)
	public ModelAndView updateofficialnameconversion(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);

		String[] requestUriSplit = requestUri.split("~");

		if (requestUriSplit.length < 3) {
			logger.warn("Expecting atleast 3 arguments but received " + requestUriSplit.length);
			return null;
		}

		String bank_code = requestUriSplit[0];
		String financial_institution_name = requestUriSplit[1];
		String DelF = requestUriSplit[2];
		
		HNameConversion obj = new HNameConversion();
		
		obj.setBank_code(bank_code);
		obj.setFinancial_institution_name(financial_institution_name);
		obj.setDelF(Integer.parseInt(DelF));

		boolean fo = bbmsMasterService.updateOfficianlNameConversion(obj);
		List<HNameConversion> nameConversion = new ArrayList<HNameConversion>();
		ModelMap mm = new ModelMap();
		
		if(fo) {
			nameConversion = bbmsMasterService.getNameConversionWrk();
			mm.addAttribute("nameConversion", nameConversion);
			return new ModelAndView("officialnameconversionsub", mm);
		}
		mm.addAttribute("msg", "-1");
		return new ModelAndView("result", mm);
	}
	
	@RequestMapping(value = "/saveofficialnameconversion", method = RequestMethod.POST)
	public ModelAndView saveofficialnameconversion(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);
		String delValue = "1";
		boolean fo = bbmsMasterService.saveOfficialNameConversion(delValue);
		
		int res = -1;
		if(fo)
			res = 1;
		
		ModelMap mm = new ModelMap();
		mm.addAttribute("msg", res);

		return new ModelAndView("result", mm);
	}
	
	@RequestMapping(value="/systemSelection", method = RequestMethod.GET) 
	public ModelAndView systemSelection(HttpServletRequest request) throws Exception {
		boolean validSession = getSessionService().isSessionValid();

		ModelMap mm = new ModelMap();
		mm.addAttribute("validSession", validSession);
		mm.addAttribute("currentuser", getUser());

		if (!validSession) {
			return new ModelAndView("redirect:/");
		}

		List<HSystemSelection> systemSelection = bbmsMasterService.getSystemSelection();
		mm.addAttribute("systemSelection", systemSelection);

		return new ModelAndView("systemselection", mm);
	}
	@RequestMapping(value="/systemMaintenance/{fieldname}/{systemname}", method = RequestMethod.GET) 
	public ModelAndView systemMaintenance(@PathVariable("fieldname") String fieldname,@PathVariable("systemname") String systemname,
			 HttpServletRequest request) throws Exception {
		boolean validSession = getSessionService().isSessionValid();

		ModelMap mm = new ModelMap();
		mm.addAttribute("validSession", validSession);
		mm.addAttribute("currentuser", getUser());

		if (!validSession) {
			return new ModelAndView("redirect:/");
		}

		List<HSytemSpecificMaintenance> systemMaintenance = bbmsMasterService.getSystemSpecificiMaintenance();
		mm.addAttribute("systemMaintenance", systemMaintenance);
		mm.addAttribute("fieldname", fieldname);
		mm.addAttribute("systemname", systemname);

		return new ModelAndView("systemmaintenance", mm);
	}
	
	@RequestMapping(value = "/updatefieldname", method = RequestMethod.POST) 
	public ModelAndView updatefieldname(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);			

		String system_name = requestUri;

		HSystemSelection obj = new HSystemSelection();
		
		obj.setSystem_name(system_name);

		HSystemSelection fo = bbmsMasterService.updateFieldName(obj);

		ModelMap mm = new ModelMap();
		
		if((null!=fo) && (!fo.getField_name().equals("")))
			mm.addAttribute("msg", fo.getField_name());
		else 
			mm.addAttribute("msg", "-1");

		return new ModelAndView("result", mm);
	}
	
	@RequestMapping(value = "/updateFinancialInstitutionMasterTemp", method = RequestMethod.POST)
	public ModelAndView updateFinancialInstitutionMasterTemp(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);

		String[] requestUriSplit = requestUri.split("~");

		if (requestUriSplit.length < 3) {
			logger.warn("Expecting atleast 3 arguments but received " + requestUriSplit.length);
			return null;
		}
		
		String fieldname = requestUriSplit[0];
		String usestatus = requestUriSplit[1];
		String bankcode = requestUriSplit[2];
		
		boolean fo = bbmsMasterService.updateSystemSpecificMaintenance(fieldname,usestatus,bankcode);
		List<HSytemSpecificMaintenance> systemMaintenance = new ArrayList<HSytemSpecificMaintenance>();
 		ModelMap mm = new ModelMap();
		
		if(fo){
			systemMaintenance = bbmsMasterService.getSystemSpecificiMaintenanceTemp();
			mm.addAttribute("systemMaintenance", systemMaintenance);
			return new ModelAndView("systemmaintenancesub", mm);
		}
		mm.addAttribute("msg", "-1");
		
		return new ModelAndView("result", mm);
	}
	@RequestMapping(value = "/saveSystemMaintenance", method = RequestMethod.POST)
	public ModelAndView saveSystemMaintenance(HttpServletRequest request) throws Exception {
		String requestUri = getStringFromHttpRequest(request);
		logger.info("requestUri=" + requestUri);
		
		String fieldname = requestUri;
		
		boolean fo = bbmsMasterService.saveSystemMaintenance(fieldname);
		
		String res = "-1";
		if(fo)
			res = "1";
		
		ModelMap mm = new ModelMap();
		mm.addAttribute("msg", res);

		return new ModelAndView("result", mm);
	}
	
}
