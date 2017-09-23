<script type="text/javascript" src="${rc.contextPath}/js/home.js"></script>
<script>
$(document).ready(function() {
	$('#txtTargetAuction').datepicker({ dateFormat: 'dd-mm-yy' });	
})
</script>

<div class="body col-md-6 col-md-offset-3">
    <div class="col-md-6 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/FinancialInstitutionMaster'">金融機関マスターメンテ</button>
    </div>
    <div class="col-md-6 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/ChangeofCertificateWording'">証書文言変更</button>
    </div>
    <div class="col-md-6 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/VariousParametersChange'">各種パラメータ変更</button>
    </div>
    <div class="col-md-6 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/OfficialNameConversion'">正式名称変換テーブル</button>
    </div>
    <div class="col-md-6 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="clearTable('${rc.contextPath}');">明細データクリア</button>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <div class="col-sm-5">
            <p>対象入札日</p>
        </div>
        <div class="col-sm-7">
            <input type="text" name="txtTargetAuction" id="txtTargetAuction" class="form-control"/>
        </div>
    </div>
    <div class="col-md-6 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/home'">終　　　　　　　　了</button>
    </div>
</div>