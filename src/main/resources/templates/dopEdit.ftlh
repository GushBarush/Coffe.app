<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Редактирование товара</p>
    <hr/>
    <br/>
    <form action="/admin/product/edit_dop" method="post" enctype="multipart/form-data">
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="productName">Название товара</span>
                <input class="form-control" type="text" value="${productDTO.productName!}" aria-describedby="productName" name="productName" required/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-text" id="price">Цена</span>
                <input class="form-control" type="number" value="${productPriceDTO.price!}" aria-describedby="price" name="price" required/>
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
        <div class="form-file">
            <input type="file" name="file" class="form-file-input" id="customFileDisabled" disabled>
            <label class="form-file-label" for="customFileDisabled">
                <span class="form-file-text">Выбирете фото...</span>
                <span class="form-file-button">Выбрать</span>
            </label>
        </div>
        <br/>
        <input type="hidden" value="${productPriceDTO.id}" name="productPriceId">
        <input type="hidden" value="${productDTO.id}" name="productId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Сохранить</button>
    </form>
</@c.page>