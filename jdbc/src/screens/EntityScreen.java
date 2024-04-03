package screens;

import entities.Entity;
import lombok.Setter;
import repositories.Repository;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.console;

public class EntityScreen {
    static protected Scanner scanner;

    @Setter
    protected Repository repository;

    @Setter
    protected Entity entity;

    public EntityScreen() {

    }

    protected void readAll() throws SQLException {
        List<Entity> entities = repository.readAll();
        for (Field field : entity.getClass().getDeclaredFields()) {
            console().printf("%-16s", field.getName());
        }
        console().printf("\n");
        for (Entity currentEntity : entities) {
            console().printf(currentEntity.toString());
            console().printf("\n");
        }
    }

    protected void getById() throws SQLException {
        console().printf("ID: ");
        long id = scanner.nextLong();
        entity = repository.getById(id);
        for (Field field : entity.getClass().getDeclaredFields()) {
            console().printf("%-16s", field.getName());
        }
        console().printf("\n" + entity.toString() + "\n");
    }

    protected void create() throws IllegalAccessException, SQLException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().toLowerCase().contains(entity.getClass().getSimpleName().toLowerCase())) {
                console().printf(field.getName() + ": ");
                if (field.getType() == Long.class) {
                    Long value = scanner.nextLong();
                    field.set(entity, value);
                } else if (field.getType() == String.class) {
                    String value = scanner.next();
                    field.set(entity, value);
                }
            }
        }
        repository.create(entity);
    }

    protected void update() throws IllegalAccessException, SQLException {
        console().printf("ID: ");
        Long idValue = scanner.nextLong();
        entity = repository.getById(idValue);
        scanner.nextLine();
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().toLowerCase().contains(entity.getClass().getSimpleName().toLowerCase())) {
                console().printf(field.getName() + ": ");
                String value = scanner.nextLine();
                if (!value.isEmpty()) {
                    if (field.getType() == Long.class) {
                        field.set(entity, Long.parseLong(value));
                    } else if (field.getType() == String.class) {
                        field.set(entity, value);
                    }
                }
            }
        }

        repository.update(entity);
    }

    protected void delete() throws SQLException {
        System.out.print("ID: ");
        Long value = scanner.nextLong();
        repository.delete(value);
    }

    public void display() throws SQLException, IllegalAccessException {
        scanner = new Scanner(System.in);

        while(true) {
            console().printf("\n" + entity.getClass().getSimpleName() + "\n");
            console().printf("Choose operation:\n");
            console().printf("[1] Read all\n");
            console().printf("[2] Get by id\n");
            console().printf("[3] Create\n");
            console().printf("[4] Update\n");
            console().printf("[5] Delete\n");
            console().printf("[0] Back to menu\n");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    readAll();
                    break;
                case 2:
                    getById();
                    break;
                case 3:
                    create();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;

                default:
                    return;
            }
        }
    }
}
