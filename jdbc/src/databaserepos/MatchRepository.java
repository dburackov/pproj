package databaserepos;

import entities.Like;
import entities.Match;

import java.sql.SQLException;
import java.util.Date;

public class MatchRepository extends DBController {
    public MatchRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    public int create(Match match) {
        int id = -1;
        try {
            open();

            String sql = String.format("""
                    INSERT INTO match
                    (first_pet_id, second_pet_id)
                    VALUES
                    (%d, %d);
                    """, match.first_pet_id, match.second_pet_id);

            statement.executeUpdate(sql);

            sql = String.format("""
                SELECT currval('%s');
                """, Match.sequence);

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

    public int update(int id, Match match) {
        int rowsUpdated = 0;
        try {
            open();

            String sql = String.format("""
                    UPDATE match
                    SET first_pet_id = %d, second_pet_id = %d
                    WHERE match_id = %d;
                    """, match.first_pet_id, match.second_pet_id, id);

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
                    SELECT * FROM match;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Match get(int id) {
        Match result = new Match();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        try {
            open();

            String sql = String.format("""
                    DELETE FROM match
                    WHERE match_id = %d
                    """, id);

            statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
