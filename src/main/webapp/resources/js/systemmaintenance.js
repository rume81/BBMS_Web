function updateSystemMaintenance(base,code) {
	var fieldname = jQuery("#fieldname").val();
	var usestatus = jQuery("#usestatus"+code).val();
	var system_name = jQuery("#systemname").val();
	
	//alert(fieldname);
	var pars =  fieldname+"~"+usestatus+"~"+code;
	
	ajaxHelper.complexAjaxRequest(base+"/updateFinancialInstitutionMasterTemp", pars, function(res){
	 	if(res!="-1"){
	 		//window.location.href=base+"/systemMaintenance/"+field_name+"/"+system_name;
	 		
	 		jQuery('#smtable').html(res);
	 		jQuery('#systemmaintenance').DataTable({
	 		    "iDisplayLength": 50
	 		  });
	 		jQuery("#s"+code+" .close").click();
	 		
	 		//window.location.href=base+"/systemSelection";
	    } else{
	    	alert("Not able to Update.");
	    }
	});
}

function saveSystemMaintenance(base) {
	var fieldname = jQuery("#fieldname").val();
	var system_name = jQuery("#systemname").val();
	//var status = jQuery("#usestatus").val();
	
	var pars = fieldname;
	
	ajaxHelper.complexAjaxRequest(base+"/saveSystemMaintenance", pars, function(res){
		if(res!="-1"){
	 		window.location.href=base+"/systemMaintenance/"+fieldname+"/"+system_name;
	 		//window.location.href=base+"/home";

	    } else{
	    	alert("Not able to Update.");
	    }
	});
	
}