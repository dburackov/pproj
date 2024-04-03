package screens;

import entities.PetProfile;
import entities.Tag;
import repositories.PetProfileRepository;
import repositories.TagRepository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.console;

public class PetProfileScreen extends EntityScreen {

    private TagRepository tagRepository;

    public PetProfileScreen () throws IOException {
        super();

        repository = new PetProfileRepository();
        entity = new PetProfile();
        tagRepository = new TagRepository();
    }

    @Override
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

        ((PetProfile)entity).setPetProfileId(repository.create(entity));

        scanner.nextLine();
        console().printf("Enter tags id (enter empty value to stop): \n");
        while (true) {
            String inputValue = scanner.nextLine();
            if (inputValue.isEmpty()) {
                break;
            }
            Long tagId = Long.parseLong(inputValue);
            addTag(tagId);
        }
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
            console().printf("[6] Add Tag\n");
            console().printf("[7] Delete Tag Tag\n");
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
                case 6:
                    addTag();
                    break;
                case 7:
                    deleteTag();
                    break;
                default:
                    return;
            }
        }
    }


    private void addTag(Long tagId) throws SQLException {
        Tag tag = (Tag) tagRepository.getById(tagId);
        ((PetProfileRepository) repository).addTag((PetProfile) entity, tag);
    }
    
    private void deleteTag(Long tagId) throws SQLException {
        Tag tag = (Tag) tagRepository.getById(tagId);
        ((PetProfileRepository) repository).deleteTag((PetProfile) entity, tag);
    }
    
    public void addTag() throws SQLException {
        console().printf("Enter pet profile id: ");
        Long value = scanner.nextLong();
        entity = repository.getById(value);
        console().printf("Enter tag id: ");
        value = scanner.nextLong();
        addTag(value);
    }

    public void deleteTag() throws SQLException {
        console().printf("Enter pet profile id: ");
        Long value = scanner.nextLong();
        entity = repository.getById(value);
        console().printf("Enter tag id: ");
        value = scanner.nextLong();
        deleteTag(value);
    }


}
