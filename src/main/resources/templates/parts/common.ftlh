<#macro page>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>RAFT</title>
            <link rel="stylesheet" href="/static/style.css">
        </head>
        <body>
            <div xmlns="http://www.w3.org/1999/html">
                <form method="post" action="/logout">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="submit" value="Выход"/>
                </form>
                <br/>
                <span><a href="/main">Профиль </a></span>
                <span><a href="/prise_list">Меню </a></span>
                <span><a href="/barista">БАР </a></span>
                <span><a href="/user">Администрирование </a></span>
                <span><a href="/message">Message</a></span>
                <hr/>
            </div>
            <br/>
            <#nested>
        </body>
    </html>
</#macro>