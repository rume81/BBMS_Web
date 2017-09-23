<script type="text/javascript" src="${rc.contextPath}/js/notesapplicationformprint.js"></script>
<script>
$(document).ready(function() {
	$('#txtBorrowingDate').datepicker({ dateFormat: 'dd-mm-yy' });
	$('#txtMaturityDate').datepicker({ dateFormat: 'dd-mm-yy' });
	$('#txtIntermidiateInterestPayment').datepicker({ dateFormat: 'dd-mm-yy' });
})
</script>
<div class="body col-md-8 col-md-offset-2">
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
            <input type="text" class="form-control" id="txtDepartmentName" name="txtDepartmentName" placeholder="" <#list divisions as dept><#if base.department_code==dept.department_code>value=${base.department_name!''}<#break></#if></#list>/>
            <input type="text" class="form-control" id="txtProcessing" name="txtProcessing"/>
        </div>
	</div>
    <div class="col-sm-12">
        <div class="col-sm-2">
            <p class="white_border">借入日</p>
        </div>
        <div class="col-sm-5">
            <input type="text" name="txtBorrowingDate" id="txtBorrowingDate"  class="form-control" value="${borrowingDate!''}"  onchange="setFormValue('${rc.contextPath}')"/>
        </div>
    </div>
    <div class="col-sm-12 stop_rate1">
        <div class="col-sm-2">
            <p class="white_border">返済日</p>
        </div>
        <div class="col-sm-5">
            <input type="text" name="txtMaturityDate" id="txtMaturityDate" class="form-control" value="${maturityDate!''}"  onchange="setFormValue('${rc.contextPath}')"/>
        </div>
    </div>
    <div class="col-sm-12" style="display:none">
        <div class="col-sm-2">
            <p class="white_border">中間利払日</p>
        </div>
        <div class="col-sm-5">
            <input type="text" name="txtIntermidiateInterestPayment" id="txtIntermidiateInterestPayment" class="form-control"/>
        </div>
    </div>
    
    <div class="col-sm-4 ">
        <p class="white_border">認可申請書別<br>紙の単位・並<br>替順</p>
    </div>
    <div class="col-sm-4">
        <div class="col-md-6 no_padding">
            <select class="form-control no_padding" id="cmbUnit" name="cmbUnit">
                <option value="億円">億円</option>
                <option value="円">円</option>
            </select>
            <select class="form-control" id="cmbRearrangement" name="cmbRearrangement">
                <option value="金融機関">金融機関</option>
                <option value="借入金額">借入金額</option>
                <option value="利率">利率"</option>
            </select>
        </div>
        <div class="col-sm-6">
            <p class="white_border">申込書一覧<br>の並替</p>
        </div>
    </div>
    <div class="col-sm-4 btn_min_height">
        <select class="form-control" id="cmbBillRearrangement" name="cmbBillRearrangement">
            <option value="利率・手 形番号">利率・手形番号</option>
            <option value="金融機関">金融機関</option>
            <option value="手形番号">手形番号</option>
        </select>
    </div>
    <div class="col-sm-4">
        <button class="btn btn-default" type="button" style="height:50px" id="cmdCreatingCreditApplication" onclick="getRptNAFP('${rc.contextPath}',1);">借入申込書デー<br>タ作成</button>
    </div>
    <div class="col-sm-4">
        <button class="btn btn-default" type="button" style="height:50px" id="cmdCreditApplication" onclick="getRptNAFP('${rc.contextPath}',2);">借入申込書印刷<br></button>
    </div>
    <div class="col-sm-4">
        <button class="btn btn-default" type="button" style="height:50px" id="cmdApprovalApplicationForm" onclick="getRptNAFP('${rc.contextPath}',4);">認可申請書別紙</button>
    </div>
    <div class="col-sm-4">
        <button class="btn btn-default" type="button" style="height:50px" id="cmdCreditApplicationList" onclick="getRptNAFP('${rc.contextPath}',3);">借入申込書一覧 <br>印刷</button>
    </div>
    <div class="col-sm-4">
        <button class="btn btn-default" type="button" style="height:50px" id="cmdDeedPrint" onclick="LoanAgreementDeed('${rc.contextPath}');">証書印刷 </button>
    </div>
    <div class="col-sm-4">
        <button class="btn btn-default" type="button" style="height:50px" id="cmdDocumentImage" onclick="TransferSlip('${rc.contextPath}');">伝票イメージ</button>
    </div>
    <div class="col-sm-4">
        <button class="btn btn-default" type="button" style="height:50px" id="cmdCreditorManagementBook" onclick="changeProcessing('債権'); getRptNAFP('${rc.contextPath}',3);">債権者管理簿<br>作成</button>
    </div>
    <div class="col-sm-4 empty_column">
        &nbsp;
    </div>
    <div class="col-sm-4 ">
        <button class="btn btn-default" type="button" style="height:50px" id="cmdCloseup" onclick="closeform('${rc.contextPath}');">閉じる</button>
    </div>
</div>