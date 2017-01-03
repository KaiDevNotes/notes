<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="loginUrl" value="/"></c:url>

<h2>Hello, ${user.firstName} ${user.lastName} !</h2>       
<br/>
<br/>
<a href="${loginUrl}" class="btn center">Back to login page</a>