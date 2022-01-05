package com.pos.posproject.servlet.user;

import com.pos.posproject.ejb.UserBean;
import com.pos.posproject.enums.UserRoles;
import com.pos.posproject.util.PasswordUtil;
import java.io.IOException;
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
//@ServletSecurity(value=@HttpConstraint(rolesAllowed={"AdminRole"}))
@WebServlet(name = "AddUser", urlPatterns = {"/Users/Create"})
public class AddUser extends HttpServlet {
      @Inject
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
        List<String> roles = new ArrayList<>();
        for (UserRoles user : UserRoles.values())
        {
            roles.add(user.name());
        }
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        String first_name=request.getParameter("first_name");
        String last_name=request.getParameter("last_name");
        String password = request.getParameter("password");
        String position = request.getParameter("position");
        String passwordSha256=PasswordUtil.convertToSha256(password);
        if("ADMIN".equals(position) || "DG".equals(position))
        {
            userBean.createUser(username, first_name,last_name, passwordSha256, position , true);
        }
        else if("CASHIER".equals(position))
        {
            userBean.createUser(username, first_name,last_name, passwordSha256, position, false);
        }
        response.sendRedirect(request.getContextPath()+"/Users");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
