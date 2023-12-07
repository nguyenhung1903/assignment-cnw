package lazyfood.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;

import lazyfood.demo.models.BO.UserBO;
import lazyfood.demo.models.Bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/login", "/logout" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserBO userBO;

    @Override
    public void init() throws ServletException {
        userBO = new UserBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/login")) {
            showLoginForm(request, response);
        } else if (action.equals("/logout")) {
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        if (action.equals("/login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = null;

            try {
                user = userBO.getUserByUsername(username);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (user != null) {
                if (user.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("role", user.getRole());
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("login");
                }
            } else {
                response.sendRedirect("login");
            }
        }

    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/Authentication/login.jsp").forward(request, response);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
