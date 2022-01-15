<%-- 
    Document   : actions
    Created on : Jan 3, 2022, 9:10:22 PM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Actions">

    <h1 style="text-align: center">Initialize:</h1>
    <div style="padding-left: 500px;">
        <form method="POST" action="${pageContext.request.contextPath}/Actions">
            <div class="row" style="padding: 20px; text-align: center;">
                <div class="col-md-4">
                    <button class="btn btn-primary" type="submit">Sale</button>              
                </div>  
            </div>
        </form>   
        <div class="row" style="padding: 20px; text-align: center;">
            <div class="col-md-4">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/ReturAction" role="button">Retur</a>             
            </div>  
        </div>
    </div>
</t:pageTemplate>
