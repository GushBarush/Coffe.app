<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Новый товар</p>
    <hr/>
    <br/>
    <form action="/admin/product/new" method="post" enctype="multipart/form-data">
        <div class="col-sm-5">
            <div class="input-group">
                ${messageName!}
                <span class="input-group-text" id="productName">Название товара</span>
                <input class="form-control" type="text" aria-describedby="productName" name="productName"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                ${messageSmall!}
                <span class="input-group-text" id="priceSmall">Цена Мал.</span>
                <input class="form-control" type="number" aria-describedby="priceSmall" name="priceSmall"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                ${messageMiddle!}
                <span class="input-group-text" id="priceMiddle">Цена Ср.</span>
                <input class="form-control" type="number" aria-describedby="priceMiddle" name="priceMiddle"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                ${messageBig!}
                <span class="input-group-text" id="priceBig">Цена Бол.</span>
                <input class="form-control" type="number" aria-describedby="priceBig" name="priceBig"/>
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
        <div class="form-check">
            <input class="form-check-input" value="classic" type="radio" name="category" id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
                Класический кофе
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" value="raf" type="radio" name="category" id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
                Raf Кофе
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" value="hot" type="radio" name="category" id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
                Согревающие напитки
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" value="season" type="radio" name="category" id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
                Сезонные Напитки
            </label>
        </div>
        <br/>
        <div class="from-file">
            <input type="file" class="from-file-input" name="file" id="customFile">
            <label class="form-file-label" for="customFile">
                <span class="from-file-text">Выбирете фото...</span>
            </label>
        </div>
        <br/>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Добавить</button>
    </form>
</@c.page>