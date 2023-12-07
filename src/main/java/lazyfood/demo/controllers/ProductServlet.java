package lazyfood.demo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lazyfood.demo.models.BO.ProductBO;
import lazyfood.demo.models.Bean.Product;

@WebServlet(urlPatterns = {
        "/Product",
        "/Product/view",
        "/Product/create",
        "/Product/update",
        "/Product/delete",
        "/api/Product/getAllProductIds"
})
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductBO productBO;

    @Override
    public void init() throws ServletException {
        productBO = new ProductBO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        String id = req.getParameter("id");
        switch (action) {

            // api
            case "/api/Product/getAllProductIds":
                getAllProductIds(req, resp);
                break;

            case "/Product":
                ShowAllProducts(req, resp);
                break;
            case "/Product/view":
                if (id != null)
                    ShowDetailsProduct(req, resp, id);
                else
                    ShowAllProducts(req, resp);
                break;
            case "/Product/create":
                ShowCreateForm(req, resp);
                break;
            case "/Product/update":
                ShowUpdateForm(req, resp, id);
                break;
            case "/Product/delete":
                DeleteItem(req, resp, id);
                break;
            default:
                ShowErrorPage(req, resp, "404");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        String id = req.getParameter("id");
        switch (action) {
            case "/Product/create":
                CreateItem(req, resp);
                break;
            case "/Product/update":
                UpdateItem(req, resp, id);
                break;
            case "/Product/delete":
                DeleteItem(req, resp, id);
                break;
            default:
                ShowErrorPage(req, resp, "404");
                break;
        }
    }

    private void getAllProductIds(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<Product> products = null;
        try {
            products = productBO.getAllProducts();
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(products));
            out.flush();
        } catch (SQLException | IOException e) {
            resp.setContentType("application/json");
            try {
                PrintWriter out = resp.getWriter();
                out.print("{\"error\": \"500\"}");
                out.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void ShowAllProducts(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<Product> products = null;
        try {
            products = productBO.getAllProducts();
        } catch (SQLException e) {
            ShowErrorPage(req, resp, "500");
            return;
        }

        req.setAttribute("products", products);
        try {
            String role = (String) req.getSession().getAttribute("role");
            if (role == null)
                req.getRequestDispatcher("/Customer/Product/index.jsp").forward(req, resp);
            else if (role.equals("customer"))
                req.getRequestDispatcher("/Customer/Product/index.jsp").forward(req, resp);
            else if (role.equals("admin"))
                req.getRequestDispatcher("/Admin/Product/index.jsp").forward(req, resp);

        } catch (Exception e) {
            ShowErrorPage(req, resp, "500");
        }
    }

    private void ShowDetailsProduct(HttpServletRequest req, HttpServletResponse resp, String id) {
    }

    private void ShowCreateForm(HttpServletRequest req, HttpServletResponse resp) {
        String role = (String) req.getSession().getAttribute("role");
        if (role == null) {
            ShowErrorPage(req, resp, "401");
        } else if (role.equals("admin")) {
            try {
                req.getRequestDispatcher("/Admin/Product/create.jsp").forward(req, resp);
            } catch (Exception e) {
                ShowErrorPage(req, resp, "500");
            }
        } else {
            ShowErrorPage(req, resp, "401");
        }
    }

    private void CreateItem(HttpServletRequest req, HttpServletResponse resp) {
        String role = (String) req.getSession().getAttribute("role");
        if (role == null) {
            ShowErrorPage(req, resp, "401");
        } else if (role.equals("admin")) {
            String id = req.getParameter("ProductId");
            String name = req.getParameter("Name");
            String cid = req.getParameter("CategoryId");
            Double price = Double.parseDouble(req.getParameter("Price"));
            try {
                productBO.addProduct(new Product(id, name, cid, price, null));
            }
            catch (SQLException e) {
                ShowErrorPage(req, resp, "500");
            }
            catch (IOException e) {
                ShowErrorPage(req, resp, "500");
            }
        }
    }

    private void ShowUpdateForm(HttpServletRequest req, HttpServletResponse resp, String id) {
        String role = (String) req.getSession().getAttribute("role");
        if (role == null) {
            ShowErrorPage(req, resp, "401");
        } else if (role.equals("admin")) {
            try {
                Product product = productBO.getProductById(id);
                req.setAttribute("product", product);
                req.getRequestDispatcher("/Admin/Product/update.jsp").forward(req, resp);
            } catch (Exception e) {
                ShowErrorPage(req, resp, "500");
            }
        } else {
            ShowErrorPage(req, resp, "401");
        }
    }

    private void UpdateItem(HttpServletRequest req, HttpServletResponse resp, String id) {
        String role = (String) req.getSession().getAttribute("role");
        if (role == null) {
            ShowErrorPage(req, resp, "401");
        } else if (role.equals("admin")) {
            String name = req.getParameter("Name");
            String cid = req.getParameter("CategoryId");
            Double price = Double.parseDouble(req.getParameter("Price"));
            try {
                productBO.updateProduct(new Product(id, name, cid, price, null));
            } catch (SQLException | IOException e) {
                ShowErrorPage(req, resp, "500");
            }
        }
    }

    private void DeleteItem(HttpServletRequest req, HttpServletResponse resp, String id) {
String role = (String) req.getSession().getAttribute("role");
        if (role == null) {
            ShowErrorPage(req, resp, "401");
        } else if (role.equals("admin")) {
            try {
                productBO.deleteProduct(id);
            } catch (SQLException e) {
                ShowErrorPage(req, resp, "500");
            }
        }
    }

    private void ShowErrorPage(HttpServletRequest req, HttpServletResponse resp, String errorCode) {

    }

}
