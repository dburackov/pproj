package repositories;

import entities.Entity;
import entities.PetProfile;
import entities.Tag;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PetProfileRepository extends Repository {
    public PetProfileRepository() throws IOException {
        super();

        sequenceName = "pet_profiles_pet_profile_id_seq";
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
            Long petProfileId = resultSet.getLong("pet_profile_id");
            petProfiles.add(new PetProfile(petProfileId,
                    resultSet.getLong("user_id"),
                    resultSet.getString("purpose"),
                    null));
        }

        for (Entity petProfile : petProfiles) {
            ((PetProfile) petProfile).setTags(getTags(((PetProfile) petProfile).getPetProfileId()));
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
            petProfile.setPurpose(resultSet.getString("purpose"));
        }

        petProfile.setTags(getTags(id));

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

        if (petProfile.getTags() != null) {
            sql = """
             INSERT INTO pet_xref_tag
             (pet_profile_id, tag_id)
             """;

            boolean ok = false;
            for (Long tagId : petProfile.getTags()) {
                sql = sql.concat(String.format("(%d, %d),", petProfile.getPetProfileId(), tagId));
                ok = true;
            }

            if (ok) {
                sql = sql.substring(0, sql.length() - 1) + ";";
            }

            statement.executeQuery(sql);
        }

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

        updateTags(petProfile);

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM pet_profiles
                    WHERE pet_profile_id = %d;
                    """, id);

        //tags delete by trigger

        statement.executeUpdate(sql);

        close();
    }

    private List<Long> getTags(Long petProfileId) throws SQLException {
        List<Long> tags = new ArrayList<>(0);
        String sql = String.format("""
                SELECT tags.tag_id FROM pet_profiles
                JOIN pet_xref_tag ON pet_profiles.pet_profile_id = pet_xref_tag.pet_profile_id
                JOIN tags ON pet_xref_tag.tag_id = tags.tag_id
                WHERE pet_profiles.pet_profile_id = %d;
                """, petProfileId);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            tags.add(resultSet.getLong("tag_id"));
        }

        return tags;
    }

    private void updateTags(PetProfile petProfile) throws SQLException {

        String sql = String.format("""
                SELECT tags.tag_id FROM pet_profiles
                JOIN pet_xref_tag ON pet_profiles.pet_profile_id = pet_xref_tag.pet_profile_id
                JOIN tags ON pet_xref_tag.tag_id = tags.tag_id
                WHERE pet_profiles.pet_profile_id = %d;
                """, petProfile.getPetProfileId());

        TreeSet<Long> currentTags = new TreeSet<>();
        TreeSet<Long> updatedTags = new TreeSet<>(petProfile.getTags());

        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Long tagId = resultSet.getLong("tag_id");
            currentTags.add(tagId);
            if (!updatedTags.contains(tagId)) {
                deleteTag(petProfile, tagId);
            }
        }

        for (Long tagId : petProfile.getTags()) {
            if (!currentTags.contains(tagId)) {
                addTag(petProfile, tagId);
            }
        }
    }

    public void addTag(PetProfile petProfile, Long tagId) throws SQLException {
        open();

        String sql = String.format("""
                INSERT INTO pet_xref_tag
                (pet_profile_id, tag_id)
                VALUES
                (%d, %d);
                """, petProfile.getPetProfileId(), tagId);

        statement.executeUpdate(sql);

        if (petProfile.getTags() == null) {
            petProfile.setTags(new ArrayList<>());
        } else {
            petProfile.getTags().add(tagId);
        }
        close();
    }

    public void deleteTag(PetProfile petProfile, Long tagId) throws SQLException {
        open();

        String sql = String.format("""
                DELETE FROM pet_xref_tag
                WHERE pet_profile_id = %d AND tag_id = %d;
                """, petProfile.getPetProfileId(), tagId);

        statement.executeUpdate(sql);

        if (petProfile.getTags() == null) {
            petProfile.setTags(new ArrayList<>());
        } else {
            petProfile.getTags().remove(tagId);
        }

        close();
    }

}
