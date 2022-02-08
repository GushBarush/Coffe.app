<#import "parts/common.ftlh" as c>

<@c.page>
        <h3>Авторизация</h3>
        <br/>
        <form action="/login" method="post">
            <div class="row mb-2">
                <label class="col-sm-3 col-form-label">Номер телефона:</label>
                <div class="col-sm-5">
                    <div class="input-group">
                        <span class="input-group-text" id="addon-phone">+7</span>
                        <input class="form-control" type="tel" aria-describedby="addon-phone" maxlength="10" pattern="[0-9]{3}[0-9]{3}[0-9]{2}[0-9]{2}" name="username" placeholder="номер"/>
                    </div>
                </div>
            </div>
            <div class="row mb-2">
                <label class="col-sm-3 col-form-label">Пароль:</label>
                <div class="col-sm-5">
                    <input class="form-control" type="password" name="password"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Войти</button>
        </form>
        <br/>
        <a href="/registration">Регистрация</a>
</@c.page>