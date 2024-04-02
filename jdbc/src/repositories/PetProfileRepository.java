package repositories;

import entities.Entity;
import entities.Match;
import entities.PetProfile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetProfileRepository extends Repository {
    public PetProfileRepository() throws IOException {
        super();

        sequenceName = "pet_profile_pet_profile_id_seq";
    }

    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> petProfiles = new ArrayList<>();

        String sql ="""
                    SELECT * FROM pet_profiles;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            petProfiles.add(new PetProfile(resultSet.getLong("pet_profile_id"),
                    resultSet.getLong("user_id"),
                    resultSet.getString("purpose")));
        }

        close();
        return petProfiles;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        PetProfile petProfile = new PetProfile();

        String sql = String.format("""
                    SELECT *
                    FROM pet_profiles
                    WHERE pet_profile_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            petProfile.setPetProfileId(resultSet.getLong("pet_profile_id"));
            petProfile.setUserId(resultSet.getLong("user_id"));
            petProfile.setPurpose(resultSet.getString("second_pet_id"));
        }

        close();
        return petProfile;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        PetProfile petProfile = (PetProfile) entity;

        String sql = String.format("""
               INSERT INTO pet_profiles
                (user_id, purpose)
                VALUES
                (%d, '%s');
                """, petProfile.getUserId(), petProfile.getPurpose());

        statement.executeUpdate(sql);
        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        open();

        PetProfile petProfile = (PetProfile) entity;

        String sql = String.format("""
                    UPDATE pet_profiles
                    SET purpose = '%s'
                    WHERE pet_profile_id = '%d';
                    """, petProfile.getPurpose(), petProfile.getPetProfileId());

        statement.executeUpdate(sql);

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM pet_profiles
                    WHERE pet_profile_id = %d
                    """, id);

        statement.executeUpdate(sql);

        close();
    }

}
