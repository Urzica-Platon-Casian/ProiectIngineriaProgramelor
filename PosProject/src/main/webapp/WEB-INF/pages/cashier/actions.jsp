<%-- 
    Document   : actions
    Created on : Jan 3, 2022, 9:10:22 PM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Actions">
    <form method="POST" action="${pageContext.request.contextPath}/Actions">
        <h1 style="text-align: center">Initialize:</h1>
        <div style="padding-left: 500px;">
             <div class="row" style="padding: 20px; text-align: center;">
            <div class="col-md-4">
                <button class="btn btn-primary" type="submit">Sale</button>              
            </div>  
        </div>
        <div class="row" style="padding: 20px; text-align: center;">
            <div class="col-md-4">
                <button class="btn btn-primary" type="submit" formaction="#">Return</button>             
            </div>  
        </div>
    </div>
</t:pageTemplate>
