package Hospital;

import JDBCIntro.StudentController;

import java.util.Scanner;

public class Start {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an activity: ");
        System.out.println("1. Get a patient by ID");
        System.out.println("2. Add a new patient");
        System.out.println("3. Edit a patient's details");
        System.out.println("4. Delete a patient");
        System.out.println("5. Add patient's medical history");
        System.out.println("6. Edit a patient's medical history");
        System.out.println("7. Delete a patient's medical history");

        System.out.println("Select an option by number: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                PatientReception.getPatientById();
                break;
            case 2:
                PatientReception.addPatient();
                break;
            case 3:
                PatientReception.editPatientDetails();
                break;
            case 4:
                PatientReception.deletePatientDetails();
                break;
            case 5:
                PatientReception.addMedicalHistory();
                break;
            case 6:
                PatientReception.editMedicalHistory();
                break;
            case 7:
                PatientReception.deleteMedicalHistory();
                break;
            default:
                System.out.println("Option unavailable");
                break;
        }

    }
}

