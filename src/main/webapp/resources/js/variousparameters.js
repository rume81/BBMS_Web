function updateVariousParameters(base,conid) {
	var chairman_name = jQuery("#chairman_name").val();
	var chairman_name_2 = jQuery("#chairman_name_2").val();
	var minister_of_finance = jQuery("#minister_of_finance").val();
	var division_name = jQuery("#division_name").val();
	var provisional_successful_bid_document_wording = jQuery("#provisional_successful_bid_document_wording").val();
	var provisional_successful_bid_document_wording_2 = jQuery("#provisional_successful_bid_document_wording_2").val();
	var successful_bid_document_wording = jQuery("#successful_bid_document_wording").val();
	var successful_bid_document_wording_2 = jQuery("#successful_bid_document_wording_2").val();
	var credit_application_wording = jQuery("#credit_application_wording").val();
	var street_address = jQuery("#street_address").val();
	var department_code = jQuery("#department_code").val();
	
	if(chairman_name==""){
		chairman_name = "";
	}
	
	var pars =  chairman_name+"~"+chairman_name_2+"~"+minister_of_finance+"~"+division_name+"~"+provisional_successful_bid_document_wording+"~"+provisional_successful_bid_document_wording_2+"~"+successful_bid_document_wording+"~"+successful_bid_document_wording_2+"~"+credit_application_wording+"~"+street_address+"~"+department_code;
	
	ajaxHelper.complexAjaxRequest(base+"/updatevariousparameters", pars, function(res){
	 	if(res!="-1"){
	 		window.location.href=base+"/VariousParametersChange";
	    } else{
	    	alert("Not able to Update.");
	    }
	});
}