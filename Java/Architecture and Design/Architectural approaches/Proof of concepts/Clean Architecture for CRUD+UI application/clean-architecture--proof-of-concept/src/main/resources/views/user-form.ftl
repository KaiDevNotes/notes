<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>User's Form</title>      
        <link href="/static/styles/bootstrap.min.css" rel="stylesheet">
    </head>
    
    <body>
        
        <div class="container"> 
            <br/>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-list-alt"></span>&nbsp;
                    User's Form
                </div>
                <#if message??>
                    <#if message.type == 'error'>
                        <#assign messageTypeClass='alert-danger'>
                    <#else>
                        <#assign messageTypeClass='alert-success'>
                    </#if>
                <div class="alert ${messageTypeClass}" style="margin: 20px 20px 0 20px;">${message.text}</div>
                </#if>
                <form method="POST" action="/users" role="form" style="padding: 20px;">
                    <div class="form-group">
                        <label for="login">Login</label>
                        <input type="text" name="login" value="${form.login}" class="form-control" id="login" placeholder="Logon">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" name="password" value="${form.password}" class="form-control" id="password" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" name="email" value="${form.email}" class="form-control" id="email" placeholder="Email">
                    </div>
                    <input type="hidden" name="id" value="${form.id}">
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
        
        <script src="/static/js/jquery-1.11.0.js"></script>
        <script src="/static/js/bootstrap.min.js"></script>
      
    </body>
    
</html>