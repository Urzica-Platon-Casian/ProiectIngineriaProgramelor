<%-- 
    Document   : addProduct
    Created on : Dec 29, 2021, 8:11:51 PM
    Author     : Monica
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add Product">
    <h1 style="text-align: center; padding-top: 10px; padding-bottom: 10px">Add Product</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/Products/Create">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="barcode"> Barcode </label>
                <input type="text" class="form-control" id="barcode" name="barcode" placeholder="Barcode" value="" required>
                <div class="invalid-feedback">
                    Barcode is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="name"> Name </label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="" required>
                <div class="invalid-feedback">
                    Name is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description"> Description </label>
                <input type="text" class="form-control" id="description" name="description" placeholder="Description" value="" required>
                <div class="invalid-feedback">
                    Description is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description"> Price </label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" value="" required>
                <div class="invalid-feedback">
                    Price is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description"> Category </label>
                <input type="text" class="form-control" id="category" name="category" placeholder="Category" value="" required>
                <div class="invalid-feedback">
                    Category is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description"> Quantity </label>
                <input type="text" class="form-control" id="quantity" name="quantity" placeholder="Quantity" value="" required>
                <div class="invalid-feedback">
                    Quantity is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="productCatalog_id"> Product Catalog </label>
                <select class="form-select" id="productCatalog_id" name="productCatalog_id" required>
                    <option value=""> Choose... </option> 
                    <c:forEach var="productCatalog" items="${productCatalogs}" varStatus="status">
                        <option value="${productCatalog.id}"> ${productCatalog.productCatalogName} </option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please select a Catalog.
                </div>
            </div>
        </div>
        <hr class="my-4">
        <button class="w-100 btn btn-primary btn-lg" type="submit">Save</button>
    </form>
    <script src="/docs/5.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="form-validation.js"></script>
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