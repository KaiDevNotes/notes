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
                    <#if RequestParameters.error??>
                    <br/>
                    <div class="alert alert-danger">Login or Password is incorrect</div>
                    </#if>
                    <h1>Login, please...</h1>
                    <br/>
                    <form action="/login" method="POST" role="form">
                        <div class="form-group">
                            <input type="text" class="form-control" name="username" placeholder="Login">
                        </div>
                        <div class="form-group">
                          <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-default">Login</button>
                    </form>
                    <br/>
                    <br/>                    
                    <br/>
                    <div class="panel panel-default">
                        <div class="panel-heading">Test credentials</div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Account</th>
                                        <th>Login / Password</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>USER</td>
                                        <td>user1 / password1</td>
                                    </tr>
                                    <tr>
                                        <td>ADMIN</td>
                                        <td>admin1 / password2</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>            
                <div class="col-md-6"></div>
            </div>
        </div>
        
        <script type="text/javascript" src="/static/js/jquery-1.11.0.js"></script>       
        <script type="text/javascript" src="/static/js/bootstrap.min.js"></script> 
    </body>
</html>