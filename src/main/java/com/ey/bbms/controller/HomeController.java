package com.ey.bbms.controller;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bbms.model.main.HBase;
import com.ey.bbms.model.main.HBidData;
import com.ey.bbms.model.main.HBillCreditApplicationDatas;
import com.ey.bbms.model.main.HCommon;
import com.ey.bbms.model.main.HDivisions;
import com.ey.bbms.model.main.HFinancialInstitutionsMasters;
import com.ey.bbms.model.main.HSuccessfulBidData3;
import com.ey.bbms.model.main.HSuccessfulBidSituations;
import com.ey.bbms.model.main.HUser;
import com.ey.bbms.model.main.UserSession;
import com.ey.bbms.model.rpt.HFinancialInstituteBy;
import com.ey.bbms.model.rpt.JournalSearchResult;
import com.ey.bbms.model.rpt.BillApplicationFormListReport;
import com.ey.bbms.model.rpt.BillCreditApplicationIntermediateInterestPaymentsReport;
import com.ey.bbms.model.rpt.CreditApplicationPrintReport;
import com.ey.bbms.model.rpt.Financialinstitute;
import com.ey.bbms.model.rpt.NoSpecification;
import com.ey.bbms.model.rpt.ThereSpecification;
import com.ey.bbms.model.rpt.SuccessfulBidDocument;
import com.ey.bbms.model.rpt.SuccessfulBidDocumentSub;
import com.ey.bbms.model.rpt.TransferSlip;
import com.ey.bbms.services.interfaces.IBbmsService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private ApplicationContext applicationContext;

    private IBbmsService bbmsService;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.ApplicationContextAware#setApplicationContext
     * (org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	this.applicationContext = applicationContext;
    }

    public void setBbmsService(IBbmsService bbmsService) {
	this.bbmsService = bbmsService;
    }
    
    public HUser getUser(){
	HUser user = getSessionService().getUserSession().getUser();
	return user;
    }
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView init(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	
	if (validSession) {
	    return new ModelAndView("redirect:/home");
	}

	return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView validation(HttpServletRequest request) throws Exception {

	boolean validSession = getSessionService().isSessionValid();

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (validSession) {
	    return new ModelAndView("redirect:/");
	}

	return new ModelAndView("login", mm);
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();

	String bidDate = getSessionService().getUserSession().getBidDate();

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	mm.addAttribute("bidDate", bidDate);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	return new ModelAndView("home", mm);
    }

    @RequestMapping(value = "/BidDataInput/{biddate}", method = RequestMethod.GET)
    public ModelAndView BidDataInput(@PathVariable String biddate, HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	List<HDivisions> divisions = bbmsService.getDivision();
	List<HFinancialInstitutionsMasters> finslist = bbmsService.getHFinancialInstitutionsMasters("");
	HBase base = bbmsService.getBase();

	mm.addAttribute("divisions", divisions);
	mm.addAttribute("finacial", finslist);
	mm.addAttribute("bidDate", biddate);
	mm.addAttribute("base", base);

	return new ModelAndView("biddatainput", mm);
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public ModelAndView userLogout(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);

	getSessionService().invalidateSession(getSessionService().getUserSession().getSessionId());

	return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/rptview/{rptname}/{param}", method = RequestMethod.GET)
    public ModelAndView showFinancialinstituterpt(@PathVariable("rptname") String rptname,
	    @PathVariable("param") String param, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("rptname=" + rptname + ", param=" + param);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	// mm.addAttribute("biddate", biddate);
	mm.addAttribute("rpturl", rptname + "/" + param);
	if(rptname.equals("Financialinstitutepdf")){
	    mm.addAttribute("back", "/home");
	} else if (rptname.equals("ThereSpecification") || rptname.equals("NoSpecification")) {
	    mm.addAttribute("back", "InterestRateOrderBidStatusView/" + param);
	} else if (rptname.equals("SuccessfulBidStatusTableByBank") || rptname.equals("InterestRateBy")) {
	    String[] paramUriSplit = param.split("~");
	    mm.addAttribute("back", "SuccessfulBidDataCreationView/" + paramUriSplit[0]);
	} else if (rptname.equals("ProvisionalNotice") || rptname.equals("SuccessfulBidDocument")) {
	    String[] paramUriSplit = param.split("~");
	    mm.addAttribute("back", "BidDocumentPrintView/" + paramUriSplit[0]);
	} else if (rptname.equals("CreditApplicationPrint")
		|| rptname.equals("BillCreditApplicationIntermediateInterestPayments")
		|| rptname.equals("BillApplicationFormList3") || rptname.equals("BillApplicationFormList2")
		|| rptname.equals("BillApplicationFormList") || rptname.equals("CreditorManagementBook3")
		|| rptname.equals("CreditorManagementBook") || rptname.equals("CreditorManagementBook2")
		|| rptname.equals("ApprovalApplicationFormAttachment")
		|| rptname.equals("ApprovalApplicationFormAttachment2")
		|| rptname.equals("ApprovalApplicationFormAttachment3") || rptname.equals("TransferSlip")
		|| rptname.equals("LoanAgreementDeed")) {
	    String[] paramUriSplit = param.split("~");
	    mm.addAttribute("back", "NotesApplicationFormPrint/" + paramUriSplit[0]);
	}

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	return new ModelAndView("reportView", mm);
    }

    @RequestMapping(value = "/InterestRateOrderBidStatusView/{biddate}", method = RequestMethod.GET)
    public ModelAndView InterestRateOrderBidStatusView(@PathVariable String biddate, HttpServletRequest request)
	    throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	List<HDivisions> divisions = bbmsService.getDivision();
	// List<HFinancialInstitutionsMasters> finslist =
	// bbmsService.getHFinancialInstitutionsMasters("");
	HBase base = bbmsService.getBase();

	mm.addAttribute("divisions", divisions);
	// mm.addAttribute("finacial", finslist);
	mm.addAttribute("bidDate", biddate);
	mm.addAttribute("base", base);

	return new ModelAndView("interestrateorderbidstatus", mm);
    }

    @RequestMapping(value = "/SuccessfulBidDataCreationView/{biddate}", method = RequestMethod.GET)
    public ModelAndView SuccessfulBidDataCreationView(@PathVariable String biddate, HttpServletRequest request)
	    throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	String bidAmountPland = getSessionService().getUserSession().getBidAmountPland();
	String footCutInterestRates = getSessionService().getUserSession().getFootCutInterestRates();

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	List<HDivisions> divisions = bbmsService.getDivision();
	// List<HFinancialInstitutionsMasters> finslist =
	// bbmsService.getHFinancialInstitutionsMasters("");
	HBase base = bbmsService.getBase();

	mm.addAttribute("divisions", divisions);
	// mm.addAttribute("finacial", finslist);
	mm.addAttribute("bidDate", biddate);
	mm.addAttribute("bidAmountPland", bidAmountPland);
	mm.addAttribute("footCutInterestRates", footCutInterestRates);
	mm.addAttribute("base", base);

	return new ModelAndView("successfulbiddatacreation", mm);
    }

    @RequestMapping(value = "/SuccessfulBidDataMaintanenceView/{biddate}", method = RequestMethod.GET)
    public ModelAndView SuccessfulBidDataMaintanenceView(@PathVariable String biddate, HttpServletRequest request)
	    throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// List<HDivisions> divisions = bbmsService.getDivision();
	HBase base = bbmsService.getBase();

	List<HSuccessfulBidData3> dataList = bbmsService.loadBidMaintenanceData(convertDateFormate(biddate),
		base.getDepartment_code());

	// mm.addAttribute("divisions", divisions);
	mm.addAttribute("bidDate", biddate);
	mm.addAttribute("base", base);
	mm.addAttribute("biddata", dataList);

	return new ModelAndView("successfulbiddatamaintanence", mm);
    }

    @RequestMapping(value = "/BidDocumentPrintView/{biddate}", method = RequestMethod.GET)
    public ModelAndView BidDocumentPrintView(@PathVariable String biddate, HttpServletRequest request)
	    throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	List<HDivisions> divisions = bbmsService.getDivision();
	HBase base = bbmsService.getBase();

	mm.addAttribute("divisions", divisions);
	mm.addAttribute("bidDate", biddate);
	mm.addAttribute("biddateJP", ConvertDateToJP(biddate));
	mm.addAttribute("base", base);

	return new ModelAndView("biddocumentprint", mm);
    }

    @RequestMapping(value = "/NotesApplicationFormPrint/{biddate}", method = RequestMethod.GET)
    public ModelAndView NotesApplicationFormPrint(@PathVariable String biddate, HttpServletRequest request)
	    throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	
	String borrowingDate = getSessionService().getUserSession().getBorrowingDate();
	String maturityDate = getSessionService().getUserSession().getMaturityDate();
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	mm.addAttribute("borrowingDate", borrowingDate);
	mm.addAttribute("maturityDate", maturityDate);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	List<HDivisions> divisions = bbmsService.getDivision();
	HBase base = bbmsService.getBase();

	mm.addAttribute("divisions", divisions);
	mm.addAttribute("bidDate", biddate);
	mm.addAttribute("base", base);

	return new ModelAndView("notesapplicationformprint", mm);
    }

    @RequestMapping(value = "/ExcelDownload", method = RequestMethod.GET)
    public ModelAndView ExcelDownload(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}

	return new ModelAndView("exceldownload", mm);
    }

    @RequestMapping(value = "/BidDataDownload", method = RequestMethod.GET)
    public ModelAndView BidDataDownload(HttpServletRequest request, HttpServletResponse response) {
	boolean validSession = getSessionService().isSessionValid();

	ModelMap mm = new ModelMap();
	if (!validSession) {
	    return new ModelAndView("redirect:/");
	} else {
	    
	    List<HBidData> biddataList = new ArrayList<HBidData>();
	    biddataList = bbmsService.getBidData();

	    return new ModelAndView("bidDataListExcel", "bidList", biddataList);
	}
    }
    
    @RequestMapping(value = "/BorrowingDataDownload", method = RequestMethod.GET)
    public ModelAndView BorrowingDataDownload(HttpServletRequest request, HttpServletResponse response) {
	boolean validSession = getSessionService().isSessionValid();

	ModelMap mm = new ModelMap();
	if (!validSession) {
	    return new ModelAndView("redirect:/");
	} else {
	    
	    List<HBillCreditApplicationDatas> dataList = new ArrayList<HBillCreditApplicationDatas>();
	    dataList = bbmsService.getAllBillCreditApplicationData();

	    return new ModelAndView("borrowingDataListExcel", "dataList", dataList);
	}
    }
    
    @RequestMapping(value = "/SuccessfulBidDataDownload", method = RequestMethod.GET)
    public ModelAndView SuccessfulBidDataDownload(HttpServletRequest request, HttpServletResponse response) {
	boolean validSession = getSessionService().isSessionValid();

	ModelMap mm = new ModelMap();
	if (!validSession) {
	    return new ModelAndView("redirect:/");
	} else {
	    
	    List<HSuccessfulBidData3> dataList = new ArrayList<HSuccessfulBidData3>();
	    dataList = bbmsService.getSuccessfulBidDocumentAll();

	    return new ModelAndView("successfulBidDataListExcel", "dataList", dataList);
	}
    }
    
    @RequestMapping(value = "/createnewuser", method = RequestMethod.GET)
    public ModelAndView createnewuser(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}

	return new ModelAndView("createnewuser", mm);
    }
    
    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public ModelAndView edituser(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	
	List<HUser> users= new ArrayList<HUser>();
	users = bbmsService.getAllUser();
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	mm.addAttribute("users", users);
	

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}

	return new ModelAndView("edituser", mm);
    }
    
    @RequestMapping(value = "/changepass", method = RequestMethod.GET)
    public ModelAndView changepass(HttpServletRequest request) throws Exception {
	boolean validSession = getSessionService().isSessionValid();
	
	List<HUser> users= new ArrayList<HUser>();
	users = bbmsService.getAllUser();
	
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);
	mm.addAttribute("currentuser", getUser());
	mm.addAttribute("users", users);
	

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}

	return new ModelAndView("changepass", mm);
    }
    // ==================================================REPORT==========================================================
    @RequestMapping(value = "/Financialinstitutepdf/{biddate}", method = RequestMethod.GET)
    public ModelAndView generatePdfReport(@PathVariable String biddate, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<Financialinstitute> financialinstituteList = bbmsService
		.getFinancialInstituteList(convertDateFormate(biddate));
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(financialinstituteList);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportFinancialinstitute", parameters);

    }// generatePdfReport

    @RequestMapping(value = "/ThereSpecification/{biddate}", method = RequestMethod.GET)
    public ModelAndView thereSpecification(@PathVariable String biddate, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<ThereSpecification> thereSpecification = bbmsService.getThereSpecification(convertDateFormate(biddate));

	int count_metter = 0;
	Set<String> bank_code = new HashSet<String>();
	for (ThereSpecification sp : thereSpecification) {
	    if ((sp.getBid_number() == 1) && (sp.getBid_amount_of_money() != 0))
		bank_code.add(sp.getBank_code());
	}
	count_metter = bank_code.size();

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(thereSpecification);

	parameters.put("datasource", JRdataSource);
	parameters.put("count_metter", count_metter);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportThereSpecification", parameters);

    }// generatePdfReport

    @RequestMapping(value = "/NoSpecification/{biddate}", method = RequestMethod.GET)
    public ModelAndView noSpecification(@PathVariable String biddate, HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("biddate=" + biddate);
	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<NoSpecification> noSpecification = bbmsService.getNoSpecification(convertDateFormate(biddate));
	JRDataSource JRdataSource = new JRBeanCollectionDataSource(noSpecification);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportNoSpecification", parameters);

    }// generatePdfReport

    @RequestMapping(value = "/InterestRateBy/{param}", method = RequestMethod.GET)
    public ModelAndView InterestRateBy(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<HSuccessfulBidSituations> interestRateBy = bbmsService.getInterestRateBy(bidDateforDB, deptcode);
	int count_metter = 0;
	Set<String> bank_code = new HashSet<String>();
	for (HSuccessfulBidSituations sp : interestRateBy) {
	    bank_code.add(sp.getBank_code());
	}
	count_metter = bank_code.size();

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(interestRateBy);

	parameters.put("datasource", JRdataSource);
	parameters.put("count_metter", count_metter);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportInterestRateBy", parameters);

    }// InterestRateBy

    @RequestMapping(value = "/SuccessfulBidStatusTableByBank/{param}", method = RequestMethod.GET)
    public ModelAndView SuccessfulBidStatusTableByBank(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");

	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];
	String bidPlaned = "";
	boolean bp = false;
	if (requestUriSplit.length == 3) {
	    bidPlaned = requestUriSplit[2];
	    bp = true;
	}

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<HSuccessfulBidData3> fiBy = bbmsService.getFinancialInstituteBy(bidDateforDB, deptcode);
	int count_metter = 0;
	Set<String> bank_code = new HashSet<String>();
	for (HSuccessfulBidData3 sp : fiBy) {
	    bank_code.add(sp.getBank_code());
	}
	count_metter = bank_code.size();

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(fiBy, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("count_metter", count_metter);
	if (bp) {
	    parameters.put("bidPlaned", bidPlaned);
	}
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportSuccessfulBidStatusTableByBank", parameters);

    }// SuccessfulBidStatusTableByBank

    @RequestMapping(value = "/SuccessfulBidDocument/{param}", method = RequestMethod.GET)
    public ModelAndView SuccessfulBidDocument(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<SuccessfulBidDocument> successfulBidDocument = bbmsService.getSuccessfulBidDocument(bidDateforDB,
		deptcode);
	List<SuccessfulBidDocumentSub> successfulBidDocumentSub = new ArrayList<SuccessfulBidDocumentSub>();

	for (SuccessfulBidDocument sbd : successfulBidDocument) {
	    List<SuccessfulBidDocumentSub> successfulBidDocumentSubtemp = bbmsService
		    .getSuccessfulBidDocumentSub(bidDateforDB, deptcode, sbd.getBank_code());
	    for (SuccessfulBidDocumentSub sub : successfulBidDocumentSubtemp) {
		successfulBidDocumentSub.add(sub);
	    }
	}

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(successfulBidDocument, false);
	JRDataSource JRCustomSubReportdataSource = new JRBeanCollectionDataSource(successfulBidDocumentSub, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("JasperCustomSubReportDatasource", JRCustomSubReportdataSource);
	parameters.put("dept_code", deptcode);
	parameters.put("auction_date", bidDateforDB);
	parameters.put("auctionJp_date", ConvertDateToJP(biddate));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportSuccessfulBidDocument", parameters);

    }// SuccessfulBidDocument

    @RequestMapping(value = "/ProvisionalNotice/{param}", method = RequestMethod.GET)
    public ModelAndView ProvisionalNotice(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<SuccessfulBidDocument> successfulBidDocument = bbmsService.getSuccessfulBidDocument(bidDateforDB,
		deptcode);
	List<SuccessfulBidDocumentSub> successfulBidDocumentSub = new ArrayList<SuccessfulBidDocumentSub>();

	for (SuccessfulBidDocument sbd : successfulBidDocument) {
	    List<SuccessfulBidDocumentSub> successfulBidDocumentSubtemp = bbmsService
		    .getSuccessfulBidDocumentSub(bidDateforDB, deptcode, sbd.getBank_code());
	    for (SuccessfulBidDocumentSub sub : successfulBidDocumentSubtemp) {
		successfulBidDocumentSub.add(sub);
	    }
	}

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(successfulBidDocument, false);
	JRDataSource JRCustomSubReportdataSource = new JRBeanCollectionDataSource(successfulBidDocumentSub, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("JasperCustomSubReportDatasource", JRCustomSubReportdataSource);
	parameters.put("dept_code", deptcode);
	parameters.put("auction_date", bidDateforDB);
	parameters.put("auctionJp_date", ConvertDateToJP(biddate));
	parameters.put("imagePath", getClass().getClassLoader().getResource("provition.png").getPath());

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportProvisionalNotice", parameters);

    }// ProvisionalNotice

    @RequestMapping(value = "/CreditApplicationPrint/{param}", method = RequestMethod.GET)
    public ModelAndView CreditApplicationPrint(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<CreditApplicationPrintReport> creditapplicationPrint = bbmsService.getCreditApplicationPrint();

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(creditapplicationPrint);

	parameters.put("datasource", JRdataSource);

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportCreditApplicationPrint", parameters);

    }// CreditApplicationPrint

    @RequestMapping(value = "/BillCreditApplicationIntermediateInterestPayments/{param}", method = RequestMethod.GET)
    public ModelAndView BillCreditApplicationIntermediateInterestPayments(@PathVariable("param") String param,
	    HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillCreditApplicationIntermediateInterestPaymentsReport> datas = bbmsService
		.getBillCreditApplicationIntermediateInterestPayments();

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportBillCreditApplicationIntermediateInterestPayments", parameters);

    }// BillCreditApplicationIntermediateInterestPayments

    @RequestMapping(value = "/BillApplicationFormList3/{param}", method = RequestMethod.GET)
    public ModelAndView BillApplicationFormList3(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(3);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportBillApplicationFormList3", parameters);

    }// BillApplicationFormList3

    @RequestMapping(value = "/BillApplicationFormList2/{param}", method = RequestMethod.GET)
    public ModelAndView BillApplicationFormList2(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(2);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportBillApplicationFormList2", parameters);

    }// BillApplicationFormList2

    @RequestMapping(value = "/BillApplicationFormList/{param}", method = RequestMethod.GET)
    public ModelAndView BillApplicationFormList(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(2);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportBillApplicationFormList", parameters);

    }// BillApplicationFormList

    @RequestMapping(value = "/CreditorManagementBook3/{param}", method = RequestMethod.GET)
    public ModelAndView CreditorManagementBook3(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(33);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	parameters.put("CurrentDate", ConvertCDateToJP());
	parameters.put("auctionJp_date", ConvertDateToJP(biddate));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportCreditorManagementBook3", parameters);

    }// CreditorManagementBook3

    @RequestMapping(value = "/CreditorManagementBook/{param}", method = RequestMethod.GET)
    public ModelAndView CreditorManagementBook(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(31);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	parameters.put("CurrentDate", ConvertCDateToJP());
	parameters.put("auctionJp_date", ConvertDateToJP(biddate));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportCreditorManagementBook", parameters);

    }// CreditorManagementBook

    @RequestMapping(value = "/CreditorManagementBook2/{param}", method = RequestMethod.GET)
    public ModelAndView CreditorManagementBook2(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(31);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
	parameters.put("CurrentDate", ConvertCDateToJP());
	parameters.put("auctionJp_date", ConvertDateToJP(biddate));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportCreditorManagementBook2", parameters);

    }// CreditorManagementBook2

    @RequestMapping(value = "/ApprovalApplicationFormAttachment/{param}", method = RequestMethod.GET)
    public ModelAndView ApprovalApplicationFormAttachment(@PathVariable("param") String param,
	    HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(41);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportApprovalApplicationFormAttachment", parameters);

    }// ApprovalApplicationFormAttachment

    @RequestMapping(value = "/ApprovalApplicationFormAttachment2/{param}", method = RequestMethod.GET)
    public ModelAndView ApprovalApplicationFormAttachment2(@PathVariable("param") String param,
	    HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(42);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportApprovalApplicationFormAttachment2", parameters);

    }// ApprovalApplicationFormAttachment2

    @RequestMapping(value = "/ApprovalApplicationFormAttachment3/{param}", method = RequestMethod.GET)
    public ModelAndView ApprovalApplicationFormAttachment3(@PathVariable("param") String param,
	    HttpServletRequest request) throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getBillApplicationFormList(43);

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportApprovalApplicationFormAttachment3", parameters);

    }// ApprovalApplicationFormAttachment3

    @RequestMapping(value = "/TransferSlip/{param}", method = RequestMethod.GET)
    public ModelAndView TransferSlip(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<TransferSlip> transferSlip = bbmsService.getTransferSlip();
	List<JournalSearchResult> journalSearchResultSub = new ArrayList<JournalSearchResult>();

	for (TransferSlip sbd : transferSlip) {
	    List<JournalSearchResult> journalSearchResult = bbmsService.getJournalSearchResult(sbd.getJournal_number(),
		    sbd.getSlip_number());
	    for (JournalSearchResult sub : journalSearchResult) {
		journalSearchResultSub.add(sub);
	    }
	}

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(transferSlip, false);
	JRDataSource JRCustomSubReportdataSource = new JRBeanCollectionDataSource(journalSearchResultSub, false);

	parameters.put("datasource", JRdataSource);
	parameters.put("JasperCustomSubReportDatasource", JRCustomSubReportdataSource);
	// parameters.put("dept_code", deptcode);
	// parameters.put("auction_date", bidDateforDB);
	// parameters.put("auctionJp_date", ConvertDateToJP(biddate));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportTransferSlip", parameters);

    }// TransferSlip

    @RequestMapping(value = "/LoanAgreementDeed/{param}", method = RequestMethod.GET)
    public ModelAndView LoanAgreementDeed(@PathVariable("param") String param, HttpServletRequest request)
	    throws JRException {
	boolean validSession = getSessionService().isSessionValid();
	logger.info("param=" + param);

	String[] requestUriSplit = param.split("~");
	String biddate = requestUriSplit[0];
	String deptcode = requestUriSplit[1];

	String bidDateforDB = convertDateFormate(biddate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("validSession", validSession);

	if (!validSession) {
	    return new ModelAndView("redirect:/");
	}
	// logger.debug("--------------generate PDF report----------");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "KozMinPro-Regular.otf");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
	JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", false);

	Map<String, Object> parameters = new HashMap<String, Object>();
	List<BillApplicationFormListReport> datas = bbmsService.getLoanAgreementDeed();

	JRDataSource JRdataSource = new JRBeanCollectionDataSource(datas, false);

	parameters.put("datasource", JRdataSource);
	// parameters.put("CurrentDateTime", String.valueOf(format.format(new
	// Date())));

	// pdfReport bean has ben declared in the jasper-views.xml file

	return new ModelAndView("pdfReportLoanAgreementDeed", parameters);

    }// ApprovalApplicationFormAttachment3

    // ==================================================POST============================================================

    @RequestMapping(value = "/users/valideduser", method = RequestMethod.POST)
    public ModelAndView userVerify(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 2) {
	    logger.warn("Expecting atleast 2 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String uname = requestUriSplit[0];
	String pass = requestUriSplit[1];

	HUser user = new HUser();
	user.setUser_name(uname);
	user.setPassword(pass);

	HUser sysuser = bbmsService.getUserValidation(user);
	String user_name = "-1";
	if (sysuser != null) {
	    UserSession session = getSessionService().insertSession(request, sysuser);
	    user_name = sysuser.getUser_name();
	}

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", user_name);

	return new ModelAndView("result", mm);
    }

    @RequestMapping(value = "/financialinstitutedetails", method = RequestMethod.POST)
    public ModelAndView financialinstitutedetails(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 3) {
	    logger.warn("Expecting atleast 3 arguments but received " + requestUriSplit.length);
	    return null;
	}

	ModelMap mm = new ModelMap();

	String biddate = requestUriSplit[0];
	String bankcode = requestUriSplit[1];
	String deptcode = requestUriSplit[2];

	List<HFinancialInstitutionsMasters> finslist = bbmsService.getHFinancialInstitutionsMasters(bankcode);
	List<HBidData> biddata = bbmsService.getBidData(convertDateFormate(biddate), bankcode, deptcode);
	Integer count = bbmsService.getRowCountOfBidDataForABank(biddate, bankcode, deptcode);

	if (finslist.size() > 0 || biddata.size() > 0) {
	    mm.addAttribute("bankName", finslist.get(0).getFinancial_institution_name());
	    mm.addAttribute("busCategory", finslist.get(0).getBusiness_category());
	    mm.addAttribute("biddata", biddata);
	    mm.addAttribute("rowcount", count);
	    return new ModelAndView("biddatainputsub", mm);
	} else {
	    mm.addAttribute("msg", "-1");
	    return new ModelAndView("result", mm);
	}
    }

    @RequestMapping(value = "/biddataderegister", method = RequestMethod.POST)
    public ModelAndView getRowCountOfBidDataForABank(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 3) {
	    logger.warn("Expecting atleast 3 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String bidDate = requestUriSplit[0];
	String bankcode = requestUriSplit[1];
	String deptcode = requestUriSplit[2];

	String bidDateforDB = convertDateFormate(bidDate);

	Boolean Fo = bbmsService.deleteRowOfBidDataForABank(bidDateforDB, bankcode, deptcode);

	ModelMap mm = new ModelMap();
	if (Fo)
	    mm.addAttribute("msg", "1");
	else
	    mm.addAttribute("msg", "-1");

	return new ModelAndView("result", mm);
    }

    @RequestMapping(value = "/biddataregister", method = RequestMethod.POST)
    public ModelAndView addleaveRequest(HttpServletRequest request, HttpServletResponse response)
	    throws MessagingException {
	UserSession session = getSessionService().getUserSession();

	Map<String, String[]> parameterMap = request.getParameterMap();

	boolean isOk = true;
	double interestRate[] = { 0, 0, 0, 0, 0 };
	double bidAmoung[] = { 0, 0, 0, 0, 0 };

	if (!parameterMap.get("txtInterestRate")[0].equals(""))
	    interestRate[0] = Double.parseDouble(parameterMap.get("txtInterestRate")[0]);
	if (!parameterMap.get("txtInterestRate")[1].equals(""))
	    interestRate[1] = Double.parseDouble(parameterMap.get("txtInterestRate")[1]);
	if (!parameterMap.get("txtInterestRate")[2].equals(""))
	    interestRate[2] = Double.parseDouble(parameterMap.get("txtInterestRate")[2]);
	if (!parameterMap.get("txtInterestRate")[3].equals(""))
	    interestRate[3] = Double.parseDouble(parameterMap.get("txtInterestRate")[3]);
	if (!parameterMap.get("txtInterestRate")[4].equals(""))
	    interestRate[4] = Double.parseDouble(parameterMap.get("txtInterestRate")[4]);

	if (!parameterMap.get("txtBidAmount")[0].equals(""))
	    bidAmoung[0] = Double.parseDouble(parameterMap.get("txtBidAmount")[0]);
	if (!parameterMap.get("txtBidAmount")[1].equals(""))
	    bidAmoung[1] = Double.parseDouble(parameterMap.get("txtBidAmount")[1]);
	if (!parameterMap.get("txtBidAmount")[2].equals(""))
	    bidAmoung[2] = Double.parseDouble(parameterMap.get("txtBidAmount")[2]);
	if (!parameterMap.get("txtBidAmount")[3].equals(""))
	    bidAmoung[3] = Double.parseDouble(parameterMap.get("txtBidAmount")[3]);
	if (!parameterMap.get("txtBidAmount")[4].equals(""))
	    bidAmoung[4] = Double.parseDouble(parameterMap.get("txtBidAmount")[4]);

	String bankcode = String.valueOf(parameterMap.get("financial_institute")[0]);
	String deptcode = String.valueOf(parameterMap.get("departmentCode")[0]);
	String bidDate = String.valueOf(parameterMap.get("bidDate")[0]);
	String business_category = String.valueOf(parameterMap.get("busi_cat")[0]);
	String financial_institution_name = String.valueOf(parameterMap.get("inst_name")[0]);
	String txtTotalAmount = String.valueOf(parameterMap.get("txtTotalAmount")[0]);

	String bidDateforDB = convertDateFormate(bidDate);

	boolean Fo = bbmsService.deleteRowOfBidDataForABank(bidDateforDB, bankcode, deptcode);

	List<HBidData> biddatas = new ArrayList<HBidData>();
	for (int i = 1; i <= 5; i++) {
	    HBidData biddata = new HBidData();
	    biddata.setDepartment_code(deptcode);
	    biddata.setBusiness_category(business_category);
	    biddata.setBank_code(bankcode);
	    biddata.setFinancial_institution_name(financial_institution_name);
	    biddata.setBid_number(i);
	    biddata.setBid_interest_rate(interestRate[i - 1]);
	    biddata.setBid_amount_money(bidAmoung[i - 1]);
	    biddata.setAuction_date(bidDateforDB);

	    biddatas.add(biddata);
	}

	Fo = bbmsService.addRowOfBidDataForABank(biddatas);

	if (Integer.parseInt(txtTotalAmount) > 0) {
	    Fo = bbmsService.updateFlagOfBidDataForABank(bidDateforDB, bankcode, deptcode, "1");
	} else {
	    Fo = bbmsService.updateFlagOfBidDataForABank(bidDateforDB, bankcode, deptcode, "0");
	}

	ModelMap mm = new ModelMap();
	if (Fo)
	    mm.addAttribute("msg", "1");
	else
	    mm.addAttribute("msg", "-1");

	return new ModelAndView("result", mm);

    }

    @RequestMapping(value = "/setbiddateinsession", method = RequestMethod.POST)
    public ModelAndView setbiddateinsession(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String bidDate = requestUri;

	getSessionService().getUserSession().setBidDate(bidDate);
	// String bidDateforDB = convertDateFormate(bidDate);

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", "1");

	return new ModelAndView("result", mm);
    }
    
    
    @RequestMapping(value = "/setbormatdateinsession", method = RequestMethod.POST)
    public ModelAndView setbormatdateinsession(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");
	
	String bordate = requestUriSplit[0];
	String matdate = requestUriSplit[1];
	
	if(!bordate.equals("0"))
		getSessionService().getUserSession().setBorrowingDate(bordate);
	else
		getSessionService().getUserSession().setBorrowingDate("");
	
	if(!matdate.equals("0"))
		getSessionService().getUserSession().setMaturityDate(matdate);
	else
		getSessionService().getUserSession().setMaturityDate("");

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", "1");

	return new ModelAndView("result", mm);
    }
        
    @RequestMapping(value = "/setbidplanintrateinsession", method = RequestMethod.POST)
    public ModelAndView setbidplanintrateinsession(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");
	
	String bidplan = requestUriSplit[0];
	String intrate = requestUriSplit[1];
	
	if(!bidplan.equals("@"))
		getSessionService().getUserSession().setBidAmountPland(bidplan);
	else
		getSessionService().getUserSession().setBidAmountPland("");
	
	if(!intrate.equals("@"))
		getSessionService().getUserSession().setFootCutInterestRates(intrate);
	else
		getSessionService().getUserSession().setFootCutInterestRates("");

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", "1");

	return new ModelAndView("result", mm);
    }

    @RequestMapping(value = "/interestrateorderbidstatus", method = RequestMethod.POST)
    public ModelAndView interestrateorderbidstatus(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 4) {
	    logger.warn("Expecting atleast 4 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String bidDate = requestUriSplit[0];
	String bidDateforDB = convertDateFormate(bidDate);

	String dept = requestUriSplit[1];
	String deptname = requestUriSplit[2];
	int SHORI = Integer.parseInt(requestUriSplit[3]);

	String callReport = bbmsService.NRJ_T_MAKE(SHORI, dept, bidDateforDB);

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", callReport);

	return new ModelAndView("result", mm);
    }

    @RequestMapping(value = "/SuccessfulBidDataCreation", method = RequestMethod.POST)
    public ModelAndView SuccessfulBidDataCreation(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 8) {
	    logger.warn("Expecting atleast 8 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String bidDate = requestUriSplit[0];
	String bidDateforDB = convertDateFormate(bidDate);

	String deptname = requestUriSplit[1];
	int SHORI = Integer.parseInt(requestUriSplit[2]);
	String cmbDataUpdated = requestUriSplit[3];
	String txtMinimumAmount = requestUriSplit[4];
	String txtBidAmountPland = requestUriSplit[5];
	String txtFootCutInterestRates = requestUriSplit[6];
	String dept = requestUriSplit[7];
	
	String callReport = bbmsService.RI_T_MAKE(SHORI, cmbDataUpdated, txtBidAmountPland, txtFootCutInterestRates,
		txtMinimumAmount, dept, bidDateforDB);

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", callReport);

	return new ModelAndView("result", mm);
    }

    @RequestMapping(value = "/editbiddata", method = RequestMethod.POST)
    public ModelAndView editbiddata(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 11) {
	    logger.warn("Expecting atleast 11 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String department_code = requestUriSplit[0];
	String business_category = requestUriSplit[1];
	String bank_code = requestUriSplit[2];
	String financial_institution_name = requestUriSplit[3];
	String bid_number = requestUriSplit[4];
	String bid_interest_rate = requestUriSplit[5];
	String bid_amount_money = requestUriSplit[6];
	String successful_bid_interest_rate = requestUriSplit[7];
	String successful_bid_price = requestUriSplit[8];
	String deptCode = requestUriSplit[9];
	String bidDate = requestUriSplit[10];

	String bidDateforDB = convertDateFormate(bidDate);

	HSuccessfulBidData3 obj = new HSuccessfulBidData3();

	if (!department_code.equals("nill")) {
	    obj.setDepartment_code(department_code);
	}
	if (!business_category.equals("nill")) {
	    obj.setBusiness_category(business_category);
	}
	if (!bank_code.equals("nill")) {
	    obj.setBank_code(bank_code);
	}
	if (!financial_institution_name.equals("nill")) {
	    obj.setFinancial_institution_name(financial_institution_name);
	}
	if (!bid_number.equals("nill")) {
	    obj.setBid_number(Integer.parseInt(bid_number));
	}
	if (!bid_interest_rate.equals("nill")) {
	    obj.setBid_interest_rate(Double.parseDouble(bid_interest_rate));
	}
	if (!bid_amount_money.equals("nill")) {
	    obj.setBid_amount_money(Double.parseDouble(bid_amount_money));
	}
	if (!successful_bid_interest_rate.equals("nill")) {
	    obj.setSuccessful_bid_interest_rate(Double.parseDouble(successful_bid_interest_rate));
	}
	if (!successful_bid_price.equals("nill")) {
	    obj.setSuccessful_bid_price(Double.parseDouble(successful_bid_price));
	}

	boolean fo = bbmsService.UpdateSucessfullBidData(obj, deptCode);
	int res = -1;
	if (fo)
	    res = 1;

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", res);

	return new ModelAndView("result", mm);
    }

    @RequestMapping(value = "/BidDocumentPrintDetails", method = RequestMethod.POST)
    public ModelAndView BidDocumentPrintDetails(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 3) {
	    logger.warn("Expecting atleast 3 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String bidDate = requestUriSplit[0];
	String bidDateforDB = convertDateFormate(bidDate);

	String dept = requestUriSplit[1];
	int SHORI = Integer.parseInt(requestUriSplit[2]);

	String callReport = bbmsService.RAKU_PRINT(SHORI, dept, bidDateforDB);

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", callReport);

	return new ModelAndView("result", mm);
    }

    @RequestMapping(value = "/NotesApplicationFormPrint", method = RequestMethod.POST)
    public ModelAndView NotesApplicationFormPrint(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 11) {
	    logger.warn("Expecting atleast 11 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String bidDate = requestUriSplit[0];
	String bidDateforDB = convertDateFormate(bidDate);

	String dept = requestUriSplit[1];
	String deptname = requestUriSplit[2];
	int SHORI = Integer.parseInt(requestUriSplit[3]);
	String processing = requestUriSplit[4];
	String unit = requestUriSplit[5];
	String rearrangement = requestUriSplit[6];
	String billRearrangement = requestUriSplit[7];
	String intermidiateInterestPayment = requestUriSplit[8];
	String borrowingDate = requestUriSplit[9];
	String maturityDate = requestUriSplit[10];

	List<String> datas = new ArrayList<String>();
	datas.add(bidDateforDB);
	datas.add(dept);
	datas.add(deptname);
	datas.add(processing);
	datas.add(unit);
	datas.add(rearrangement);
	datas.add(billRearrangement);
	datas.add(borrowingDate);
	datas.add(maturityDate);
	datas.add(intermidiateInterestPayment);
	HCommon obj = new HCommon();
	obj.setDatas(datas);
	int user_id = getSessionService().getUserSession().getUser().getUser_id();
	String callReport = bbmsService.KM_PRINT(SHORI, obj,user_id);

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", callReport);

	return new ModelAndView("result", mm);
    }

    @RequestMapping(value = "/TransferSlipProcess", method = RequestMethod.POST)
    public ModelAndView TransferSlip(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);
	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 2) {
	    logger.warn("Expecting atleast 2 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String bidDate = requestUriSplit[0];
	String bidDateforDB = convertDateFormate(bidDate);

	String dept = requestUriSplit[1];

	boolean callReport = bbmsService.TransferSlip(bidDateforDB, dept);
	int res = 1;
	if (!callReport)
	    res = -1;

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", res);

	return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value = "/userregister", method = RequestMethod.POST)
    public ModelAndView userregister(HttpServletRequest request, HttpServletResponse response)
	    throws MessagingException {
	UserSession session = getSessionService().getUserSession();

	Map<String, String[]> parameterMap = request.getParameterMap();

	String username="";
	String password="";
	String fullname="";
	String roles="";

	username = parameterMap.get("username")[0];
	password = parameterMap.get("pass")[0];
	fullname = parameterMap.get("fname")[0];
	roles = parameterMap.get("roles")[0];
		
	HUser newuser=new HUser();
	newuser.setUser_name(username);
	newuser.setPassword(password);
	newuser.setFull_name(fullname);
	newuser.setRoles(roles);
	
	boolean Fo = bbmsService.addNewUser(newuser);

	ModelMap mm = new ModelMap();
	if (Fo)
	    mm.addAttribute("msg", "1");
	else
	    mm.addAttribute("msg", "-1");

	return new ModelAndView("result", mm);

    }
    
    @RequestMapping(value = "/checkusername", method = RequestMethod.POST)
    public ModelAndView checkusername(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);

	String[] requestUriSplit = requestUri.split("~");

	if (requestUriSplit.length < 2) {
	    logger.warn("Expecting atleast 2 arguments but received " + requestUriSplit.length);
	    return null;
	}

	String uname = requestUriSplit[0];
	String pass = requestUriSplit[1];

	HUser user = new HUser();
	user.setUser_name(uname);
	user.setPassword(pass);

	HUser sysuser = bbmsService.getUserValidation(user);
	String user_name = "-1";
	
	if (sysuser != null) {
	    user_name = sysuser.getUser_name();
	}

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", user_name);

	return new ModelAndView("result", mm);
    }
    
    @RequestMapping(value = "/getuserinfo", method = RequestMethod.POST)
    public ModelAndView getuserinfo(HttpServletRequest request) throws Exception {

	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);
		
	ModelMap mm = new ModelMap();
	
	String user_id = requestUri;
	if(user_id.equals("")){
	    mm.addAttribute("msg", "-1");
	}
	
	int userId=Integer.parseInt(user_id);
	HUser user = new HUser();
	
	List<HUser> users= new ArrayList<HUser>();
	users = bbmsService.getAllUser();
	
	for(HUser u:users){
	    if(u.getUser_id()==userId){
		user = u;
		break;
	    }
	}	
	
	mm.addAttribute("suser", user);

	return new ModelAndView("editusersub", mm);
    }
    
    @RequestMapping(value = "/userupdate", method = RequestMethod.POST)
    public ModelAndView userupdate(HttpServletRequest request, HttpServletResponse response)
	    throws MessagingException {
	UserSession session = getSessionService().getUserSession();

	Map<String, String[]> parameterMap = request.getParameterMap();

	String username="";
	String password="";
	String fullname="";
	String roles="";

	username = parameterMap.get("username")[0];
	password = parameterMap.get("pass")[0];
	fullname = parameterMap.get("fname")[0];
	roles = parameterMap.get("roles")[0];
		
	HUser newuser=new HUser();
	newuser.setUser_id(Integer.parseInt(username));
	newuser.setPassword(password);
	newuser.setFull_name(fullname);
	newuser.setRoles(roles);
	
	boolean Fo = bbmsService.editUser(newuser);

	ModelMap mm = new ModelMap();
	if (Fo)
	    mm.addAttribute("msg", "1");
	else
	    mm.addAttribute("msg", "-1");

	return new ModelAndView("result", mm);

    }
    
    @RequestMapping(value = "/cleartables", method = RequestMethod.POST)
    public ModelAndView cleartables(HttpServletRequest request) throws Exception {
	String requestUri = getStringFromHttpRequest(request);

	logger.info("requestUri=" + requestUri);
	String bidDate = requestUri;
	String bidDateforDB = convertDateFormate(bidDate);
	
	HBase base = bbmsService.getBase();
	String DepartmentCode = base.getDepartment_code();

	boolean Fo = bbmsService.ClearTables(bidDateforDB, DepartmentCode);
	
	String res="-1";
	if(Fo)
	    res="1";

	ModelMap mm = new ModelMap();
	mm.addAttribute("msg", res);

	return new ModelAndView("result", mm);

    }
}
