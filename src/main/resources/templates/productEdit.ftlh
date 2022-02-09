<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Редактирование товара</p>
    <hr/>
    <br/>
    <form action="/admin/product/edit" method="post" enctype="multipart/form-data">
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="productName">Название товара</span>
                <input class="form-control" type="text" value="${productDTO.productName!}" aria-describedby="productName" name="productName" required/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="description">Описание</span>
                <input class="form-control" type="text" value="${productDTO.description!}" aria-describedby="description" name="description" required/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="averagePrice">Цена Мал.</span>
                <input class="form-control" type="number" value="${productDTO.priceSmall!}" aria-describedby="averagePrice" name="priceSmall" required/>
            </div>
        </div>
        <br/>
        <#if productDTO.dop>
            <input type="hidden" value="true" name="dop">
            <input type="hidden" value="dop" name="category">
            <#else>
            <div class="col-sm-5">
                <div class="input-group">
                    <span class="input-group-text" id="middlePrice">Цена Ср.</span>
                    <input class="form-control" type="number" value="${productDTO.priceMedium!}" aria-describedby="middlePrice" name="priceMedium" required/>
                </div>
            </div>
            <br/>
            <div class="col-sm-5">
                <div class="input-group">
                    <span class="input-group-text" id="bigPrice">Цена Бол.</span>
                    <input class="form-control" type="number" value="${productDTO.priceBig!}" aria-describedby="bigPrice" name="priceBig" required/>
                </div>
            </div>
            <br/>
            <div class="form-check">
                <input class="form-check-input" value="classic" type="radio" name="category" id="flexRadioDefault1" checked>
                <label class="form-check-label" for="flexRadioDefault1">
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
                <input class="form-check-input" value="hot" type="radio" name="category" id="flexRadioDefault3" checked>
                <label class="form-check-label" for="flexRadioDefault3">
                    Согревающие напитки
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" value="season" type="radio" name="category" id="flexRadioDefault4" checked>
                <label class="form-check-label" for="flexRadioDefault4">
                    Сезонные Напитки
                </label>
            </div>
            <br/>
            <input type="hidden" value="false" name="dop">
        </#if>
        <div class="form-file">
            <input type="file" class="from-file-input" name="file" id="customFile">
        </div>
        <br/>
        <input type="hidden" value="${productDTO.id}" name="productId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Сохранить</button>
    </form>
</@c.page>