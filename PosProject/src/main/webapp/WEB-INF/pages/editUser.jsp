<%-- 
    Document   : editUser
    Created on : 03.01.2022, 23:11:42
    Author     : stupa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit user">
    <h1>Edit User</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/Users/Update">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="first_name">First name</label>
                <input type="text" class="form-control" id="first_name" name="first_name" placeholder="" value="${user.firstName}" required>
                <div class="invalid-feedback">
                    First name is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="last_name">Last name</label>
                <input type="text" class="form-control" id="last_name" name="last_name" placeholder="" value="${user.lastName}" required>
                <div class="invalid-feedback">
                    Last name is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="last_name">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="" value="${user.username}" required>
                <div class="invalid-feedback">
                    Username is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="position">Position</label>
                <select class="custom-select d-block w-100" id="position" name="position" required>
                    <option value="">Choose...</option>
                    <c:forEach items="${roles}" var="item" varStatus="status">
                        <option ${user.position eq item ? 'selected' : ' '}> ${item} </option>
                    </c:forEach>
                </select>    
                <div class="invalid-feedback">
                    Please select a position.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="last_name">Validation</label>
                <select class="custom-select d-block w-100" id="validation" name="validation" required>
                    <option value="">Choose...</option>
                    <c:choose>
                        <c:when test="${user.validation eq true}">
                            <option selected>VALID</option>
                            <option>INVALID</option>
                        </c:when>
                        <c:when test="${user.validation eq false}">
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
        <input type="hidden" name="user_id" value="${user.id}" />
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

