<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>

<@c.url var="bootstrapCssUrl" value="/resources/styles/bootstrap.min.css" />
<@c.url var="jQueryUrl" value="/resources/js/jquery-1.11.0.js" />
<@c.url var="bootstrapJsUrl" value="/resources/js/bootstrap.min.js" />

<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Users</title>      
        <link href="${bootstrapCssUrl}" rel="stylesheet">
    </head>
    
    <body>
        
        <div class="container"> 
            <br/>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-user"></span>&nbsp;
                    All Users
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Login</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list users as user>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.login}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role.roleName}</td>
                        </tr> 
                        </#list>                                               
                    </tbody>
                </table>
            </div>
        </div>

        <script src="${jQueryUrl}"></script>
        <script src="${bootstrapJsUrl}"></script>
      
    </body>
    
</html>