<script type="text/javascript" src="${rc.contextPath}/js/variousparameters.js"></script>

<div class="body col-md-8 col-md-offset-2 menu2_3">
    <div class="col-md-2">
        <p class="white_border">社長名</p>
    </div>
    <div class="col-md-9">
    	<input type="hidden" id="department_code" name="" value="${base.department_code!''}">
        <textarea class="form-control" rows="1" id="chairman_name" name="president_name">${base.chairman_name!''}</textarea>
    </div>
    <div class="col-md-2">
        <p class="white_border">社長名２</p>
    </div>
    <div class="col-md-9">
        <textarea class="form-control" rows="1" id="chairman_name_2">${base.chairman_name_2!''}</textarea>
    </div>
    <div class="col-md-2">
        <p class="white_border">大蔵大臣</p>
    </div>
    <div class="col-md-9">
        <textarea class="form-control" rows="1" id="minister_of_finance">${base.minister_of_finance!''}</textarea>
    </div>
    <div class="col-md-2">
        <p class="white_border">部署名</p>
    </div>
    <div class="col-md-9">
        <textarea class="form-control" rows="1" id="division_name">${base.division_name!''}</textarea>
    </div>
    <div class="col-md-2">
        <p class="white_border">仮落札書文言</p>
    </div>
    <div class="col-md-9">
        <textarea class="form-control" rows="2" id="provisional_successful_bid_document_wording">${base.provisional_successful_bid_document_wording!''}</textarea>
    </div>
    <div class="col-md-9 col-md-offset-2">
        <textarea class="form-control" rows="1" id="provisional_successful_bid_document_wording_2">${base.provisional_successful_bid_document_wording_2!''}</textarea>
    </div>
    <div class="col-md-2">
        <p class="white_border">落札書文言</p>
    </div>
    <div class="col-md-9">
        <textarea class="form-control" rows="1" id="successful_bid_document_wording">${base.successful_bid_document_wording!''}</textarea>
    </div>
    <div class="col-md-9 col-md-offset-2">
        <textarea class="form-control" rows="1" id="successful_bid_document_wording_2">${base.successful_bid_document_wording_2!''}</textarea>
    </div>
    <div class="col-md-2">
        <p class="white_border">借入申込書文言</p>
    </div>
    <div class="col-md-9">
        <textarea class="form-control" rows="2" id="credit_application_wording">${base.credit_application_wording!''}</textarea>
    </div>
    <div class="col-md-2">
        <p class="white_border">住所</p>
    </div>
    <div class="col-md-9">
        <textarea class="form-control" rows="1" id="street_address" name="street_address">${base.street_address!''}</textarea>
    </div>
    
    <div class="col-md-3 col-md-offset-3">
        <button class="btn btn-default" type="button" onclick="updateVariousParameters('${rc.contextPath}','${base.department_code}')">更新</button>
    </div>
    
    <div class="col-md-3">
        <button class="btn btn-default" type="submit" onclick="location.href='${rc.contextPath}/mastermaintenance'">閉じる</button>
    </div>
    
</div>