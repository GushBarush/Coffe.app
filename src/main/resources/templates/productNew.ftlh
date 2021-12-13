<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Новый товар</p>
    <hr/>
    <br/>
    ${messageName!}
    <form action="/admin/product/new" method="post">
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="productName">Название товара</span>
                <input class="form-control" type="text" aria-describedby="productName" name="productName"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="averagePrice">Цена Мал.</span>
                <input class="form-control" type="number" aria-describedby="averagePrice" name="averagePrice"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="middlePrice">Цена Ср.</span>
                <input class="form-control" type="number" aria-describedby="middlePrice" name="middlePrice"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="bigPrice">Цена Бол.</span>
                <input class="form-control" type="number" aria-describedby="bigPrice" name="bigPrice"/>
            </div>
        </div>
        <br/>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Добавить</button>
    </form>
</@c.page>