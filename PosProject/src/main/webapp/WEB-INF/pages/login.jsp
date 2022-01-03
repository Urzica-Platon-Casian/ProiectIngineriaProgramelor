<%-- 
    Document   : login
<<<<<<< Updated upstream
    Created on : 02.01.2022, 00:36:13
    Author     : stupa
--%>


=======
    Created on : 01.01.2022, 21:18:02
    Author     : stupa
--%>

>>>>>>> Stashed changes
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Login">
    
    <form class="form-signin" method="POST" action="">
        <h1 class="h3 mb-3 font-weight-normal"> Sign in </h1>
        <label for="username" class="sr-only"> Username </label>
        <input type="text" id="username" name="" class="form-control" placeholder="Username" required autofocus />
        <label for="password" class="sr-only"> Password </label>
        <input type="password" id="password" name="" class="form-control" placeholder="Password" required />
         <div class="col-md-1">
                    <a class="btn btn-primary " href="${pageContext.request.contextPath}/Users?id=${product.id}" role="button">Login</a>
                </div>

    </form>
<<<<<<< Updated upstream
</t:pageTemplate>


