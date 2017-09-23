<link rel="stylesheet" href="${rc.contextPath}/css/common/jquery.dataTables.min.css" type="text/css"/>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/systemmaintenance.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#systemmaintenance').DataTable({
	    "iDisplayLength": 50
	  });
})
</script>

<div class="body col-md-8 col-md-offset-2">
	<div class="col-md-3">
		<p class="white_border padding_top">${systemname!''}</p>
		<input type="hidden" value="${systemname}" id="systemname"/>
	</div>
    <div class="col-md-3 col-md-offset-2">
        <button class="btn btn-default" type="button" onclick="saveSystemMaintenance('${rc.contextPath}');">登　　録</button>
    </div>
    <div class="col-md-3">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/home'">閉じる</button>
    </div>
    <div class="col-sm-12">
    <input type="hidden" value="${fieldname}" id="fieldname"/>
    	<div id="fitable">
    	<div id="smtable">
	        <table class="table table-bordered table-hover" id="systemmaintenance">
	            <thead>
	                <tr>
		                <th>コード</th>
		                <th>名称</th>
		                <th>店舗</th>
		                <th>業態</th>
		                <th>使用状況</th>
		                <th></th>
	                </tr>
	            </thead>
	                <tbody>
	                <#list systemMaintenance as sm>
	                    <tr>                   
	                        <td>${sm.bank_code}</td>
	                        <td>${sm.financial_institution_name}</td>
	                        <td>${sm.store}</td>
	                        <td>${sm.business_category}</td>
	                        <td>${sm.FN}</td>
	                        <td class="no_margin_bottom">
			                    <div class="btn-group">
			                        <button type="button" title="" class="btn btn-primary" data-toggle="modal" data-target="#s${sm.bank_code}"><i class="fa fa-pencil-square-o"></i></button>
			                    </div>
			                </td>
	                    </tr>
	                    </#list>
	                </tbody>
	            </table>
	          </div>
        </div>
    </div>
</div>

<#list systemMaintenance as sm>
<div class="modal fade" id="s${sm.bank_code}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel"></h4>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">使用状況</label>
	                <select class="col-sm-7" id="usestatus${sm.bank_code}" name="usestatus">
						 <option value="0">0</option>
						 <option value="1">1</option>
					</select>
	            </div>
	            <div class="modal-footer">
	                <button type="button" id="editFinancailInstitution" class="btn btn-primary" onclick="updateSystemMaintenance('${rc.contextPath}','${sm.bank_code}');" />登　　録</button>
	                <button type="button" id="" class="btn btn-default" data-dismiss="modal">キャンセル</button>
	            </div>
	        </div>
	    </div>
	</div>
</#list>
