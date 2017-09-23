<script type="text/javascript" src="${rc.contextPath}/js/home.js"></script>
<script>
$(document).ready(function() {
	$('#bid_date').datepicker({ dateFormat: 'dd-mm-yy' });	
})
</script>
<div class="body col-md-8 col-md-offset-2">
    <div class="col-md-4">
        <span class="datetitle">入札日</span>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <div class='input-group date' id='datetimepicker1'>
                <input type='text' class="form-control" id="bid_date" name="bid_date" value="${bidDate!''}" onchange="setBidDate('${rc.contextPath}')"/>
                <!--<span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>-->
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <button class="btn btn-default" type="button" style="background-color:transparent; border-color:transparent;"  onclick="downloadExcel('${rc.contextPath}');"><img src="${rc.contextPath}/images/Excel.png" height="35"/>Excel出力</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="getBidData('${rc.contextPath}');">入札データ入力・修正・削除</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="getFinancialinstituteRpt('${rc.contextPath}');">金融機関別入札状況表</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="getInterestRateOrderBidStatusView('${rc.contextPath}');">利率順入札状況表</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="getSuccessfulBidDataCreationView('${rc.contextPath}');">落札データ作成</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="getSuccessfulBidDataMaintanenceView('${rc.contextPath}');">落札データメンテ</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="getBidDocumentPrintView('${rc.contextPath}');">落  札  書  印  刷</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="getNotesApplicationFormPrintView('${rc.contextPath}');">借入申込書作成</button>
    </div>
    
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/user/logout'">終　　　　　　　　了</button>
    </div>
</div>
        