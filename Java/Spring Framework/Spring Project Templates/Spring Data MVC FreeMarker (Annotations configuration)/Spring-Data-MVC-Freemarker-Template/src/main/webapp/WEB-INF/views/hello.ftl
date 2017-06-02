<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>

<@c.url var="cssUrl" value="/resources/styles/style.css" />
<@c.url var="jqueryUrl" value="/resources/js/jquery.js" />
<@c.url var="jsUrl" value="/resources/js/script.js" />
<@c.url var="loginUrl" value="/" />

<html>
    <head>
        <title>Welcome Page</title>
        <link rel="stylesheet" href="${cssUrl}" type="text/css" />
        <script src="${jqueryUrl}"></script>
    </head>
    
    <body>
        <#include "/common/header.ftl">
        <div id="content-wrap">
            <div id="common-content">
                <h2>Hello, ${user.firstName} ${user.lastName} !</h2>       
                <br/>
                <br/>
                <a href="${loginUrl}" class="btn center">Back to login page</a>
            </div>            
        </div>
        <#include "/common/footer.ftl">
                
        <script src="${jsUrl}"></script> 
    </body>
</html>