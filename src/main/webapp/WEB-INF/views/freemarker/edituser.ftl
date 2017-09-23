<script type="text/javascript" src="${rc.contextPath}/js/user.js"></script>
<div class="body col-md-6 col-md-offset-2">
	<form class="form-horizontal" id="usereditform" name="usereditform" action="${rc.contextPath}/userupdate" method="post">
    <div class="col-sm-12">
        <div class="col-sm-4">
            <p class="white_border">ユーザー名</p>
        </div>
        <div class="col-sm-7">
            <select class="form-control" name="username" id="username" onchange="getuserinfo('${rc.contextPath}')">
            	<option value=""></option>
            	<#list users as user>
            	<option value="${user.user_id!''}">${user.user_name!''}</option>
            	</#list>
            </select>
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
	            <p class="white_border">フルネーム</p>
	        </div>
	        <div class="col-sm-7">
	            <input type="text" id="fname" name="fname" value="" class="form-control"/>
	        </div>
	    </div>
	    
	    <div class="col-sm-12  stop_rate1">
	        <div class="col-sm-4">
	            <p class="white_border">ロール</p>
	        </div>
	        <div class="col-sm-7">
	            <select class="form-control" name="roles" id="roles">
	            	<option value=""></option>
	            	<option value="Admin">Admin</option>
	            	<option value="User">User</option>
	            </select>
	        </div>
	    </div>
    </div>
    <div class="col-sm-12">
        <div class="col-sm-offset-2 col-sm-4">
            <button type="button" class="btn btn-default" id="cmdSave" onclick="editUser('${rc.contextPath}')">編集</button>
        </div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-default data_button" onclick="location.href='${rc.contextPath}/'">閉じる</button>
        </div>
    </div>
    </form>
</div>