package ma.enset.javafx_jdbc_orm.dao;

import ma.enset.javafx_jdbc_orm.dao.entities.Category;
import ma.enset.javafx_jdbc_orm.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImp implements CategoryDao{
    @Override
    public Category find(long id) {
        Connection connection = ConnectionDBSingleton.getConnection();
        Category c = new Category();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from CATEGORY where ID=?");
            pstm.setLong(1,id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from CATEGORY");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Category c = new Category();
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));
                categories.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public void save(Category o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into CATEGORY(NAME) values (?)");
            pstm.setString(1,o.getName());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("delete from CATEGORY where ID=?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("update CATEGORY set NAME=? where ID=?");
            pstm.setString(1,o.getName());
            pstm.setLong(2,o.getId());
            pstm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
