<#import "parts/common.ftlh" as c>

<@c.page>
    <h1>Список пользователей</h1>
    <br/>
    <table>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Номер</th>
            <th>Роль</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.uname!}</td>
                <td>${user.username!}</td>
                <td><#list user.roles as role>${role}<#sep>. </#list></td>
                <td><a href="/user/${user.id}">⚙️</a></td>
                <td><a href="/user/delete/${user.id}">❌️</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>