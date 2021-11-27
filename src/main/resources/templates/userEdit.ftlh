<#import "parts/common.ftlh" as c>

<@c.page>
    <h1>Редактирование пользователей</h1>
    <hr/>
    <form action="/admin" method="post">
        <input type="text" name="nameUser" value="${user.nameUser!}">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@c.page>