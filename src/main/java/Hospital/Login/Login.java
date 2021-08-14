package Hospital.Login;

import Hospital.Database;
import Hospital.Start;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    static Connection connection;

    static {
        try {
            connection = Database.DbConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static PreparedStatement ps;
    static ResultSet rs;
    Scanner scanner = new Scanner(System.in);


    public Login() throws SQLException {
    }

    public static boolean SignInNewPatients() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter patient's name: ");
        String name = scanner.next();

        System.out.println("Please enter patient's lastName: ");
        String lastName = scanner.next();

        System.out.println("Please enter patient's age: ");
        int age = scanner.nextInt();

        System.out.println("Please enter patient's gender (male/female): ");
        String gender = scanner.next();

        PreparedStatement ps;

        try {
            ps = connection.prepareStatement("INSERT INTO patients(name,lastName,age,gender) VALUES('"
                    + name + "', '" +
                    lastName + "', '" +
                    age + "', '" +
                    gender + "')");

            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean signUpNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a preferred username (max 30): ");
        String username = scanner.next();

        System.out.println("Enter a preferred password (max 30): ");
        String password = scanner.next();

        try {
            ps = connection.prepareStatement("INSERT INTO users(username, password, userType)" +
                    "VALUES('" +
                    username.trim() + "', '" +
                    password.trim() + "', '" +
                    "administrator')");
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.next();

        System.out.println("Enter your password: ");
        String password = scanner.next();

        try {
            ps = connection.prepareStatement("SELECT * FROM users WHERE username='" + username.trim() + "'");

            rs = ps.executeQuery();
            String passWordCheck = "";
            while (rs.next()) {
                passWordCheck = rs.getString("password");
            }
            if (password.equals(passWordCheck)) {
                Start.start();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /*public static boolean createUserTable() {
        try {
            ps = connection.prepareStatement("CREATE TABLE users(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "username VARCHAR(30) NOT NULL," +
                    "password VARCHAR(30) NOT NULL," +
                    "userType VARCHAR(30) NOT NULL)");
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }*/

}
