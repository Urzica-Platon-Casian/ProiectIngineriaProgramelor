/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.common.ReturDetails;
import com.pos.posproject.ejb.LineIteamBean;
import com.pos.posproject.ejb.ProductBean;
import com.pos.posproject.ejb.ReturBean;
import com.pos.posproject.ejb.SaleLineItemBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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
@WebServlet(name = "Retur", urlPatterns = {"/Retur"})
public class Retur extends HttpServlet {

    @Inject
    private ProductBean productBean;

    @Inject
    private SaleLineItemBean saleLineItemBean;

    @Inject
    private LineIteamBean lineItemBean;

    @Inject
    private ReturBean returBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Retur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Retur at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer returId = Integer.parseInt(request.getParameter("id"));
        List<ProductDetails> products = productBean.getAllProducts();
        Double total = returBean.getTotal(returId);
        if (!saleLineItemBean.getSaleLineItems().isEmpty()) {
            Collection<LineItemDetails> lineItemsDetails = saleLineItemBean.getSaleLineItems();
            request.setAttribute("returItems", lineItemsDetails);
        }
        request.setAttribute("returId", returId);
        request.setAttribute("products", products);
        request.setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/pages/cashier/returPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int returId = Integer.parseInt(request.getParameter("returId"));
        ProductDetails product = productBean.findById(productId);{
            lineItemBean.createLineItem(quantity, product.getId(), returId);
            ReturDetails retur = returBean.findById(returId);
            saleLineItemBean.getSaleLineItems().clear();
            saleLineItemBean.getSaleLineItems().addAll(retur.getLineItems());
        }

        response.sendRedirect(request.getContextPath() + "/Retur?id=" + returId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
