package com.pos.posproject.servlet.cashier;

import com.pos.posproject.common.LineItemDetails;
import com.pos.posproject.ejb.ReturBean;
import com.pos.posproject.ejb.SealeBean;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Monica
 */
@WebServlet(name = "AddRetur", urlPatterns = {"/AddRetur"})
public class AddRetur extends HttpServlet {

    @Inject
    private SealeBean saleBean;

    @Inject
    private ReturBean returBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer returId = Integer.parseInt(request.getParameter("returId"));
        Integer saleId = returBean.getSaleId(returId);
        List<LineItemDetails> itemDetails = saleBean.getLineItem(saleId);
        request.setAttribute("itemDetails", itemDetails);
        request.setAttribute("returId", returId);
        
        request.getRequestDispatcher("/WEB-INF/pages/cashier/addRetur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int returId = Integer.parseInt(request.getParameter("returId"));
        saleBean.setNewQuantity(itemId, quantity);
        returBean.addReturItem(itemId, quantity, returId);
        response.sendRedirect(request.getContextPath() + "/AddRetur?returId=" + returId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
