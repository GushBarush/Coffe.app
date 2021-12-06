<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"><p style="font-family: 'Rubik', sans-serif;">RAFT</p></a>
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
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/barista">Бариста</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="/admin" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            АдминПанель
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="/admin">АдминПанель</a></li>
                            <li><a class="dropdown-item" href="/admin/product">Продукты</a></li>
                        </ul>
                    </li>
                </#if>
            </ul>
            <#if name != "unknown">
                <div class="navbar-text mr-3">${name}</div>
                <form method="post" action="/logout">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-danger" type="submit">Выход</button>
                </form>
            </#if>
        </div>
    </div>
</nav>