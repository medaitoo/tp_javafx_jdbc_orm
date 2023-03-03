package ma.enset.javafx_jdbc_orm.service;

import ma.enset.javafx_jdbc_orm.dao.CategoryDaoImp;
import ma.enset.javafx_jdbc_orm.dao.ProductDaoImpl;
import ma.enset.javafx_jdbc_orm.dao.entities.Category;
import ma.enset.javafx_jdbc_orm.dao.entities.Product;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        CatalogueService catService = new CatalogueServiceImpl(new ProductDaoImpl(),new CategoryDaoImp());
        Category c1 = new Category();
        c1.setName("Gaming");
        c1.setId(1);

        Product p1 = new Product();
        p1.setName("MSI");
        p1.setReference("REF0002");
        p1.setPrice(23000);
        p1.setCategory(c1);
        /*catService.addProduct(p1);*/
        List<Product> productList = catService.getAllProducts();
        for (Product p:productList) {
            System.out.println("ID :"+p.getId()+" Name :"+p.getName()+" Price"+p.getPrice());
        }
    }
}
