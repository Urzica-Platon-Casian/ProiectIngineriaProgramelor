<%-- 
    Document   : addRetur
    Created on : Jan 14, 2022, 7:54:38 PM
    Author     : Monica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<t:pageTemplate pageTitle="Retur Page">
    <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Add return</h1>
    <div class="split left">
        
        <div>
            <div class="row" style="padding: 10px; text-align: center">
                <div class="col-md-2">
                    <b>Product name</b>
                </div>
                <div class="col-md-2">
                    <b>Quantity</b>
                </div>
                <div class="col-md-2">
                    <b>Price</b>
                </div>
                <div class="col-md-2">
                    <b>Retured quantity</b>
                </div>
                <div class="col-md-2">
                    <b>Action</b>
                </div>
            </div>
        </div>       
         <c:forEach var="item" items="${itemDetails}" varStatus="status">
            <form method="POST" action="${pageContext.request.contextPath}/AddRetur">
                <div>
                    <div class="row" style="padding: 10px; text-align: center">
                        <div class="col-md-2">
                           ${item.productName}
                        </div>
                        <div class="col-md-2">
                           ${item.quantity}
                        </div>
                        <div class="col-md-2">
                            ${item.quantity * item.price}
                         </div>
                        <div class="col-md-2">
                            <label>Quantity</label>
                            <input type="text" name="quantity" style="width: 35px" value=""/>
                        </div>
                        <div class="col-md-3">
                            <input type="hidden" name="itemId" value="${item.id}"/>
                            <input type="hidden" name="returId" value="${returId}"/>
                            <button class="btn btn-primary" type="submit">Return Item</button>
                        </div>                        
                    </div>
                </div>
            </form>
        </c:forEach>  
        <div style="padding-left: 100px">
            <div class="row" style="padding: 10px">
                <div style="display: flex">
                    <div class="col-md-6"><a class="btn btn-danger" href="${pageContext.request.contextPath}/FinishRetur?id=${returId}" role="button">Finish Return</a></div>
                </div>
            </div>
        </div>
    </div>
</t:pageTemplate>