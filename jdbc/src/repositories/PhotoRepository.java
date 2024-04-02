package repositories;

import entities.Entity;
import entities.Match;
import entities.Photo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoRepository extends Repository {
    public PhotoRepository() throws IOException {
        super();

        sequenceName = "photos_photo_id_seq";
    }

    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> photos = new ArrayList<>();

        String sql ="""
                    SELECT * FROM photos;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            photos.add(new Photo(resultSet.getLong("photo_id"),
                    resultSet.getLong("pet_profile_id"),
                    resultSet.getString("file_path")));
        }

        close();
        return photos;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        Photo photo = new Photo();

        String sql = String.format("""
                    SELECT *
                    FROM photos
                    WHERE photo_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            photo.setPhotoId(resultSet.getLong("photo_id"));
            photo.setPetProfileId(resultSet.getLong("pet_profile_id"));
            photo.setFilePath(resultSet.getString("file_path"));
        }

        close();
        return photo;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        Photo photo = (Photo) entity;

        String sql = String.format("""
               INSERT INTO photos
                (pet_profile_id, file_path)
                VALUES
                (%d, '%s');
                """, photo.getPetProfileId(), photo.getFilePath());

        statement.executeUpdate(sql);
        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        open();

        Photo photo = (Photo) entity;

        String sql = String.format("""
                    UPDATE photos
                    SET file_path = '%s'
                    WHERE photo_id = %d;
                    """, photo.getFilePath(), photo.getPhotoId());

        statement.executeUpdate(sql);

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM photos
                    WHERE photo_id = %d
                    """, id);

        statement.executeUpdate(sql);

        close();
    }
}
