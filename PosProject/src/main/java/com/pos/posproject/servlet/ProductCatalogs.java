/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet;

import com.pos.posproject.common.ProductCatalogDetails;
import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.ejb.InvoiceBean;
import com.pos.posproject.ejb.ProductCatalogBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 * @author Monica
 */

//@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"AdminRole", "ClientRole"}))

@WebServlet(name = "ProductCatalogs", urlPatterns = {"/ProductCatalogs"})
public class ProductCatalogs extends HttpServlet {

    @Inject
    ProductCatalogBean productCatalogBean;
    
    @Inject
    InvoiceBean invoiceBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("activePage", "ProductCatalogs");
        
        List<ProductCatalogDetails> productCatalogs = productCatalogBean.getAllProductCatalogs();
        request.setAttribute("productCatalogs", productCatalogs);
        
       if(!invoiceBean.getProductCatalogIds().isEmpty()){
            
        Collection<String> productCatalogNames= productCatalogBean.findProductCatalogNames(invoiceBean.getProductCatalogIds());
        request.setAttribute("invoices", productCatalogNames);
        }
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
            
            invoiceBean.getProductCatalogIds().addAll(productCatalogIds);
        }
        response.sendRedirect(request.getContextPath() + "/ProductCatalogs");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
