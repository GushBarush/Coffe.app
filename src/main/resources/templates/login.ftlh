<#import "parts/common.ftlh" as c>

<@c.page>
    Авторизация
    <br/>
    <form action="/login" method="post">
        <div><label> Имя : <input type="text" name="username"/> </label></div>
        <div><label> Пароль: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Войти"/></div>
    </form>
    <br/>
    <a href="/registration">Регистрация</a>
</@c.page>