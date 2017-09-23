<script type="text/javascript" src="${rc.contextPath}/js/user.js"></script>
<div class="body col-md-6 col-md-offset-2">
	<form class="form-horizontal" id="changepassform" name="changepassform" action="${rc.contextPath}/userupdate" method="post">
    <div class="col-sm-12">
        <div class="col-sm-4">
            <p class="white_border">ユーザー名</p>
        </div>
        <div class="col-sm-7">
            <input type="hidden" id="username" name="username" value="${currentuser.user_id!''}" class="form-control"/>
            <input type="text" id="un" name="un" value="${currentuser.user_name!''}" class="form-control" disabled/>
            <input type="hidden" id="fname" name="fname" value="${currentuser.full_name!''}" class="form-control"/>
            <input type="hidden" id="roles" name="roles" value="${currentuser.roles!''}" class="form-control"/>
        </div>
    </div>
    <div id="subload">
	    <div class="col-sm-12">
	        <div class="col-sm-4">
	            <p class="white_border">パスワード</p>
	        </div>
	        <div class="col-sm-7">
	            <input type="password" id="pass" name="pass" value="" class="form-control"/>
	        </div>
	    </div>
	    
	    <div class="col-sm-12">
	        <div class="col-sm-4">
	            <p class="white_border">パスワードを再度入力してください</p>
	        </div>
	        <div class="col-sm-7">
	            <input type="password" id="repass" name="repass" value="" class="form-control"/>
	        </div>
	    </div>
	    
    </div>
    <div class="col-sm-12">
        <div class="col-sm-offset-2 col-sm-4">
            <button type="button" class="btn btn-default" id="cmdSave" onclick="changePass('${rc.contextPath}')">編集</button>
        </div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-default data_button" onclick="location.href='${rc.contextPath}/'">閉じる</button>
        </div>
    </div>
    </form>
</div>