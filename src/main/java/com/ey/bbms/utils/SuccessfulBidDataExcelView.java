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

import com.ey.bbms.model.main.HSuccessfulBidData3;

public class SuccessfulBidDataExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String fileName = URLEncoder.encode("落札データ.xls","UTF-8");
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
	List<HSuccessfulBidData3> bidList = (List<HSuccessfulBidData3>) model.get("dataList");
	
	HSSFSheet excelSheet = workbook.createSheet("落札データ");
	setExcelHeader(excelSheet);
	setExcelRows(excelSheet,bidList);

    }
    
    public void setExcelHeader(HSSFSheet excelSheet) {
	HSSFRow excelHeader = excelSheet.createRow(0);
	excelHeader.createCell(0).setCellValue("部門コード");
	excelHeader.createCell(1).setCellValue("業態");
	excelHeader.createCell(2).setCellValue("金融機関コード");
	excelHeader.createCell(3).setCellValue("金融機関名");
	excelHeader.createCell(4).setCellValue("入札番号");
	excelHeader.createCell(5).setCellValue("入札利率");
	excelHeader.createCell(6).setCellValue("入札金額");
	excelHeader.createCell(7).setCellValue("落札利率");
	excelHeader.createCell(8).setCellValue("落札金額");
	excelHeader.createCell(9).setCellValue("金融機関別フラグ");
	excelHeader.createCell(10).setCellValue("入札日");
    }

    public void setExcelRows(HSSFSheet excelSheet, List<HSuccessfulBidData3> dataList) {
	int record = 1;
	NumberFormat formatter = new DecimalFormat("#0.0000");	
	    
	for (HSuccessfulBidData3 att : dataList) {
	    HSSFRow excelRow = excelSheet.createRow(record++);
	   if(att.getDepartment_code()!=null)
		excelRow.createCell(0).setCellValue(att.getDepartment_code());
	    else
		excelRow.createCell(0).setCellValue("");
	   
	    if(att.getBusiness_category()!=null)
		excelRow.createCell(1).setCellValue(att.getBusiness_category());
	    else
		excelRow.createCell(1).setCellValue("");
	    
	    if(att.getBank_code()!=null)
		excelRow.createCell(2).setCellValue(att.getBank_code());
	    else
		excelRow.createCell(2).setCellValue("");
	    
	    if(att.getFinancial_institution_name()!=null)
		excelRow.createCell(3).setCellValue(att.getFinancial_institution_name());
	    else
		excelRow.createCell(3).setCellValue("");
	    
	    if(att.getBid_number()>=0)
		excelRow.createCell(4).setCellValue(att.getBid_number());
	    else
		excelRow.createCell(4).setCellValue("");
	    
	    if(att.getBid_interest_rate()>=0)
		excelRow.createCell(5).setCellValue(String.valueOf(formatter.format(att.getBid_interest_rate())));
	    else
		excelRow.createCell(5).setCellValue("");
	    
	    if(att.getBid_amount_money()>=0)
		excelRow.createCell(6).setCellValue(att.getBid_amount_money());
	    else
		excelRow.createCell(6).setCellValue("");
	    
	    if(att.getSuccessful_bid_interest_rate()>=0)
		excelRow.createCell(7).setCellValue(String.valueOf(formatter.format(att.getSuccessful_bid_interest_rate())));
	    else
		excelRow.createCell(7).setCellValue("");
	    
	    if(att.getSuccessful_bid_price()>=0)
		excelRow.createCell(8).setCellValue(att.getSuccessful_bid_price());
	    else
		excelRow.createCell(8).setCellValue("");
	    
	    if(att.getFinancial_institutions_by_flag()>0)
		excelRow.createCell(9).setCellValue(att.getFinancial_institutions_by_flag());
	    else
		excelRow.createCell(9).setCellValue("");
	    
	    if(att.getAuction_date() != null)
		excelRow.createCell(10).setCellValue(att.getAuction_date());
	    else
		excelRow.createCell(10).setCellValue("");
	}
    }

}
