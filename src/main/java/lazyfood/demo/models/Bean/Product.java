package lazyfood.demo.models.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.util.Base64;


public class Product {
    private String ProductId;
    private String ProductName;
    private String CategoryId;
    private String CategoryName;
    private double Price;
    private boolean IsAvailable;

    private String Image;

    public Product(String productId, String productName, String categoryId, String categoryName, double price,
            boolean isAvailable, String image) {
        this.ProductId = productId;
        this.ProductName = productName;
        this.CategoryId = categoryId;
        this.CategoryName = categoryName;
        this.Price = price;
        this.IsAvailable = isAvailable;
        this.Image = image;
    }

    public Product(String productId, String productName, String categoryId, double price, String image) {
        this.ProductId = productId;
        this.ProductName = productName;
        this.CategoryId = categoryId;
        this.Price = price;
        this.IsAvailable = true;
        this.Image = image;
    }

    public String getProductId() {
        return ProductId;
    }

    // public void setProductId(String productId) {
    // ProductId = productId;
    // }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        IsAvailable = available;
    }
    public String getImage() {

        return Image;
    }
    public void setImageUrl(String image) {
        this.Image = image;
    }
}
