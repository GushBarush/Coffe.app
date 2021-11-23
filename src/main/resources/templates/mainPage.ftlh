<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <h3>Профиль</h3>
        <br/>
        <hr/>
    </div>
    <div>
        Номер: ${user.username}
        <br/>
        Почта: ${user.email!}
        <br/>
        Имя: ${user.nameUser!}
        <hr/>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>Кофе</th>
                <th>Подарки</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${user.coffee!}</td>
                    <td>${user.happyCoffee!}</td>
                </tr>
            </tbody>
        </table>
    </div>
</@c.page>