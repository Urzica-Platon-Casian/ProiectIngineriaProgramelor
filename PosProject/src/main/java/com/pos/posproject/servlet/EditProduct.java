/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet;

import com.pos.posproject.common.ProductCatalogDetails;
import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.ejb.ProductBean;
import com.pos.posproject.ejb.ProductCatalogBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Monica
 */
// @ServletSecurity(value = @HttpConstraint(rolesAllowed = {"AdminRole"}))  pt permisiuni
@WebServlet(name = "EditProduct", urlPatterns = {"/Products/Update"})
public class EditProduct extends HttpServlet {

    @Inject
    ProductBean productBean;
    @Inject
    ProductCatalogBean productCatalogBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductCatalogDetails> productCatalogs = productCatalogBean.getAllProductCatalogs();
        request.setAttribute("productCatalogs", productCatalogs);
        int productId = Integer.parseInt(request.getParameter("id"));
        ProductDetails product = productBean.findById(productId);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/pages/editProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String barcode = request.getParameter("barcode");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String category = request.getParameter("category");
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int productCatalogId = Integer.parseInt(request.getParameter("productCatalog_id"));
        productBean.updateProduct(productId, barcode, name, description, Double.valueOf(price), category, productCatalogId);
        response.sendRedirect(request.getContextPath() + "/Products");
    }

    @Override
    public String getServletInfo() {
        return "EditProduct v1.0";
    }
}
