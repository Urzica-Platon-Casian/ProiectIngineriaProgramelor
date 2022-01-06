package com.pos.posproject.servlet.payment;

import com.pos.posproject.common.PaymentDetails;
import com.pos.posproject.ejb.LineIteamBean;
import com.pos.posproject.ejb.PayByCashBean;
import com.pos.posproject.ejb.PaymentBean;
import com.pos.posproject.ejb.ProductBean;
import com.pos.posproject.ejb.SaleLineItemBean;
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
@WebServlet(name = "PayByCash", urlPatterns = {"/PayByCash"})
public class PayByCash extends HttpServlet {

    @Inject
    private PaymentBean paymentBean;

    @Inject
    private SaleLineItemBean saleLineItemBean;

    @Inject
    private PayByCashBean payByCashBean;

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
            out.println("<title>Servlet PayByCash</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PayByCash at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer saleId = Integer.parseInt(request.getParameter("id"));
        Double total = saleBean.getTotal(saleId);
        PaymentDetails paymentDetails = paymentBean.findBySaleId(saleId);
        Boolean saleStatus = false;
        if(paymentDetails != null)
        {
            saleStatus = saleBean.getStatus(saleId);
            request.setAttribute("amount", paymentDetails.getAmount());
            request.setAttribute("change", paymentDetails.getAmount()-paymentDetails.getTotal());
        }
        request.setAttribute("status", saleStatus);
        request.setAttribute("saleId", saleId);
        request.setAttribute("total", total);
        
        request.getRequestDispatcher("/WEB-INF/pages/cashier/payment/payByCash.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer saleId = Integer.parseInt(request.getParameter("saleId"));
        String amount = request.getParameter("amount");
        Double total = saleBean.getTotal(saleId);
        Integer paymentId = paymentBean.createPayment(saleId,total,Double.valueOf(amount),PaymentMethods.CASH);
        
        PaymentDetails paymentDetails = paymentBean.findById(paymentId);
        payByCashBean.createPayByCash(paymentDetails.getAmount()-paymentDetails.getTotal(), paymentId); 
        saleBean.updateSaleStatus(saleId);
        response.sendRedirect(request.getContextPath() + "/PayByCash?id=" + saleId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
