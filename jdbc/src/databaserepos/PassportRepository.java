package databaserepos;

import java.sql.SQLException;
import java.util.Date;

public class PassportRepository extends DBController {
    public PassportRepository() throws SQLException, ClassNotFoundException {
        super();
    }

        public int create(Passport passport) {
            int id = -1;
            try {
                open();

                String sql = String.format("""
                        INSERT INTO passport
                        (pet_profile_id, name, birth_date, kind, breed, breeding_certificate, coat, bio)
                        VALUES
                        (%d, '%s', '%s', '%s', '%s', %b, '%s', '%s');
                        """, passport.pet_profile_id, passport.name, passport.birth_date.toString(),
                        passport.kind.toString(), passport.breed, passport.breeding_certificate, passport.coat, passport.bio);

                statement.executeUpdate(sql);

                sql = String.format("""
                    SELECT currval('%s');
                    """, Passport.sequence);

                resultSet = statement.executeQuery(sql);

                while (resultSet.next())
                {
                    id = resultSet.getInt("currval");
                }

                close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return id;
        }

    public int update(int id, Passport passport) {
        int rowsUpdated = 0;
        try {
            open();

            String sql = String.format("""
                    UPDATE passport
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
                    """, passport.pet_profile_id, passport.name, passport.birth_date.toString(),
                    passport.kind.toString(), passport.breed, passport.breeding_certificate, passport.coat, passport.bio,
                    id);

            rowsUpdated = statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    public Passport get(int id) {
        Passport result = new Passport();
        try {
            open();

            String sql = String.format("""
                    SELECT *
                    FROM passport
                    WHERE passport_id = %d
                    """, id);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                result.passport_id = resultSet.getInt("passport_id");
                result.pet_profile_id = resultSet.getInt("pet_profile_id");
                result.name = resultSet.getString("name");
                result.birth_date = new Date(resultSet.getString("birth_date"));
                result.kind = Kind.valueOf(resultSet.getString("kind"));
                result.breed = resultSet.getString("breed");
                result.breeding_certificate = resultSet.getBoolean("breeding_certificate");
                result.breed = resultSet.getString("coat");
                result.breed = resultSet.getString("bio");
            }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        try {
            open();

            String sql = String.format("""
                    DELETE FROM passport
                    WHERE passport_id = %d
                    """, id);

            statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
