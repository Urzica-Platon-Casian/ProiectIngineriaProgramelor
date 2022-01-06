package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.SaleDetails;
import com.pos.posproject.ejb.LineIteamBean;
import com.pos.posproject.ejb.SaleLineItemBean;
import com.pos.posproject.ejb.SealeBean;
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
@WebServlet(name = "DeleteProductFromSale", urlPatterns = {"/DeleteProductFromSale"})
public class DeleteProductFromSale extends HttpServlet {

    @Inject
    private SaleLineItemBean saleLineItemBean;
    
    @Inject
    private LineIteamBean lineItemBean;
    
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
            out.println("<title>Servlet DeleteProductFromSale</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteProductFromSale at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int saleItemId = Integer.parseInt(request.getParameter("saleItemId"));
        int saleId = Integer.parseInt(request.getParameter("saleId"));
        lineItemBean.deleteLineItemById(saleItemId);
        SaleDetails sale = saleBean.findById(saleId);
        saleLineItemBean.getSaleLineItems().clear();
        saleLineItemBean.getSaleLineItems().addAll(sale.getLineItems());
        
        response.sendRedirect(request.getContextPath() + "/Sale?id=" + saleId);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
