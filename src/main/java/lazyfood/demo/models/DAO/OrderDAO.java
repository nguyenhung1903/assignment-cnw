package lazyfood.demo.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lazyfood.demo.models.Bean.Order;
import lazyfood.demo.models.Bean.ProductInOrder;

public class OrderDAO {

    public ArrayList<Order> getAllOrders() {

        ArrayList<Order> orders = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {
            String query = "SELECT `order`.OrderId, `order`.CustomerId, user.FullName, `order`.PhoneNumber, `order`.Address, `order`.Time, `order`.IsDelivered FROM `order` INNER JOIN user on `order`.CustomerId = user.UserId";
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                orders.add(new Order(res.getString("OrderId"),
                        res.getString("CustomerId"),
                        res.getString("FullName"),
                        null,
                        res.getDate("Time"),
                        res.getString("PhoneNumber"),
                        res.getString("Address"),
                        res.getBoolean("IsDelivered")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public ArrayList<Order> getOrdersByUser(String userId) {

        ArrayList<Order> orders = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {
            String query = "SELECT `order`.OrderId, `order`.CustomerId, user.FullName, `order`.PhoneNumber, `order`.Address, `order`.Time, `order`.IsDelivered FROM `order`. INNER JOIN user on `order`.CustomerId = user.UserId WHERE UserId = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, userId);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                orders.add(new Order(res.getString("OrderId"),
                        res.getString("CustomerId"),
                        res.getString("FullName"),
                        null,
                        res.getDate("Time"),
                        res.getString("PhoneNumber"),
                        res.getString("Address"),
                        res.getBoolean("IsDelivered")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrderById(String orderId) {
        Order order = null;
        ArrayList<ProductInOrder> products = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {
            ProductDAO productDAO = new ProductDAO();

            String query = "SELECT ProductId, Quantity FROM orderproduct WHERE OrderId = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, orderId);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                products.add(new ProductInOrder(productDAO.getProductById(res.getString("ProductId")),
                        res.getInt("Quantity")));
            }

            String query2 = "SELECT `order`.OrderId, `order`.CustomerId, user.FullName, `order`.PhoneNumber, `order`.Address, `order`.Time, `order`.IsDelivered FROM `order`. INNER JOIN user on `order`.CustomerId = user.UserId WHERE OrderId = ?";
            Statement statement2 = conn.createStatement();
            ResultSet res2 = statement2.executeQuery(query2);
            while (res2.next()) {
                order = new Order(res.getString("OrderId"),
                        res.getString("CustomerId"),
                        res.getString("FullName"),
                        products,
                        res.getDate("Time"),
                        res.getString("PhoneNumber"),
                        res.getString("Address"),
                        res.getBoolean("IsDelivered"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    public void createOrder(Order order) {

    }

    public void setDeliveredState(String orderId, boolean state) {
        try (Connection conn = DBConnector.getConnection()) {

            String query = "UPDATE order SET IsDelivered = ? WHERE OrderId = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setBoolean(1, state);
            statement.setString(2, orderId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
