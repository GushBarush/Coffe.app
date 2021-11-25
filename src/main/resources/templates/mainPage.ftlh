<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Профиль</p>
    <hr/>
    <dl class="row">
        <dt class="col-sm-2">Номер:</dt>
        <dd class="col-sm-5">${user.username}</dd>
    </dl>
    <dl class="row">
        <dt class="col-sm-2">Почта:</dt>
        <dd class="col-sm-5">${user.email!}</dd>
    </dl>
    <dl class="row">
        <dt class="col-sm-2">Имя:</dt>
        <dd class="col-sm-5">${user.nameUser!}</dd>
    </dl>
    <hr/>
    <dl class="row">
        <dt class="col-sm-3"><p class="h4">Кофе</p></dt>
        <dd class="col-sm-3"><p class="h4">${user.coffee!}</p></dd>
    </dl>
    <dl class="row">
        <dt class="col-sm-3"><p class="h4">Подарки</p></dt>
        <dd class="col-sm-3"><p class="h4">${user.happyCoffee!}</p></dd>
    </dl>
</@c.page>