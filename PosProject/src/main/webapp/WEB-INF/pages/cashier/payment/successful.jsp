<%-- 
    Document   : succesful
    Created on : Jan 6, 2022, 4:32:58 PM
    Author     : upcas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Payment Succesful">
    <form method="POST" action="${pageContext.request.contextPath}/Successful">
        <input type="hidden" name="saleId" value="${saleId}"/>
        <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Payment registered successfully</h1>
        <h2 style="padding-bottom: 15px">This is your receipt</h2>
        <div class="row" text-align: center">
            <div class="col-md-2">
                <b>
                    Payment id:
                    <c:out value="${saleDetails.id}"/>
                </b>
            </div>
        </div>
        <div class="row" text-align: center">
            <div class="col-md-2">
                <b>Cashier id:<c:out value="${saleDetails.cashierId}"/></b>
            </div>
        </div>
        <div class="row" text-align: center">
            <div class="col-md-5">
                <b>
                    Date and time of purchase:
                    <c:out value="${saleDetails.date}"/>
                </b>
            </div>
        </div>
        <div class="row" style="padding: 10px; text-align: center">
            <div class="col-md-2"><b>Products</b></div>
            <div class="col-md-2"><b>Number of Products</b></div>
            <div class="col-md-2"><b>Price</b></div>
        </div>
        <c:forEach var="item" items="${itemDetails}" varStatus="status">
            <div class="row" style="padding: 10px; text-align: center">
                <div class="col-md-2">
                    ${item.productName}:
                </div>
                <div class="col-md-2">
                    ${item.quantity}
                </div>
                <div class="col-md-2">
                    ${item.quantity * item.price}
                </div>
            </div>
        </c:forEach>
        <div class="row" text-align: center">
            <div class="col-md-2">
                <b>
                    Total paid:
                    <c:out value="${total}"/>
                </b>
            </div>
        </div>
    </form>
</t:pageTemplate>