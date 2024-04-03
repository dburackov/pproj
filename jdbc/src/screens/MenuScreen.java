package screens;

import entities.*;
import repositories.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.console;

public class MenuScreen {
    public static void display() throws SQLException, IOException, IllegalAccessException {
        while (true) {
            console().printf("Choose table:\n");
            console().printf("[1] User\n");
            console().printf("[2] Pet Profile\n");
            console().printf("[3] Passport\n");
            console().printf("[4] Photo\n");
            console().printf("[5] Social Media\n");
            console().printf("[6] Tag\n");
            console().printf("[7] Like\n");
            console().printf("[8] Match\n");
            console().printf("[9] Viewed Profile\n");
            console().printf("[0] Exit\n");

            EntityScreen screen = new EntityScreen();
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    screen.setRepository(new UserRepository());
                    screen.setEntity(new User());
                    screen.display();
                    break;
                case 2:
                    PetProfileScreen petProfileScreen = new PetProfileScreen();
                    petProfileScreen.display();
                    break;
                case 3:
                    screen.setRepository(new PassportRepository());
                    screen.setEntity(new Passport());
                    screen.display();
                    break;
                case 4:
                    screen.setRepository(new PhotoRepository());
                    screen.setEntity(new Photo());
                    screen.display();
                    break;
                case 5:
                    screen.setRepository(new SocialMediaRepository());
                    screen.setEntity(new SocialMedia());
                    screen.display();
                    break;
                case 6:
                    screen.setRepository(new TagRepository());
                    screen.setEntity(new Tag());
                    screen.display();
                    break;
                case 7:
                    screen.setRepository(new LikeRepository());
                    screen.setEntity(new Like());
                    screen.display();
                    break;
                case 8:
                    screen.setRepository(new MatchRepository());
                    screen.setEntity(new Match());
                    screen.display();
                    break;
                case 9:
                    screen.setRepository(new ViewedProfileRepository());
                    screen.setEntity(new ViewedProfile());
                    screen.display();
                    break;
                default:
                    return;
            }


        }
    }
}
