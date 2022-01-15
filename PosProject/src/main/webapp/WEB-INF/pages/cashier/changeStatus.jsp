<%-- 
    Document   : changeStatus
    Created on : Jan 5, 2022, 8:21:11 PM
    Author     : Rori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Change status">
    <h1>Change Status</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/Cashier/Validate">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="validation">Validation</label>
                <select class="custom-select d-block w-100" id="validation" name="validation" required>
                    <c:choose>
                        <c:when test="${cashier.validation eq true}">
                            <option selected>VALID</option>
                            <option>INVALID</option>
                        </c:when>
                        <c:when test="${cashier.validation eq false}">
                            <option>VALID</option>
                            <option selected>INVALID</option>
                        </c:when>
                    </c:choose>
                </select>    
                <div class="invalid-feedback">
                    Validation is required.
                </div>
            </div>
        </div>
        <hr class="my-4">
        <input type="hidden" name="cashier_id" value="${cashier.id}" />
        <button class="w-100 btn btn-primary btn-lg" type="submit">Save</button>
    </form>
    <script src="/docs/5.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script>
        (function () {
            'use strict';

            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');

                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                })
            }, false);
        }());
    </script>    
</t:pageTemplate>
