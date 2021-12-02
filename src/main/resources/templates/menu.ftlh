<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">МЕНЮ</p>
    <hr/>
    <p class="h5">Классический кофе</p>
    <#list products as product>
        <div class="card" style="width: 18rem;">
            <img src="https://images.pexels.com/photos/1695052/pexels-photo-1695052.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">${product.productName}</h5>
                <p class="card-text">Описание</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">280мл. - ${product.averagePrice} р.</li>
                <li class="list-group-item">350мл. - ${product.middlePrice} р.</li>
                <li class="list-group-item">500мл. - ${product.bigPrice} р.</li>
            </ul>
            <div class="card-body">
                <a href="#" class="card-link">Ссылка карточки</a>
                <a href="#" class="card-link">Другая ссылка</a>
            </div>
        </div>
    </#list>
</@c.page>