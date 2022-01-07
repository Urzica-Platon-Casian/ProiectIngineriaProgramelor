<%--
    Document   : menu
    Created on : Dec 27, 2021, 10:09:00 PM
    Author     : upcas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">POS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <c:if test="${pageContext.request.isUserInRole('AdminRole')}">
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/users.jsp' ? 'active' : ''}" 
                           href="${pageContext.request.contextPath}/Users">Users</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/productCatalogs.jsp' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/ProductCatalogs">Product Catalogs</a>
                </li>
                <c:if test="${pageContext.request.isUserInRole('DGRole')}">
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/products.jsp' ? 'active' : ''}" 
                           href="${pageContext.request.contextPath}/Products">Products</a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('CashierRole')}">
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/actions.jsp' ? 'active' : ''}" 
                           href="${pageContext.request.contextPath}/Actions">Actions</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/about.jsp' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/about.jsp">About</a>
                </li>
                <c:if test="${pageContext.request.isUserInRole('DGRole')}">
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/cashiers.jsp' ? 'active' : ''}" 
                           href="${pageContext.request.contextPath}/Cashiers">Cashiers</a>
                    </li>
                </c:if>
            </ul>
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${pageContext.request.getRemoteUser()==null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Login"> Login </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-link">
                            Welcome to POS
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout"> Logout </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
