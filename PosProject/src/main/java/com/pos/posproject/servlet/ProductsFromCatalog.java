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
import java.util.ArrayList;
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
@WebServlet(name = "Products", urlPatterns = {"/ProductsFromCatalog"})
public class ProductsFromCatalog extends HttpServlet {

    @Inject
    private ProductBean productBean;

    @Inject
    private ProductCatalogBean productCatalogBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer productCatalogId = Integer.parseInt(request.getParameter("id"));
        ProductCatalogDetails productCatalog = productCatalogBean.findById(productCatalogId);
        List<ProductDetails> products = productBean.getAllProductsFromCatalog(productCatalog.getId());
        request.setAttribute("activePage", "Products");
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/pages/productsFromCatalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] productIdsAsString = request.getParameterValues("product_ids");
        String productCatalogId = request.getParameter("catalog_id");
        if (productIdsAsString != null) {
            List<Integer> productIds = new ArrayList<>();
            for (String productIdAsString : productIdsAsString) {
                productIds.add(Integer.parseInt(productIdAsString));
            }
            productBean.deleteProductsByIds(productIds);
        }
        response.sendRedirect(request.getContextPath() + "/ProductsFromCatalog?id=" + productCatalogId);
    }

    @Override
    public String getServletInfo() {
        return "Products v1.0";
    }

}
