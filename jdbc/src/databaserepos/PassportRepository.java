package databaserepos;

import entities.Kind;
import entities.Passport;

import java.sql.SQLException;
import java.util.Date;

public class PassportRepository extends DBController {
    public PassportRepository() throws SQLException, ClassNotFoundException {
        super();

        sequence = "passport_passport_id_seq";
        tableName = "passports";
        idFieldName = "passport_id";
    }

        public int create(Passport passport) throws SQLException {
            int lastInsertedId;
            open();

            String sql = String.format("""
                        INSERT INTO passports
                        (pet_profile_id, name, birth_date, kind, breed, breeding_certificate, coat, bio)
                        VALUES
                        (%d, '%s', '%s', '%s', '%s', %b, '%s', '%s');
                        """, passport.petProfileId, passport.name, passport.birthDate.toString(),
                    passport.kind.toString(), passport.breed, passport.breedingCertificate, passport.coat, passport.bio);

            statement.executeUpdate(sql);

            lastInsertedId = getLastInsertedId();

            close();
            return lastInsertedId;
        }

    public int update(int id, Passport passport) throws SQLException {
        int rowsUpdated = 0;
        open();

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
                    """, passport.petProfileId, passport.name, passport.birthDate.toString(),
                passport.kind.toString(), passport.breed, passport.breedingCertificate, passport.coat, passport.bio,
                id);

        rowsUpdated = statement.executeUpdate(sql);

        close();
        return rowsUpdated;
    }

    public Passport get(int id) throws SQLException {
        Passport result = new Passport();
        open();

        String sql = String.format("""
                    SELECT *
                    FROM passports
                    WHERE passport_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result.passportId = resultSet.getInt(idFieldName);
            result.petProfileId = resultSet.getInt("pet_profile_id");
            result.name = resultSet.getString("name");
            result.birthDate = new Date(resultSet.getString("birth_date"));
            result.kind = Kind.valueOf(resultSet.getString("kind"));
            result.breed = resultSet.getString("breed");
            result.breedingCertificate = resultSet.getBoolean("breeding_certificate");
            result.breed = resultSet.getString("coat");
            result.breed = resultSet.getString("bio");
        }

        close();
        return result;
    }
}
