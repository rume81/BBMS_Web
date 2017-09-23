function getRpt(base,shori){
	var bidDate = jQuery('#bidDate').val();
	var dept = jQuery('#cmbDeptCode').val();
	var deptname = jQuery('#txtDeptName').val();
	//var total = jQuery('#total_amount').val();
	
	if(bidDate=="" || dept=="")
	{
		return;
	}
	pars = bidDate+"~"+dept+"~"+deptname+"~"+shori;
	ajaxHelper.wait();
	ajaxHelper.complexAjaxRequest(base+"/interestrateorderbidstatus", pars, function(res){
		if(res!="-1"){
			if(res=="-2"){
				alert("計算エラーが発生しました。再度実行してください");
			}
			else if(res=="ThereSpecification"){
				var rptName = res;
				var param = bidDate;
				location.href=base+"/rptview/"+rptName+"/"+param;
			} else{
				var data = res.split('~');
		   		jQuery('#total_amount').val(data[1]);
		   		var rptName = data[0];
		   		var param = bidDate;
		   		location.href=base+"/rptview/"+rptName+"/"+param;
			}
		}
	});
}