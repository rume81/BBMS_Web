<link rel="stylesheet" href="${rc.contextPath}/css/common/jquery.dataTables.min.css" type="text/css"/>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/officialnameconversion.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#officialnameconversion').DataTable({
	    "iDisplayLength": 50
	  });
})
</script>
<div class="body col-md-8 col-md-offset-2">
    <div class="col-md-3 col-md-offset-6">
        <button class="btn btn-default" type="button" onclick="saveOfficialNameConversion('${rc.contextPath}')">登　　録</button>
    </div>
    <div class="col-md-3 col-md-offset-6">
        <button class="btn btn-default" type="submit" onclick="location.href='${rc.contextPath}/mastermaintenance'">閉じる</button>
    </div>
    <div class="col-sm-12">
    <div id="ncsub">
        <table class="table table-bordered table-hover" id="officialnameconversion">
            <thead>
                <tr>
	                <th>金融機関コード</th>
	                <th>名称</th>
	                <th>正式名称</th>
	                <th>業態</th>
	                <th>削除</th>
	                <th></th>
                </tr>
            </thead>
            <tbody>
            <#list nameConversion as nc>
                <tr>
                    <td>${nc.bank_code!''}</td>
                    <td>${nc.abbreviation!''}</td>
                    <td>${nc.financial_institution_name!''}</td>
                    <td>${nc.business_category!''}</td>
                    <td><input type="checkbox" id="delf" value="" name="chk" <#if nc.delF==-1>checked="checked"</#if> disabled>
                    </td>
                    <td class="no_margin_bottom">
	                    <div class="btn-group">
	                        <button type="button" title="編集" class="btn btn-primary" data-toggle="modal" data-target="#n${nc.bank_code}"><i class="fa fa-pencil-square-o"></i></button>
	                    </div>
	                </td>
                </tr>
                </#list>
            </tbody>
          </table>
         </div>
    </div>
</div>

<!-- Edit official name conversion -->
<#list nameConversion as nc>
	<div class="modal fade" id="n${nc.bank_code}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel"></h4>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">金融機関コード</label>
	                <input type="text" class="col-sm-7" value="${nc.bank_code}" id="bank_code${nc.bank_code}" name="bank_code" disabled>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">名称</label>
	                <input type="text" class="col-sm-7" value="${nc.abbreviation}" id="abbreviation${nc.bank_code}" name="abbreviation" disabled>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">店舗</label>
	                <input type="text" class="col-sm-7" value="${nc.financial_institution_name}" id="financial_institution_name${nc.bank_code}" name="financial_institution_name">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">業態</label>
	                <input type="text" class="col-sm-7" value="${nc.business_category}" id="business_category${nc.bank_code}" name="business_category" disabled>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">削除</label>
	                <input type="checkbox" id="delete${nc.bank_code}" name="delete" value=""<#if nc.delF==-1>checked="checked"</#if>> <br>
	            </div>
	            <div class="modal-footer">
	                <button type="button" id="updateOfficialNameConversion" class="btn btn-primary" onclick="updateOfficialNameConversion('${rc.contextPath}','${nc.bank_code}');">セーブ</button>
	                <button type="button" id="" class="btn btn-default" data-dismiss="modal">キャンセル</button>
	            </div>
	        </div>
	    </div>
	</div>
</#list>