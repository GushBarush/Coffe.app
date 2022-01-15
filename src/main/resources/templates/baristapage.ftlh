<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Список пользователей</p>
    <hr/>
    <form method="get" action="/barista">
        <div class="col-sm-5">
            <div class="input-group mb-3">
                <input class="form-control" type="text" name="filter" aria-describedby="button-search" value="${filter!}">
                <button class="btn btn-primary" type="submit" id="button-search">Поиск🔍</button>
            </div>
        </div>
    </form>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Номер</th>
            <th>Кофе</th>
            <th>Подарки</th>
            <th>Добавить</th>
            <th>Списать</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.name}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.coffee}</td>
                <td>${user.happyCoffee}</td>
                <td>
                    <form method="get" action="/barista/add_bonus/${user.id}">
                        <input type="hidden" name="filter" value="${filter!}">
                        <button class="btn btn-success" type="submit">Добавить кофе</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="/barista/del_bonus/${user.id}">
                        <input type="hidden" name="filter" value="${filter!}">
                        <button class="btn btn-success" type="submit">Списать подарок</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>