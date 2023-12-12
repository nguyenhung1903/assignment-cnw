package lazyfood.demo.models.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private String OrderId;
    private String CustomerId;
    private String CustomerName;
    private ArrayList<ProductInOrder> Products;
    private LocalDateTime Time;
    private String PhoneNumber;
    private String Address;
    private boolean IsDelivered;

    // from database to application
    public Order(String orderId, String customerId, String customerName, ArrayList<ProductInOrder> products,
            LocalDateTime time,
            String phoneNumber,
            String address, boolean isDelivered) {
        OrderId = orderId;
        CustomerId = customerId;
        CustomerName = customerName;
        Products = products;
        Time = time;
        PhoneNumber = phoneNumber;
        Address = address;
        IsDelivered = isDelivered;
    }

    // public Order(String orderId, String customerId, String customerName,
    // ArrayList<ProductInOrder> products, Date time,
    // String phoneNumber,
    // String address) {
    // OrderId = orderId;
    // CustomerId = customerId;
    // CustomerName = customerName;
    // Products = products;
    // Time = time;
    // PhoneNumber = phoneNumber;
    // Address = address;
    // IsDelivered = false;
    // }

    // from application to database
    public Order(String orderId, String customerId, ArrayList<ProductInOrder> products, LocalDateTime time,
            String phonenumber,
            String address) {
        OrderId = orderId;
        CustomerId = customerId;
        Products = products;
        Time = time;
        PhoneNumber = phonenumber;
        Address = address;
        IsDelivered = false;
    }

    public String getOrderId() {
        return OrderId;
    }

    // public void setOrderId(String orderId) {
    // OrderId = orderId;
    // }

    public String getCustomerId() {
        return CustomerId;
    }

    // public void setCustomerId(String customerId) {
    // CustomerId = customerId;
    // }

    public String getCustomerName() {
        return CustomerName;
    }

    // public void setCustomerName(String customerName) {
    // CustomerName = customerName;
    // }

    public ArrayList<ProductInOrder> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<ProductInOrder> products) {
        Products = products;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public void setTime(LocalDateTime time) {
        Time = time;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isDelivered() {
        return IsDelivered;
    }

    public void setIsDelivered(boolean isDelivered) {
        IsDelivered = isDelivered;
    }
}
