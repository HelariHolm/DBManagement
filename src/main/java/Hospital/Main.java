package Hospital;

import Hospital.Login.Login;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Connection to the database...");
        Connection connection = Database.DbConnect();
        System.out.println("Database connected successfully");

        /*try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE patients(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(30) NOT NULL, " +
                    "lastName VARCHAR(30) NOT NULL, " +
                    "age INT NOT NULL, " +
                    "gender VARCHAR(20) NOT NULL)");
            System.out.println("Table successfully created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        /* List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient("Isiah", "Devlin", 21, "male"));
        patientList.add(new Patient("Gareth", "Harmon", 22, "male"));
        patientList.add(new Patient("Keaton", "Adam", 34, "male"));
        patientList.add(new Patient("Courtney", "Hutchinson", 56, "female"));
        patientList.add(new Patient("Teddy", "Murphy", 9, "female"));
        patientList.add(new Patient("Alexa", "Perez", 41, "female"));
        patientList.add(new Patient("Mischa", "Connor", 18, "female"));
        patientList.add(new Patient("Nel", "Hurley", 88, "male"));
        patientList.add(new Patient("Rodney", "Eastwood", 100, "male"));
        patientList.add(new Patient("Ann", "Kennedy", 76, "female"));

        for (Patient patient : patientList) {
            //ctrl + alt + t = surround with try/catch
            try {
                Statement stmt = connection.createStatement();
                stmt.execute("INSERT INTO patients(name,lastname,age,gender) VALUES(" +
                        "'" + patient.getName() + "', '"
                        + patient.getLastName() + "', '"
                        + patient.getAge() + "', '"
                        + patient.getGender() + "')");
                System.out.println("Patient successfully created");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("All students successfully created");
        }*/

        /*try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE medical_history(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "diagnosis VARCHAR(100), " +
                    "daysToSignOut INT, " +
                    "nurse VARCHAR(30), " +
                    "doctor VARCHAR(30), " +
                    "patient_id INT, " +
                    "FOREIGN KEY (patient_id) REFERENCES patients(id))");
            System.out.println("Table successfully created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        Login login = new Login();
        Login.loginUser();

        //System.out.println(Login.createUserTable() ? "All good!" : "No success!");
        //System.out.println(Login.signUpNewUser() ? "All good!" : "No success!");

        //Add patient diagnosis
        //System.out.println(PatientReception.addMedicalHistory() ? "All good!" : "No success!");

        //Edit patient diagnosis
        //System.out.println(PatientReception.editMedicalHistory() ? "Edit successful" : "No success");

        //Delete patient diagnosis
        //System.out.println(PatientReception.deleteMedicalHistory() ? "Deletion succeeded" : "Deletion failed");

        //Add patients details
        //System.out.println(PatientReception.addPatient() ? "Adding was successful" : "Adding failed");

        //Edit patients details
        //System.out.println(PatientReception.editPatientDetails() ? "Editing succeeded" : "Editing failed");

        //Delete patient's details
        //System.out.println(PatientReception.deletePatientDetails() ? "Deletion succeeded" : "Deletion failed");

    }
}
