<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
        <title>Spring Security Example</title>
        <link rel="stylesheet" href="/static/css/bootstrap.min.css" type="text/css" />
    </head>

    <body>
        
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <#if successMessage??>
                    <br/>
                    <div class="alert alert-success">${successMessage}</div>
                    </#if>
                    <h1>Hello, <u>${user.login}</u></h1>
                    <br/>
                    <h3>Here is your account details</h3>
                    <br/>
                    <div class="panel panel-default">
                        <div class="panel-heading">Account details</div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Login</th>
                                        <th>Password</th>
                                        <th>Role</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.login}</td>
                                        <td>${user.password}</td>
                                        <td>${user.role.name()}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <br/>
                    <form action="/logout" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary">Logout</button>
                    </form>
                </div>            
                <div class="col-md-6"></div>
            </div>
        </div>
        
        <script type="text/javascript" src="/static/js/jquery-1.11.0.js"></script>       
        <script type="text/javascript" src="/static/js/bootstrap.min.js"></script> 
    </body>
</html>