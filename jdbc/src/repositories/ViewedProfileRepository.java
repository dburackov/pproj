package repositories;

import entities.Entity;
import entities.ViewedProfile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewedProfileRepository extends Repository {

    public ViewedProfileRepository() throws IOException {
        super();

        sequenceName = "viewed_profiles_viewed_profile_id_seq";
    }

    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> viewedProfiles = new ArrayList<>();

        String sql ="""
                    SELECT * FROM viewed_profiles;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            viewedProfiles.add(new ViewedProfile(resultSet.getLong("viewed_profile_id"),
                    resultSet.getLong("object_pet_id"),
                    resultSet.getLong("target_pet_id")));
        }

        close();
        return viewedProfiles;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        ViewedProfile viewedProfile = new ViewedProfile();

        String sql = String.format("""
                    SELECT *
                    FROM viewed_profiles
                    WHERE viewed_profile_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            viewedProfile.setViewedProfileId(resultSet.getLong("viewed_profile_id"));
            viewedProfile.setObjectPetId(resultSet.getLong("object_pet_id"));
            viewedProfile.setTargetPetId(resultSet.getLong("target_pet_id"));
        }

        close();
        return viewedProfile;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        ViewedProfile viewedProfile = (ViewedProfile) entity;

        String sql = String.format("""
               INSERT INTO viewed_profiles
                (object_pet_id, target_pet_id)
                VALUES
                (%d, %d);
                """, viewedProfile.getObjectPetId(), viewedProfile.getTargetPetId());

        statement.executeUpdate(sql);
        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        //useless
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM viewed_profiles
                    WHERE viewed_profile_id = %d
                    """, id);

        statement.executeUpdate(sql);

        close();
    }

}
