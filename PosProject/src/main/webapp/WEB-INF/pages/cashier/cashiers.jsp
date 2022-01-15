<%-- 
    Document   : cashiers
    Created on : Jan 5, 2022, 7:46:16 PM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cashiers">
    <h1>Cashier</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Cashiers">
        <div class="row">
                <div class="col-md-2">
                   First Name
                </div>
                <div class="col-md-2">
                    Last Name
                </div>
                <div class="col-md-2">
                    Username
                </div>
                <div class="col-md-2">
                    Position
                </div>
                <div class="col-md-2">
                   Status
                </div>
            </div>
        <c:forEach var="user" items="${cashiers}" varStatus="status">
            <div class="row">
                <div class="col-md-2">
                    ${user.firstName}
                </div>
                <div class="col-md-2">
                    ${user.lastName}
                </div>
                <div class="col-md-2">
                    ${user.username}
                </div>
                <div class="col-md-2">
                    ${user.position}
                </div>
                <div class="col-md-2">
                    <c:choose>
                        <c:when test="${user.validation eq true}">
                            VALID
                        </c:when>
                        <c:when test="${user.validation eq false}">
                            INVALID
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-md-2" style="padding-bottom: 15px">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/Cashier/Validate?id=${user.id}" role="button">Change Status</a>
                </div>
            </div>
        </c:forEach>
    </form>
</t:pageTemplate>