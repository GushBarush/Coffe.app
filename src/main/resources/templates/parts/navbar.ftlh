<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"><p style="color:RGB(187,30,16); font-weight:bolder; font-size:xx-large ;">RAFT</p></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/main">Профиль</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/menu">Меню</a>
                </li>
                <#if isBarista>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="/barista" id="navbarDropdownMenuLinkBarista" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Бариста
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLinkBarista">
                            <li><a class="dropdown-item" href="/barista">Гости</a></li>
                            <li><a class="dropdown-item" href="/payday">Смена</a></li>
                        </ul>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="/admin" id="navbarDropdownMenuLinkAdmin" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            АдминПанель
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLinkAdmin">
                            <li><a class="dropdown-item" href="/admin">Админ Меню</a></li>
                            <li><a class="dropdown-item" href="/admin/product">Продукты</a></li>
                        </ul>
                    </li>
                </#if>
            </ul>
            <#if name != "unknown">
                <form method="post" action="/logout">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-danger" type="submit">Выход</button>
                </form>
            </#if>
        </div>
    </div>
</nav>