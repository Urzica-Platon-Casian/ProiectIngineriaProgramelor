<%-- 
    Document   : productsFromCatalog
    Created on : Dec 31, 2021, 1:55:50 PM
    Author     : upcas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String id = request.getParameter("id");%>

<t:pageTemplate pageTitle="Products">
    <h1>Products</h1>
    <form method="POST" action="${pageContext.request.contextPath}/ProductsFromCatalog">

<!--<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Products/Create" role="button">Add Product</a>-->
        <button class="btn btn-danger" type="submit">Delete Products</button>              
        <input type="hidden" name="catalog_id" value="<c:out value="${param['id']}"/>" />
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
                <!--                <div class="col-md-1">
                                    <a class="btn btn-secondary" href="" role="button"> Edit Product </a>
                                </div>-->
            </div>
        </c:forEach>
    </t:pageTemplate>
