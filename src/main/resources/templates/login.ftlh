<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>RAFT</title>
    </head>
    <body>
        Авторизация
        <br/>
        <form action="/login" method="post">
            <div><label> Телефон в формате 8xxxxxxxxxx:<input type="tel" pattern="8[0-9]{3}[0-9]{3}[0-9]{2}[0-9]{2}" name="username"/> </label></div>
            <div><label> Пароль: <input type="password" name="password"/> </label></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div><input type="submit" value="Войти"/></div>
        </form>
        <br/>
        <a href="/registration">Регистрация</a>
    </body>
</html>