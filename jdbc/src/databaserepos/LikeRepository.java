package databaserepos;

import entities.Like;

import java.sql.SQLException;
import java.util.Date;

public class LikeRepository extends DBController {
    public LikeRepository() throws SQLException, ClassNotFoundException {
        super();

        sequence = "likes_like_id_seq";
        tableName = "likes";
        idFieldName = "like_id";
    }

    public int create(Like like) throws SQLException {
        int lastInsertedId = 0;
        open();

        String sql = String.format("""
               INSERT INTO likes
                (object_pet_id, target_pet_id, date)
                VALUES
                (%d, %d, '%s');
                """, like.objectPetId, like.targetPetId, like.date.toString());

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    public int update(int id, Like like) throws SQLException {
        int rowsUpdated = 0;
        open();

        String sql = String.format("""
                    UPDATE likes
                    SET object_pet_id = %d, target_pet_id = %d, date = '%s'
                    WHERE like_id = %d;
                    """, like.objectPetId, like.targetPetId, like.date.toString(), id);

        rowsUpdated = statement.executeUpdate(sql);

        close();
        return rowsUpdated;
    }

    public void readAll() throws SQLException {
        open();

        String sql = """
                    SELECT * FROM likes;
                    """;

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            String likeId = resultSet.getString("like_id");
            String objectPetId = resultSet.getString("object_pet_id");
            String targetPetId = resultSet.getString("target_pet_id");
            String date = resultSet.getString("date");
            System.out.println(likeId + " " + objectPetId + " " + targetPetId + " " + date);
        }

        close();
    }

    public Like get(int id) throws SQLException {
        Like result = new Like();
        open();

        String sql = String.format("""
                    SELECT *
                    FROM likes
                    WHERE like_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result.likeId = resultSet.getInt("user_id");
            result.targetPetId = resultSet.getInt("target_pet_id");
            result.objectPetId = resultSet.getInt("object_pet_id");
            result.date = new Date(resultSet.getString("date"));
        }

        close();
        return result;
    }
}
