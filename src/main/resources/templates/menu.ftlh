<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">МЕНЮ</p>
    <hr/>
    <p class="h5">Классический кофе</p>
    <div class="row row-cols-1 row-cols-md-3 g-4">
    <#list products as product>
        <div class="col">
            <div class="card text-white bg-dark" style="width: 18rem;">
                <img src="https://images.pexels.com/photos/1695052/pexels-photo-1695052.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${product.productName}</h5>
                    <p class="card-text">Описание</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item list-group-item-action list-group-item-dark">280мл. - ${product.averagePrice} р.</li>
                    <li class="list-group-item list-group-item-action list-group-item-dark">350мл. - ${product.middlePrice} р.</li>
                    <li class="list-group-item list-group-item-action list-group-item-dark">500мл. - ${product.bigPrice} р.</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Ссылка карточки</a>
                    <a href="#" class="card-link">Другая ссылка</a>
                </div>
            </div>
        </div>
    </#list>
    </div>
</@c.page>