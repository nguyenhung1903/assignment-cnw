package lazyfood.demo.models.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import lazyfood.demo.models.Bean.Product;
import lazyfood.demo.models.DAO.ProductDAO;

public class ProductBO {
    private ProductDAO productDAO;

    public ProductBO() {
        productDAO = new ProductDAO();
    }

    public ArrayList<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    public Product getProductById(String id) throws SQLException {
        return productDAO.getProductById(id);
    }

    public void addProduct(Product product) throws SQLException,  IOException{
        productDAO.addProduct(product);
    }

    public void updateProduct(Product product) throws SQLException, IOException {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(String id) throws SQLException {
        productDAO.deleteProduct(id);
    }
}
