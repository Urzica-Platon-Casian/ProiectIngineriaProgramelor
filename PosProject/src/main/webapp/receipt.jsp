<%-- 
    Document   : payment
    Created on : Dec 27, 2021, 10:48:22 PM
    Author     : upcas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Payment">
    <h1 style="text-align: center">Customers Receipt</h1>
    <h2>Receipt:</h2>
    <div>
        <form>
            <input type="text" placeholder="Search Barcode" style="border-radius: 15px"/>
            <input type="submit" value="Search" style="border-radius: 15px"/>
        </form>
    </div>
    <div style="padding-top: 10px">
        <a href="${pageContext.request.contextPath}/AddProducts"><button style="border-radius: 15px">Add products</button></a>
    </div>
    <div style="padding-top: 10px">
        <a href="${pageContext.request.contextPath}/Payment"><button style="border-radius: 15px">Choose Payment</button></a>
    </div>
</t:pageTemplate>
