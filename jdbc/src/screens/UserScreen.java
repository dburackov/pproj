package screens;

import entities.Entity;
import repositories.UserRepository;
import entities.User;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.console;

public class UserScreen {
    static public void display() throws IOException, SQLException, IllegalAccessException {
        UserRepository rep = new UserRepository();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("User");
            System.out.println("Choose operation:");
            System.out.println("[1] Read all");
            System.out.println("[2] Get by id");
            System.out.println("[3] Create");
            System.out.println("[4] Update");
            System.out.println("[5] Delete");
            System.out.println("[0] Back to menu");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    List<Entity> users = rep.readAll();
                    System.out.println(String.format("%-16s%-16s%-16s%-16s",
                            "user_id", "name", "email", "password"));
                    for (Entity user : users) {
                        System.out.println(user.toString());
                    }
                    break;
                case 2:
                    System.out.print("Enter id: ");
                    long id = scanner.nextLong();
                    Entity currentUser = rep.getById(id);
                    System.out.println(String.format("%-16s%-16s%-16s%-16s",
                            "user_id", "name", "email", "password"));
                    System.out.println(currentUser.toString());
                    break;
                case 3:
                    Entity createUser = new User();
                    for (Field field : createUser.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        if (!field.getName().equals("userId")) {
                            System.out.print(field.getName() + ": ");
                            if (field.getType() == Long.class) {
                                Long value = scanner.nextLong();
                                field.set(createUser, value);
                            } else if (field.getType() == String.class) {
                                String value = scanner.next();
                                field.set(createUser, value);
                            }
                        }
                    }
                    rep.create(createUser);
                    break;
                case 4:
                    Entity updateUser = new User();
                    for (Field field : updateUser.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        System.out.print(field.getName() + ": ");
                        if (field.getType() == Long.class) {
                            Long value = scanner.nextLong();
                            field.set(updateUser, value);
                        } else if (field.getType() == String.class) {
                            String value = scanner.next();
                            field.set(updateUser, value);
                        }
                    }

                    rep.update(updateUser);
                    break;
                case 5:
                    System.out.print("Enter id: ");
                    Long deleteId = scanner.nextLong();
                    rep.delete(deleteId);
                    break;

                default:
                    return;
            }
        }
    }
}
