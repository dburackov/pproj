package databaserepos;

import entities.User;

import java.sql.SQLException;

public class UserRepository extends DBController {

    public UserRepository() throws SQLException, ClassNotFoundException {
        super();

        sequence = "users_user_id_seq";
        tableName = "users";
        idFieldName = "user_id";
    }

    public int create(User user) throws SQLException {
        int lastInsertedId = 0;
        open();

        String sql = String.format("""
                    INSERT INTO users
                    (name, email, password)
                    VALUES
                    ('%s', '%s', '%s');
                    """, user.name, user.email, user.password);

        statement.executeUpdate(sql);

        lastInsertedId = getLastInsertedId();

        close();
        return lastInsertedId;
    }

    public int update(int id, User new_user) throws SQLException {
        int rowsUpdated = 0;
        open();

        String sql = String.format("""
                    UPDATE users
                    SET name = '%s', email = '%s', password = '%s'
                    WHERE user_id = %d;
                    """, new_user.name, new_user.email, new_user.password, id);

        rowsUpdated = statement.executeUpdate(sql);

        close();
        return rowsUpdated;
    }

    public void read_all() throws SQLException {
        open();

        String sql = """
                    SELECT * FROM users;
                    """;

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            String user_id = resultSet.getString("user_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            System.out.println(user_id + " " + name + " " + email + " " + password);
        }

        close();
    }

    public User get(int id) throws SQLException {
        User result = new User();
        open();

        String sql = String.format("""
                    SELECT * 
                    FROM users
                    WHERE user_id = %d
                    """, id);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result.user_id = resultSet.getInt("user_id");
            result.name = resultSet.getString("name");
            result.email = resultSet.getString("email");
            result.password = resultSet.getString("password");
        }

        close();
        return result;
    }
}
