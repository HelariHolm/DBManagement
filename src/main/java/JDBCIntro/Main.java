package JDBCIntro;

import JDBCIntro.login.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Connection to the database...");
        Connection connection = Database.DbConn();
        System.out.println("Database connected successfully");

        /*try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE students(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(30) NOT NULL, " +
                    "lastName VARCHAR(30) NOT NULL, " +
                    "age INT NOT NULL, " +
                    "gender VARCHAR(20) NOT NULL)");
            System.out.println("Table successfully created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        /*List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Jimmy", "Shaw", 21, "male"));
        studentList.add(new Student("John", "Carfuncle", 22, "male"));
        studentList.add(new Student("Sarah", "Baker", 34, "female"));
        studentList.add(new Student("George", "Ezra", 56, "male"));
        studentList.add(new Student("Jean-Claude", "Van Damme", 9, "male"));
        studentList.add(new Student("Mikey", "Mouse", 41, "male"));
        studentList.add(new Student("Minney", "Mouse", 18, "female"));
        studentList.add(new Student("Tony", "Stark", 88, "male"));
        studentList.add(new Student("Jim", "Carrey", 100, "male"));
        studentList.add(new Student("Mercedes", "Benz", 76, "female"));

        for (Student student : studentList) {
            //ctrl + alt + t = surrount with try/catch
            try {
                Statement stmt = connection.createStatement();
                stmt.execute("INSERT INTO students(name,lastname,age,gender) VALUES(" +
                        "'" + student.getName() + "', '"
                        + student.getLastName() + "', '"
                        + student.getAge() + "', '"
                        + student.getGender() + "')");
                System.out.println("Student successfully created");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("All students successfully created");
        }*/

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();

        /*try {
            ps = connection.prepareStatement("SELECT * FROM students");
            rs = ps.executeQuery(); //will enable to execute and return results.

            System.out.println("id\t Name\t lastName\t age\t gender\t");

            while (rs.next()) {
                int id, age;
                String name, lastName, gender;
                id = rs.getInt("id");
                name = rs.getString("name");
                lastName = rs.getString("lastName");
                age = rs.getInt("age");
                gender = rs.getString("gender");

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setLastName(lastName);
                student.setAge(age);
                student.setGender(gender);

                students.add(student);

                //System.out.println(students);

                Double averageAge = students
                        .stream()
                        .collect(Collectors.averagingInt(Student::getAge));

                //System.out.println(averageAge);

                System.out.println("The average age of students is: " + getAverageAge(students));

                //System.out.println(id + "\t" + name + "\t" + lastName + "\t" + age + "\t" + gender + "\t");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        //task 1-1
        /*try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE subjects(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "math INT, " +
                    "history INT, " +
                    "physics INT, " +
                    "chemistry INT, " +
                    "biology INT, " +
                    "geography INT, " +
                    "arts INT, " +
                    "geometry INT, " +
                    "sports INT, " +
                    "literature INT, " +
                    "student_id INT, " +
                    "FOREIGN KEY (student_id) REFERENCES students(id))");
            System.out.println("Table successfully created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        Login login = new Login();
        Login.loginUser();

        //System.out.println(login.SignUpNewStudents() ? "New student added!" : "Didn't work!");

        /*Student studentValue = StudentController.getStudentById();
        System.out.println("The student is: " + studentValue.getName() + " "
                + studentValue.getLastName() + ", age: "
                + studentValue.getAge() + ", "
                + studentValue.getGender());*/

        //System.out.println(Login.createUserTable() ? "Table created!" : "Table creation failed!");

        //System.out.println(Login.signUpNewUser() ? "User is created!" : "User creation failed!");

        //System.out.println(Login.loginUser() ? "Login successful!" : "Login failed!");

        //Task 1: create a table in db with 10 subjects as fields and add a column called student_id foreign key for that database.
        //In studentController add methods to add a score, delete a score.
        //Add methods to add, edit and delete a student's details.

        //StudentController.addScore();
        //System.out.println(StudentController.editScore() ? "Edit succeeded!" : "Editing failed!");
        //System.out.println(StudentController.deleteScore() ? "Deletion successful!" : "Deletion failed!");
        //System.out.println(StudentController.addStudentsDetails() ? "Adding was successful!" : "Adding failed!");
        //System.out.println(StudentController.editStudentDetails() ? "Editing was successful!" : "Editing failed!");
        //System.out.println(StudentController.deleteStudentDetails() ? "Deletion was successful!" : "Deletion failed!");
    }

    public static float getAverageAge(List<Student> students) {
        float sum = 0;
        for (int i = 0; i < students.size(); i++) {
            sum += students.get(i).getAge();
        }
        return sum/students.size();
    }
}
