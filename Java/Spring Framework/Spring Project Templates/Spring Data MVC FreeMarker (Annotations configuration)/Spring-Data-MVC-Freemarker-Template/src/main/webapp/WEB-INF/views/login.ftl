<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<@c.url var="cssUrl" value="/resources/styles/style.css" />
<@c.url var="jqueryUrl" value="/resources/js/jquery.js" />
<@c.url var="jsUrl" value="/resources/js/script.js" />


<html>
    <head>
        <title>Login Page</title>
        <link rel="stylesheet" href="${cssUrl}" type="text/css" />
        <script src="${jqueryUrl}"></script>
    </head>
    
    <body>
        <#include "/common/header.ftl">
        <div id="content-wrap">
            <div id="common-content">
                <h2>Please, Sing in:</h2>  
                <@sf.form method="POST" modelAttribute="loginForm" id="login-form">        
                    <#if errorMessage??>
                        <p class="error-msg">${errorMessage}</p>
                     </#if>
                    <div class="input-wrap">
                        <@sf.input path="login" placeholder="Login"/>
                    </div>
                    <div class="input-wrap">
                        <@sf.input path="password" type="password" placeholder="Password"/>
                    </div> 
                    <input type="submit" name="submit" class="btn" id="login-form-btn" value="Sign in" />   
                </@sf.form>
            </div>            
        </div>
        <#include "/common/footer.ftl">
                
        <script src="${jsUrl}"></script> 
    </body>
</html>