<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Редактирование товара</p>
    <hr/>
    <br/>
    ${messageName!}
    <form action="/admin/product/edit" method="post">
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="productName">Название товара</span>
                <input class="form-control" type="text" value="${product.productName!}" aria-describedby="productName" name="productName"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="averagePrice">Цена Мал.</span>
                <input class="form-control" type="number" value="${product.averagePrice!}" aria-describedby="averagePrice" name="averagePrice"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="middlePrice">Цена Ср.</span>
                <input class="form-control" type="number" value="${product.middlePrice!}" aria-describedby="middlePrice" name="middlePrice"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="bigPrice">Цена Бол.</span>
                <input class="form-control" type="number" value="${product.bigPrice!}" aria-describedby="bigPrice" name="bigPrice"/>
            </div>
        </div>
        <br/>
        <div class="from-file">
            <input type="file" class="from-file-input" name="file" id="customFile">
            <label class="form-file-label" for="customFile">
                <span class="from-file-text">Выбирете фото...</span>
            </label>
        </div>
        <br/>
        <input type="hidden" value="${product.id}" name="productId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Сохранить</button>
    </form>
</@c.page>