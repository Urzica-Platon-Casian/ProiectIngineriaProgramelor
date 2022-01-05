package com.pos.posproject.servlet.user;

import com.pos.posproject.common.UserDetails;
import com.pos.posproject.ejb.UserBean;
import com.pos.posproject.enums.UserRoles;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "EditUser", urlPatterns = {"/Users/Update"})
public class EditUser extends HttpServlet {

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
            out.println("<title>Servlet EditUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        UserDetails user = userBean.findById(userId);
        request.setAttribute("user", user);
        List<String> roles = new ArrayList<>();
        for (UserRoles userRole : UserRoles.values()) {
            roles.add(userRole.name());
        }
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/WEB-INF/pages/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String position = request.getParameter("position");
        String username = request.getParameter("username");
        Integer userId = Integer.parseInt(request.getParameter("user_id"));
        
        userBean.updateUser(userId, firstName, lastName, username, position);
        response.sendRedirect(request.getContextPath() + "/Users");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
