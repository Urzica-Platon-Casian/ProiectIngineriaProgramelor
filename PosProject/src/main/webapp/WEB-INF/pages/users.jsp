<%-- 
    Document   : users
    Created on : 29.12.2021, 18:47:50
    Author     : stupa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Users">
        <c:if test="${pageContext.request.isUserInRole('DGRole')}">
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Users/Create" role="button">Add User</a>
            <button class="btn btn-danger" type="submit">Delete Users</button>
        </c:if>
        <c:forEach var="user" items="${users}" varStatus="status">
            <div class="row">
                <div class="col-md">
                    <input type="checkbox" name="user_ids" value="${user.id}" /> 
                </div>

                <div class="col-md-2">
                    ${user.firstName}
                </div>
                <div class="col-md-2">
                    ${user.lastName}
                </div>


                <div class="col-md-2">
                    ${user.position}
                </div>

                <div class="col-md-2">
                    ${user.username}
                </div>
            </div>
            <c:if test="${pageContext.request.isUserInRole('DGRole')}">
                <div class="col-md-2">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/Users/Update?id=${user.id}" role="button"> Edit User </a>
                </div>
            </c:if>
        </c:forEach>

    </form>

</t:pageTemplate>
