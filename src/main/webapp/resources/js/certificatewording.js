function insertCertificateWording(base) {
	
	var department_code=jQuery("#department_code").val();
	var horei01=jQuery("#horei01").val();
	var horei02=jQuery("#horei02").val();
	//alert(department_code);
	if(department_code==""){
		alert("Select Department Code");
	}
	if(horei01==""){
		alert("Insert horei01");
	}
	if(horei02==""){
		alert("Insert horei02");
	}
	
	
	var pars =  department_code+"~"+horei01+"~"+horei02;
	ajaxHelper.complexAjaxRequest(base+"/insertcertificatewording", pars, function(res){
	 	if(res!="-1"){
	 		window.location.href=base+"/ChangeofCertificateWording";
	    } else{
	    	alert("Not able to Save.");
	    }
	});
}

function updateCertificateWording(base,conid) {
	
	var department_code=jQuery("#department_code"+conid).val();
	var horei01=jQuery("#horei01"+conid).val();
	var horei02=jQuery("#horei02"+conid).val();
	
	
	var pars =  department_code+"~"+horei01+"~"+horei02;
	ajaxHelper.complexAjaxRequest(base+"/updatecertificatewording", pars, function(res){
		//alert(res);
	 	if(res!="-1"){
	 		window.location.href=base+"/ChangeofCertificateWording";
	    } else{
	    	alert("Not able to Update.");
	    }
	});
}