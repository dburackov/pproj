package repositories;

import entities.Entity;
import entities.Like;
import entities.Match;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository extends Repository {
    public MatchRepository() throws IOException {
        super();

        sequenceName = "match_match_id_seq";
    }

    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> matches = new ArrayList<>();

        String sql ="""
                    SELECT * FROM matches;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            matches.add(new Match(resultSet.getLong("match_id"),
                    resultSet.getLong("first_pet_id"),
                    resultSet.getLong("second_pet_id")));
        }

        close();
        return matches;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        Match match = new Match();

        String sql = String.format("""
                    SELECT *
                    FROM matches
                    WHERE match_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            match.setMatchId(resultSet.getLong("match_id"));
            match.setFirstPetId(resultSet.getLong("first_pet_id"));
            match.setSecondPetId(resultSet.getLong("second_pet_id"));
        }

        close();
        return match;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        Match match = (Match) entity;

        String sql = String.format("""
               INSERT INTO Matches
                (first_pet_id, second_pet_id)
                VALUES
                (%d, %d);
                """, match.getFirstPetId(), match.getSecondPetId());

        statement.executeUpdate(sql);
        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        open();

        Match match = (Match) entity;

        String sql = String.format("""
                    UPDATE
                    SET first_pet_id = %d, second_pet_id = %d
                    WHERE like_id = %d;
                    """, match.getFirstPetId(), match.getSecondPetId(), match.getMatchId());

        statement.executeUpdate(sql);

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM matches
                    WHERE match_id = %d
                    """, id);

        statement.executeUpdate(sql);

        close();
    }

}
