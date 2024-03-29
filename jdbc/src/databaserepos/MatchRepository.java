package databaserepos;

import entities.Match;

import java.sql.SQLException;

public class MatchRepository extends DBController {
    public MatchRepository() throws SQLException, ClassNotFoundException {
        super();

        sequence = "match_match_id_seq";
        tableName = "matches";
        idFieldName = "match_id";
    }

    public int create(Match match) throws SQLException {
        int lastInsertedId = 0;
        open();

        String sql = String.format("""
                    INSERT INTO matches
                    (first_pet_id, second_pet_id)
                    VALUES
                    (%d, %d);
                    """, match.getFirstPetId(), match.getSecondPetId());

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    public int update(int id, Match match) throws SQLException {
        int rowsUpdated = 0;
        open();

        String sql = String.format("""
                    UPDATE matches
                    SET first_pet_id = %d, second_pet_id = %d
                    WHERE match_id = %d;
                    """, match.getFirstPetId(), match.getSecondPetId(), id);

        rowsUpdated = statement.executeUpdate(sql);

        close();
        return rowsUpdated;
    }

    public void readAll() throws SQLException {
        open();

        String sql = """
                    SELECT * FROM matches;
                    """;

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            String matchId = resultSet.getString(idFieldName);
            String firstPetId = resultSet.getString("first_pet_id");
            String secondPetId = resultSet.getString("second_pet_id");
            System.out.println(matchId + " " + firstPetId + " " + secondPetId);
        }

        close();
    }

    public Match get(int id) throws SQLException {
        Match result = new Match();
        open();

        String sql = String.format("""
                    SELECT *
                    FROM matches
                    WHERE match_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result.setMatchId(resultSet.getInt(idFieldName));
            result.setFirstPetId(resultSet.getInt("first_pet_id"));
            result.setSecondPetId(resultSet.getInt("second_pet_id"));
        }

        close();
        return result;
    }
}
