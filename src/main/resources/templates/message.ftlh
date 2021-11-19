<#import "parts/common.ftlh" as c>

<@c.page>
    <div xmlns="http://www.w3.org/1999/html">
        <form method="post" action="/logout">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Sign Out"/>
        </form>
        <br/>
        <span><a href="/user">Администрирование</a></span>
        <hr/>
    </div>
    <div>
        <form method="post" action="/message">
            <input type="text" name="text" placeholder="Введите вообщение"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="text" name="tag" placeholder="Тэг">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form method="get" action="/message">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No message
    </#list>
</@c.page>