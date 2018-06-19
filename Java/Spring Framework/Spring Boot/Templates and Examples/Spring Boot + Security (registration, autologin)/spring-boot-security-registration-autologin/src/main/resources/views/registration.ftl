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
                    <#if failureMessage??>
                    <br/>
                    <div class="alert alert-danger">${failureMessage}</div>
                    </#if>
                    <h1>Sugn Up, please...</h1>
                    <br/>
                    <form action="/registration" method="POST" role="form">
                        <div class="form-group">
                            <input type="text" class="form-control" name="login" placeholder="Login">
                        </div>
                        <div class="form-group">
                          <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-default">Sing up</button>
                    </form>
                </div>            
                <div class="col-md-6"></div>
            </div>
        </div>
        
        <script type="text/javascript" src="/static/js/jquery-1.11.0.js"></script>       
        <script type="text/javascript" src="/static/js/bootstrap.min.js"></script> 
    </body>
</html>