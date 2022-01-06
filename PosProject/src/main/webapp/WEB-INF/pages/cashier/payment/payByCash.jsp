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
                <p><b>Total: </b>${total}</p>
            </div> 
            <div class="row" style="padding-left: 500px;  padding-top: 10px">
                <div class="col-md-3 mb-1">
                    <label for="first_name"><b>Money:</b></label>
                    <input type="text" class="form-control" id="money" name="money" placeholder="" value="" required>
                    <div class="invalid-feedback">
                        Money is required.
                    </div>
                </div>
                <div class="col-md-3">
                    <a class="btn btn-primary " href="${pageContext.request.contextPath}/DelSmth" role="button">Delete Product</a>
                </div>
            </div>
            <div class="row" style="padding-left: 500px; padding-top: 10px">
                <p><b>Change: </b>${change}</p>
            </div>
            <div class="row" style="padding-left: 500px; padding-top: 10px">
                <div class="col-md-3">
                    <button class="w-100 btn btn-primary btn-lg" type="submit">Finish payment</button>
                </div>
            </div>
        </div>
    </form>
</t:pageTemplate>
