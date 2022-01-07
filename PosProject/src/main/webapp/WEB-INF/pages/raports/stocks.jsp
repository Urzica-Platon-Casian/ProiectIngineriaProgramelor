<%-- 
    Document   : stocks
    Created on : Jan 7, 2022, 11:14:31 PM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="StockReport">
    <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Stocks Report</h1>
    <div style="padding-left: 300px">
        <form method="POST" action="${pageContext.request.contextPath}/ProductsStocksReport">
            <div class="row">
                <div class="col-md-3">
                    <b>Products with the stock under: </b>
                </div>
                <div class="col-md-2">
                    <input type="number" style="margin-bottom: 10px" class="form-control" id="stock" name="stock" placeholder="Stock number" value="">
                </div>
                <div class="col-md-3">
                    <button class="btn btn-primary" type="submit">Get Report</button>
                </div>
            </div>
            <div class="row" style="padding: 10px; text-align: center">
                <div class="col-md-1">
                    <b>Id</b>                   
                </div>
                <div class="col-md-1">
                    <b>Barcode</b>                   
                </div>
                <div class="col-md-1">
                    <b>Name</b>                   
                </div>
                <div class="col-md-1">
                    <b>Price</b>      
                </div>
                <div class="col-md-1">
                    <b>Quantity</b>               
                </div>
            </div>  
            <c:forEach var="product" items="${products}" varStatus="status">
                <div class="row" style="padding: 10px; text-align: center">
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
                        ${product.price}
                    </div>
                    <div class="col-md-1">
                        ${product.quantity}
                    </div> 
                </div>
            </c:forEach>
    </div>
</t:pageTemplate>
