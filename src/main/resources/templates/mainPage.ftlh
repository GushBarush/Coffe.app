<#import "parts/common.ftlh" as c>

<@c.page>
    <h1>Профиль</h1>
    <br/>
    <hr/>
    Имя: ${user.username}
    <br/>
    Почта: ${user.email!}
    <br/>
    Номер: ${user.numberPhone!}
    <br/>
    <hr/>
    Кофе: ${user.coffee!}
    <br/>
    Подарочных Кофе: ${user.happyCoffee!}
</@c.page>