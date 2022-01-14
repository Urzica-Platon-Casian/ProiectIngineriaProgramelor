<%-- 
    Document   : returPage
    Created on : Jan 13, 2022, 9:19:26 PM
    Author     : Monica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Retur">
    <c:if test="${message !=null}">
        <div class="alert-warning" role="alert">
            ${message}
        </div>
    </c:if>
  <form class="needs-validation" nonvalidate method="POST" action="${pageContext.request.contextPath}/ReturAction">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="saleId">Sale Id</label>
                <input type="text" class="form-control" id="saleId" name="saleId" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Sale Id is required.
                </div>
            </div>
        </div>
         <button class="btn  btn-lg btn-primary btn-block" type="submit"> Verify </button>
    </form>
</t:pageTemplate>
