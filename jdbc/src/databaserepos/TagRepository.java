package databaserepos;

import entities.Tag;

import java.sql.SQLException;

public class TagRepository extends DBController {
    public TagRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    public int create(Tag tag) {
        int id = -1;
        try {
            open();

            String sql = String.format("""
                    INSERT INTO tags
                    (name)
                    VALUES
                    ('%s');
                    """, tag.name);

            statement.executeUpdate(sql);

            sql = String.format("""
                SELECT currval('%s');
                """, Tag.sequence);

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

    public int update(int id, Tag tag) {
        int rowsUpdated = 0;
        try {
            open();

            String sql = String.format("""
                    UPDATE tags
                    SET name = '%s'
                    WHERE tag_id = %d;
                    """, tag.name, id);

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
                    SELECT * FROM tags;
                    """;

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                String tag_id = resultSet.getString("user_id");
                String name = resultSet.getString("name");
                System.out.println(tag_id + " " + name + " ");
            }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Tag get(int id) {
        Tag result = new Tag();
        try {
            open();

            String sql = String.format("""
                    SELECT * 
                    FROM users
                    WHERE user_id = %d
                    """, id);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                result.tag_id = resultSet.getInt("tag_id");
                result.name = resultSet.getString("name");
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
                    DELETE FROM tags
                    WHERE tag_id = %d
                    """, id);

            statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
