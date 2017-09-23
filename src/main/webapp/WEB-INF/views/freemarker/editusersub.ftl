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
        <input type="text" id="fname" name="fname" value="${suser.full_name!''}" class="form-control"/>
    </div>
</div>

<div class="col-sm-12  stop_rate1">
    <div class="col-sm-4">
        <p class="white_border">ロール</p>
    </div>
    <div class="col-sm-7">
        <select class="form-control" name="roles" id="roles">
        	<option value=""></option>
        	<option value="Admin" <#if suser.roles?? && suser.roles=="Admin">selected="selected"</#if>>Admin</option>
        	<option value="User" <#if suser.roles?? && suser.roles=="User">selected="selected"</#if>>User</option>
        </select>
    </div>
</div>