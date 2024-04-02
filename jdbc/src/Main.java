
import screens.UserScreen;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static java.lang.System.console;
import static java.lang.System.in;


public class Main {

    private static void displayMainMenu() throws SQLException, IOException, IllegalAccessException {

        while (true) {
            System.out.println("Choose table:");
            System.out.println("[1] User");
            System.out.println("[2] Pet Profile");
            System.out.println("[3] Passport");
            System.out.println("[4] Photo");
            System.out.println("[5] Social Media");
            System.out.println("[6] Tag");
            System.out.println("[7] Like");
            System.out.println("[8] Match");
            System.out.println("[9] Viewed Profile");
            System.out.println("[0] Exit");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    UserScreen.display();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                default:
                    return;
            }

        }
    }

    public static void main(String[] args) throws SQLException, IOException, IllegalAccessException {
        displayMainMenu();
    }
}