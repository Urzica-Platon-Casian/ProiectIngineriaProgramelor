/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.ejb.LineIteamBean;
import com.pos.posproject.ejb.ProductBean;
import com.pos.posproject.ejb.SealeBean;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddRetur", urlPatterns = {"/AddRetur"})
public class AddRetur extends HttpServlet {

    @Inject
    private ProductBean productBean;
        
     @Inject
    private LineIteamBean lineItemBean;
     
     @Inject
    private SealeBean saleBean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddRetur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRetur at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer saleId=Integer.parseInt(request.getParameter("saleId"));
       // Integer returId=Integer.parseInt(request.getParameter("returId"));
        Integer lineItemId = lineItemBean.getLineItemId(saleId);
        LineItemDetails lineItem= lineItemBean.findById(lineItemId);
        List<ProductDetails> lineItemProducts = productBean.getAllProductsFromLineItem(lineItem.getId());
        request.setAttribute("activePage", "Products");
        request.setAttribute("products", lineItemProducts);
        request.getRequestDispatcher("/WEB-INF/pages/cashier/addRetur.jsp").forward(request, response);
      //  List<ProductDetails> returProducts= productBean.getAllProducts();
      //  Double total = saleBean.getTotal(saleId);
       // LineItemDetails returProduct=lineItemBean.findById(returProductId);    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] productIdsAsString = request.getParameterValues("product_ids");
        String lineItemId = request.getParameter("lineItem_id");
        if (productIdsAsString != null) {
            List<Integer> productIds = new ArrayList<>();
            for (String productIdAsString : productIdsAsString) {
                productIds.add(Integer.parseInt(productIdAsString));
            }
            productBean.deleteProductsByIds(productIds);
        }
        response.sendRedirect(request.getContextPath() + "/AddRetur?id=" + lineItemId);
        processRequest(request, response);
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
