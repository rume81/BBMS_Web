function editfinancialinstitution(base,conid) {
	var bank_code=jQuery("#bank_code_"+conid).val();
	var financial_institution_name=jQuery("#financial_institution_name_"+conid).val();
	var store=jQuery("#store_"+conid).val();
	var business_category=jQuery("#business_category_"+conid).val();
	var DelF = 0;
	if(jQuery("#delete_"+conid).is(':checked')) {
		DelF = -1;
	} else {
		DelF = 0;
	}
	//var DelF=jQuery("#DelF"+conid).val();
	
	if(bank_code==""){
		bank_code = "nill";
	}
	if(financial_institution_name==""){
		financial_institution_name = "nill";
	}
	if(store==""){
		store = "nill";
	}
	if(business_category==""){
		business_category = "nill";
	}
	
	var pars =  bank_code+"~"+financial_institution_name+"~"+store+"~"+business_category+"~"+DelF;
	ajaxHelper.complexAjaxRequest(base+"/editfinancialinstitution", pars, function(res){
		//alert(res);
	 	if(res!="-1"){
	 		jQuery('#fitable').html(res);
	 		jQuery('#financialInstitute').DataTable({
	 		    "iDisplayLength": 50
	 		  });
	 		jQuery("#f"+conid+" .close").click();
	    } else{
	    	alert("Not able to Update.");
	    }
	});
}

function insertfinancialinstitution(base) {
	
	var bank_code=jQuery("#bank_code").val();
	var financial_institution_name=jQuery("#financial_institution_name").val();
	var store=jQuery("#store").val();
	var business_category=jQuery("#business_category").val();
	//alert("ok");
	if(bank_code==""){
		alert("Insert Bank code");
	}
	if(financial_institution_name==""){
		alert("Insert financail institution name");
	}
	if(store==""){
		alert("Insert store");
	}
	if(business_category==""){
		alert("Select Business Category");
	}
	
	
	var pars =  bank_code+"~"+financial_institution_name+"~"+store+"~"+business_category;
	ajaxHelper.complexAjaxRequest(base+"/insertfinancialinstitution", pars, function(res){
		//alert(res);
	 	if(res!="-1"){
	 		jQuery('#fitable').html(res);
	 		jQuery('#financialInstitute').DataTable({
	 		    "iDisplayLength": 50
	 		  });
	 		jQuery("#f_insert .close").click();
	    } else{
	    	alert("Not able to Save.");
	    }
	});
}
function savefinancialmaster(base) {
	
	checkboxes = document.getElementsByName('chk');
	var se = 0;
	for(var i=0, n=checkboxes.length;i<n;i++) {
		  if(checkboxes[i].checked)
		  {
			  se = 1;
			  break;
		  }
	}
	var nxt=false;
	if(se==1){
		nxt = confirm("削除対象のデータがありますが、よろしいですか？");
	}
		
		var pars =  "";
		if(nxt==true){
			pars = "1";
		} else {
			pars = "0";
		}
		ajaxHelper.complexAjaxRequest(base+"/savefinancialmaster", pars, function(res){
		 	if(res!="-1"){
		 		//alert("");
		 		window.location.href=base+"/FinancialInstitutionMaster";
		    } else{
		    	alert("Not able to Save data");
		    }
		});
		
	
}