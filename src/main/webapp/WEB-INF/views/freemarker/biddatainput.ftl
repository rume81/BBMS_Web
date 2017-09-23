<script type="text/javascript" src="${rc.contextPath}/js/biddatainput.js"></script>
<div class="body col-md-8 col-md-offset-2">
	<div class="col-md-12">
	    <form class="form-horizontal" id="bidform" name="bidform" action="${rc.contextPath}/biddataregister" method="post">
	    	<div class="form-group" style="display:none">
	    		<div class="col-sm-4">
		    		<select class="form-control" id="departmentCode" name="departmentCode">
		    			<option value=""></option>
		    			<#list divisions as dept>
		    			<option value="${dept.department_code}" <#if base.department_code==dept.department_code>selected</#if>>${dept.department_code}</option>
		    			</#list>
		    		</select>
		    		<input type="hidden" id="bidDate" name="bidDate" value="${bidDate}"/>
	    		</div>
	    		<div class="col-sm-4">
	                <input type="text" class="form-control" id="deptname" name="deptname" placeholder="" <#list divisions as dept><#if base.department_code==dept.department_code>value=${base.department_name!''}<#break></#if></#list>/>
	            </div>
	    	</div>
	        <div class="form-group">
	            <label for="" class="col-sm-2 border">金融機関</label>
	            <div class="col-sm-4">
	                <select class="form-control" name="financial_institute" id="financial_institute" onChange="FinancialinstituteDetails('${rc.contextPath}');">
	                	<option value=""></option>
	                	<#list finacial as fi>
	                    <option value="${fi.bank_code!''}">${fi.bank_code!''}&nbsp;|&nbsp;${fi.financial_institution_name!''}</option>
	                    </#list>
	                </select>
	            </div>
	            <div class="col-sm-4">
	                <input type="text" class="form-control" id="inst_name" name="inst_name" placeholder="">
	            </div>
	            <div class="col-sm-2">
	                <input type="text" class="form-control" id="busi_cat" name="busi_cat" placeholder="">
	            </div>
	        </div>
	        <div class="form-group">
	            <label for="inputPassword3" class="col-sm-4 border bid_interest col-sm-offset-2">入札利率</label>
	            <label for="inputPassword3" class="col-sm-4 border bid_interest">入札金額</label>
	        </div>
	        <div id="bid_data" name="bid_data">
		        <div class="form-group">
		            <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label" >1</label>
		            <div class="col-sm-3 no_padding_right">
		                <input type="text" class="form-control" id="txtInterestRate1" name="txtInterestRate" style="text-align:right;" placeholder="">
		            </div> 
		            <div class="col-sm-1 no_padding_left percentise">
		                 &#37;
		            </div>
		            <div class="col-sm-4">
		                <input type="text" class="form-control" id="txtBidAmount1" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="">
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">2</label>
		            <div class="col-sm-3 no_padding_right">
		                <input type="text" class="form-control" id="txtInterestRate2" name="txtInterestRate" style="text-align:right;" placeholder="">
		            </div> 
		            <div class="col-sm-1 no_padding_left percentise">
		                 &#37;
		            </div>
		            <div class="col-sm-4">
		                <input type="text" class="form-control" id="txtBidAmount2" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="">
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">3</label>
		            <div class="col-sm-3 no_padding_right">
		                <input type="text" class="form-control" id="txtInterestRate3" name="txtInterestRate" style="text-align:right;" placeholder="">
		            </div> 
		            <div class="col-sm-1 no_padding_left percentise">
		                 &#37;
		            </div>
		            <div class="col-sm-4">
		                <input type="text" class="form-control" id="txtBidAmount3" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="">
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">4</label>
		            <div class="col-sm-3 no_padding_right">
		                <input type="text" class="form-control" id="txtInterestRate4" name="txtInterestRate" style="text-align:right;" placeholder="">
		            </div> 
		            <div class="col-sm-1 no_padding_left percentise">
		                 &#37;
		            </div>
		            <div class="col-sm-4">
		                <input type="text" class="form-control" id="txtBidAmount4" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="">
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">5</label>
		            <div class="col-sm-3 no_padding_right">
		                <input type="text" class="form-control" id="txtInterestRate5" name="txtInterestRate" style="text-align:right;" placeholder="">
		            </div> 
		            <div class="col-sm-1 no_padding_left percentise">
		                 &#37;
		            </div>
		            <div class="col-sm-4">
		                <input type="text" class="form-control" id="txtBidAmount5" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="">
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="" class="col-sm-2 col-sm-offset-3 control-label">計</label>
		            
		            <div class="col-sm-4 col-sm-offset-1">
		            	<input type="hidden" id="rowCountOfBidDataForABank" name="rowCountOfBidDataForABank" value="0"/>
		                <input type="text" class="form-control" id="txtTotalAmount" name="txtTotalAmount" style="text-align:right;" placeholder="">
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-4">
		                <button type="button" class="btn btn-default data_button" id="cmdRegistration"  onclick="BidDataRegister('${rc.contextPath}')">登録</button>
		            </div>
		            <div class="col-sm-4">
		                <button type="button" class="btn btn-default data_button" onclick="location.href='${rc.contextPath}/'">閉じる</button>
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-4">
		                <button type="button" class="btn btn-default" id="cmdDataCorrection" onclick="BidDataRegister('${rc.contextPath}')" disabled>データ修正</button>
		            </div>
		            <div class="col-sm-4">
		                <button type="button" class="btn btn-default" id="cmdDelete" onclick="BidDataDeregister('${rc.contextPath}')" disabled>削除</button>
		            </div>
		        </div>
			</div>
	    </form>
	</div>
</div>