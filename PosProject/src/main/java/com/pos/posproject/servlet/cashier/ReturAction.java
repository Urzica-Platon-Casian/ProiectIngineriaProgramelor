/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.UserDetails;
import com.pos.posproject.ejb.ReturBean;
import com.pos.posproject.ejb.SealeBean;
import com.pos.posproject.ejb.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ReturAction", urlPatterns = {"/ReturAction"})
public class ReturAction extends HttpServlet {

    @Inject
    ReturBean returBean;

    @Inject
    UserBean userBean;
    
    @Inject
    SealeBean saleBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReturAction</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReturAction at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/pages/cashier/returPage.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String saleId = request.getParameter("saleId");
        Integer x=Integer.valueOf(saleId);
        Integer verifiedSaleId=saleBean.findFinishedSaleById(x);
        if(null != verifiedSaleId && saleBean.getStatus(verifiedSaleId)==true){
            String username = request.getUserPrincipal().getName();
            UserDetails user = userBean.findUserByUsername(username);
            Integer returId = returBean.createRetur(user.getId());
            response.sendRedirect(request.getContextPath() + "/ReturAction?id=" + returId); 
           
        }
        else{
        request.setAttribute("message", "Sale Id does not exist");
        request.getRequestDispatcher("/WEB-INF/pages/cashier/returPage.jsp").forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
