<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<h2>Please, Sing in:</h2>       
    
<sf:form method="POST" modelAttribute="loginForm" id="login-form">        
    <c:if test="${not empty errorMessage}">
        <p class="error-msg">${errorMessage}</p>
    </c:if>
    <div class="input-wrap">
        <sf:input path="login" placeholder="Login"/>
    </div>
    <div class="input-wrap">
        <sf:input path="password" type="password" placeholder="Password"/>
    </div> 
    <input type="submit" name="submit" class="btn" id="login-form-btn" value="Sign in" />   
</sf:form>