package lazyfood.demo.models.Bean;

public class Category {
    private String CategoryId;
    private String CategoryName;

    public Category(String id, String name) {
        CategoryId = id;
        CategoryName = name;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    // public void setCategoryId(String categoryId) {
    // CategoryId = categoryId;
    // }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
