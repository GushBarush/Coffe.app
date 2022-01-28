<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h2">Смена</p>
    <br/>
    <p class="h2">Смену открыл: ${PayDayDTO.user.name}</p>
    <p class="h2">Смена открта в: ${PayDayDTO.openTime.toLocalTime().hour}:${PayDayDTO.openTime.toLocalTime().minute}</p>
    <hr/>
    <form action="/payday/end" method="post">
        <input type="hidden" value="${PayDayDTO.id}" name="id">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">закрыть смену</button>
    </form>
</@c.page>