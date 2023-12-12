package lazyfood.demo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lazyfood.demo.models.BO.OrderBO;
import lazyfood.demo.models.BO.UserBO;
import lazyfood.demo.models.Bean.Order;
import lazyfood.demo.models.Bean.User;

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
        String role = (String) req.getSession().getAttribute("role");
        String id = req.getParameter("OrderId");

        switch (action) {
            case "/Order":
                ShowAllMyOrders(req, resp);
                break;
            case "/Admin/Order":
                if (role == null)
                    UnauthorizedErrorPage(req, resp);
                else if (!role.equals("admin"))
                    UnauthorizedErrorPage(req, resp);
                else
                    ShowAllOrders(req, resp);
                break;
            case "/Order/view":
                if (id != null)
                    ShowDetailsOrder(req, resp, id);
                else
                    ShowAllMyOrders(req, resp);
                break;
            case "/Order/create":
                if (role == null) {
                    UnauthorizedErrorPage(req, resp);
                } else {
                    ShowOrderComponent(req, resp);
                }
                break;

            case "/Admin/Order/view":
                if (role == null)
                    UnauthorizedErrorPage(req, resp);
                else if (!role.equals("admin"))
                    UnauthorizedErrorPage(req, resp);
                else if (id != null)
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
        String action = req.getServletPath();
        String role = (String) req.getSession().getAttribute("role");

        switch (action) {
            case "/Order/create":
                if (role == null)
                    UnauthorizedErrorPage(req, resp);
                else if (!role.equals("customer"))
                    UnauthorizedErrorPage(req, resp);
                else
                    CreateItem(req, resp);
                break;

            case "/Admin/Order/update":
                if (role == null)
                    UnauthorizedErrorPage(req, resp);
                else if (!role.equals("admin"))
                    UnauthorizedErrorPage(req, resp);
                else
                    ShowAllOrders(req, resp);
                break;

            default:
                NotFoundErrorPage(req, resp);
                break;
        }
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
            req.getRequestDispatcher("/Admin/Order/testindex.jsp").forward(req, resp); // TODO: Replace path
        } catch (Exception e) {
            NotFoundErrorPage(req, resp);
        }
    }

    private void ShowAllMyOrders(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<Order> orders = null;
        String role = (String) req.getSession().getAttribute("role");

        try {
            if (role != null && (role.equals("customer") || role.equals("admin"))) {
                String userid = (String) req.getSession().getAttribute("userid");
                orders = orderBO.getOrdersByUser(userid);
            }
        } catch (SQLException e) {
            InternalServerErrorPage(req, resp);
            return;
        }

        req.setAttribute("orders", orders);

        try {
            req.getRequestDispatcher("/Customer/Order/Orders.jsp").forward(req, resp);
        } catch (Exception e) {
            NotFoundErrorPage(req, resp);
        }
    }

    private void ShowDetailsOrder(HttpServletRequest req, HttpServletResponse resp, String id) {
        Order order = null;
        String role = (String) req.getSession().getAttribute("role");

        if (role == null) {
            NotFoundErrorPage(req, resp);
            return;
        }

        else if (!(role.equals("admin") || role.equals("customer"))) {
            NotFoundErrorPage(req, resp);
            return;
        }

        try {
            order = orderBO.getOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            InternalServerErrorPage(req, resp);
            return;
        }

        if (role.equals("admin")) {
            req.setAttribute("order", order);
            try {
                req.getRequestDispatcher("/Admin/Order/testdetails.jsp").forward(req, resp); // TODO: Replace path
            } catch (Exception e) {
                e.printStackTrace();
                NotFoundErrorPage(req, resp);
            }

        }

        else {
            String userid = (String) req.getSession().getAttribute("userid");
            if (order.getCustomerId().equals(userid)) {
                req.setAttribute("order", order);
                try {
                    req.getRequestDispatcher("/Customer/Order/OrderDetails.jsp").forward(req, resp);
                } catch (Exception e) {
                    NotFoundErrorPage(req, resp);
                }
            }

            else {
                NotFoundErrorPage(req, resp);
                return;
            }
        }
    }

    private void CreateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the request's BufferedReader to read the JSON data
        BufferedReader reader = request.getReader();

        // Use Gson to parse the JSON data into a JsonObject
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        JsonArray cart = jsonObject.getAsJsonArray("cart");
        for (JsonElement item : cart) {
            JsonObject obj = item.getAsJsonObject();
            JsonPrimitive id = obj.getAsJsonPrimitive("ProductId");
            JsonPrimitive quantity = obj.getAsJsonPrimitive("Quantity");
            System.out.println(id.getAsString() + " " + quantity.getAsString());
        }
    }

    private void ShowOrderComponent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String userid = (String) req.getSession().getAttribute("userid");
            User user = (new UserBO()).getUserById(userid);

            req.setAttribute("addr", user.getAddress());
            req.setAttribute("phone", user.getPhoneNumber());

            req.getRequestDispatcher("/Customer/Order/OrderComponent.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            NotFoundErrorPage(req, resp);
        }
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
