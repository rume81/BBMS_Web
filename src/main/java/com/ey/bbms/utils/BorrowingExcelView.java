package com.ey.bbms.utils;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.ey.bbms.model.main.HBillCreditApplicationDatas;

public class BorrowingExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String fileName = URLEncoder.encode("借入データ.xls","UTF-8");
	String userAgent = request.getHeader("User-Agent");
	try {
	    response.setContentType("application/ms-excel; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    if(userAgent.contains("Firefox"))
	        response.setHeader("Content-Disposition","attachment; filename*=UTF-8''"+fileName);
	    else
		response.setHeader("Content-Disposition","attachment; filename="+fileName);
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	
	//response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xls\"");
	List<HBillCreditApplicationDatas> bidList = (List<HBillCreditApplicationDatas>) model.get("dataList");
	
	HSSFSheet excelSheet = workbook.createSheet("借入データ");
	setExcelHeader(excelSheet);
	setExcelRows(excelSheet,bidList);

    }
    
    public void setExcelHeader(HSSFSheet excelSheet) {
	HSSFRow excelHeader = excelSheet.createRow(0);
	excelHeader.createCell(0).setCellValue("部門コード");
	excelHeader.createCell(1).setCellValue("金融機関コード");
	excelHeader.createCell(2).setCellValue("金融機関名");
	excelHeader.createCell(3).setCellValue("落札利率");
	excelHeader.createCell(4).setCellValue("落札金額");
	excelHeader.createCell(5).setCellValue("手形番号");
	excelHeader.createCell(6).setCellValue("振出日");
	excelHeader.createCell(7).setCellValue("満期日");
	excelHeader.createCell(8).setCellValue("日数");
	excelHeader.createCell(9).setCellValue("利率");
	excelHeader.createCell(10).setCellValue("金額");
	excelHeader.createCell(11).setCellValue("利息額");
	excelHeader.createCell(12).setCellValue("備考");
	excelHeader.createCell(13).setCellValue("入札日");
    }

    public void setExcelRows(HSSFSheet excelSheet, List<HBillCreditApplicationDatas> dataList) {
	int record = 1;
	NumberFormat formatter = new DecimalFormat("#0.0000");	
	NumberFormat formatter1 = new DecimalFormat("#0.00");
	    
	for (HBillCreditApplicationDatas att : dataList) {
	    HSSFRow excelRow = excelSheet.createRow(record++);
	   if(att.getDepartment_code()!=null)
		excelRow.createCell(0).setCellValue(att.getDepartment_code());
	    else
		excelRow.createCell(0).setCellValue("");
	   
	    if(att.getBank_code()!=null)
		excelRow.createCell(1).setCellValue(att.getBank_code());
	    else
		excelRow.createCell(1).setCellValue("");
	    
	    if(att.getFinancial_institution_name()!=null)
		excelRow.createCell(2).setCellValue(att.getFinancial_institution_name());
	    else
		excelRow.createCell(2).setCellValue("");
	    
	    if(att.getSuccessful_bid_interest_rate()>=0)
		excelRow.createCell(3).setCellValue(String.valueOf(formatter.format(att.getSuccessful_bid_interest_rate())));
	    else
		excelRow.createCell(3).setCellValue("");
	    
	    if(att.getSuccessful_bid_price()>=0)
		excelRow.createCell(4).setCellValue(att.getSuccessful_bid_price());
	    else
		excelRow.createCell(4).setCellValue("");
	    
	    if(att.getBill_number()!=null)
		excelRow.createCell(5).setCellValue(att.getBill_number());
	    else
		excelRow.createCell(5).setCellValue("");
	    
	    if(att.getDrawer_date()!=null)
		excelRow.createCell(6).setCellValue(att.getDrawer_date());
	    else
		excelRow.createCell(6).setCellValue("");
	    
	    if(att.getMaturity_date()!=null)
		excelRow.createCell(7).setCellValue(att.getMaturity_date());
	    else
		excelRow.createCell(7).setCellValue("");
	    
	    if(att.getNumber_of_days()>=0)
		excelRow.createCell(8).setCellValue(att.getNumber_of_days());
	    else
		excelRow.createCell(8).setCellValue("");
	    
	    if(att.getInterest_rate()>0)
		excelRow.createCell(9).setCellValue(att.getInterest_rate());
	    else
		excelRow.createCell(9).setCellValue("");
	    
	    if(att.getAmount_of_money()>=0)
		excelRow.createCell(10).setCellValue(String.valueOf(formatter1.format(att.getAmount_of_money())));
	    else
		excelRow.createCell(10).setCellValue("");
	    
	    if(att.getInterest_amount()>0)
		excelRow.createCell(11).setCellValue(att.getInterest_amount());
	    else
		excelRow.createCell(11).setCellValue("");
	    
	    if(att.getRemarks()!= null)
		excelRow.createCell(12).setCellValue(att.getRemarks());
	    else
		excelRow.createCell(12).setCellValue("");
	    
	    if(att.getAuction_date() != null)
		excelRow.createCell(13).setCellValue(att.getAuction_date());
	    else
		excelRow.createCell(13).setCellValue("");
	}
    }

}
