package lazyfood.demo.models.Bean;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String OrderId;
    private String CustomerId;
    private String CustomerName;
    private ArrayList<ProductInOrder> Products;
    private Date Time;
    private String PhoneNumber;
    private String Address;
    private boolean IsDelivered;

    public Order(String orderId, String customerId, String customerName, ArrayList<ProductInOrder> products, Date time,
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

    public Order(String orderId, String customerId, String customerName, ArrayList<ProductInOrder> products, Date time,
            String phoneNumber,
            String address) {
        OrderId = orderId;
        CustomerId = customerId;
        CustomerName = customerName;
        Products = products;
        Time = time;
        PhoneNumber = phoneNumber;
        Address = address;
        IsDelivered = false;
    }

    public Order(String orderId, String customerId, String customerName, ArrayList<ProductInOrder> products,
            Date time) {
        OrderId = orderId;
        CustomerId = customerId;
        CustomerName = customerName;
        Products = products;
        Time = time;
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

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
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
