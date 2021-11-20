<#import "parts/common.ftlh" as c>

<@c.page>
    <h1>Профиль</h1>
    <br/>
    <hr/>
    Номер: ${user.username}
    <br/>
    Почта: ${user.email!}
    <br/>
    Имя: ${user.Uname!}
    <br/>
    <hr/>
    Кофе: ${user.coffee!}
    <br/>
    Подарочных Кофе: ${user.happyCoffee!}
</@c.page>