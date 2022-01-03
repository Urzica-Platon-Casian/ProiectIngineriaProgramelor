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
        <%-- <c:if test="${pageContext.request.isUserInRole('AdminRole')}">
           
            <button class="btn btn-secondary" type="submit">Invoice</button>
        </c:if>
           --%>
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Users/Create" role="button">Add User</a>
        <button class="btn btn-danger" type="submit">Delete Users</button>
        <c:forEach var="user" items="${users}" varStatus="status">
            <div class="row">
                <div class="col-md">
                    <input type="checkbox" name="user_ids" value="${user.id}" /> 
                </div>
                <div class="col-md-4">
                    ${user.username}
                </div>
                
                <div class="col-md-4">
                    ${user.position}
                </div>
            </div>
                <div class="col-md-2">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/Users/Update?id=${user.id}" role="button"> Edit User </a>
                </div>
        </c:forEach>

    </form>
   
    <c:forEach var="username" items="${invoices}" varStatus="status">
        ${username}
    </c:forEach>
</t:pageTemplate>
