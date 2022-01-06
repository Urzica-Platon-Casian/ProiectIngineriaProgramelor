<%-- 
    Document   : succesful
    Created on : Jan 6, 2022, 4:32:58 PM
    Author     : upcas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Payment Succesful">
    <form method="POST" action="${pageContext.request.contextPath}/Succesful">
        <input type="hidden" name="saleId" value="${saleId}"/>
        <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Payment registered successfully</h1>
        <button type="submit" value="Press Here"> Press Here </button>
        <h2 style="text-align: center">This is your receipt</h2>
        <div class="row" style="padding: 10px; text-align: center">
            <div class="row-md-2">
                <b>
                    Payment id:
                    <c:out value="${saleDetails.id}"/>
                </b>
            </div>
            <div class="row-md-2">
                <b>
                    Cashier id:
                    <c:out value="${saleDetails.cashierId}"/>
                </b>
            </div>
            <div class="row-md-2">
                <b>
                    Date and time of purchase:
                    <c:out value="${saleDetails.date}"/>
                </b>
            </div>
            <div class="row-md-2">
                <b>
                    Products
                    <c:forEach var="item" items="${itemDetails}" varStatus="status">
                        <div>
                            ${item.productName}: ${item.quantity}
                        </div>
                    </c:forEach>
                </b>
            </div>
        </div>
    </form>
</t:pageTemplate>