<script type="text/javascript" src="${rc.contextPath}/js/successfulbiddata.js"></script>
<div class="body col-md-6 col-md-offset-3">
	<div style="display:none">
		<div class="col-sm-12">
			<label for="" class="col-sm-3">勘定</label>
    		<select class="col-sm-7" id="cmbDeptCode" name="cmbDeptCode">
    			<option value=""></option>
    			<#list divisions as dept>
    			<option value="${dept.department_code}" <#if base.department_code==dept.department_code>selected</#if>>${dept.department_code}</option>
    			</#list>
    		</select>
    		<input type="hidden" id="bidDate" name="bidDate" value="${bidDate}"/>
		</div>
		<div class="col-sm-12">
			<input type="text" class="col-sm-7" id="txtDeptName" name="txtDeptName" placeholder="" <#list divisions as dept><#if base.department_code==dept.department_code>value=${base.department_name!''}<#break></#if></#list>/>
		</div>	
		<div class="col-sm-12">
			<label for="" class="col-sm-3">データの更新</label>
			<select class="col-sm-7" id="cmbDataUpdated" name="cmbDataUpdated">
    			<option value=""></option>
    			<option value="する" selected>する</option>
    			<option value="しない" >しない</option>
    		</select>
    	</div>
		<div class="col-sm-12">
			<label for="" class="col-sm-3">最低限度額</label>
            <input type="text" class="col-sm-7" id="txtMinimumAmount" name="txtMinimumAmount" placeholder=""/>
        </div>
	</div>
    <div class="col-sm-12">
        <label for="" class="col-sm-3">入札予定額 &nbsp; （単位：億円）</label>
        <input type="text" id="txtBidAmountPland" name="txtBidAmountPland" class="col-sm-7" style="text-align:right;" value="${bidAmountPland!''}" onchange="setFormValue('${rc.contextPath}')"/>
    </div>
    <div class="col-sm-12 stop_rate">
        <label for="" class="col-sm-3">足切り利率</label>
        <input type="text" id="txtFootCutInterestRates" name="txtFootCutInterestRates" class="col-sm-7" style="text-align:right;" value="${footCutInterestRates!''}" onchange="setFormValue('${rc.contextPath}')"/>
        &#37;
        <!--<label class="col-sm-1">&#37;</label>-->
    </div>

    <div class="col-md-6">
        <button class="btn btn-default" id="cmdFinancialInstitutionsBy" type="button" onclick="getRptSB('${rc.contextPath}',1);">金融機関別</button>
    </div>
    <div class="col-sm-6 empty_column">
        &nbsp;
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" id="cmdInterestRateBy"  type="button" onclick="getRptSB('${rc.contextPath}',2);">利率別</button>
    </div>
    
    <div class="col-md-6">
        <button class="btn btn-default" id="cmdCloseup"  type="button"  onclick="closeform('${rc.contextPath}');">閉じる</button>
    </div>
   
</div>