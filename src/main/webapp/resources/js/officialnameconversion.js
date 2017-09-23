function updateOfficialNameConversion(base,conid) {
	var bank_code = jQuery("#bank_code"+conid).val();
	var financial_institution_name=jQuery("#financial_institution_name"+conid).val();
	var DelF = 0;
	if(jQuery("#delete"+conid).is(':checked')) {
		DelF = -1;
	} else {
		DelF = 0;
	}

	var pars = bank_code+"~"+financial_institution_name +"~"+DelF;
	ajaxHelper.complexAjaxRequest(base+"/updateofficialnameconversion", pars, function(res){
		//alert(res);
	 	if(res!="-1"){
	 		jQuery('#ncsub').html(res);
	 		jQuery('#officialnameconversion').DataTable({
	 		    "iDisplayLength": 50
	 		  });
	 		jQuery("#n"+conid+" .close").click();
	    } else{
	    	alert("Not able to Update.");
	    }
	});
}

function saveOfficialNameConversion(base) {
	
	checkboxes = document.getElementsByName('chk');
	var se = 0;
	for(var i=0, n=checkboxes.length;i<n;i++) {
		if(checkboxes[i].checked) {
			se = 1;
			break;
			}
		}
	var nxt=false;
	/*if(se==1){
		nxt = confirm("削除対象のデータがありますが、よろしいですか？");
	}*/
		var pars =  "";
		if(nxt==true){
			pars = "1";
		} else {
			pars = "0";
		}
		ajaxHelper.complexAjaxRequest(base+"/saveofficialnameconversion", pars, function(res){
		 	if(res!="-1"){
		 		//alert("");
		 		window.location.href=base+"/OfficialNameConversion";
		    } else{
		    	alert("Not able to Save data");
		    }
		});
		
	
}