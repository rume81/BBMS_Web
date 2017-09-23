function getRptNAFP(base,shori){
	var bidDate = jQuery('#bidDate').val();
	var dept = jQuery('#cmbDeptCode').val();
	var deptname = jQuery('#txtDepartmentName').val();
	var processing = jQuery('#txtProcessing').val();
	var unit = jQuery('#cmbUnit').val();
	var rearrangement = jQuery('#cmbRearrangement').val();
	var billRearrangement = jQuery('#cmbBillRearrangement').val();
	var borrowingDate = jQuery('#txtBorrowingDate').val();
	var maturityDate = jQuery('#txtMaturityDate').val();
	var intermidiateInterestPayment = jQuery('#txtIntermidiateInterestPayment').val();
	
	
	if(bidDate=="" || dept=="")
	{
		return;
	}
	if(borrowingDate=="" || maturityDate=="")
	{
		alert("引き出し日付と満期日を挿入してください。");
		return;
	}
	
	pars = bidDate+"~"+dept+"~"+deptname+"~"+shori+"~"+processing+"~"+unit+"~"+rearrangement+"~"+billRearrangement+"~"+intermidiateInterestPayment+"~"+borrowingDate+"~"+maturityDate;
	ajaxHelper.wait();
	ajaxHelper.complexAjaxRequest(base+"/NotesApplicationFormPrint", pars, function(res){
		
		if(res!="-1"){
			var data = res.split('~');
			if(data[0]=="1"){
				alert(data[1]);
			}
			else if(data[0]=="2"){
				var rptName = data[1];
				var param = bidDate+"~"+dept;
				location.href=base+"/rptview/"+rptName+"/"+param;
			} else if(data[0]=="3"){
				jQuery('#txtProcessing').val("");
				var rptName = data[1];
				var param = bidDate+"~"+dept;
				location.href=base+"/rptview/"+rptName+"/"+param;
			} else if(data[0]=="4"){
				var rptName = data[1];
				var param = bidDate+"~"+dept;
				location.href=base+"/rptview/"+rptName+"/"+param;
			} else{
				
			}
		}
	});
}

function changeProcessing(txt){
	jQuery('#txtProcessing').val(txt);
}

function TransferSlip(base){
	var bidDate = jQuery('#bidDate').val();
	var dept = jQuery('#cmbDeptCode').val();
	
	if(bidDate=="" || dept=="")
	{
		return;
	}
	
	pars = bidDate+"~"+dept;
	ajaxHelper.wait();
	ajaxHelper.complexAjaxRequest(base+"/TransferSlipProcess", pars, function(res){		
		if(res!="-1"){
			var rptName = "TransferSlip";
			var param = bidDate+"~"+dept;
			location.href=base+"/rptview/"+rptName+"/"+param;
		}
	});
	
}

function LoanAgreementDeed(base){
	var bidDate = jQuery('#bidDate').val();
	var dept = jQuery('#cmbDeptCode').val();
		
	pars = bidDate+"~"+dept;
	
	var rptName = "LoanAgreementDeed";
	var param = bidDate+"~"+dept;
	location.href=base+"/rptview/"+rptName+"/"+param;
	
}

function setFormValue(base){
	var borrowingDate = jQuery('#txtBorrowingDate').val();
	var maturityDate = jQuery('#txtMaturityDate').val();
	
	if(borrowingDate=="")
	{
		borrowingDate="0";
	}
	if(maturityDate==""){
		maturityDate="0";
	}
	
	pars = borrowingDate+"~"+maturityDate;
	
	ajaxHelper.complexAjaxRequest(base+"/setbormatdateinsession", pars, function(res){
			if(res!="-1"){
			}
	});
	
}

function closeform(base){
	jQuery('#txtBorrowingDate').val("");
	jQuery('#txtMaturityDate').val("");
	
	var borrowingDate="0";
	var maturityDate="0";
	
	pars = borrowingDate+"~"+maturityDate;
	
	ajaxHelper.complexAjaxRequest(base+"/setbormatdateinsession", pars, function(res){
		if(res!="-1"){
				location.href=base+"/home";
		}
	});
	
}
