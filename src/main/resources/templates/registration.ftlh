<#import "parts/common.ftlh" as c>

<@c.page>
        <h3>Регистрация</h3>
        ${message!}
    <br xmlns="http://www.w3.org/1999/html"/>
        <form action="/registration" method="post">
            <div class="row mb-3">
                <label class="col-sm-3 col-form-label">Номер телефона:</label>
                    <div class="col-sm-5">
                        <input class="form-control" type="tel" pattern="8[0-9]{3}[0-9]{3}[0-9]{2}[0-9]{2}" name="username" placeholder="8"/>
                    </div>
            </div>
            <div class="row mb-3">
                <label class="col-sm-3 col-form-label">Email:</label>
                    <div class="col-sm-5">
                        <input class="form-control" type="email" name="email"/>
                    </div>
            </div>
            <div class="row mb-3">
                <label class="col-sm-3 col-form-label" >Имя:</label>
                    <div class="col-sm-5">
                        <input class="form-control" type="text" name="nameUser"/>
                    </div>
            </div>
            <div class="row mb-3">
                <label class="col-sm-3 col-form-label" >Пароль:</label>
                    <div class="col-sm-5">
                        <input class="form-control" type="password" name="password"/>
                    </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Зарегистрироваться</button>
        </form>
</@c.page>