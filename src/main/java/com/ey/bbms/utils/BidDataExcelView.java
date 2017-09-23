package com.ey.bbms.utils;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.ey.bbms.model.main.HBidData;


public class BidDataExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String fileName = URLEncoder.encode("入札データ.xls","UTF-8");
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
	List<HBidData> bidList = (List<HBidData>) model.get("bidList");
	
	HSSFSheet excelSheet = workbook.createSheet("入札データ");
	setExcelHeader(excelSheet);
	setExcelRows(excelSheet,bidList);
	
	
    }

    public void setExcelHeader(HSSFSheet excelSheet) {
	HSSFRow excelHeader = excelSheet.createRow(0);
	//excelHeader.createCell(0).setCellValue("EmpId");
	excelHeader.createCell(0).setCellValue("部門コード");
	excelHeader.createCell(1).setCellValue("業態");
	excelHeader.createCell(2).setCellValue("金融機関コード");
	excelHeader.createCell(3).setCellValue("金融機関名");
	excelHeader.createCell(4).setCellValue("入札番号");
	excelHeader.createCell(5).setCellValue("入札利率");
	excelHeader.createCell(6).setCellValue("入札金額");
	excelHeader.createCell(7).setCellValue("入札日");
	excelHeader.createCell(8).setCellValue("flag1");
	excelHeader.createCell(9).setCellValue("flag2");
	excelHeader.createCell(10).setCellValue("flag3");
    }

    public void setExcelRows(HSSFSheet excelSheet, List<HBidData> bidList) {
	int record = 1;
	for (HBidData att : bidList) {
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
	    
	    if(att.getBid_number()!=null)
		excelRow.createCell(4).setCellValue(att.getBid_number());
	    else
		excelRow.createCell(4).setCellValue("");
	    
	    if(att.getBid_interest_rate()!=null)
		excelRow.createCell(5).setCellValue(att.getBid_interest_rate());
	    else
		excelRow.createCell(5).setCellValue("");
	    
	    if(att.getBid_amount_money()!=null)
		excelRow.createCell(6).setCellValue(att.getBid_amount_money());
	    else
		excelRow.createCell(6).setCellValue("");
	    
	    if(att.getAuction_date()!=null)
		excelRow.createCell(7).setCellValue(att.getAuction_date());
	    else
		excelRow.createCell(7).setCellValue("");
	    
	    if(att.getFlag1()!=null)
		excelRow.createCell(8).setCellValue(att.getFlag1());
	    else
		excelRow.createCell(8).setCellValue("");
	    
	    if(att.getFlag2()!=null)
		excelRow.createCell(9).setCellValue(att.getFlag2());
	    else
		excelRow.createCell(9).setCellValue("");
	    
	    if(att.getFlag3()!=null)
		excelRow.createCell(10).setCellValue(att.getFlag3());
	    else
		excelRow.createCell(10).setCellValue("");
	}
    }

}
