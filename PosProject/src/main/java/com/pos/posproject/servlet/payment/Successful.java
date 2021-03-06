/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet.payment;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.common.PaymentDetails;
import com.pos.posproject.common.SaleDetails;
import com.pos.posproject.ejb.PaymentBean;
import com.pos.posproject.ejb.SealeBean;
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
 * @author upcas
 */
@WebServlet(name = "Successful", urlPatterns = {"/Successful"})
public class Successful extends HttpServlet {

    @Inject
    SealeBean saleBean;
    
    @Inject
    PaymentBean paymentBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Succesful</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Succesful at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int saleId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("saleId", saleId);
        SaleDetails saleDetails = saleBean.getSaleDetails(saleId);
        Double total = saleBean.getTotal(saleId);
        PaymentDetails paymentDetails = paymentBean.findBySaleId(saleId);
        List<LineItemDetails> itemDetails = saleBean.getLineItem(saleId);
        request.setAttribute("saleDetails", saleDetails);
        request.setAttribute("itemDetails", itemDetails);
        request.setAttribute("paymentDetails", paymentDetails);
        request.setAttribute("total", total);        
        request.getRequestDispatcher("/WEB-INF/pages/cashier/payment/successful.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
