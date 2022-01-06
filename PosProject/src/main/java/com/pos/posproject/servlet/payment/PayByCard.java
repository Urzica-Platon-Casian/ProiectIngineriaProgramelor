package com.pos.posproject.servlet.payment;

import com.pos.posproject.common.PaymentDetails;
import com.pos.posproject.ejb.PayByCardBean;
import com.pos.posproject.ejb.PayByCashBean;
import com.pos.posproject.ejb.PaymentBean;
import com.pos.posproject.ejb.SealeBean;
import com.pos.posproject.enums.PaymentMethods;
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
@WebServlet(name = "PayByCard", urlPatterns = {"/PayByCard"})
public class PayByCard extends HttpServlet {

    @Inject
    private PaymentBean paymentBean;

    @Inject
    private PayByCardBean payByCardBean;

    @Inject
    private SealeBean saleBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PayByCard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PayByCard at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer saleId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("saleId", saleId);

        request.getRequestDispatcher("/WEB-INF/pages/cashier/payment/payByCard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer saleId = Integer.parseInt(request.getParameter("saleId"));
        Double total = saleBean.getTotal(saleId);
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");
        Integer paymentId = paymentBean.createPayment(saleId, total, total, PaymentMethods.CARD);

        payByCardBean.createPayByCard(cardNumber, expiryDate, cvv, paymentId);
        saleBean.updateSaleStatus(saleId);
        response.sendRedirect(request.getContextPath() + "/Sucessful?id=" + saleId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
