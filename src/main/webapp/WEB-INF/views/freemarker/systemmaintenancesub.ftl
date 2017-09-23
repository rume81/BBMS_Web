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