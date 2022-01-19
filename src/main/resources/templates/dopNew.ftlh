<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Новый Доп</p>
    <hr/>
    <br/>
    ${messageName!}
    <form action="/admin/product/new_dop" method="post">
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="productName">Название товара</span>
                <input class="form-control" type="text" aria-describedby="productName" name="productName"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="price">Цена</span>
                <input class="form-control" type="number" aria-describedby="price" name="price"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="description">Описание</span>
                <input class="form-control" type="text" aria-describedby="description" name="description"/>
            </div>
        </div>
        <br/>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Добавить</button>
    </form>
</@c.page>