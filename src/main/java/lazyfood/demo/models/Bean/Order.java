package lazyfood.demo.models.Bean;

import java.util.Date;

public class Order {
    private String OrderId;
    private String CustomerId;
    private String ProductId;
    private int Quantity;
    private Date Time;
    private String PhoneNumber;
    private String Address;
    private boolean IsDelivered;

    // public Order(String orderId, String customerId, String productId, int
    // quantity, Date time, String phoneNumber, String address) {
    // OrderId = orderId;
    // CustomerId = customerId;
    // ProductId = productId;
    // Quantity = quantity;
    // Quantity = quantity;
    // Time = time;
    // PhoneNumber = phoneNumber;
    // Address = address;
    // }

    public Order(String orderId, String customerId, String productId, int quantity, Date time) {
        OrderId = orderId;
        CustomerId = customerId;
        ProductId = productId;
        Quantity = quantity;
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

    public String getProductId() {
        return ProductId;
    }

    // public void setProductId(String productId) {
    // ProductId = productId;
    // }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
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
