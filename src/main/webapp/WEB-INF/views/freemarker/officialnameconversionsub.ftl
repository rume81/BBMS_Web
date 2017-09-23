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