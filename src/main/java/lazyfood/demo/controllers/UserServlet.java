package lazyfood.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lazyfood.demo.models.BO.UserBO;
import lazyfood.demo.models.Bean.User;

@WebServlet(urlPatterns = { "/register" })
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    private UserBO userBO;

    @Override
    public void init() throws ServletException {
        userBO = new UserBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userid = "ctm" + ID_GENERATOR.getAndIncrement();
        String username = request.getParameter("usernameS");
        String password = request.getParameter("passwordS");
        String role = "Customer";
        String fullname = request.getParameter("name");
        String phonenumber = request.getParameter("phone");
        String address = request.getParameter("addr");

        User user = new User(userid, username, password, role, fullname, phonenumber, address);

        try {
            userBO.addUser(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            request.setAttribute("error", "Username already exists");
            try {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (Exception e1) {
                e1.printStackTrace();
                NotFoundErrorPage(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            InternalServerErrorPage(request, response);
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void ShowErrorPage(HttpServletRequest req, HttpServletResponse resp, String errorCode) {
        try {
            req.getRequestDispatcher("/Error/Error" + errorCode + ".jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void NotFoundErrorPage(HttpServletRequest req, HttpServletResponse resp) {
        ShowErrorPage(req, resp, "404");
    }

    // private void UnauthorizedErrorPage(HttpServletRequest req,
    // HttpServletResponse resp) {
    // ShowErrorPage(req, resp, "401");
    // }

    private void InternalServerErrorPage(HttpServletRequest req, HttpServletResponse resp) {
        ShowErrorPage(req, resp, "500");
    }
}
