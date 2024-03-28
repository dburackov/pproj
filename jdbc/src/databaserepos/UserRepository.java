package databaserepos;

import entities.User;

import java.sql.SQLException;

public class UserRepository extends DBController {

    public UserRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    public int create(User user) {
        int user_id = -1;
        try {
            open();

            String sql = String.format("""
                    INSERT INTO users
                    (name, email, password)
                    VALUES
                    ('%s', '%s', '%s');
                    """, user.name, user.email, user.password);

            statement.executeUpdate(sql);

            sql = String.format("""
                SELECT currval('%s');
                """, User.sequence);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                user_id = resultSet.getInt("currval");
            }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }

    public int update(int id, User new_user) {
        int rowsUpdated = 0;
        try {
            open();

            String sql = String.format("""
                    UPDATE users
                    SET name = '%s', email = '%s', password = '%s'
                    WHERE user_id = %d;
                    """, new_user.name, new_user.email, new_user.password, id);

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get(int id) {
        User result = new User();
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
                result.user_id = resultSet.getInt("user_id");
                result.name = resultSet.getString("name");
                result.email = resultSet.getString("email");
                result.password = resultSet.getString("password");
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
                    DELETE FROM users
                    WHERE user_id = %d
                    """, id);

            statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
