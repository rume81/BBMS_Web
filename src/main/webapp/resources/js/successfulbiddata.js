function getRptSB(base,shori){
	var bidDate = jQuery('#bidDate').val();
	var dept = jQuery('#cmbDeptCode').val();
	var deptname = jQuery('#txtDeptName').val();
	var cmbDataUpdated = jQuery('#cmbDataUpdated').val();
	var txtMinimumAmount = jQuery('#txtMinimumAmount').val();
	var txtBidAmountPland = jQuery('#txtBidAmountPland').val();
	var txtFootCutInterestRates = jQuery('#txtFootCutInterestRates').val();
	
	
	if(bidDate=="" || dept=="")
	{
		return;
	}
	
	
	pars = bidDate+"~"+deptname+"~"+shori+"~"+cmbDataUpdated+"~"+txtMinimumAmount+"~"+txtBidAmountPland+"~"+txtFootCutInterestRates+"~"+dept;
	ajaxHelper.wait();
	ajaxHelper.complexAjaxRequest(base+"/SuccessfulBidDataCreation", pars, function(res){
		
		if(res!="-1"){
			if(res=="2"){
				var rptName = "InterestRateBy";
				var param = bidDate+"~"+dept;
				location.href=base+"/rptview/"+rptName+"/"+param;
			}
			else if(res=="3"){
				var rptName = "SuccessfulBidStatusTableByBank";
				var param = bidDate+"~"+dept;
				location.href=base+"/rptview/"+rptName+"/"+param;
			} else{
				//var data = res.split('~');
		   		//var deptcode = data[0];
		   		//var bidPlaned = data[1]
		   		var rptName = "SuccessfulBidStatusTableByBank";
		   		var param = bidDate+"~"+res;
		   		location.href=base+"/rptview/"+rptName+"/"+param;
			}
		}
	});
}

function editBidData(base,conid)
{
	var department_code=jQuery("#dept"+conid).val();
	var business_category=jQuery("#buscat"+conid).val();
	var bank_code=jQuery("#bank"+conid).val();
	var financial_institution_name=jQuery("#fi"+conid).val();
	var bid_number=jQuery("#bidn"+conid).val();
	var bid_interest_rate=jQuery("#bidinst"+conid).val();
	var bid_amount_money=jQuery("#bidamo"+conid).val();
	var successful_bid_interest_rate=jQuery("#sbidinst"+conid).val();
	var successful_bid_price=jQuery("#samount"+conid).val();	
	var deptCode=jQuery("#deptCode").val();
	var bidDate=jQuery("#bidDate").val();
	
	if(department_code==""){
		department_code = "nill";
	}
	if(business_category==""){
		business_category = "nill";
	}
	if(bank_code==""){
		bank_code = "nill";
	}
	if(financial_institution_name==""){
		financial_institution_name = "nill";
	}
	if(bid_number==""){
		bid_number = "nill";
	}
	if(bid_interest_rate==""){
		bid_interest_rate = "nill";
	}
	if(bid_amount_money==""){
		bid_amount_money = "nill";
	}
	if(successful_bid_interest_rate==""){
		successful_bid_interest_rate = "nill";
	}
	if(successful_bid_price==""){
		successful_bid_price = "nill";
	}
	var pars =  department_code+"~"+business_category+"~"+bank_code+"~"+financial_institution_name+"~"+bid_number+"~"+bid_interest_rate+"~"+bid_amount_money+"~"+successful_bid_interest_rate+"~"+successful_bid_price+"~"+deptCode+"~"+bidDate;
	ajaxHelper.complexAjaxRequest(base+"/editbiddata", pars, function(res){
	 	if(res!="-1"){
	 		
	 		window.location.href=base+"/SuccessfulBidDataMaintanenceView/"+bidDate;
	 		
	    } else{
	    	alert("Not able to Save.");
	    }
	});
}

function setFormValue(base){
	var bidAmountPland = jQuery('#txtBidAmountPland').val();
	var footCutInterestRates = jQuery('#txtFootCutInterestRates').val();
	
	if(bidAmountPland=="")
	{
		bidAmountPland="@";
	}
	if(footCutInterestRates==""){
		footCutInterestRates="@";
	}
	
	pars = bidAmountPland+"~"+footCutInterestRates;
	
	ajaxHelper.complexAjaxRequest(base+"/setbidplanintrateinsession", pars, function(res){
			if(res!="-1"){
			}
	});
	
}

function closeform(base){
	jQuery('#txtBidAmountPland').val("");
	jQuery('#txtFootCutInterestRates').val("");
	
	var bidAmountPland="@";
	var footCutInterestRates="@";
	
	pars = bidAmountPland+"~"+footCutInterestRates;
	
	ajaxHelper.complexAjaxRequest(base+"/setbidplanintrateinsession", pars, function(res){
		if(res!="-1"){
				location.href=base+"/home";
		}
	});
	
}