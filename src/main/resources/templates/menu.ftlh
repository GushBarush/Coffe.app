<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">МЕНЮ</p>
    <hr/>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Название</th>
            <th>ЦенаМАЛ.</th>
            <th>ЦенаСР.</th>
            <th>ЦенаБол.</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.productName}</td>
                <td>${product.averagePrice}</td>
                <td>${product.middlePrice}</td>
                <td>${product.bigPrice}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>