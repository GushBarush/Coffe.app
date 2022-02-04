<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

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
        <p class="h3">Расходы: ${PayDayDTO.sumExpense}</p>
        <p class="h3">Безнал: ${PayDayDTO.sumNotCash}</p>
        <p class="h3">Наличные: ${PayDayDTO.sumCash}</p>
    <br/>
        <p class="h3">Бесплатные: ${PayDayDTO.sumFree}</p>
    <hr/>
        <p class="h2">Заказы</p>
    <br/>
        <form action="/order" method="get">
            <input type="hidden" value="false" name="isFree">
            <input type="hidden" value="${PayDayDTO.id}" name="id">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button class="btn btn-primary" type="submit">Новый заказ</button>
        </form>
    <br/>
        <form action="/order" method="get">
            <input type="hidden" value="true" name="isFree">
            <input type="hidden" value="${PayDayDTO.id}" name="id">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button class="btn btn-primary" type="submit">Новый Бесплатный</button>
        </form>
    <br/>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Сумма</th>
            <th>Статус</th>
            <th>Информация</th>
            <th>Наличные или карта</th>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr>
                <td>${order.sum}</td>
                <td>
                    <#if order.isActive()>
                        НЕ оплачен
                    <#else>
                        оплачен
                    </#if>
                </td>
                <td>
                    <#if isAdmin>
                            <form method="get" action="/payday/order/info">
                                <input type="hidden" value="${order.id}" name="orderId">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                                <button class="btn btn-success" type="submit">!</button>
                            </form>
                        <#else>
                            <#if order.isActive()>
                                <form method="get" action="/payday/order/info">
                                    <input type="hidden" value="${order.id}" name="orderId">
                                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                                    <button class="btn btn-success" type="submit">!</button>
                                </form>
                            <#else>
                                оплачен!
                            </#if>
                    </#if>
                </td>
                <td>
                    <#if order.isFree()>
                        Бесплатный
                    <#else >
                        <#if order.isCash()>
                            Наличные
                        <#else>
                            Карта
                        </#if>
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <br/>
    <p class="h2">Расходы</p>
    <br/>
        <form action="/order/expense" method="post">
            <input type="hidden" value="${PayDayDTO.id}" name="id">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button class="btn btn-primary" type="submit">Добавить рассход</button>
        </form>
    <br/>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Описание</th>
            <th>Сумма</th>
            <th>Удолыт</th>
        </tr>
        </thead>
        <tbody>
        <#list expenses as expense>
            <tr>
                <td>${expense.commit}</td>
                <td>${expense.sum}</td>
                <td>
                    <form action="/order/expense/del" method="post">
                        <input type="hidden" value="${expense.id}" name="expenseId">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <button class="btn btn-primary" type="submit">❌</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <br/>
    <hr/>
    <br/>
    <form action="/payday/end" method="post">
        <input type="hidden" value="${PayDayDTO.id}" name="id">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">закрыть смену</button>
    </form>
</@c.page>