package repositories;

import entities.Entity;
import entities.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository {

    public UserRepository() throws IOException {
        super();

        sequenceName = "users_user_id_seq";
    }


    @Override
    public List<Entity> readAll() throws SQLException {
        open();

        List<Entity> users = new ArrayList<>();

        String sql ="""
                    SELECT * FROM users;
                    """;

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            users.add(new User(resultSet.getLong("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password")));
        }

        close();
        return users;
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        open();
        User user = new User();

        String sql = String.format("""
                    SELECT *
                    FROM users
                    WHERE user_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            user.setUserId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }

        close();
        return user;
    }

    @Override
    public Long create(Entity entity) throws SQLException {
        open();

        Long lastInsertedId;
        User user = (User) entity;

        String sql = String.format("""
                    INSERT INTO users
                    (name, email, password)
                    VALUES
                    ('%s', '%s', '%s');
                    """, user.getName(), user.getEmail(), user.getPassword());

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        open();

        User user = (User) entity;

        String sql = String.format("""
                    UPDATE users
                    SET name = '%s', email = '%s', password = '%s'
                    WHERE user_id = %d;
                    """, user.getName(), user.getEmail(), user.getPassword(), user.getUserId());

        statement.executeUpdate(sql);

        close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM users
                    WHERE user_id = %d
                    """, id);

        statement.executeUpdate(sql);

        close();
    }


}
