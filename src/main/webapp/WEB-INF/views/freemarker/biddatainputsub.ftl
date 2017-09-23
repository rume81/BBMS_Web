<#assign total=0>
<#assign item=0>
<div class="form-group">
    <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">1</label>
    <#assign fou=0>
    <#list biddata as bid>
		<#if bid.bid_number==1>
		    <div class="col-sm-3 no_padding_right">
		    	<input type="text" class="form-control" id="txtInterestRate1" name="txtInterestRate" style="text-align:right;" placeholder="" value="${bid.bid_interest_rate?string.number}">
		    </div> 
		    <div class="col-sm-1 no_padding_left percentise">
		         &#37;
		    </div>
		    <div class="col-sm-4">
		        <input type="text" class="form-control" id="txtBidAmount1" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="${bid.bid_amount_money?string.number}">
		        <#assign total=total+bid.bid_amount_money>
		    </div>
		    <#assign item=item+1>
		    <#assign fou=1>
			<#break>
	    </#if>
	</#list>
	<#if fou==0>
		<div class="col-sm-3 no_padding_right">
	    	<input type="text" class="form-control" id="txtInterestRate1" name="txtInterestRate" style="text-align:right;" placeholder="" value="">
	    </div> 
	    <div class="col-sm-1 no_padding_left percentise">
	         &#37;
	    </div>
	    <div class="col-sm-4">
	        <input type="text" class="form-control" id="txtBidAmount1" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="">
	    </div>
	</#if>
</div>
<div class="form-group">
    <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">2</label>
    <#assign fou=0>
    <#list biddata as bid>
		<#if bid.bid_number==2>
		    <div class="col-sm-3 no_padding_right">
		    	<input type="text" class="form-control" id="txtInterestRate2" name="txtInterestRate" style="text-align:right;" placeholder="" value="${bid.bid_interest_rate?string.number}">
		    </div> 
		    <div class="col-sm-1 no_padding_left percentise">
		         &#37;
		    </div>
		    <div class="col-sm-4">
		        <input type="text" class="form-control" id="txtBidAmount2" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="${bid.bid_amount_money?string.number}">
		        <#assign total=total+bid.bid_amount_money>
		    </div>
		    <#assign item=item+1>
		    <#assign fou=1>
			<#break>
	    </#if>
	</#list>
	<#if fou==0>
		<div class="col-sm-3 no_padding_right">
	    	<input type="text" class="form-control" id="txtInterestRate2" name="txtInterestRate" style="text-align:right;" placeholder="" value="">
	    </div> 
	    <div class="col-sm-1 no_padding_left percentise">
	         &#37;
	    </div>
	    <div class="col-sm-4">
	        <input type="text" class="form-control" id="txtBidAmount2" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="">
	    </div>
	</#if>
</div>
<div class="form-group">
    <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">3</label>
    <#assign fou=0>
    <#list biddata as bid>
		<#if bid.bid_number==3>
		    <div class="col-sm-3 no_padding_right">
		    	<input type="text" class="form-control" id="txtInterestRate3" name="txtInterestRate" style="text-align:right;" placeholder="" value="${bid.bid_interest_rate?string.number}">
		    </div> 
		    <div class="col-sm-1 no_padding_left percentise">
		         &#37;
		    </div>
		    <div class="col-sm-4">
		        <input type="text" class="form-control" id="txtBidAmount3" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="${bid.bid_amount_money?string.number}">
		        <#assign total=total+bid.bid_amount_money>
		    </div>
		    <#assign item=item+1>
		    <#assign fou=1>
			<#break>
	    </#if>
	</#list>
	<#if fou==0>
		<div class="col-sm-3 no_padding_right">
	    	<input type="text" class="form-control" id="txtInterestRate3" name="txtInterestRate" style="text-align:right;" placeholder="" value="">
	    </div> 
	    <div class="col-sm-1 no_padding_left percentise">
	         &#37;
	    </div>
	    <div class="col-sm-4">
	        <input type="text" class="form-control" id="txtBidAmount3" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="">
	    </div>
	</#if>
