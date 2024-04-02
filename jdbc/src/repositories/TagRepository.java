package repositories;

import entities.Entity;
import entities.Match;
import entities.Tag;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagRepository extends Repository {
    public TagRepository() throws IOException {
        super();

        sequenceName = "tags_tag_id_seq";
    }

    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> tags = new ArrayList<>();

        String sql ="""
                    SELECT * FROM tags;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            tags.add(new Tag(resultSet.getLong("tag_id"),
                    resultSet.getString("name")));
        }

        close();
        return tags;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        Tag tag = new Tag();

        String sql = String.format("""
                    SELECT *
                    FROM tags
                    WHERE tag_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            tag.setTagId(resultSet.getLong("tag_id"));
            tag.setName(resultSet.getString("name"));
        }

        close();
        return tag;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        Tag tag = (Tag) entity;

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

    @Override
    public void update(Entity entity) throws SQLException {
        open();

        Tag tag = (Tag) entity;

        String sql = String.format("""
                    UPDATE tags
                    SET name = '%s'
                    WHERE tag_id = %d;
                    """, tag.getName(), tag.getTagId());

        statement.executeUpdate(sql);

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM tags
                    WHERE tag_id = %d
                    """, id);

        //in pet_xref_tag delete by trigger

        statement.executeUpdate(sql);

        close();
    }


}
