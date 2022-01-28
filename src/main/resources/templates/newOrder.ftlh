<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h2">Новый заказ</p>

    <form method="get" action="/order">
        <div class="col-sm-5">
            <div class="input-group mb-3">
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
            <th>Выбрать</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.phoneNumber!}</td>
                <td>${user.name!}</td>
                <td>
                    <form method="get" action="/order/${user.id}">
                        <input type="hidden" value="${PayDayId}" name="id">
                        <button type="submit" class="btn btn-danger">выбрать</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>