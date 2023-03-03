package ma.enset.javafx_jdbc_orm.dao;

import ma.enset.javafx_jdbc_orm.dao.entities.Product;

import java.util.List;

public interface ProductDao extends Dao<Product>{
    List<Product> findbyQuery(String query);

}
