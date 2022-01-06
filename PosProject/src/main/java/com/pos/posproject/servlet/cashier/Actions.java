package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.UserDetails;
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
 * @author Rori
 */
@WebServlet(name = "Actions", urlPatterns = {"/Actions"})
public class Actions extends HttpServlet {

    @Inject
    SealeBean saleBean;

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
            out.println("<title>Servlet CashierActions</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CashierActions at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/cashier/actions.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getUserPrincipal().getName();
        UserDetails user = userBean.findUserByUsername(username);
        Integer saleId = saleBean.createSale(user.getId());
        response.sendRedirect(request.getContextPath() + "/Sale?id=" + saleId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
