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
    <c:if test="${message !=null}">
        <div class="alert-warning" role="alert">
            ${message}
        </div>
    </c:if>
    <form class="form-signin" method="POST" action="j_security_check">
        <h1 class="h3 mb-3 font-weight-normal"> Sign in </h1>
        <label for="username" class="sr-only"> Username </label>
        <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus />
        <label for="password" class="sr-only"> Password </label>
        <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required />
        <hr class="my-4">
        <button class="btn  btn-lg btn-primary btn-block" type="submit"> Sign in </button>
    </form>
<<<<<<< Updated upstream
</t:pageTemplate>


