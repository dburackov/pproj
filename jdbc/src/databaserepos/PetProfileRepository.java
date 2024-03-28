package databaserepos;

import java.sql.SQLException;

public class PetProfileRepository extends DBController {
    public PetProfileRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    public int create(PetProfile petProfile) {
        int id = -1;
        try {
            open();

            String sql = String.format("""
                    INSERT INTO pet_profiles
                    (user_id, purpose)
                    VALUES
                    (%d, '%s');
                    """, petProfile.user_id, petProfile.purpose.toString());

            statement.executeUpdate(sql);

            sql = String.format("""
                SELECT currval('%s');
                """, PetProfile.sequence);

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

    public int update(int id, PetProfile new_pet_profile) {
        int rowsUpdated = 0;
        try {
            open();

            String sql = String.format("""
                    UPDATE pet_profiles
                    SET purpose = '%s'
                    WHERE pet_profile_id = '%d';
                    """, new_pet_profile.purpose, id);

            rowsUpdated = statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    public void read_all() {
        try {
            open();

            String sql = """
                    SELECT * FROM pet_profiles;
                    """;

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                String pet_profile_id = resultSet.getString("pet_profile_id");
                String user_id = resultSet.getString("user_id");
                String purpose = resultSet.getString("purpose");
                System.out.println(pet_profile_id + " " + user_id + " " + purpose);
            }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PetProfile get(int id) {
        PetProfile result = new PetProfile();
        try {
            open();

            String sql = String.format("""
                    SELECT * 
                    FROM pet_profiles
                    WHERE pet_profile_id = %d
                    """, id);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                result.pet_profile_id = resultSet.getInt("pet_profile_id");
                result.user_id = resultSet.getInt("user_id");
                result.purpose = Purpose.valueOf(resultSet.getString("purpose"));
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
                    DELETE FROM pet_profiles
                    WHERE pet_profile_id = %d
                    """, id);

            statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
