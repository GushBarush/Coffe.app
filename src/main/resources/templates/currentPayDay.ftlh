<#import "parts/common.ftlh" as c>

<@c.page>
        <p class="h2">Смена</p>
    <br/>
        <p class="h3">Смену открыл: ${PayDayDTO.user.name}</p>
        <p class="h3">Смена открта в: ${PayDayDTO.openTime.toLocalTime().hour}:${PayDayDTO.openTime.toLocalTime().minute}</p>
    <hr/>
    <br/>
        <p class="h2">Касса</p>
    <br/>
        <p class="h3">Всего: ${PayDayDTO.sumAll}</p>
        <p class="h3">Безнал: ${PayDayDTO.sumNotCash}</p>
        <p class="h3">Наличные: ${PayDayDTO.sumCash}</p>
    <hr/>
        <p class="h2">Заказы</p>
    <br/>
        <form action="/order" method="post">
            <input type="hidden" value="${PayDayDTO.id}" name="id">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button class="btn btn-primary" type="submit">Новый заказ</button>
        </form>
    <br/>
    <br/>
    <br/>
    <form action="/payday/end" method="post">
        <input type="hidden" value="${PayDayDTO.id}" name="id">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">закрыть смену</button>
    </form>
</@c.page>