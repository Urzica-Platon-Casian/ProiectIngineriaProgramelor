package com.pos.posproject.servlet.cashier;

import com.pos.posproject.ejb.ReturBean;
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
 * @author stupa
 */
@WebServlet(name = "FinishRetur", urlPatterns = {"/FinishRetur"})
public class FinishRetur extends HttpServlet {

    @Inject
    private ReturBean returBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FinishRetur</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinishRetur at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer returId = Integer.parseInt(request.getParameter("id"));
        returBean.updateReturStatus(returId);
        Double total = returBean.getTotal(returId);
        request.setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/pages/cashier/finishRetur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
