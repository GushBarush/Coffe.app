<#import "parts/common.ftlh" as c>

<@c.page>
    <h3>Список пользователей</h3>
    <form method="get" action="/barista">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Поиск</button>
    </form>
    <table>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Номер</th>
            <th>Кофе</th>
            <th>Подарки</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.nameUser!}</td>
                <td>${user.username!}</td>
                <td>${user.coffee}</td>
                <td>${user.happyCoffee}</td>
                <td>
                    <form method="get" action="/barista/add_bonus/${user.id}">
                        <input type="hidden" name="filter" value="${filter!}">
                        <button type="submit">Добавить кофе</button>
                    </form>
                    <form method="get" action="/barista/del_bonus/${user.id}">
                        <input type="hidden" name="filter" value="${filter!}">
                        <button type="submit">Списать подарок</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>