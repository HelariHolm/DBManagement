package JDBCIntro.login;

import JDBCIntro.Database;
import JDBCIntro.Menu;
import JDBCIntro.Student;
import com.sun.source.tree.WhileLoopTree;

import java.sql.*;
import java.util.Scanner;

public class Login {
    static Connection connection;

    static {
        try {
            connection = Database.DbConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static PreparedStatement ps;
    static ResultSet rs;
    Scanner scanner = new Scanner(System.in);


    public Login() throws SQLException {
    }

    public static boolean SignUpNewStudents() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter student's name: ");
        String name = scanner.next();

        System.out.println("Please enter student's lastName: ");
        String lastName = scanner.next();

        System.out.println("Please enter student's age: ");
        int age = scanner.nextInt();

        System.out.println("Please enter student's gender (male/female): ");
        String gender = scanner.next();

        PreparedStatement ps;

        try {
            ps = connection.prepareStatement("INSERT INTO students(name,lastName,age,gender) VALUES('"
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
                    "teacher')");
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
                Menu.menu();
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
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }*/

    /*public Student getStudentById() {
        System.out.println("Enter the students ID: ");
        int studentId = scanner.nextInt();
        Student student = new Student();

        try {
            ps = connection.prepareStatement("SELECT * FROM students WHERE id = '" + studentId + "'");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id, age;
                String name, lastName, gender;

                id = rs.getInt("id");
                age = rs.getInt("age");
                name = rs.getString("name");
                lastName = rs.getString("lastName");
                gender = rs.getString("gender");

                student.setId(id);
                student.setAge(age);
                student.setName(name);
                student.setLastName(lastName);
                student.setGender(gender);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;

    }*/
}
