/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet;

import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.ejb.ProductBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author upcas
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"AdminRole", "DGRole"}))
@WebServlet(name = "AllProducts", urlPatterns = {"/Products"})
public class Products extends HttpServlet {

    
    @Inject
    private ProductBean productBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductDetails> products = productBean.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] productIdsAsString = request.getParameterValues("product_ids");
        if (productIdsAsString != null) {
            List<Integer> productIds = new ArrayList<>();
            for (String productIdAsString : productIdsAsString) {
                productIds.add(Integer.parseInt(productIdAsString));
            }
            productBean.deleteProductsByIds(productIds);
        }
        response.sendRedirect(request.getContextPath() + "/Products");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
