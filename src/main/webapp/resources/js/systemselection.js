function systemSelection(base) {
	var system_name = jQuery("#system_name").val();
	var pars =  system_name;
	//var system_name
	
	
	
	ajaxHelper.complexAjaxRequest(base+"/updatefieldname", pars, function(res){
	 	if(res!="-1"){
	 		jQuery("#field_name").val(res);
	    } else{
	    	alert("Not able to Update.");
	    }
	});
}
function getSystemMaintenance(base){
	var field_name = jQuery('#field_name').val();
	var system_name = jQuery("#system_name").val();

	if(system_name =="") {
		alert("システム名を選択してください");
		return;
		//window.location.href=base+"/systemSelection";
	}
	
	ajaxHelper.wait(); 
	location.href=base+"/systemMaintenance/"+field_name+"/"+system_name;
	
}


