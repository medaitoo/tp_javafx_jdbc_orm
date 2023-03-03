package ma.enset.javafx_jdbc_orm.dao;

import ma.enset.javafx_jdbc_orm.dao.entities.Category;
import ma.enset.javafx_jdbc_orm.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao{
    @Override
    public Product find(long id) {
        Connection connection = ConnectionDBSingleton.getConnection();
        Product p = new Product();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from PRODUCTS where ID=?");
            pstm.setLong(1,id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setReference(rs.getString("REFERENCE"));
                p.setPrice(rs.getFloat("PRICE"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Product> findAll() {
        //mapping objet relationnel
        List<Product> products = new ArrayList<>();
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from PRODUCTS");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setReference(rs.getString("REFERENCE"));
                p.setPrice(rs.getFloat("PRICE"));
                PreparedStatement pstm1 = connection.prepareStatement("select * from CATEGORY where ID=?");
                pstm1.setLong(1,rs.getLong("ID_CAT"));
                ResultSet rs1 = pstm1.executeQuery();
                Category c = new Category();
                if (rs1.next()){
                    c.setName(rs1.getString("NAME"));
                }
                p.setCategory(c);
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void save(Product o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into PRODUCTS(NAME,REFERENCE,PRICE,ID_CAT) values (?,?,?,?)");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setFloat(3,o.getPrice());
            pstm.setLong(4,o.getCategory().getId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("delete from PRODUCTS where ID=?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("update PRODUCTS set NAME=?,REFERENCE=?,PRICE=?,ID_CAT=? where ID=?");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setFloat(3,o.getPrice());
            pstm.setLong(4,o.getCategory().getId());
            pstm.setLong(5,o.getId());
            pstm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findbyQuery(String query) {
        List<Product> products = new ArrayList<>();
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from PRODUCTS where NAME like ? or REFERENCE like ? or PRICE like ?");
            pstm.setString(1,"%"+query+"%");
            pstm.setString(2,"%"+query+"%");
            pstm.setString(3,"%"+query+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setReference(rs.getString("REFERENCE"));
                p.setPrice(rs.getFloat("PRICE"));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
