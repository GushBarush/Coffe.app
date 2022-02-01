<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h2">Новый заказ</p>

    <p class="h3">для: ${order.user.name}</p>

    <form action="/order/addProduct" method="post">
        <select class="form-select" aria-label="Default select example" name="productId">
            <option selected>выберете</option>
            <#list products as productDTO>
                <option value="${productDTO.id}">${productDTO.productName}</option>
            </#list>
        </select>
        <div class="form-check">
            <input class="form-check-input" value="SMALL" type="radio" name="size" id="flexRadioDefault1" checked>
            <label class="form-check-label" for="flexRadioDefault1">
                280мл.
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" value="MEDIUM" type="radio" name="size" id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
                350мл.
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" value="BIG" type="radio" name="size" id="flexRadioDefault3" checked>
            <label class="form-check-label" for="flexRadioDefault1">
                500мл.
            </label>
        </div>
        <input type="hidden" value="${order.id}" name=orderId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Добавить</button>
    </form>
    <br/>
    <hr/>
    <br/>
<form action="/order/addProduct" method="post">
    <select class="form-select" aria-label="Default select example" name="productId">
        <option selected>выберете</option>
        <#list productsDop as productDTO>
            <option value="${productDTO.id}">${productDTO.productName}</option>
        </#list>
    </select>
    <input type="hidden" value="SMALL" name="size">
    <input type="hidden" value="${order.id}" name="productId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button class="btn btn-primary" type="submit">Добавить</button>
    <br/>
    <hr/>
    <br/>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Название</th>
            <th>Обьём</th>
            <th>${order.sum}</th>
        </tr>
        </thead>
        <tbody>
            <#list order.productPrice as productPrice>
                <tr>
                    <td>${productPrice.product.productName}</td>
                    <td>${productPrice.productSize.sizeName}</td>
                    <td>${productPrice.price}</td>
                </tr>
            </#list>
        </tbody>
    </table>
    <br/>
    <hr/>
    <br/>
    <form action="/order/save" method="post">
        <div class="form-check">
            <input class="form-check-input" value="true" type="radio" name="isCash" id="flexRadioDefault1" checked>
            <label class="form-check-label" for="flexRadioDefault1">
                Наличные
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" value="false" type="radio" name="isCash" id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
                Карта
            </label>
        </div>
        <input type="hidden" value="${order.id}" name="productId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Сохранить</button>
    </form>
</@c.page>
