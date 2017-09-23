function getBidDocumentRpt(base,shori){
	var bidDate = jQuery('#bidDate').val();
	var dept = jQuery('#cmbDeptCode').val();
		
	if(bidDate=="" || dept=="")
	{
		return;
	}
	pars = bidDate+"~"+dept+"~"+shori;
	ajaxHelper.wait();
	ajaxHelper.complexAjaxRequest(base+"/BidDocumentPrintDetails", pars, function(res){
		if(res!="-1"){
			var rptName = res;
			var param = bidDate+"~"+dept;
			location.href=base+"/rptview/"+rptName+"/"+param;
		}
	});
}