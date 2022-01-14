<%-- 
    Document   : addRetur
    Created on : Jan 14, 2022, 7:54:38 PM
    Author     : Monica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<t:pageTemplate pageTitle="Sale Page">
    <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Add return</h1>
    <div class="split left">
        <div>
            <div class="row" style="padding: 10px; text-align: center">
                <div class="col-md-4">
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
                
         <c:forEach var="product" items="${products}" varStatus="status">
            <form method="POST" action="${pageContext.request.contextPath}/AddRetur">
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
                            <button class="btn btn-primary" type="submit">Add</button>
                        </div>                        
                    </div>
                </div>
            </form>
        </c:forEach> 
                
    </div>
</t:pageTemplate>