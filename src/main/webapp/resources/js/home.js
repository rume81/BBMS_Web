function getFinancialinstituteRpt(base){
	var bidDate = jQuery('#bid_date').val();
	if(bidDate=="")
	{
		alert("入札日を挿入してください。");
		return;
	}
	ajaxHelper.wait(); 
	var param = bidDate;
	location.href=base+"/rptview/Financialinstitutepdf/"+param;
	
}

function getBidData(base){
	var bidDate = jQuery('#bid_date').val();
	if(bidDate=="")
	{
		alert("入札日を挿入してください。");
		return;
	}
	ajaxHelper.wait(); 
	location.href=base+"/BidDataInput/"+bidDate+"";
	
}

function getInterestRateOrderBidStatusView(base){
	var bidDate = jQuery('#bid_date').val();
	if(bidDate=="")
	{
		alert("入札日を挿入してください。");
		return;
	}
	ajaxHelper.wait();
	location.href=base+"/InterestRateOrderBidStatusView/"+bidDate+"";
}

function getSuccessfulBidDataCreationView(base){
	var bidDate = jQuery('#bid_date').val();
	if(bidDate=="")
	{
		alert("入札日を挿入してください。");
		return;
	}
	ajaxHelper.wait();
	location.href=base+"/SuccessfulBidDataCreationView/"+bidDate+"";
}

function getSuccessfulBidDataMaintanenceView(base){
	var bidDate = jQuery('#bid_date').val();
	if(bidDate=="")
	{
		alert("入札日を挿入してください。");
		return;
	}
	ajaxHelper.wait();
	location.href=base+"/SuccessfulBidDataMaintanenceView/"+bidDate+"";
}

function getBidDocumentPrintView(base){
	var bidDate = jQuery('#bid_date').val();
	if(bidDate=="")
	{
		alert("入札日を挿入してください。");
		return;
	}
	ajaxHelper.wait();
	location.href=base+"/BidDocumentPrintView/"+bidDate+"";
}

function getNotesApplicationFormPrintView(base){
	var bidDate = jQuery('#bid_date').val();
	if(bidDate=="")
	{
		alert("入札日を挿入してください。");
		return;
	}
	ajaxHelper.wait();
	location.href=base+"/NotesApplicationFormPrint/"+bidDate+"";
}

function downloadExcel(base){
	ajaxHelper.wait();
	location.href=base+"/ExcelDownload/";
}

function downloadBidDataExcel(base){
	location.href=base+"/BidDataDownload/";
}

function downloadBorrowDataExcel(base){
	location.href=base+"/BorrowingDataDownload/";
}

function downloadSusBidDataExcel(base){
	location.href=base+"/SuccessfulBidDataDownload/";
}

function setBidDate(base){
	var bidDate = jQuery('#bid_date').val();
	
	pars = bidDate;
	if(bidDate!="")
	{
		ajaxHelper.complexAjaxRequest(base+"/setbiddateinsession", pars, function(res){
			if(res!="-1"){
			}
		});
	}
}

function clearTable(base){
	var bidDate = jQuery('#txtTargetAuction').val();
	
	if(bidDate==""){
		alert("入札日を指定してください");
		return;
	}
	
	var proc = confirm("現在対象となっている入札データをクリアします。よろしいですか？");
	
	if(proc==true){
		pars = bidDate;
		
		ajaxHelper.complexAjaxRequest(base+"/cleartables", pars, function(res){
			if(res!="-1"){
				jQuery('#txtTargetAuction').val('');
				alert("現在の入札データがクリアされました");
			}
		});
		
	}
}
/*function FinancialinstituteDetails(base,id){
	var pars =  id;
	
	ajaxHelper.complexAjaxRequest(base+"/financialinstitutedetails", pars, function(res){
		if(res!="-1"){
	   		var data = res.split('~');
	   		jQuery('#inst_name').val(data[0]);
	   		jQuery('#busi_cat').html(data[1]);
	    }
	 });
}*/

