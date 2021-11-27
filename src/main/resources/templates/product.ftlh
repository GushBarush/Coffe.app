<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Список продуктов</p>
    <hr/>
    <form method="get" action="/admin/product/new">
        <button class="btn btn-success" type="submit">Новый продукт</button>
    </form>
    <hr/>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Название</th>
            <th>ЦенаМАЛ.</th>
            <th>ЦенаСР.</th>
            <th>ЦенаБол.</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.productName}</td>
                <td>${product.averagePrice}</td>
                <td>${product.middlePrice}</td>
                <td>${product.bigPrice}</td>
                <td>
                    <form method="get" action="/admin/product/edit/${product.id}">
                        <button class="btn btn-success" type="submit">Изменить</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="/admin/product/delete/${product.id}">
                        <button class="btn btn-success" type="submit">удалить</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>