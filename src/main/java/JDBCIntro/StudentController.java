package JDBCIntro;

import java.sql.*;
import java.util.Scanner;

public class StudentController {
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

    public StudentController() {
    }

    public static Student getStudentById() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the student's ID: ");
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

                System.out.println(student.getName() + " " + student.getLastName() + " "
                        + student.getAge() + " " + student.getGender());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;

    }

    public static void addScore() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the student's ID: ");
        int student_Id = scanner.nextInt();

        System.out.println("Enter the score for Maths: ");
        int maths = scanner.nextInt();

        System.out.println("Enter the score for History: ");
        int history = scanner.nextInt();

        System.out.println("Enter the score for Physics: ");
        int physics = scanner.nextInt();

        System.out.println("Enter the score for Chemistry: ");
        int chemistry = scanner.nextInt();

        System.out.println("Enter the score for Biology: ");
        int biology = scanner.nextInt();

        System.out.println("Enter the score for Geography: ");
        int geography = scanner.nextInt();

        System.out.println("Enter the score for Arts: ");
        int arts = scanner.nextInt();

        System.out.println("Enter the score for Geometry: ");
        int geometry = scanner.nextInt();

        System.out.println("Enter the score for Sports: ");
        int sports = scanner.nextInt();

        System.out.println("Enter the score for literature: ");
        int literature = scanner.nextInt();

        try {
            ps = connection.prepareStatement("INSERT INTO subjects(math,history,physics,chemistry,biology," +
                    "geography,arts,geometry,sports,literature,student_id) " +
                    "VALUES('" + maths + "', '" + history + "', '" + physics + "', '" + chemistry + "', '" + biology + "', '"
                    + geography + "', '" + arts + "', '" + geometry + "', '" + sports + "', '"+ literature + "', '"+ student_Id + "')");
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean editScore() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the student's id whose score is about to be edited: ");
        int student_id = scanner.nextInt();
        System.out.println("Available subjects are:");
        System.out.println("math \nhistory \nphysics \nchemistry \nbiology \ngeography \narts \ngeometry \nsports \nliterature");
        System.out.print("Write the name of the subject which score you want to edit: ");
        String subject = scanner.next();
        System.out.print("Enter new score: ");
        int score = scanner.nextInt();

        try{
            String updateSubjectQuery = "UPDATE subjects SET " + subject + " = ? WHERE student_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
            preparedStatement.setInt(1, score);
            preparedStatement.setInt(2, student_id);
            preparedStatement.executeUpdate();

            return true;
        }catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }

    public static boolean deleteScore() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the student's id whose score will be deleted: ");
        int student_id = scanner.nextInt();
        System.out.println("Available subjects are:");
        System.out.println("math \nhistory \nphysics \nchemistry \nbiology \ngeography \narts \ngeometry \nsports \nliterature");
        System.out.print("Write the name of the subject which score you want to delete: ");
        String subject = scanner.next();

        try{
            String updateSubjectQuery = "UPDATE subjects SET " + subject + " = NULL WHERE student_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
            preparedStatement.setInt(1, student_id);
            preparedStatement.executeUpdate();

            return true;
        }catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }

    public static boolean addStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter new student's name: ");
        String name = scanner.next();
        System.out.println("Please enter new student's last name: ");
        String lastName = scanner.next();
        System.out.println("Please enter new student's age: ");
        int age = scanner.nextInt();
        System.out.println("Please enter new student's gender: ");
        String gender = scanner.next();
        /*System.out.println("Available subjects are:");
        System.out.println("math \nhistory \nphysics \nchemistry \nbiology \ngeography \narts \ngeometry \nsports \nliterature");
        System.out.print("Write the name of the subject which score you want to delete: ");
        String subject = scanner.next();*/

        try{
            ps = connection.prepareStatement( "INSERT INTO student's(name,lastName,age,gender) VALUES('"
                    + name + "', '" + lastName + "', '" + age + "', '" + gender + "')");


            ps.execute();

            return true;
        }catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }
    public static boolean editStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the student's id whose data needs to be edited: ");
        int student_id = scanner.nextInt();
        System.out.println("Data types available: ");
        System.out.println("name \nlastName \nage \ngender");
        System.out.print("Write the data type that you want to edit: ");
        String input = scanner.next();
        int age = 0;
        String data = "";
        if (input.equals("age")) {
            System.out.println("What is the new value for student's age: ");
            age = scanner.nextInt();
        } else {
            System.out.println("What is the new value for student's " + input + ": ");
            data = scanner.next();
        }

        try {
            String updateSubjectQuery = "";
            if (input.equals("age")) {
                updateSubjectQuery = "UPDATE students SET age = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
                preparedStatement.setInt(1, age);
                preparedStatement.setInt(2, student_id);
                preparedStatement.executeUpdate();

                return true;
            } else {
                updateSubjectQuery = "UPDATE students SET " + input + " = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
                preparedStatement.setString(1, data);
                preparedStatement.setInt(2, student_id);
                preparedStatement.executeUpdate();

                return true;
            }

        } catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }

    public static boolean deleteStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the student's id whose data needs to be deleted: ");
        int student_id = scanner.nextInt();
        System.out.println("Data types that can be deleted: ");
        System.out.println("name \nlastName \nage \ngender");
        System.out.print("Write the data type that you want to delete: ");
        String input = scanner.next();

        try {

            //terve rida delete: "DELETE FROM students WHERE id = ?"
            String updateSubjectQuery = "UPDATE students SET " + input + " = NULL WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
            preparedStatement.setInt(1, student_id);
            preparedStatement.executeUpdate();

            return true;

        } catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }

}
