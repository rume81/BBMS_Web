function createUser(base){
	var username = jQuery('#username').val();
	var pass = jQuery('#pass').val();
	var roles = jQuery('#roles').val();
		
	
	if(username == "")
	{
		alert("ユーザー名を挿入");
		return;
	}
	
	if(pass == "")
	{
		alert("パスワードを挿入する");
		return;
	}
	
	if(roles == "")
	{
		alert("役割の選択");
		return;
	}
	
	var pars =  username+"~"+pass;
	ajaxHelper.complexAjaxRequest(base+"/checkusername", pars, function(res){
		if(res!="-1"){
	 		alert("ユーザー名は既に存在します");
	    	return;
	    } else{
	    	jQuery('#userform').ajaxForm({
	    		success:function(res) {
	    			if(res == 1){
	    				alert("新しいユーザーが作成されました");
	    				window.location.href=base+"/";
	    			}
	    		},
	    	dataType:"text"
	    	}).submit();
	    }
	});
}

function getuserinfo(base){
	var userid = jQuery('#username').val();
	var pars =  userid;
	ajaxHelper.complexAjaxRequest(base+"/getuserinfo", pars, function(res){
		if(res!="-1"){
			jQuery('#subload').html(res);
	    } 
	});
}

function editUser(base){
	var username = jQuery('#username').val();
	var pass = jQuery('#pass').val();
	var roles = jQuery('#roles').val();
		
	
	if(username == "")
	{
		alert("ユーザー名を挿入");
		return;
	}
	
	if(pass == "")
	{
		alert("パスワードを挿入する");
		return;
	}
	
	if(roles == "")
	{
		alert("役割の選択");
		return;
	}
	
	jQuery('#usereditform').ajaxForm({
		success:function(res) {
			if(res == 1){
				alert("ユーザーが編集しました");
				window.location.href=base+"/";
			}
		},
	dataType:"text"
	}).submit();
}

function changePass(base){
	var pass = jQuery('#pass').val();
	var repass = jQuery('#repass').val();
		
	
	if(pass != repass)
	{
		alert("パスワードが一致しません");
		return;
	}
		
	jQuery('#changepassform').ajaxForm({
		success:function(res) {
			if(res == 1){
				alert("ユーザーが編集しました");
				window.location.href=base+"/";
			}
		},
	dataType:"text"
	}).submit();
}