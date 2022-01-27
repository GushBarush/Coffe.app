<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h2">Смена Закрыта</p>
    <br/>
    <form action="/payday/new" method="post">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">открыть смену</button>
    </form>
</@c.page>