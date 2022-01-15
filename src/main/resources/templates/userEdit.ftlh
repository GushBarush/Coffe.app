<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Редактирование</p>
    <hr/>
    <form action="/admin" method="post">
        <div class="col-sm-5">
            <input class="form-control" type="text" name="name" value="${user.name!}"/>
        </div>
        <#list roles as role>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    ${role}
                </label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" name="filter" value="${user.userNumber!}">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Сохранить</button>
    </form>
</@c.page>