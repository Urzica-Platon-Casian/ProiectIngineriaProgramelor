<%-- 
    Document   : Products
    Created on : Dec 29, 2021, 7:44:16 PM
    Author     : Monica
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Products">
    <h1>Products</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Products">

        <button hidden class="btn btn-danger" type="submit">Delete Products</button>
        <div class="row" style="padding: 10px;">
            <div class="col-md-2">
                <b> Select product</b>                    
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
                <b>Edit</b>
            </div>  
            <div class="col-md-2">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/Products/Create" role="button">Add Product</a>
            </div>  
        </div>  




        <c:forEach var="product" items="${products}" varStatus="status">
            <div class="row" style="padding: 10px;">
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
                    <a class="btn btn-primary " href="${pageContext.request.contextPath}/Products/Update?id=${product.id}" role="button"> Edit</a>
                </div>

            </div>
        </c:forEach>
    </t:pageTemplate>