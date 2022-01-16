<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Список продуктов</p>
    <hr/>

    <form method="get" action="/admin/product/new">
        <input type="hidden" name="dop" value="false">
        <button class="btn btn-success" type="submit">Новый продукт</button>
    </form>
    <hr/>
    <form method="get" action="/admin/product/new">
        <input type="hidden" name="dop" value="true">
        <button class="btn btn-success" type="submit">Новый Доп</button>
    </form>

    <hr/>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Название</th>
            <th>Информация</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.productName!}</td>
                <td>
                    <form method="get" action="/admin/product/info/${product.id}">
                        <button class="btn btn-success" type="submit">показать</button>
                    </form>
                </td>
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

