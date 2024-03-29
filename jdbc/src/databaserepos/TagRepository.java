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
                    """, tag.getName());

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
                    """, tag.getName(), id);

        rowsUpdated = statement.executeUpdate(sql);

        close();
        return rowsUpdated;
    }

    public void readAll() throws SQLException {
        open();

        String sql = """
                    SELECT * FROM tags;
                    """;

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            String tagId = resultSet.getString(idFieldName);
            String name = resultSet.getString("name");
            System.out.println(tagId + " " + name + " ");
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
            result.setTagId(resultSet.getInt(idFieldName));
            result.setName(resultSet.getString("name"));
        }

        close();
        return result;
    }

}
