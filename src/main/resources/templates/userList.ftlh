<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Список пользователей</p>
    <hr/>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Номер</th>
            <th>Роль</th>
            <th>Настройки</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.nameUser!}</td>
                <td>${user.username!}</td>
                <td><#list user.roles as role>${role}<#sep>. </#list></td>
                <td>
                    <form method="get" action="/admin/${user.id}">
                        <button type="submit" class="btn btn-info">⚙</button>
                    </form>
                <td>
                    <form method="get" action="/admin/delete/${user.id}">
                        <button type="submit" class="btn btn-danger">❌</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>