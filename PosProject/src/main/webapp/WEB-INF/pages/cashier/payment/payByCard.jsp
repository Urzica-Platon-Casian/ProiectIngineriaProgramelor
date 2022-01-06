<%-- 
    Document   : payByCard
    Created on : Jan 6, 2022, 12:57:14 AM
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
                <p><b>Total: </b>25 euro</p>
            </div> 
            <div class="row" style="padding-left: 500px;  padding-top: 10px">
                <div class="col-md-3 mb-1">
                    <label for="first_name"><b>Card Number:</b></label>
                    <input type="text" class="form-control" id="card_number" name="card_number" placeholder="" value="" required>
                    <div class="invalid-feedback">
                       Card Number is required.
                    </div>
                </div>
            </div>
            <div class="row" style="padding-left: 500px;  padding-top: 10px">
                <div class="col-md-3 mb-1">
                    <label for="first_name"><b>Expire Date:</b></label>
                    <!--<input type="date" class="form-control" id="expire_date" name="expire_date" placeholder="" value="" required>-->
                    <input type="month" id="start" name="start" min="2022-02" value="" required>
                    <div class="invalid-feedback">
                        Expire Date is required.
                    </div>
                </div>
            </div>
            <div class="row" style="padding-left: 500px;  padding-top: 10px">
                <div class="col-md-3 mb-1">
                    <label for="first_name"><b>CVV:</b></label>
                    <input type="text" class="form-control" id="cvv" name="cvv" placeholder="" value="" required>
                    <div class="invalid-feedback">
                        CVV is required.
                    </div>
                </div>
            </div>
            <div class="row" style="padding-left: 500px; padding-top: 10px">
                <div class="col-md-3">
                    <button class="w-100 btn btn-primary btn-lg" type="submit">Finish payment</button>
                </div>
            </div>
        </div>
    </form>
</t:pageTemplate>
