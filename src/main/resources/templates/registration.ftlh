<#import "parts/common.ftlh" as c>

<@c.page>
    Регистрация
    ${message!}
    <br/>
    <form action="/registration" method="post">
        <div><label> Имя : <input type="text" name="username"/> </label></div>
        <div><label> Email: <input type="email" name="email"/> </label></div>
        <div><label> Номер телефона +7:<input type="number" name="numberPhone"/> </label></div>
        <div><label> Пароль: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Зарегистрироваться"/></div>
    </form>
</@c.page>