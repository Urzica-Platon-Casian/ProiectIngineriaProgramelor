package com.pos.posproject.servlet.log;

import com.pos.posproject.common.UserDetails;
import com.pos.posproject.ejb.UserBean;
import com.pos.posproject.entity.User;
import com.pos.posproject.util.PasswordUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stupa
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String username = request.getParameter("j_username");
//        String password = request.getParameter("j_password");
//        PasswordUtil passwordUtil = null;
//        String shaPassword = passwordUtil.convertToSha256(password);
//        User user;
//        try{
//            user = userBean.checkLogin(username, shaPassword);
//            if(user != null)
//            {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//            }
//            else
//            {
//                String message = "Somthing went wrong. You don`t have a valid accont, or you have an invalid email or password";
//                request.setAttribute("message",message);
//                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
//            }            
//        }
//        catch (IOException | ServletException ex)
//        {
//            throw new ServletException(ex);
//        }      
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
