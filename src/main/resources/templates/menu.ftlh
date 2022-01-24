<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h2">МЕНЮ</p>
    <hr/>
    <p class="h3">Классический кофе</p>
    <div class="row row-cols-1 row-cols-md-3 g-4">
    <#list productClassic as product>
        <div class="col">
            <div class="card text-white bg-dark" style="width: 18rem;">
                <img src="/images/${product.imageId!}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${product.productName}</h5>
                    <p class="card-text">${product.description!}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item list-group-item-action list-group-item-dark">280мл. - ${product.priceSmall} р.</li>
                    <li class="list-group-item list-group-item-action list-group-item-dark">350мл. - ${product.priceMedium} р.</li>
                    <li class="list-group-item list-group-item-action list-group-item-dark">500мл. - ${product.priceBig} р.</li>
                </ul>
            </div>
        </div>
    </#list>
    </div>
    <br/>
    <p class="h3">Raf кофе</p>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list productRaf as product>
            <div class="col">
                <div class="card text-white bg-dark" style="width: 18rem;">
                    <img src="/images/${product.imageId!}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${product.productName}</h5>
                        <p class="card-text">${product.description!}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item list-group-item-action list-group-item-dark">280мл. - ${product.priceSmall} р.</li>
                        <li class="list-group-item list-group-item-action list-group-item-dark">350мл. - ${product.priceMedium} р.</li>
                        <li class="list-group-item list-group-item-action list-group-item-dark">500мл. - ${product.priceBig} р.</li>
                    </ul>
                </div>
            </div>
        </#list>
    </div>
    <br/>
    <p class="h3">Согревающие напитки</p>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list productHot as product>
            <div class="col">
                <div class="card text-white bg-dark" style="width: 18rem;">
                    <img src="/images/${product.imageId!}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${product.productName}</h5>
                        <p class="card-text">${product.description!}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item list-group-item-action list-group-item-dark">280мл. - ${product.priceSmall} р.</li>
                        <li class="list-group-item list-group-item-action list-group-item-dark">350мл. - ${product.priceMedium} р.</li>
                        <li class="list-group-item list-group-item-action list-group-item-dark">500мл. - ${product.priceBig} р.</li>
                    </ul>
                </div>
            </div>
        </#list>
    </div>
    <br/>
    <p class="h3">Сезонные напитки</p>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list productSeason as product>
            <div class="col">
                <div class="card text-white bg-dark" style="width: 18rem;">
                    <img src="/images/${product.imageId!}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${product.productName}</h5>
                        <p class="card-text">${product.description!}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item list-group-item-action list-group-item-dark">280мл. - ${product.priceSmall} р.</li>
                        <li class="list-group-item list-group-item-action list-group-item-dark">350мл. - ${product.priceMedium} р.</li>
                        <li class="list-group-item list-group-item-action list-group-item-dark">500мл. - ${product.priceBig} р.</li>
                    </ul>
                </div>
            </div>
        </#list>
    </div>
    <br/>
    <p class="h3">Добавки</p>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list productDop as product>
            <div class="col">
                <div class="card text-white bg-dark" style="width: 18rem;">
                    <img src="/images/${product.imageId!}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${product.productName}</h5>
                        <p class="card-text">${product.description!}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item list-group-item-action list-group-item-dark">280мл. - ${product.priceSmall} р.</li>
                    </ul>
                </div>
            </div>
        </#list>
    </div>
</@c.page>