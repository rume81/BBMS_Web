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
            <td><input type="checkbox" disabled id="delf" value="" name="chk" <#if fi.delF==-1>checked="checked"</#if>></td>
            <td class="no_margin_bottom">
            	<#if fi.delF!=1>
                <div class="btn-group">
                    <button type="button" title="" class="btn btn-primary" data-toggle="modal" data-target="#f${fi.bank_code}"><i class="fa fa-pencil-square-o"></i></button>
                </div>
                </#if>
            </td>
        </tr>
    </#list>
    </tbody>
</table>