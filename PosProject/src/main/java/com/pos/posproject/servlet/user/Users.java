/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.pos.posproject.servlet.user;

import com.pos.posproject.common.UserDetails;
import com.pos.posproject.ejb.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 * @author stupa
 */
//@ServletSecurity(value=@HttpConstraint(rolesAllowed={"AdminRole", "ClientRole"}))
@WebServlet(name = "Users", urlPatterns = {"/Users"})
public class Users extends HttpServlet {
    @Inject
    private UserBean userBean;
    
  

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          out.println("<!DOCTYPE html>");
           out.println("<html>");
            out.println("<head>");
           out.println("<title>Servlet Users</title>");            
            out.println("</head>");
          out.println("<body>");
            out.println("<h1>Servlet Users at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
   }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("activePage", "Users");
        
        List<UserDetails> users = userBean.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request, response);
        
         processRequest(request, response);
       // processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

               String[] userIdsAsString=request.getParameterValues("user_ids");
               if(userIdsAsString !=null){
                   Set<Integer> userIds= new HashSet<Integer>();
                   for(String userIdAsString : userIdsAsString){
                       userIds.add(Integer.parseInt(userIdAsString));
                   }
               }
               response.sendRedirect(request.getContextPath()+"/Users");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
