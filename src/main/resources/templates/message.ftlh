<#import "parts/common.ftlh" as c>

<@c.page>
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