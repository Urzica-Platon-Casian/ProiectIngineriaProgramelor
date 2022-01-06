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
    <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Sale Page</h1>
    <div class="split left">
        <div>
            <div class="row" style="padding: 10px; text-align: center">
                <div class="col-md-4">
                    <b>Added Products:</b>
                </div>
                <div class="col-md-2">
                    <b>Total:</b> ${total} euro
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
        <c:forEach var="saleItem" items="${saleItems}" varStatus="status">
            <div>
                <div class="row" style="padding: 10px; text-align: center">
                    <div class="col-md-4">
                        ${saleItem.productName}
                    </div>
                    <div class="col-md-2">
                        ${saleItem.quantity}
                    </div> 
                    <div class="col-md-2">
                        ${saleItem.price}
                    </div>
                    <div class="col-md-3">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/DelSmth" role="button">Delete Product</a>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div style="padding-left: 100px">
            <div class="row" style="width: 20%; padding: 10px">
                <div style="display: flex">
                    <div class="col-md-10"><a class="btn btn-primary" href="${pageContext.request.contextPath}/PayByCash?id=${saleId}" role="button">Pay By Cash</a></div>
                    <div class="col-md-10"><a class="btn btn-primary" href="${pageContext.request.contextPath}/PayByCard?id=${saleId}" role="button">Pay By Card</a></div>
                </div>
            </div>
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
                    <b style="padding-right: 25px">All Products</b>
                </div>
            </div>
        </div>
        <c:forEach var="product" items="${products}" varStatus="status">
            <form method="POST" action="${pageContext.request.contextPath}/Sale">
                <div>
                    <div class="row" style="padding: 10px; text-align: center">
                        <div class="col-md-4">
                            ${product.name}
                        </div>
                        <div class="col-md-2">
                            <label>Quantity</label>
                            <input type="text" name="quantity" style="width: 35px" value=""/>
                        </div>
                        <div class="col-md-3">
                            <input type="hidden" name="product_id" value="${product.id}"/>
                            <input type="hidden" name="saleId" value="${saleId}"/>
                            <!--<a class="btn btn-primary" href="${pageContext.request.contextPath}/DeleteProdFromSale" role="button">Add</a>-->
                            <button class="btn btn-primary" type="submit">Add</button>
                        </div>                        
                    </div>
                </div>
            </form>
        </c:forEach> 
    </div>
</t:pageTemplate>