</div>
<div class="form-group">
    <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">4</label>
    <#assign fou=0>
    <#list biddata as bid>
		<#if bid.bid_number==4>
		    <div class="col-sm-3 no_padding_right">
		    	<input type="text" class="form-control" id="txtInterestRate4" name="txtInterestRate" style="text-align:right;" placeholder="" value="${bid.bid_interest_rate?string.number}">
		    </div> 
		    <div class="col-sm-1 no_padding_left percentise">
		         &#37;
		    </div>
		    <div class="col-sm-4">
		        <input type="text" class="form-control" id="txtBidAmount4" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="${bid.bid_amount_money?string.number}">
		        <#assign total=total+bid.bid_amount_money>
		    </div>
		    <#assign item=item+1>
		    <#assign fou=1>
		    <#break>
	    </#if>
	</#list>
	<#if fou==0>
		<div class="col-sm-3 no_padding_right">
	    	<input type="text" class="form-control" id="txtInterestRate4" name="txtInterestRate" style="text-align:right;" placeholder="" value="">
	    </div> 
	    <div class="col-sm-1 no_padding_left percentise">
	         &#37;
	    </div>
	    <div class="col-sm-4">
	        <input type="text" class="form-control" id="txtBidAmount4" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="">
	    </div>
	</#if>
</div>
<div class="form-group">
    <label for="inputEmail3" class="col-sm-1 col-sm-offset-1 border control-label">5</label>
    <#assign fou=0>
    <#list biddata as bid>
		<#if bid.bid_number==5>
		    <div class="col-sm-3 no_padding_right">
		    	<input type="text" class="form-control" id="txtInterestRate5" name="txtInterestRate" style="text-align:right;" placeholder="" value="${bid.bid_interest_rate?string.number}">
		    </div> 
		    <div class="col-sm-1 no_padding_left percentise">
		         &#37;
		    </div>
		    <div class="col-sm-4">
		        <input type="text" class="form-control" id="txtBidAmount5" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="${bid.bid_amount_money?string.number}">
		        <#assign total=total+bid.bid_amount_money>
		    </div>
		    <#assign item=item+1>
		    <#assign fou=1>
		    <#break>
	    </#if>
	</#list>
	<#if fou==0>
		<div class="col-sm-3 no_padding_right">
	    	<input type="text" class="form-control" id="txtInterestRate5" name="txtInterestRate" style="text-align:right;" placeholder="" value="">
	    </div> 
	    <div class="col-sm-1 no_padding_left percentise">
	         &#37;
	    </div>
	    <div class="col-sm-4">
	        <input type="text" class="form-control" id="txtBidAmount5" name="txtBidAmount" onblur="findTotal()" style="text-align:right;" placeholder="" value="">
	    </div>
	</#if>
</div>
<div class="form-group">
    <label for="" class="col-sm-2 col-sm-offset-3 control-label">計</label>
    
    <div class="col-sm-4 col-sm-offset-1">
    	<input type="hidden" id="rowCountOfBidDataForABank" name="rowCountOfBidDataForABank" value="${rowcount}"/>
        <input type="text" class="form-control" id="txtTotalAmount" name="txtTotalAmount" style="text-align:right;" placeholder="" value="${total?string.number}">
    </div>
</div>
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-4">
        <button type="button" class="btn btn-default data_button" id="cmdRegistration"   onclick="BidDataRegister('${rc.contextPath}')" <#if item!=0>disabled</#if>>登録</button>
    </div>
    <div class="col-sm-4">
        <button type="button" class="btn btn-default data_button" onclick="location.href='${rc.contextPath}/'">閉じる</button>
    </div>
</div>
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-4">
        <button type="button" class="btn btn-default" id="cmdDataCorrection" onclick="BidDataRegister('${rc.contextPath}')" <#if item==0>disabled</#if>>データ修正</button>
    </div>
    <div class="col-sm-4">
        <button type="button" class="btn btn-default" id="cmdDelete" onclick="BidDataDeregister('${rc.contextPath}')" <#if item==0>disabled</#if>>削除</button>
    </div>
</div>
~${bankName!''}~${busCategory!''}