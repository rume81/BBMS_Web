<link rel="stylesheet" href="${rc.contextPath}/css/common/jquery.dataTables.min.css" type="text/css"/>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/successfulbiddata.js"></script>
<script>
$(document).ready(function() {
	$('#biddataList').DataTable({
    "iDisplayLength": 50
  });
})
</script>
<div class="body col-sm-12">
	<div style="display:none">
		<div class="col-sm-12">
			<input type="hidden" id="deptCode" name="deptCode" value="${base.department_code}"/>
			<input type="hidden" id="bidDate" name="bidDate" value="${bidDate}"/>
		</div>
	</div>
	
    <div class="col-xs-12">
		<table id="biddataList" class="display" cellspacing="0" width="100%">
			<thead>
	            <tr>
	                <th>部門コード</th>
	                <th>業態_ラベル</th>
	                <th>金融機関コード</th>
	                <th>金融機関名</th>
	                <th>入札番号</th>
	                <th>入札利率</th>
	                <th>入札金額</th>
	                <th>落札利率</th>
	                <th>落札金額</th>
	                <th></th>
	            </tr>
	        </thead>
	        <tfoot>
	            <tr>
	                <th>部門コード</th>
	                <th>業態_ラベル</th>
	                <th>金融機関コード</th>
	                <th>金融機関名</th>
	                <th>入札番号</th>
	                <th>入札利率</th>
	                <th>入札金額</th>
	                <th>落札利率</th>
	                <th>落札金額</th>
	                <th></th>
	            </tr>
	        </tfoot>
	        <tbody>
	        	<#list biddata as data>
		        	<tr>
		                <td>${data.department_code!''}</td>
		                <td>${data.business_category!''}</td>
		                <td>${data.bank_code!''}</td>
		                <td>${data.financial_institution_name!''}</td>
		                <td>${data.bid_number!''}</td>
		                <td>${data.bid_interest_rate?string["0.####"]}</td>
		                <td>${data.bid_amount_money!''}</td>
		                <td>${data.successful_bid_interest_rate?string["0.#####"]}</td>
		                <td>${data.successful_bid_price!''}</td>
		                <td>
		                    <div class="btn-group">
		                        <button type="button" title="編集" class="btn btn-primary" data-toggle="modal" data-target="#s${data.bank_code}_${data.bid_number}"><i class="fa fa-pencil-square-o"></i></button>

		                    </div>
		                </td>
		            </tr> 
		         </#list>
	        </tbody>
	    </table>
	</div>
    
    <div class="col-md-3">
        <button class="btn btn-default" id="cmdCloseup"  type="button"  onclick="location.href='${rc.contextPath}/'">閉じる</button>
    </div>
 </div>
 
 <#list biddata as data>
	<div class="modal fade" id="s${data.bank_code}_${data.bid_number}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel"></h4>
	            </div>
	            
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">部門コード</label>
	                <input type="text" class="col-sm-7" value="${data.department_code!''}" placeholder="" id="dept${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">業態</label>
	                <input type="text" class="col-sm-7" value="${data.business_category!''}" placeholder="" id="buscat${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">金融機関コード</label>
	                <input type="text" class="col-sm-7" value="${data.bank_code!''}" placeholder="" id="bank${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">金融機関名</label>
	                <input type="text" class="col-sm-7" value="${data.financial_institution_name!''}" placeholder="" id="fi${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">入札番号</label>
	                <input type="text" class="col-sm-7" value="${data.bid_number!''}" placeholder="" id="bidn${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">入札利率</label>
	                <input type="text" class="col-sm-7" value="${data.bid_interest_rate?string["0.####"]}" placeholder="" id="bidinst${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">入札金額</label>
	                <input type="text" class="col-sm-7" value="${data.bid_amount_money!''}" placeholder="" id="bidamo${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">落札利率</label>
	                <input type="text" class="col-sm-7" value="${data.successful_bid_interest_rate?string["0.#####"]}" placeholder="" id="sbidinst${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">落札金額</label>
	                <input type="text" class="col-sm-7" value="${data.successful_bid_price!''}" placeholder="" id="samount${data.bank_code}_${data.bid_number}" name="">
	            </div>
	            
	            <div class="modal-footer">
	                <button type="button" id="edit" class="btn btn-primary" onclick="editBidData('${rc.contextPath}','${data.bank_code}_${data.bid_number}')">セーブ</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">キャンセル</button>
	            </div>
	        </div>
	    </div>
	</div>
</#list>
