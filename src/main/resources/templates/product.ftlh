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
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <#list products as productDTO>
            <tr>
                <td>${productDTO.productName!}</td>
                <td>
                    <form method="get" action="/admin/product/edit/${productDTO.id}">
                        <button class="btn btn-success" type="submit">⚙</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/admin/product/delete">
                        <input type="hidden" value="${productDTO.id}" name="productId">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <button class="btn btn-success" type="submit">❌</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>

