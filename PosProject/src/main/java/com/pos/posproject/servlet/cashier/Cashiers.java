/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.CashierDetails;
import com.pos.posproject.ejb.CashierBean;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Rori
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"AdminRole", "DGRole"}))
@WebServlet(name = "Cashiers", urlPatterns = {"/Cashiers"})
public class Cashiers extends HttpServlet {
    
    @Inject
    private CashierBean cashierBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("activePage", "Cashiers");
        List<CashierDetails> cashiers = cashierBean.getAllCashiers();
        request.setAttribute("cashiers", cashiers);
        request.getRequestDispatcher("/WEB-INF/pages/cashier/cashiers.jsp").forward(request, response);
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
