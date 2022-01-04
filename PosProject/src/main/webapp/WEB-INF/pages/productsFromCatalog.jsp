<%-- 
    Document   : productsFromCatalog
    Created on : Dec 31, 2021, 1:55:50 PM
    Author     : upcas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Products">
    <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Products</h1>
    <form method="POST" action="${pageContext.request.contextPath}/ProductsFromCatalog">
        <input type="hidden" name="catalog_id" value="<c:out value="${param['id']}"/>" />           
        <div class="row" style="padding: 10px; text-align: center">
            <div class="col-md-2">
                <b>Select product</b>                    
            </div>
            <div class="col-md-1">
                <b>Id</b>                   
            </div>
            <div class="col-md-1">
                <b>Barcode</b>                   
            </div>
            <div class="col-md-1">
                <b>Name</b>                   
            </div>
            <div class="col-md-2">
                <b>Description</b>                    
            </div>
            <div class="col-md-1">
                <b>Price</b>      
            </div>
            <div class="col-md-1">
                <b>Category</b>               
            </div>
            <div class="col-md-1">
                <b>Quantity</b>               
            </div>  
            <div class="col-md-2">
                <button class="btn btn-danger" type="submit">Delete Products</button>    
            </div>  
        </div>  
        <c:forEach var="product" items="${products}" varStatus="status">
            <div class="row" style="padding: 10px; text-align: center">
                <div class="col-md-2">
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
                <div class="col-md-2">
                    ${product.description}
                </div>
                <div class="col-md-1">
                    ${product.price}
                </div>
                <div class="col-md-1">
                    ${product.category}
                </div>
                <div class="col-md-1">
                    ${product.quantity}
                </div>
            </div>
        </c:forEach>
    </t:pageTemplate>
