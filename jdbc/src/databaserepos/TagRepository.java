package databaserepos;

import entities.Tag;

import java.sql.SQLException;

public class TagRepository extends DBController {
    public TagRepository() throws SQLException, ClassNotFoundException {
        super();

        sequence = "tags_tag_id_seq";
        tableName = "tags";
        idFieldName = "tag_id";
    }

    public int create(Tag tag) throws SQLException {
        int lastInsertedId = -1;
        open();

        String sql = String.format("""
                    INSERT INTO tags
                    (name)
                    VALUES
                    ('%s');
                    """, tag.name);

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    public int update(int id, Tag tag) throws SQLException {
        int rowsUpdated = 0;
        open();

        String sql = String.format("""
                    UPDATE tags
                    SET name = '%s'
                    WHERE tag_id = %d;
                    """, tag.name, id);

        rowsUpdated = statement.executeUpdate(sql);

        close();
        return rowsUpdated;
    }

    public void read_all() throws SQLException {
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
    }

    public Tag get(int id) throws SQLException {
        Tag result = new Tag();
        open();

        String sql = String.format("""
                    SELECT *
                    FROM tags
                    WHERE tag_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result.tag_id = resultSet.getInt("tag_id");
            result.name = resultSet.getString("name");
        }

        close();
        return result;
    }

}
