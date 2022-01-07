<%-- 
    Document   : reports
    Created on : Jan 8, 2022, 12:16:13 AM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Reports">
    <h1 style="text-align: center">Reports</h1>
    <div style="padding-left: 520px;">
        <div class="row" style="padding: 20px; text-align: center;">
            <div class="col-md-4">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/ProductsStocksReport?stock=0" role="button">Products Stocks</a>             
            </div>  
        </div>
    </div>
</t:pageTemplate>
