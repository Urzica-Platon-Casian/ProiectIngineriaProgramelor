/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet;

import com.pos.posproject.common.ProductCatalogDetails;
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
@WebServlet(name = "ProductCatalogs", urlPatterns = {"/ProductCatalogs"})
public class ProductCatalogs extends HttpServlet {

    @Inject
    ProductCatalogBean productCatalogBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("activePage", "ProductCatalogs");

        List<ProductCatalogDetails> productCatalogs = productCatalogBean.getAllProductCatalogs();
        request.setAttribute("productCatalogs", productCatalogs);
        request.getRequestDispatcher("/WEB-INF/pages/productCatalogs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] productCatalogIdsAsString = request.getParameterValues("productCatalog_ids");
        if (productCatalogIdsAsString != null) {
            //   Set<Integer> productCatalogIds=new HashSet<Integer>();
            List<Integer> productCatalogIds = new ArrayList<>();
            for (String productCatalogIdAsString : productCatalogIdsAsString) {
                productCatalogIds.add(Integer.parseInt(productCatalogIdAsString));// productCatalogIds.add(Integer.parseInt(productCatalogIdAsString));
            }
        }
        response.sendRedirect(request.getContextPath() + "/ProductCatalogs");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
