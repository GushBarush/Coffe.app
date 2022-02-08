<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Новый товар</p>
    <hr/>
    <br/>
    <form class="row g-3 needs-validation" action="/admin/product/new" method="post" enctype="multipart/form-data" novalidate>
        <div class="col-md-4 position-relative">
            <label for="validationTooltip01" class="form-label">Название товара</label>
            <input type="text" name="productName" class="form-control" id="validationTooltip01" required>
            <div class="valid-tooltip">
                Выглядит неплохо!
            </div>
        </div>
        <br/>
        <div class="col-md-4 position-relative">
            <label for="validationTooltip01" class="form-label">Название товара</label>
            <input type="text" name="priceSmall" class="form-control" id="validationTooltip01" required>
            <div class="valid-tooltip">
                Выглядит неплохо!
            </div>
        </div>
        <br/>
        <div class="col-md-4 position-relative">
            <label for="validationTooltip01" class="form-label">Название товара</label>
            <input type="text" name="priceMiddle" class="form-control" id="validationTooltip01" required>
            <div class="valid-tooltip">
                Выглядит неплохо!
            </div>
        </div>
        <br/>
        <div class="col-md-4 position-relative">
            <label for="validationTooltip01" class="form-label">Название товара</label>
            <input type="text" name="priceBig" class="form-control" id="validationTooltip01" required>
            <div class="valid-tooltip">
                Выглядит неплохо!
            </div>
        </div>
        <br/>
        <div class="col-md-4 position-relative">
            <label for="validationTooltip01" class="form-label">Название товара</label>
            <input type="text" name="description" class="form-control" id="validationTooltip01" required>
            <div class="valid-tooltip">
                Выглядит неплохо!
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