<link rel="stylesheet" href="${rc.contextPath}/css/common/jquery.dataTables.min.css" type="text/css"/>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/financialinstitution.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/master.js"></script>

<div class="body col-md-8 col-md-offset-2">
    <div class="col-md-3 col-md-offset-3">
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#f_insert">新しく追加する</button>
    </div>
    <div class="col-md-3">
        <button class="btn btn-default" type="button" onclick="savefinancialmaster('${rc.contextPath}')">登　　録</button>
    </div>
    <div class="col-md-3 col-md-offset-6">
        <button class="btn btn-default" type="submit" onclick="location.href='${rc.contextPath}/mastermaintenance'">閉じる</button>
    </div>
    <div class="col-sm-12">
    	<div id="fitable">
	        <table class="table table-bordered table-hover" id="financialInstitute">
	            <thead>
	                <tr>
		                <th>コード</th>
		                <th>名称</th>
		                <th>店舗</th>
		                <th>業態</th>
		                <th>削除</th>
		                <th></th>
	                </tr>
	            </thead>
	                <tbody>
	                <#list finacial as fi>
	                    <tr>                   
	                        <td>${fi.bank_code!''}</td>
	                        <td>${fi.financial_institution_name!''}</td>
	                        <td>${fi.store!''}</td>
	                        <td>${fi.business_category!''}</td>
	                        <td><input type="checkbox" id="delf" value="" name="chk" <#if fi.delF==-1>checked="checked"</#if> disabled></td>
	                        <td class="no_margin_bottom">
			                    <div class="btn-group">
			                        <button type="button" title="" class="btn btn-primary" data-toggle="modal" data-target="#f${fi.bank_code}"><i class="fa fa-pencil-square-o"></i></button>
			                    </div>
			                </td>
	                    </tr>
	                    </#list>
	                </tbody>
	            </table>
        </div>
    </div>
</div>

<!-- Edit financial Institution -->
<#list finacial as fi>
	<div class="modal fade" id="f${fi.bank_code}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel"></h4>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">コード</label>
	                <input type="text" class="col-sm-7" value="${fi.bank_code}" id="bank_code_${fi.bank_code}" name="bank_code" disabled>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">名称</label>
	                <input type="text" class="col-sm-7" value="${fi.financial_institution_name}" id="financial_institution_name_${fi.bank_code}" name="financial_institution_name">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">店舗</label>
	                <input type="text" class="col-sm-7" value="${fi.store}" id="store_${fi.bank_code}" name="store">
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">業態</label>
	                <select class="col-sm-7" id="">
	                <#list finacial as fin>
						  <option value="${fin.business_category}"<#if fin.business_category==fi.business_category>selected</#if> id="business_category_${fi.bank_code}" name="business_category">${fin.business_category}</option>
					</#list>
					</select>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">削除</label>
	                <input type="checkbox" id="delete_${fi.bank_code}" name="delete" value=""<#if fi.delF==-1>checked="checked"</#if>> <br>
	            </div>
	            <div class="modal-footer">
	                <button type="button" id="editFinancailInstitution" class="btn btn-primary" onclick="editfinancialinstitution('${rc.contextPath}','${fi.bank_code}')">セーブ</button>
	                <button type="button" id="" class="btn btn-default" data-dismiss="modal">キャンセル</button>
	            </div>
	        </div>
	    </div>
	</div>
</#list>

<div class="modal fade" id="f_insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div  class="col-sm-12">
            	<label for="" class="col-sm-3">コード</label>
                <input type="text" class="col-sm-7" value="" id="bank_code" name="bank_code">
            </div>
            <div  class="col-sm-12">
            	<label for="" class="col-sm-3">名称</label>
                <input type="text" class="col-sm-7" value="" id="financial_institution_name" name="financial_institution_name">
            </div>
            <div  class="col-sm-12">
            	<label for="" class="col-sm-3">店舗</label>
                <input type="text" class="col-sm-7" value="" id="store" name="store">
            </div>
            <div  class="col-sm-12">
            	<label for="" class="col-sm-3">業態</label>
                <select class="col-sm-7" id="">
                <#list busicat as bc>
					  <option value="${bc.businesscategory}" id="business_category" name="business_category">${bc.businesscategory}</option>
				</#list>
				</select>
            </div>
            <div class="modal-footer">
                <button type="button" id="insert" class="btn btn-primary" onclick="insertfinancialinstitution('${rc.contextPath}')">セーブ</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">キャンセル</button>
            </div>
        </div>
    </div>
</div>
