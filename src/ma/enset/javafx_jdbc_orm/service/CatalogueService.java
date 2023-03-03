package ma.enset.javafx_jdbc_orm.service;

import ma.enset.javafx_jdbc_orm.dao.entities.Category;
import ma.enset.javafx_jdbc_orm.dao.entities.Product;

import java.util.List;

public interface CatalogueService {
    List<Product> getAllProducts();
    List<Product> searchProductByQuery(String query);
    void addProduct(Product p);
    void updateProduct(Product p);
    void deleteProduct(Product p);
    List<Category> getAllCategories();
    void addCategory(Category c);
    void deleteCategory(Category c);

}
