<%-- 
    Document   : payByCash
    Created on : Jan 6, 2022, 12:26:30 AM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Pay By Cash Page">
    <form method="POST" action="${pageContext.request.contextPath}/PayByCash">
        <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Pay by cash</h1>
        <div>
            <div class="row" style="padding-left: 500px;  padding-top: 10px">
                <p><b>Total: </b>${total} euro</p>
            </div> 
            <div class="row" style="padding-left: 500px;  padding-top: 10px">
                <div class="col-md-3 mb-1">
                    <label for="first_name"><b>Money:</b></label>
                    <input type="hidden" name="saleId" value="${saleId}"/>
                    <input type="number" step="0.01" class="form-control" id="amount" name="amount" placeholder="" value="${amount}" required>
                    <div class="invalid-feedback">
                        Money is required.
                    </div>
                </div>
                <div class="col-md-3">
                    <c:choose>
                    <c:when test="${status == true}">
                        <button disabled class="btn btn-primary btn-lg" type="submit">Get Change</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-primary btn-lg" type="submit">Get Change</button>
                    </c:otherwise>
                </c:choose>
                    
                </div>
            </div>
            <div class="row" style="padding-left: 500px; padding-top: 10px">
                <p><b>Change: </b>${change} euro</p>
            </div>
            <div class="row" style="padding-left: 500px; padding-top: 10px">
                <div class="col-md-3">
                    <a class="btn btn-danger " href="${pageContext.request.contextPath}/Sucessfull?id=${saleId}" role="button">Finish</a>
                </div>
            </div>
        </div>
    </form>
</t:pageTemplate>
