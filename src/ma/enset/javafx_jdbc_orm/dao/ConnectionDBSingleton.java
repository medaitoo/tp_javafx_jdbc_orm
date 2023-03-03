package ma.enset.javafx_jdbc_orm.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDBSingleton {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_STOCK","root","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

