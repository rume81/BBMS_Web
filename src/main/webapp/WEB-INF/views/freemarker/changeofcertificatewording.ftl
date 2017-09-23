<link rel="stylesheet" href="${rc.contextPath}/css/common/jquery.dataTables.min.css" type="text/css"/>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/certificatewording.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/master.js"></script>

<div class="body col-md-8 col-md-offset-2">
    <div class="col-md-3 col-md-offset-6">
        <button class="btn btn-default" type="button" data-toggle="modal" data-target="#insert_wording">新しく追加する</button>
    </div>
    <div class="col-md-3 col-md-offset-6">
        <button class="btn btn-default" type="submit" onclick="location.href='${rc.contextPath}/mastermaintenance'">閉じる</button>
    </div>
    <div class="col-sm-12">
        <table class="table table-bordered table-hover" id="financialInstitute">
            <thead>
                <tr>
	                <th>部門コード</th>
	                <th>HOREI01</th>
	                <th>HOREI02</th>
	                <th></th>
                </tr>
            </thead>
            <tbody>
            	<#list contactsWording as cw>
	                <tr>                   
	                    <td style="width: 15%";>${cw.department_code!''}</td>
	                    <td>${cw.horei01!''}</td>
	                    <td>${cw.horei02!''}</td>
	                    <td class="no_margin_bottom">
		                    <div class="btn-group">
		                        <button type="button" title="編集" class="btn btn-primary" data-toggle="modal" data-target="#w${cw.department_code}"><i class="fa fa-pencil-square-o"></i></button>
		                    </div>
		                </td>
	                </tr>
                </#list>
            </tbody>
          </table>
    </div>
</div>

<!-- For add Change of certificate wording --> 

<div class="modal fade" id="insert_wording" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel"></h4>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">部門コード</label>
	            	<select class="col-sm-7" id="department_code">
	            	<#list divisions as dv>
	            		<option value="${dv.department_code!''}" id="department_code" name="department_code">${dv.department_code}</option>
	                </#list>
	                </select>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">HOREI01</label>
	            	<textarea class="col-sm-7" value="" id="horei01" name="horei01" rows="3" cols="50"></textarea>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">HOREI02</label>
	            	<textarea class="col-sm-7" value="" id="horei02" name="horei02" rows="3" cols="50"></textarea>
	            </div>
	
	            <div class="modal-footer">
	                <button type="button" id="insert" class="btn btn-primary" onclick="insertCertificateWording('${rc.contextPath}')">セーブ</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">キャンセル</button>
	            </div>
	        </div>
	    </div>
	</div>
	
<!-- For Edit Change of certificate wording --> 

<#list contactsWording as cw>
	<div class="modal fade" id="w${cw.department_code}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel"></h4>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">部門コード</label>
	                <input type="text" class="col-sm-7" value="${cw.department_code}" id="department_code${cw.department_code}" name="department_code" disabled>
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">HOREI01</label>
	            	<textarea class="col-sm-7" value="" id="horei01${cw.department_code}" name="horei01" rows="3" cols="50">${cw.horei01}</textarea>
	                
	            </div>
	            <div  class="col-sm-12">
	            	<label for="" class="col-sm-3">HOREI02</label>
	            	<textarea class="col-sm-7" value="" id="horei02${cw.department_code}" name="horei02${cw.horei02}" rows="3" cols="50">${cw.horei02}</textarea>
	            </div>
	
	            <div class="modal-footer">
	                <button type="button" id="updateCertificateWording" class="btn btn-primary" onclick="updateCertificateWording('${rc.contextPath}','${cw.department_code}')">セーブ</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">キャンセル</button>
	            </div>
	        </div>
	    </div>
	</div>
</#list>
