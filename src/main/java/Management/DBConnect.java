package Management;

import java.sql.*;
import java.util.Random;

public class DBConnect {

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sda", "root", "helari");
            //fetchUser(conn, 1);
            //fetchUsers(conn);
            //updateUsername(conn, 2, "Minu");
            //insertNewUser(conn, 7, "James", "May", 1);
            //fetchUserUpdated(conn, 7);
            //getUser(conn, 3);
            //createTableOfUsers(conn);
            //createTableOfAccounts(conn);
            //createUserAccount(conn,78, "Päikese 17, Rakvere", 2500);
            //getAccountBalance(conn, 71066730);
            moneyTransfer(conn, 71066730, 14688296, 1000);
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public static void fetchUser(Connection conn, int id) {
        String fetchUserQuery = "SELECT * FROM Users_table WHERE id = " + id;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(fetchUserQuery);
            while(rs.next()) {
                System.out.println("User " + id + " data is: " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getInt(5));
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public static void fetchUsers(Connection conn) {
        String fetchUsersQuery = "SELECT * FROM Users_table";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(fetchUsersQuery);
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getInt(5));
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
    public static void fetchUserUpdated(Connection conn, int id) {
        String fetchUserQueryUpdated = "SELECT * FROM Users_table WHERE id = " + id;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(fetchUserQueryUpdated);
            while(rs.next()) {
                System.out.println("User " + id + " data is: " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getInt(5));
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public static void updateUsername(Connection conn, int id, String newName) {
        String updateNameQuery = "UPDATE users_table SET name = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updateNameQuery);
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2,id);

            preparedStatement.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public static void insertNewUser(Connection conn, int id, String name, String lastname, int products_bought) {
        String insertUserQuery = "INSERT INTO users_table(id, name, lastname, products_bought) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertUserQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,lastname);
            preparedStatement.setInt(4,products_bought);

            preparedStatement.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    /* public static void getUser(Connection conn, int id) throws SQLException{
        String getUserQuery = "CALL getUser(?)";
        CallableStatement callGetUserProc = conn.prepareCall(getUserQuery);
        callGetUserProc.setInt(1,id);

        ResultSet rs = callGetUserProc.executeQuery();
        while(rs.next()) {
            System.out.println("User is " + rs.getString(2));
        }
        callGetUserProc.close();
    } */

    //exercise
    //create table of users (name,age,address,account_nr(10 digits))
    public static void createTableOfUsers(Connection conn) throws SQLException {
        String createTableOfUsersQuery = "CREATE TABLE users(id INT PRIMARY KEY AUTO_INCREMENT, age INT, address VARCHAR(30), account_nr int)";
        Statement statement = conn.createStatement();
        statement.executeUpdate(createTableOfUsersQuery);
        statement.close();
    }

    //create table of accounts (account_nr, account_balance, user_id (fk pointing users table))
    public static void createTableOfAccounts(Connection conn) throws SQLException {
        String createTableOfAccountsQuery = "CREATE TABLE accounts(id INT PRIMARY KEY AUTO_INCREMENT, account_nr int, account_bal int, user_id INT, FOREIGN KEY (user_id) REFERENCES users(id))";
        Statement statement = conn.createStatement();
        statement.executeUpdate(createTableOfAccountsQuery);
        statement.close();
    }
    //insert user: 1) generate random account_nr 2) call procedure to create user should create new user account aswell (reference to the user).
    public static void createUserAccount(Connection conn, int age, String address, int account_bal) throws SQLException{
        Random rand = new Random();
        int account_nr = rand.nextInt(1000000000);
        String callProc = "CALL create_user_account(?,?,?,?)";
        CallableStatement callableStatement = conn.prepareCall(callProc);
        callableStatement.setInt(1,age);
        callableStatement.setString(2, address);
        callableStatement.setInt(3, account_nr);
        callableStatement.setInt(4, account_bal);

        callableStatement.executeQuery();

    }

    //getAccountBalance of user using id/name/account_nr via stored procedure.
    public static void getAccountBalance(Connection conn, int account_number) throws SQLException{
        String callProc = "CALL get_account_balance(?)";
        CallableStatement callableStatement = conn.prepareCall(callProc);
        callableStatement.setInt(1, account_number);

        ResultSet rs = callableStatement.executeQuery();
        while(rs.next()) {
            System.out.println("Account balance is: " + rs.getInt(1));
        }
        callableStatement.close();

    }

    //transfer from user a to user b via stored procedure that executes a stored procedure transaction.
    public static void moneyTransfer(Connection conn, int sender_account_nr, int receiver_account_nr, int sum) throws  SQLException{
        String callProc = "CALL money_transfer(?,?,?)";
        CallableStatement callableStatement = conn.prepareCall(callProc);
        callableStatement.setInt(1, sender_account_nr);
        callableStatement.setInt(2, receiver_account_nr);
        callableStatement.setInt(3, sum);

        callableStatement.executeQuery();
    }
}
