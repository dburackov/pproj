package databaserepos;

import entities.Like;
import entities.Match;

import java.sql.SQLException;
import java.util.Date;

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
                    """, match.first_pet_id, match.second_pet_id);

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    public int update(int id, Match match) throws SQLException {
        int rowsUpdated = 0;
        open();

        String sql = String.format("""
                    UPDATE match
                    SET first_pet_id = %d, second_pet_id = %d
                    WHERE match_id = %d;
                    """, match.first_pet_id, match.second_pet_id, id);

        rowsUpdated = statement.executeUpdate(sql);

        close();
        return rowsUpdated;
    }

    public void read_all() throws SQLException {
        open();

        String sql = """
                    SELECT * FROM matches;
                    """;

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            String match_id = resultSet.getString("like_id");
            String first_pet_id = resultSet.getString("object_pet_id");
            String second_pet_id = resultSet.getString("target_pet_id");
            System.out.println(match_id + " " + first_pet_id + " " + second_pet_id);
        }

        close();
    }

    public Match get(int id) throws SQLException {
        Match result = new Match();
        open();

        String sql = String.format("""
                    SELECT *
                    FROM match
                    WHERE match_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result.match_id = resultSet.getInt("user_id");
            result.first_pet_id = resultSet.getInt("first_pet_id");
            result.second_pet_id = resultSet.getInt("second_pet_id");
        }

        close();
        return result;
    }
}
