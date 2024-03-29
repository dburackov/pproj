package databaserepos;

import entities.Photo;
import entities.User;

import java.sql.SQLException;

public class PhotoRepository extends DBController {
    public PhotoRepository() throws SQLException, ClassNotFoundException {
        super();

        sequence = "photos_photo_id_seq";
        tableName = "photos";
        idFieldName = "photo_id";
    }

    public int create(Photo photo) throws SQLException {
        int lastInsertedId = 0;
        open();

        String sql = String.format("""
                    INSERT INTO photos
                    (pet_profile_id, file_link)
                    VALUES
                    (%d, '%s');
                    """, photo.pet_profile_id, photo.file_link);

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    public void read_all() throws SQLException {
        open();

        String sql = """
                    SELECT * FROM photos;
                    """;

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            String photo_id = resultSet.getString("photo_id");
            String pet_profile_id = resultSet.getString("pet_profile_id");
            String file_link = resultSet.getString("file_link");
            System.out.println(photo_id + " " + pet_profile_id + " " + file_link);
        }

        close();
    }

    public Photo get(int id) throws SQLException {
        Photo result = new Photo();
        open();

        String sql = String.format("""
                    SELECT *
                    FROM photos
                    WHERE photo = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result.photo_id = resultSet.getInt("photo_id");
            result.pet_profile_id = resultSet.getInt("pet_profile_id");
            result.file_link = resultSet.getString("file_link");
        }

        close();
        return result;
    }
}
