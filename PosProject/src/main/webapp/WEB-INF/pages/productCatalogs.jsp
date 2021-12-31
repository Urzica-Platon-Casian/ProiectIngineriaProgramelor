<%-- 
    Document   : productCatalogs
    Created on : Dec 29, 2021, 6:18:24 PM
    Author     : Monica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="ProductCatalogs">
    <h1>ProductCatalogs</h1>
    <form method="POST" action="${pageContext.request.contextPath}/ProductCatalogs">
       
    <%-- <c:if test="${pageContext.request.isUserInRole('AdminRole')}">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/ProductCatalogs/Create" role="button">Add ProductCatalog</a>
        <button class="btn btn-secondary" type="submit">Invoice</button>    
    </c:if> --%>
    
    
    <c:forEach var="productCatalog" items="${productCatalogs}" varStatus="status">
        <div class="row">
            <div class="col-md">
                <input type="checkbox" name="productCatalog_ids" value="${productCatalog.id}" />
            </div>
                <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/ProductsFromCatalog?id=${productCatalog.id}">${productCatalog.productCatalogName}</a> 
            </div>
                
        </div>
    </c:forEach>
    </form>
</t:pageTemplate>
