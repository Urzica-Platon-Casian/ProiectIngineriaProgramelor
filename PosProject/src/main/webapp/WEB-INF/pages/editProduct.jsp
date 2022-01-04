<%-- 
    Document   : editProduct
    Created on : Jan 2, 2022, 5:18:55 PM
    Author     : Monica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit Product">
    <h1>Edit Product</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/Products/Update">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="barcode"> Barcode </label>
                <input type="text" class="form-control" id="barcode" name="barcode" placeholder="Barcode" value="${product.barcode}">
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="name"> Name </label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Name" required value="${product.name}">
                <div class="invalid-feedback">
                    Name is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description"> Description </label>
                <input type="text" class="form-control" id="description" name="description" placeholder="Description" required value="${product.description}">
                <div class="invalid-feedback">
                    Description is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description"> Price </label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required value="${product.price}">
                <div class="invalid-feedback">
                    Price is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description"> Category </label>
                <input type="text" class="form-control" id="category" name="category" placeholder="Category" required value="${product.category}">
                <div class="invalid-feedback">
                    Category is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description"> Quantity </label>
                <input type="text" class="form-control" id="quantity" name="quantity" placeholder="Quantity" required value="${product.quantity}">
                <div class="invalid-feedback">
                    Quantity is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="productCatalog_id"> Catalog </label>
                <select class="form-select" id="productCatalog_id" name="productCatalog_id" required>
                    <option value=""> Choose... </option> 
                    <c:forEach var="productCatalog" items="${productCatalogs}" varStatus="status">
                        <option value="${productCatalog.id}" ${product.productCatalogName eq productCatalog.productCatalogName  ? 'selected':' '}>${productCatalog.productCatalogName} </option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please select a catalog name.
                </div>
            </div>
        </div>

        <hr class="my-4">
        <input type="hidden" name="product_id" value="${product.id}" />
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

