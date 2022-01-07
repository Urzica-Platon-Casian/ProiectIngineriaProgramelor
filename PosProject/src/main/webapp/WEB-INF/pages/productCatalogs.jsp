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
    <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Product Catalogs</h1>
    <form method="POST" action="${pageContext.request.contextPath}/ProductCatalogs">
        <c:forEach var="productCatalog" items="${productCatalogs}" varStatus="status">
            <div class="row" style="padding: 10px;">
                <div class="col-md-4">
                    <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/ProductsFromCatalog?id=${productCatalog.id}">${productCatalog.productCatalogName}</a>              
                </div>     
            </div>
        </c:forEach>
    </form>
</t:pageTemplate>
