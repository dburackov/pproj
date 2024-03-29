package databaserepos;

import entities.Photo;

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
                    """, photo.petProfileId, photo.fileLink);

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    public void readAll() throws SQLException {
        open();

        String sql = """
                    SELECT * FROM photos;
                    """;

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            String photoId = resultSet.getString(idFieldName);
            String petProfileId = resultSet.getString("pet_profile_id");
            String fileLink = resultSet.getString("file_link");
            System.out.println(photoId + " " + petProfileId + " " + fileLink);
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
            result.photoId = resultSet.getInt(idFieldName);
            result.petProfileId = resultSet.getInt("pet_profile_id");
            result.fileLink = resultSet.getString("file_link");
        }

        close();
        return result;
    }
}
