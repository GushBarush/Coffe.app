<#import "parts/common.ftlh" as c>

<@c.page>
    <p class="h3">Новый расход</p>
    <hr/>
    <form action="/order/expense/new" method="post">
        <div class="col-sm-5">
            <div class="input-group">
                ${messageCommit!}
                <span class="input-group-text" id="commit">Пару слов о рассходе</span>
                <input class="form-control" type="text" aria-describedby="commit" name="commit"/>
            </div>
        </div>
        <br/>
        <div class="col-sm-5">
            <div class="input-group">
                ${messageSum!}
                <span class="input-group-text" id="sum">Сумма расхода</span>
                <input class="form-control" type="number" aria-describedby="sum" name="sum"/>
            </div>
        </div>
        <br/>
        <input type="hidden" value="${payDayId}" name="payDayId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Добавить</button>
    </form>
</@c.page>