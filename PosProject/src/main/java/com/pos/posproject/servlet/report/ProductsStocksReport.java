package com.pos.posproject.servlet.report;

import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.ejb.ProductBean;
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
@WebServlet(name = "ProductsStocksReport", urlPatterns = {"/ProductsStocksReport"})
public class ProductsStocksReport extends HttpServlet {

    @Inject
    private ProductBean productBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductsStocksReport</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsStocksReport at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer stock = Integer.parseInt(request.getParameter("stock"));
        List<ProductDetails> products = productBean.getAllProductsForStockReport(stock);
        request.setAttribute("stockValue", stock);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/pages/raports/stocks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stock = request.getParameter("stock");
        response.sendRedirect(request.getContextPath() + "/ProductsStocksReport?stock=" + stock);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
