package Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientReception {

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

    public PatientReception() {
    }

    public static Patient getPatientById() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the patient's ID: ");
        int patientId = scanner.nextInt();
        Patient patient = new Patient();

        try {
            ps = connection.prepareStatement("SELECT * FROM patients WHERE id = '" + patientId + "'");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id, age;
                String name, lastName, gender;

                id = rs.getInt("id");
                age = rs.getInt("age");
                name = rs.getString("name");
                lastName = rs.getString("lastName");
                gender = rs.getString("gender");

                patient.setId(id);
                patient.setAge(age);
                patient.setName(name);
                patient.setLastName(lastName);
                patient.setGender(gender);

                System.out.println(patient.getName() + " " + patient.getLastName() + " "
                        + patient.getAge() + " " + patient.getGender());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;

    }

    public static boolean addMedicalHistory() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the patient's ID: ");
        int patient_id = scanner.nextInt();

        System.out.println("Enter the diagnosis: ");
        String diagnosis = scanner.next();

        System.out.println("Enter the number of days until patient gets signed out of the hospital: ");
        int daysToSignOut = scanner.nextInt();

        System.out.println("Enter the name of patient's nurse: ");
        String nurse = scanner.next();

        System.out.println("Enter the name of patients doctor: ");
        String doctor = scanner.next();

        try {
            ps = connection.prepareStatement("INSERT INTO medical_history(diagnosis," +
                    "daysToSignOut,nurse,doctor,patient_id) " +
                    "VALUES('" + diagnosis + "', '" + daysToSignOut + "', '" +
                    nurse + "', '" + doctor + "', '" + patient_id + "')");
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean editMedicalHistory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the patient's id whose medical history is about to be edited: ");
        int patient_id = scanner.nextInt();
        System.out.println("Available data to edit is:");
        System.out.println("diagnosis \ndaysToSignOut \nnurse \ndoctor");
        System.out.print("Please write which data would you like to edit: ");
        String column = scanner.next();
        System.out.print("Please enter new data for " + column + ": ");
        String newData = scanner.next();

        try{
            String updateSubjectQuery = "UPDATE medical_history SET " + column + " = '" + newData + "' WHERE patient_id = " + patient_id;
            PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
            preparedStatement.executeUpdate();

            return true;
        }catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }

    public static boolean deleteMedicalHistory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the patient's id whose diagnosis will be deleted: ");
        int patient_id = scanner.nextInt();

        try{
            String updateSubjectQuery = "DELETE FROM medical_history WHERE patient_id = " + patient_id;
            PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
            preparedStatement.executeUpdate();

            return true;
        }catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }

    public static boolean addPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter new patient's name: ");
        String name = scanner.next();
        System.out.println("Please enter new patient's last name: ");
        String lastName = scanner.next();
        System.out.println("Please enter new patient's age: ");
        int age = scanner.nextInt();
        System.out.println("Please enter new patient's gender: ");
        String gender = scanner.next();

        try{
            ps = connection.prepareStatement( "INSERT INTO patients(name,lastName,age,gender) VALUES('"
                    + name + "', '" + lastName + "', '" + age + "', '" + gender + "')");


            ps.execute();

            return true;
        }catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }
    public static boolean editPatientDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the patient's id whose data needs to be edited: ");
        int patient_id = scanner.nextInt();
        System.out.println("Data types available: ");
        System.out.println("name \nlastName \nage \ngender");
        System.out.print("Write the data type that you want to edit: ");
        String input = scanner.next();
        int age = 0;
        String data = "";
        if (input.equals("age")) {
            System.out.println("What is the new value for patient's age: ");
            age = scanner.nextInt();
        } else {
            System.out.println("What is the new value for patient's " + input + ": ");
            data = scanner.next();
        }

        try {
            String updateSubjectQuery = "";
            if (input.equals("age")) {
                updateSubjectQuery = "UPDATE patients SET age = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
                preparedStatement.setInt(1, age);
                preparedStatement.setInt(2, patient_id);
                preparedStatement.executeUpdate();

                return true;
            } else {
                updateSubjectQuery = "UPDATE patients SET " + input + " = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
                preparedStatement.setString(1, data);
                preparedStatement.setInt(2, patient_id);
                preparedStatement.executeUpdate();

                return true;
            }

        } catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }

    public static boolean deletePatientDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the patient's id whose data needs to be deleted: ");
        int patient_id = scanner.nextInt();
        System.out.println("Do you wish to delete 'all' data or 'specific' data?");
        String option = scanner.next();

        try {

            if (option.equals("all")) {
                String updateSubjectQuery = "DELETE FROM patients WHERE id = " + patient_id;
            } else {
                System.out.println("Data types that can be deleted: ");
                System.out.println("name \nlastName \nage \ngender");
                System.out.print("Write the data type that you want to delete: ");
                String type = scanner.next();

                String updateSubjectQuery = "UPDATE patients SET " + type + " = NULL WHERE id = " + patient_id;
                PreparedStatement preparedStatement = connection.prepareStatement(updateSubjectQuery);
                preparedStatement.executeUpdate();
            }


            return true;

        } catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }


}
