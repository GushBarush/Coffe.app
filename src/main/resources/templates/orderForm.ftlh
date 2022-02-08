<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h2">Новый заказ</p>
    <p class="h3">для: ${order.user.name}</p>
    <br/>
    <hr/>
    <form action="/order/addProduct" method="post">
        <div class="col-md-3 position-relative">
            <label for="validationTooltip04" class="form-label">Кофе</label>
            <select name="productId" class="form-select" id="validationTooltip04" required>
                <option selected disabled value="">Выберете...</option>
                <#list products as productDTO>
                    <option value="${productDTO.id}">${productDTO.productName}</option>
                </#list>
            </select>
            <div class="invalid-tooltip">
                Не выбран!
            </div>
        </div>
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
        <input type="hidden" value="${order.id}" name="orderId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Добавить</button>
    </form>
    <br/>
    <hr/>
    <br/>
    <form action="/order/addProduct" method="post">
        <div class="col-md-3 position-relative">
            <label for="validationTooltip04" class="form-label">Добавки</label>
            <select name="productId" class="form-select" id="validationTooltip04" required>
                <option selected disabled value="">Выберете...</option>
                <#list productsDop as productDTO>
                    <option value="${productDTO.id}">${productDTO.productName}</option>
                </#list>
            </select>
            <div class="invalid-tooltip">
                Не выбран!
            </div>
        </div>
        <input type="hidden" value="SMALL" name="size">
        <input type="hidden" value="${order.id}" name="orderId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Добавить</button>
    </form>
    <br/>
    <hr/>
    <br/>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Название</th>
            <th>${order.sum}</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
            <#list order.productPrice as productPrice>
                <tr>
                    <td>${productPrice.product.productName}</td>
                    <td>${productPrice.price}</td>
                    <td>
                        <form action="/order/clean" method="post">
                            <input type="hidden" value="${productPrice.id}" name="productPriceId">
                            <input type="hidden" value="${order.id}" name="orderId">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button class="btn btn-danger" type="submit">❌</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
    <br/>
    <hr/>
    <br/>
    <form action="/order/save" method="post">
        <#if order.isFree()>
            <input type="hidden" value="true" name="isCash">
        <#else>
            <div class="form-check">
                <input class="form-check-input" value="true" type="radio" name="isCash" id="flexRadio1" checked>
                <label class="form-check-label" for="flexRadio1">
                    Наличные
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" value="false" type="radio" name="isCash" id="flexRadio2" checked>
                <label class="form-check-label" for="flexRadio2">
                    Карта
                </label>
            </div>
        </#if>
        <input type="hidden" value="${order.id}" name="orderId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Оплатить счёт</button>
    </form>
    <hr/>
    <br/>
    <form action="/order/delete" method="post">
        <input type="hidden" value="${order.id}" name="orderId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-danger" type="submit">Удалить</button>
    </form>
</@c.page>
