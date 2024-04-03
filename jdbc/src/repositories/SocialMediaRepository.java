package repositories;

import entities.Entity;
import entities.SocialMedia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaRepository extends Repository {
    public SocialMediaRepository() throws IOException {
        super();

        sequenceName = "social_medias_social_media_id_seq";
    }

    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> socialMedias = new ArrayList<>();

        String sql ="""
                    SELECT * FROM social_medias;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            socialMedias.add(new SocialMedia(resultSet.getLong("social_media_id"),
                    resultSet.getLong("pet_profile_id"),
                    resultSet.getString("link"),
                    resultSet.getString("type")));
        }

        close();
        return socialMedias;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        SocialMedia socialMedia = new SocialMedia();

        String sql = String.format("""
                    SELECT *
                    FROM social_medias
                    WHERE social_media_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            socialMedia.setSocialMediaId(resultSet.getLong("social_media_id"));
            socialMedia.setPetProfileId(resultSet.getLong("pet_profile_id"));
            socialMedia.setLink(resultSet.getString("link"));
            socialMedia.setType(resultSet.getString("type"));
        }

        close();
        return socialMedia;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        SocialMedia socialMedia = (SocialMedia) entity;

        String sql = String.format("""
               INSERT INTO social_medias
                (pet_profile_id, link, type)
                VALUES
                (%d, '%s', '%s');
                """, socialMedia.getSocialMediaId(), socialMedia.getLink(), socialMedia.getType());

        statement.executeUpdate(sql);
        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        open();

        SocialMedia socialMedia = (SocialMedia) entity;

        String sql = String.format("""
                    UPDATE social_medias
                    SET link = '%s', type = '%s'
                    WHERE social_media_id = %d;
                    """, socialMedia.getLink(), socialMedia.getType(), socialMedia.getSocialMediaId());

        statement.executeUpdate(sql);

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM social_medias
                    WHERE social_media_id = %d
                    """, id);

        statement.executeUpdate(sql);

        close();
    }

}
