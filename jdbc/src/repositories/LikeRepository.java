package repositories;

import entities.Entity;
import entities.Like;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LikeRepository extends Repository {
    public LikeRepository() throws IOException {
        super();

        sequenceName = "likes_like_id_seq";

    }

    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> likes = new ArrayList<>();

        String sql ="""
                    SELECT * FROM likes;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            likes.add(new Like(resultSet.getLong("like_id"),
                    resultSet.getLong("object_pet_id"),
                    resultSet.getLong("target_pet_id")));
        }

        close();
        return likes;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        Like like = new Like();

        String sql = String.format("""
                    SELECT *
                    FROM likes
                    WHERE like_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            like.setLikeId(resultSet.getLong("like_id"));
            like.setObjectPetId(resultSet.getLong("object_pet_id"));
            like.setTargetPetId(resultSet.getLong("target_pet_id"));
        }

        close();
        return like;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        Like like = (Like)entity;

        String sql = String.format("""
               INSERT INTO likes
                (object_pet_id, target_pet_id)
                VALUES
                (%d, %d);
                """, like.getObjectPetId(), like.getTargetPetId());

        statement.executeUpdate(sql);
        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        open();

        Like like = (Like)entity;

        String sql = String.format("""
                    UPDATE likes
                    SET object_pet_id = %d, target_pet_id = %d
                    WHERE like_id = %d;
                    """, like.getObjectPetId(), like.getTargetPetId(), like.getLikeId());

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM likes
                    WHERE like_id = %d
                    """, id);

        statement.executeUpdate(sql);

        close();
    }
}
