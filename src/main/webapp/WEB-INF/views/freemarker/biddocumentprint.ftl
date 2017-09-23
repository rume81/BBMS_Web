<script type="text/javascript" src="${rc.contextPath}/js/biddocumentprint.js"></script>
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
            <button class="btn btn-default" type="button" id="cmdTransmissionDate">送信用データ</button>
        </div>
	</div>
    <div class="col-sm-12 stop_rate">
        <div class="col-sm-3">
            <p class="white_border">落札書日付</p>
        </div>
        <div class="col-sm-6">
            <input type="date" id="txtSuccessfulBidDocumentDate" name="txtSuccessfulBidDocumentDate" value="${biddateJP!''}" class="form-control" style="text-align:right;"/>
        </div>
    </div>
    

    <div class="col-md-6">
        <button class="btn btn-default" type="button" id="cmdProvisionalNotice" onclick="getBidDocumentRpt('${rc.contextPath}',2);">仮通知書</button>
    </div>
    <div class="col-sm-6 empty_column">
        &nbsp;
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" id="cmdSuccessfulBidDocument" onclick="getBidDocumentRpt('${rc.contextPath}',1);">落札書</button>
    </div>
    
    <div class="col-md-6">
        <button class="btn btn-default" type="button" id="cmdCloseup" onclick="location.href='${rc.contextPath}/'">閉じる</button>
    </div>
   
</div>