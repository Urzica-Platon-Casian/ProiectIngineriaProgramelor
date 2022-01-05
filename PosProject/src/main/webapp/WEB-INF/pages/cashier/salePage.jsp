<%-- 
    Document   : salePage
    Created on : Jan 5, 2022, 6:36:48 PM
    Author     : upcas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .split {
        height: 100%;
        width: 50%;
        position: fixed;
        z-index: 1;
        overflow-x: hidden;
    }

    /* Control the left side */
    .left {
        left: 0;
    }

    /* Control the right side */
    .right {
        right: 0;
    }
</style>

<t:pageTemplate pageTitle="Sale Page">
    <form method="POST" action="${pageContext.request.contextPath}/Sale">
        <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Sale Page</h1>
        <div class="split left">
            <div>
                <div class="row" style="padding: 10px; text-align: center">
                    <div class="col-md-4">
                        <b>Added Products:</b>
                    </div>
                    <div class="col-md-2">
                        <b>Total:</b>
                    </div>
                </div>
            </div>
            <div>
                <div class="row" style="padding: 10px; text-align: center">
                    <div class="col-md-4">
                        <b>Product name</b>
                    </div>
                    <div class="col-md-2">
                        <b>Quantity</b>
                    </div>
                    <div class="col-md-2">
                        <b>Price</b>
                    </div>
                </div>
            </div>
            <c:forEach var="product" items="${products}" varStatus="status">
                <div>
                    <div class="row" style="padding: 10px; text-align: center">
                        <div class="col-md-4">
                            ${product.name}
                        </div>
                        <div class="col-md-2">
                            ${product.quantity}
                        </div> 
                        <div class="col-md-2">
                            ${product.price}
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-primary " href="${pageContext.request.contextPath}/DelSmth" role="button">Delete Product</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div style="width: 20%; padding: 10px">
                <label for="paymentMethod">Payment method</label>
                <select class="custom-select d-block w-100" id="paymentMethod" name="paymentMethod" required>
                    <option value="">Choose...</option>
                    <option value="CASH">Cash</option>
                    <option value="CARD">Card</option>
                </select>
            </div>
        </div>
        <div class="split right">
            <div>
                <div class="row" style="padding: 10px; text-align: center">
                    <div class="col-md-4">
                        <b>Number of products:</b>
                    </div>
                </div>
            </div>
            <div>
                <div class="row" style="padding: 10px; text-align: center">
                    <div class="col-md-4">
                        <b style="padding-right: 25px">Products Here</b>
                    </div>
                </div>
            </div>
            <c:forEach var="product" items="${products}" varStatus="status">
                <div>
                    <div class="row" style="padding: 10px; text-align: center">
                        <div class="col-md-4">
                            ${product.name}
                        </div>
                        <div class="col-md-2">
                            <label>Quantity</label>
                            <input type="text" name="productsQuantity" style="width: 35px" value="${product.quantity}"/>
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/DeleteProdFromSale" role="button">Add</a>
                        </div>                        
                    </div>
                </div>
            </c:forEach>            
        </div>
    </form>
</t:pageTemplate>