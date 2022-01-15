<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getName()
        isAdmin = user.isAdmin()
        isBarista = user.isBarista()
    >
<#else>
    <#assign
        name = "unknown"
        isAdmin = false
        isBarista = false
    >
</#if>