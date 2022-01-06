package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.common.SaleDetails;
import com.pos.posproject.ejb.LineIteamBean;
import com.pos.posproject.ejb.ProductBean;
import com.pos.posproject.ejb.SaleLineItemBean;
import com.pos.posproject.ejb.SealeBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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
@WebServlet(name = "Sale", urlPatterns = {"/Sale"})
public class Sale extends HttpServlet {

    @Inject
    private ProductBean productBean;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Sale</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Sale at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer saleId = Integer.parseInt(request.getParameter("id"));
        List<ProductDetails> products = productBean.getAllProducts();
        Double total = saleBean.getTotal(saleId);
        if (!saleLineItemBean.getSaleLineItems().isEmpty()) {
            Collection<LineItemDetails> lineItemsDetails = saleLineItemBean.getSaleLineItems();
            request.setAttribute("saleItems", lineItemsDetails);
        }
        request.setAttribute("saleId", saleId);
        request.setAttribute("products", products);
        request.setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/pages/cashier/salePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int saleId = Integer.parseInt(request.getParameter("saleId"));
        ProductDetails product = productBean.findById(productId);
        int quantity2 = product.getQuantity();
        if (quantity2 >= quantity) {
            lineItemBean.createLineItem(quantity, product.getId(), saleId);
            SaleDetails sale = saleBean.findById(saleId);
            saleLineItemBean.getSaleLineItems().clear();
            saleLineItemBean.getSaleLineItems().addAll(sale.getLineItems());
        }

        response.sendRedirect(request.getContextPath() + "/Sale?id=" + saleId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
