<%-- 
    Document   : Products
    Created on : Dec 29, 2021, 7:44:16 PM
    Author     : Monica
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Products">
    <h1>Products</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Products">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Products/Create" role="button">Add Product</a>
        <button class="btn btn-danger" type="submit">Delete Products</button>
        <c:forEach var="product" items="${products}" varStatus="status">
            <div class="row">
                <div class="col-md">
                    <input type="checkbox" name="product_ids" value="${product.id}" />
                </div>
                <div class="col-md-1">
                    ${product.id}
                </div>
                <div class="col-md-1">
                    ${product.barcode}
                </div>
                <div class="col-md-1">
                    ${product.name}
                </div>
                <div class="col-md-1">
                    ${product.description}
                </div>
                <div class="col-md-1">
                    ${product.price}
                </div>
                <div class="col-md-1">
                    ${product.category}
                </div>                  
                <div class="col-md-1">
                    <a class="btn btn-secondary" href="" role="button"> Edit Product </a>
                </div>
            </div>
        </c:forEach>
    </t:pageTemplate>