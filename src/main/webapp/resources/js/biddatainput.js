function FinancialinstituteDetails(base){
	var bankCode =  jQuery('#financial_institute').val();
	var biddate = jQuery('#bidDate').val();
	var departmentCode = jQuery('#departmentCode').val();
	
	var pars = biddate+"~"+bankCode+"~"+departmentCode;
	
	ajaxHelper.complexAjaxRequest(base+"/financialinstitutedetails", pars, function(res){
		if(res!="-1"){
	   		var data = res.split('~');
	   		jQuery('#bid_data').html(data[0]);
	   		jQuery('#inst_name').val(data[1]);
	   		jQuery('#busi_cat').val(data[2]);
	    }
	 });
}

function clearForm(){
	jQuery('#financial_institute').val("");
	jQuery('#busi_cat').val("");
	jQuery('#inst_name').val("");
	for(var i=1;i<=5;i++)
	{
		jQuery('#txtInterestRate'+i).val("");
		jQuery('#txtBidAmount'+i).val("");
	}
	jQuery('#txtTotalAmount').val("");
	jQuery('#cmdRegistration').prop('disabled', false);;
	jQuery('#cmdDataCorrection').attr('disabled','disabled');
	jQuery('#cmdDelete').attr('disabled','disabled');
}

function BidDataDeregister(base){
	var bankcode = jQuery('#financial_institute').val();
	var dept = jQuery('#departmentCode').val();
	var biddate = jQuery('#bidDate').val();
	
	var isOk = false;
	isOk = confirm("このデータを削除しますがよろしいですか");
	
	if(isOk){
		var pars= biddate+"~"+bankcode+"~"+dept;
		ajaxHelper.complexAjaxRequest(base+"/biddataderegister", pars, function(res){
			if(res!="-1"){
				alert("コード " + bankcode + "のデータを削除しました");
				clearForm();
			}
		});
	}
}

function BidDataRegister(base){
	
	var interestRate1 = jQuery('#txtInterestRate1').val();
	var interestRate2 = jQuery('#txtInterestRate2').val();
	var interestRate3 = jQuery('#txtInterestRate3').val();
	var interestRate4 = jQuery('#txtInterestRate4').val();
	var interestRate5 = jQuery('#txtInterestRate5').val();
	var bankcode = jQuery('#financial_institute').val();
	var dept = jQuery('#departmentCode').val();
	var biddate = jQuery('#bidDate').val();
	
	var isOk = true;
	if(((interestRate2 != 0) && (interestRate1 > interestRate2)) || ((interestRate3 != 0) && (interestRate2 > interestRate3)) || ((interestRate4 != 0) && (interestRate3 > interestRate4)) || ((interestRate5 != 0) && (interestRate4 > interestRate5)))
	{
		isOk = confirm("利率が昇順になっていませんが、そのまま登録しますか？");
	}
	
	if(bankcode == "")
	{
		alert("金融機関コードが空白なので登録されません");
		isOk = false;
	}
	
	var datacorrection = false;
	if(jQuery('#cmdDataCorrection').is(':disabled')){
		datacorrection = true;
	} else{
		datacorrection = false;
	}
	var rowCountOfBidDataForABank= jQuery('#rowCountOfBidDataForABank').val();
	
	if((datacorrection) && (rowCountOfBidDataForABank> 0)) {
		alert("この金融機関のデータはすでに登録されています。データを修正する場合は、データ修正ボタンをクリックしてください。");
		isOk = false;
	}
	
	if(isOk){
		jQuery('#bidform').ajaxForm({
		    success:function(res) {
		    	if(res == 1){
			 	   	//window.location.href=base+"/";
		    		clearForm();
			    }
		     },
		     dataType:"text"
		   }).submit();
	}
}

function findTotal(){
    var arr = document.getElementsByName('txtBidAmount');
    var tot=0;
    for(var i=0;i<arr.length;i++){
        if(parseFloat(arr[i].value))
            tot += parseFloat(arr[i].value);
    }
    jQuery('#txtTotalAmount').val(tot);
}