package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.CashierDetails;
import com.pos.posproject.ejb.CashierBean;
import com.pos.posproject.ejb.UserBean;
import java.io.IOException;
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
    
    @Inject
    CashierBean cashierBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int cashierId = Integer.parseInt(request.getParameter("id"));
        CashierDetails cashier = cashierBean.findById(cashierId);
        request.setAttribute("cashier", cashier);
        request.getRequestDispatcher("/WEB-INF/pages/cashier/changeStatus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String validation = request.getParameter("validation");
        Integer userId = Integer.parseInt(request.getParameter("cashier_id"));
        CashierDetails cashier = cashierBean.findById(userId);

        if ("INVALID".equals(validation)) {
            cashierBean.updateCashierStatus(userId, false);
            userBean.deleteByUsername(cashier.getUsername());
        } 
        if ("VALID".equals(validation)) {
            cashierBean.makeCashierValid(userId, true);
        }
        
        response.sendRedirect(request.getContextPath() + "/Cashiers");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
