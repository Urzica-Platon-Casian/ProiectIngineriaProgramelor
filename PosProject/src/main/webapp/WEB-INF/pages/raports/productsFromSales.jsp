<%-- 
    Document   : productsFromSales
    Created on : Jan 8, 2022, 12:50:37 AM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="StockReport">
    <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Products From Sales</h1>
    <c:forEach var="product" items="${products}" varStatus="status">
        <div>
            ${product.name}
            <div class="progress">
                <div class="progress-bar" role="progressbar" style="width: ${product.numberOfSales}%">${product.numberOfSales}</div>
            </div>
        </div>
    </c:forEach>
</t:pageTemplate>