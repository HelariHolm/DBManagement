package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection DbConnect() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/hospital",
                    "root", "helari"
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
