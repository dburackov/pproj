
import screens.UserScreen;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static java.lang.System.console;
import static java.lang.System.in;


public class Main {

    private static void displayMainMenu() throws SQLException, IOException {

        while (true) {
            System.out.printf("\033[2J");
            System.out.printf("adfs\n");
//            console().printf("Choose table:\n");
//            console().printf("[1] User\n");
//            console().printf("[2] Pet Profile\n");
//            console().printf("[3] Passport\n");
//            console().printf("[4] Photo\n");
//            console().printf("[5] Social Media\n");
//            console().printf("[6] Tag\n");
//            console().printf("[7] Like\n");
//            console().printf("[8] Match\n");
//            console().printf("[9] Viewed Profile\n");
//            console().printf("Type other number to end\n");

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

    public static void main(String[] args) throws SQLException, IOException {
        displayMainMenu();
    }
}