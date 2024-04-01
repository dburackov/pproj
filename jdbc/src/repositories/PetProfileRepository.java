package repositories;

import entities.PetProfile;
import entities.Purpose;

import java.sql.SQLException;

public class PetProfileRepository extends DBController {
    public PetProfileRepository() throws SQLException, ClassNotFoundException {
        super();

        sequence = "pet_profile_pet_profile_id_seq";
    }

    public int create(PetProfile petProfile) throws SQLException {
        int lastInsertedId = 0;
        open();

        String sql = String.format("""
                    INSERT INTO pet_profiles
                    (user_id, purpose)
                    VALUES
                    (%d, '%s');
                    """, petProfile.getUserId(), petProfile.getPurpose().toString());

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    public int update(int id, PetProfile petProfile) throws SQLException {
        int rowsUpdated = 0;
        open();

        String sql = String.format("""
                    UPDATE pet_profiles
                    SET purpose = '%s'
                    WHERE pet_profile_id = '%d';
                    """, petProfile.getPurpose(), id);

        rowsUpdated = statement.executeUpdate(sql);

        close();
        return rowsUpdated;
    }

    public void readAll() throws SQLException {
        open();

        String sql = """
                    SELECT * FROM pet_profiles;
                    """;

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            String petProfileId = resultSet.getString(idFieldName);
            String userId = resultSet.getString("user_id");
            String purpose = resultSet.getString("purpose");
            System.out.println(petProfileId + " " + userId + " " + purpose);
        }

        close();
    }

    public PetProfile get(int id) throws SQLException {
        PetProfile result = new PetProfile();
        open();

        String sql = String.format("""
                    SELECT * 
                    FROM pet_profiles
                    WHERE pet_profile_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result.setPetProfileId(resultSet.getInt(idFieldName));
            result.setUserId(resultSet.getInt("user_id"));
            result.setPurpose(Purpose.valueOf(resultSet.getString("purpose")));
        }

        close();
        return result;
    }

}
