package org.example;

import java.sql.SQLException;
import java.util.Date;

public class LikeRepository extends DBController {
    public LikeRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    public int create(Like like) {
        int id = -1;
        try {
            open();

            String sql = String.format("""
                    INSERT INTO likes
                    (object_pet_id, target_pet_id, date)
                    VALUES
                    (%d, %d, '%s');
                    """, like.object_pet_id, like.target_pet_id, like.date.toString());

            statement.executeUpdate(sql);

            sql = String.format("""
                SELECT currval('%s');
                """, Like.sequence);

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

    public int update(int id, Like like) {
        int rowsUpdated = 0;
        try {
            open();

            String sql = String.format("""
                    UPDATE likes
                    SET object_pet_id = %d, target_pet_id = %d, date = '%s'
                    WHERE user_id = %d;
                    """, like.object_pet_id, like.target_pet_id, like.date.toString(), id);

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
                    SELECT * FROM likes;
                    """;

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                String like_id = resultSet.getString("like_id");
                String object_pet_id = resultSet.getString("object_pet_id");
                String target_pet_id = resultSet.getString("target_pet_id");
                String date = resultSet.getString("date");
                System.out.println(like_id + " " + object_pet_id + " " + target_pet_id + " " + date);
            }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Like get(int id) {
        Like result = new Like();
        try {
            open();

            String sql = String.format("""
                    SELECT *
                    FROM likes
                    WHERE like_id = %d
                    """, id);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                result.like_id = resultSet.getInt("user_id");
                result.target_pet_id = resultSet.getInt("target_pet_id");
                result.object_pet_id = resultSet.getInt("object_pet_id");
                result.date = new Date(resultSet.getString("date"));
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
                    DELETE FROM Likes
                    WHERE like_id = %d
                    """, id);

            statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
