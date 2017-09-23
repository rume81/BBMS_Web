<script type="text/javascript" src="${rc.contextPath}/js/interestrateorderbidstatus.js"></script>
<div class="body col-md-6 col-md-offset-3">
	<div class="form-group" style="display:none">
		<div class="col-sm-4">
    		<select class="form-control" id="cmbDeptCode" name="cmbDeptCode">
    			<option value=""></option>
    			<#list divisions as dept>
    			<option value="${dept.department_code}" <#if base.department_code==dept.department_code>selected</#if>>${dept.department_code}</option>
    			</#list>
    		</select>
    		<input type="hidden" id="bidDate" name="bidDate" value="${bidDate}"/>
		</div>
		<div class="col-sm-4">
            <input type="text" class="form-control" id="txtDeptName" name="txtDeptName" placeholder="" <#list divisions as dept><#if base.department_code==dept.department_code>value=${base.department_name!''}<#break></#if></#list>/>
            <input type="text" class="form-control" id="total_amount" name="total_amount" placeholder=""/>
        </div>
	</div>
    <div class="col-md-8 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="getRpt('${rc.contextPath}',1);">明細あり</button>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="getRpt('${rc.contextPath}',2);">明細なし</button>
    </div>
    
    <div class="col-md-8 col-md-offset-2 interest_rate_close">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/'">閉じる</button>
    </div>
   
</div>