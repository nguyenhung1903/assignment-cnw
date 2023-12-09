package lazyfood.demo.models.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import lazyfood.demo.models.Bean.Order;
import lazyfood.demo.models.DAO.OrderDAO;

public class OrderBO {
    private OrderDAO orderDAO;

    public OrderBO() {
        orderDAO = new OrderDAO();
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    public ArrayList<Order> getOrdersByUser(String userId) {
        return orderDAO.getOrdersByUser(userId);
    }

    public Order getOrderById(String orderId) {
        return orderDAO.getOrderById(orderId);
    }

    public void setOrderDelivered(String orderId, boolean state) {
        orderDAO.setDeliveredState(orderId, state);
    }
}
