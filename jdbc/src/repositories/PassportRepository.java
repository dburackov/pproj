package repositories;

import entities.Entity;
import entities.Passport;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassportRepository extends Repository {
    public PassportRepository() throws IOException {
        super();

        sequenceName = "passports_passport_id_seq";
    }

    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> passports = new ArrayList<>();

        String sql ="""
                    SELECT * FROM passports;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            passports.add(new Passport(resultSet.getLong("passport_id"),
                    resultSet.getLong("pet_profile_id"),
                    resultSet.getString("name"),
                    LocalDate.parse(resultSet.getString("birth_date")),
                    resultSet.getString("kind"),
                    resultSet.getString("breed"),
                    resultSet.getBoolean("breeding_certificate"),
                    resultSet.getString("coat"),
                    resultSet.getString("bio")));
        }

        close();
        return passports;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        Passport passport = new Passport();

        String sql = String.format("""
                    SELECT *
                    FROM passports
                    WHERE passport_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            passport.setPassportId(resultSet.getLong("passport_id"));
            passport.setPetProfileId(resultSet.getLong("pet_profile_id"));
            passport.setName(resultSet.getString("name"));
            passport.setBirthDate(LocalDate.parse(resultSet.getString("birth_date")));
            passport.setKind(resultSet.getString("kind"));
            passport.setBreed(resultSet.getString("breed"));
            passport.setBreedingCertificate(resultSet.getBoolean("breeding_certificate"));
            passport.setCoat(resultSet.getString("coat"));
            passport.setBio(resultSet.getString("bio"));
        }

        close();
        return passport;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        Passport passport = (Passport) entity;

        String sql = String.format("""
                        INSERT INTO passports
                        (pet_profile_id, name, birth_date, kind, breed, breeding_certificate, coat, bio)
                        VALUES
                        (%d, '%s', '%s', '%s', '%s', %b, '%s', '%s');
                        """, passport.getPetProfileId(), passport.getName(), passport.getBirthDate().toString(),
                passport.getKind(), passport.getBreed(), passport.isBreedingCertificate(), passport.getCoat(), passport.getBio());

        statement.executeUpdate(sql);
        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        open();

        Passport passport = (Passport) entity;

        String sql = String.format("""
                    UPDATE passports
                    SET
                    pet_profile_id = %d,
                    name = '%s',
                    birth_date = '%s',
                    kind = '%s',
                    breed = '%s',
                    breeding_certificate = %b,
                    coat = '%s',
                    bio = '%s'
                    WHERE passport_id = %d;
                    """, passport.getPetProfileId(), passport.getName(), passport.getBirthDate().toString(),
                passport.getKind(), passport.getBreed(), passport.isBreedingCertificate(), passport.getCoat(), passport.getBio(),
                passport.getPassportId());

        statement.executeUpdate(sql);

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM passports
                    WHERE passport_id = %d
                    """, id);

        statement.executeUpdate(sql);

        close();
    }
}
