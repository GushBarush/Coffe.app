<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>RAFT</title>
    </head>
    <body>
        Регистрация
        ${message!}
        <br/>
        <form action="/registration" method="post">
            <div><label> Номер телефона 8xxxxxxxxxx:<input type="tel" pattern="8[0-9]{3}[0-9]{3}[0-9]{2}[0-9]{2}" name="username"/> </label></div>
            <div><label> Email: <input type="email" name="email"/> </label></div>
            <div><label> Имя:<input type="text" name="nameUser"/> </label></div>
            <div><label> Пароль: <input type="password" name="password"/> </label></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div><input type="submit" value="Зарегистрироваться"/></div>
        </form>
    </body>
</html>