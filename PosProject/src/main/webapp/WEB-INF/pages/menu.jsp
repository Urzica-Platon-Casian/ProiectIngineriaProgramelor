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

                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/productCatalogs.jsp' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/ProductCatalogs">Product Catalogs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/products.jsp' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/Products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/users.jsp' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/Users">Users</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/actions.jsp' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/Actions">Actions</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/about.jsp' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/about.jsp">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI eq '/PosProject/cashiers.jsp' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/Cashiers">Cashiers</a>
                </li>
            </ul>
            <ul class="navbar-nav d-flex">
                <li class="navbar-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
