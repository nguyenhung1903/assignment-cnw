package lazyfood.demo.models.Bean;

public class ProductInOrder {
    private Product _Product;
    private int Quantity;

    public ProductInOrder(Product product, int quantity) {
        _Product = product;
        Quantity = quantity;
    }

    public Product getProduct() {
        return _Product;
    }

    public void setProduct(Product _Product) {
        this._Product = _Product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

}
