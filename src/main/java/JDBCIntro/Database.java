package JDBCIntro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection DbConn() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/jdbc",
                    "root", "helari"
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }




}
