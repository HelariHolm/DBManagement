package JDBCIntro;

import JDBCIntro.login.Login;

import java.util.Scanner;

public class Menu {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an activity: ");
        System.out.println("1. Get a student by ID");
        System.out.println("2. Add a new student");
        System.out.println("3. Edit a student's details");
        System.out.println("4. Delete a student");
        System.out.println("5. Add a new score");
        System.out.println("6. Edit a score");
        System.out.println("7. Delete a student's score");

        System.out.println("Select an option by number: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                StudentController.getStudentById();
                break;
            case 2:
                Login.SignUpNewStudents();
                break;
            case 3:
                StudentController.editStudentDetails();
                break;
            case 4:
                break;
            case 5:
                StudentController.addScore();
                break;
            case 6:
                StudentController.editScore();
                break;
            case 7:
                StudentController.deleteStudentDetails();
                break;
            default:
                System.out.println("Option unavailable");
                break;
        }

    }
}
