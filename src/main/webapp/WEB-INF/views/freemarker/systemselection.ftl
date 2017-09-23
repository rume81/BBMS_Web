<script type="text/javascript" src="${rc.contextPath}/js/systemselection.js"></script>
<script type="text/javascript">
	
</script>
<div class="body col-md-6 col-md-offset-3">
    <div class="col-sm-12 stop_rate">
        <div class="col-sm-3">
            <p class="white_border padding_top">システム名</p>
        </div>
        <div class="col-sm-9">
        	<input type="hidden" name="field_name" value="" id="field_name" />
            <select class="form-control" id="system_name" onChange="systemSelection('${rc.contextPath}')">
            <option value="" id=""></option>
            <#list systemSelection as ss>
            	<option value="${ss.system_name!''}">${ss.system_name!''}</option>
            	
            </#list>
        </select>
        </div>
        <label for="" class="col-sm-3"></label>
        
    </div>
    
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="getSystemMaintenance('${rc.contextPath}');" />実　　行</button>
    </div>
    
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/home'">閉じる</button>
    </div>
   
</div>