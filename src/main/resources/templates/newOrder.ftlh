<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h2">Новый заказ</p>

    <form method="get" action="/order">
        <div class="col-sm-5">
            <div class="input-group mb-3">
                <input type="hidden" value="false" name="isFree">
                <input class="form-control" type="text" name="filter" aria-describedby="button-search" value="${filter!}">
                <input type="hidden" value="${PayDayId}" name="id">
                <button class="btn btn-primary" type="submit" id="button-search">Поиск🔍</button>
            </div>
        </div>
    </form>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Номер</th>
            <th>Тек/Бесп</th>
            <th>Выбрать</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.phoneNumber!}</td>
                <td>${user.name!}</td>
                <td>${user.coffee!}/${user.happyCoffee!}</td>
                <td>
                    <form method="post" action="/order">
                        <input type="hidden" value="false" name="isFree">
                        <input type="hidden" value="${PayDayId}" name="payDayId">
                        <input type="hidden" value="${user.id}" name="userId">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <button class="btn btn-success" type="submit">✔</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>