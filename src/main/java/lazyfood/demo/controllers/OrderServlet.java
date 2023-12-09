package lazyfood.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lazyfood.demo.models.BO.OrderBO;
import lazyfood.demo.models.Bean.Order;

@WebServlet(urlPatterns = {
        "/Order",
        "/Order/view",
        "/Order/create",
        "/Order/delete",
        "/Admin/Order",
        "/Admin/Order/view",
        "/Admin/Order/update",
})
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderBO orderBO;

    @Override
    public void init() throws ServletException {
        orderBO = new OrderBO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        String id = req.getParameter("OrderId");

        switch (action) {
            case "/Order":

                break;
            case "/Order/view":

                break;
            case "/Order/create":

                break;
            case "/Order/delete":

                break;
            case "/Admin/Order":
                ShowAllOrders(req, resp);
                break;
            case "/Admin/Order/view":
                if (id != null)
                    ShowDetailsOrder(req, resp, id);
                else
                    ShowAllOrders(req, resp);
                break;
            case "/Admin/Order/update":

                break;
            default:
                NotFoundErrorPage(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void ShowAllOrders(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<Order> orders = null;
        try {
            orders = orderBO.getAllOrders();
        } catch (SQLException e) {
            InternalServerErrorPage(req, resp);
            return;
        }

        req.setAttribute("orders", orders);
        try {
            String role = (String) req.getSession().getAttribute("role");
            if (role == null)
                req.getRequestDispatcher("/Customer/Product/index.jsp").forward(req, resp); // TODO: Replace path
            else if (role.equals("customer"))
                req.getRequestDispatcher("/Customer/Product/index.jsp").forward(req, resp); // TODO: Replace path
            else if (role.equals("admin"))
                req.getRequestDispatcher("/Admin/Order/index.jsp").forward(req, resp); // TODO: Replace path
        } catch (Exception e) {
            NotFoundErrorPage(req, resp);
        }
    }

    private void ShowDetailsOrder(HttpServletRequest req, HttpServletResponse resp, String id) {

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

    private void UnauthorizedErrorPage(HttpServletRequest req, HttpServletResponse resp) {
        ShowErrorPage(req, resp, "401");
    }

    private void InternalServerErrorPage(HttpServletRequest req, HttpServletResponse resp) {
        ShowErrorPage(req, resp, "500");
    }
}
