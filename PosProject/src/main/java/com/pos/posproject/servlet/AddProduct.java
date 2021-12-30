/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet;

import com.pos.posproject.common.ProductCatalogDetails;
import com.pos.posproject.ejb.ProductBean;
import com.pos.posproject.ejb.ProductCatalogBean;
import java.io.IOException;
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
@WebServlet(name = "AddProduct", urlPatterns = {"/Products/Create"})
public class AddProduct extends HttpServlet {

    @Inject
    ProductBean productBean;

    @Inject
    ProductCatalogBean productCatalogBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ProductCatalogDetails> productCatalogs = productCatalogBean.getAllProductCatalogs();
        request.setAttribute("productCatalogs", productCatalogs);

        request.getRequestDispatcher("/WEB-INF/pages/addProduct.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String barcode = request.getParameter("barcode");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String category = request.getParameter("category");
        int productCatalogId = Integer.parseInt(request.getParameter("productCatalog_id"));

        productBean.createProduct(barcode, name, description, Double.valueOf(price), category, productCatalogId);
        response.sendRedirect(request.getContextPath() + "/Products");
    }

    @Override
    public String getServletInfo() {
        return "AddProduct v1.0";
    }

}
