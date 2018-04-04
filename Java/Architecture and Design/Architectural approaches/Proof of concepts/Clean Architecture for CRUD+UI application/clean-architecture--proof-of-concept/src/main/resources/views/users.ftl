<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Users</title>      
        <link href="/static/styles/bootstrap.min.css" rel="stylesheet">
    </head>
    
    <body>
        
        <div class="container"> 
            <#if message??>
                <#if message.type == 'error'>
                    <#assign messageTypeClass='alert-danger'>
                <#else>
                    <#assign messageTypeClass='alert-success'>
                </#if>
            <div class="alert ${messageTypeClass}" style="margin-top: 20px;">${message.text}</div>
            </#if>
            <br/>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-user"></span>&nbsp;
                    All Users
                    <a href="/users/new" class="btn btn-success pull-right">Add</a>
                    <div style="clear: both;"></div>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Login</th>
                            <th>Email</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list users as user>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>
                                <a href="/users/${user.id}/delete" class="btn btn-danger pull-right" style="margin-left: 10px;">Remove</a>
                                <a href="/users/${user.id}/modify" class="btn btn-primary pull-right">Modify</a>
                                <div style="clear: both;"></div>
                            </td>
                        </tr> 
                        </#list>                                               
                    </tbody>
                </table>
            </div>
        </div>
        
        <script src="/static/js/jquery-1.11.0.js"></script>
        <script src="/static/js/bootstrap.min.js"></script>
      
    </body>
    
</html>