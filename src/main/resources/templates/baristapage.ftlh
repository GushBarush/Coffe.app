<#import "parts/common.ftlh" as c>

<@c.page>
    <h3>Список пользователей</h3>
    <br/>
    <table>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Номер</th>
            <th>Кофе</th>
            <th>Подарки</th>
            <th>Добавить</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.nameUser!}</td>
                <td>${user.username!}</td>
                <td>${user.coffee!}</td>
                <td>${user.happyCoffee!}</td>
                <td><a href="/barista/add_bonus/${user.id}">+ / </a><a href="/barista/del_bonus/${user.id}">-</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>