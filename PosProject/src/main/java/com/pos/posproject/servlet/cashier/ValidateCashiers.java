/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.UserDetails;
import com.pos.posproject.ejb.UserBean;
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
 * @author Rori
 */
@WebServlet(name = "ValidateCashiers", urlPatterns = {"/Cashier/Validate"})
public class ValidateCashiers extends HttpServlet {

    @Inject
    UserBean userBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ValidateCashiers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidateCashiers at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        UserDetails user = userBean.findById(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/cashier/changeStatus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean validationBool = null;
        String validation = request.getParameter("validation");
        Integer userId = Integer.parseInt(request.getParameter("user_id"));

        if ("INVALID".equals(validation)) {
            validationBool = false;
        } else if ("VALID".equals(validation)) {
            validationBool = true;
        }

        userBean.updateCashierStatus(userId, validationBool);
        response.sendRedirect(request.getContextPath() + "/Cashiers");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
