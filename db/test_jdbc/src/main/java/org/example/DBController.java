package org.example;

import java.sql.*;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DBController {
    private Connection connection;
    Statement statement;
    ResultSet resultSet;
    Properties props;
    String jdbcUrl;

    public DBController() throws ClassNotFoundException, SQLException {
        jdbcUrl = "jdbc:postgresql://localhost:5432/pproj";
        props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "rjhjkm");

        Class.forName("org.postgresql.Driver");


    }

    public void open() throws SQLException {
        if (connection != null) {
            close();
        }
        connection = DriverManager.getConnection(jdbcUrl, props);
        statement = connection.createStatement();
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
        if (connection != null) {
            connection.close();
        }
    }


    public int create_user(User user) {
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

    public int update_user(int id, User new_user) {
        int rowsUpdated = 0;
        try {
            open();

            String sql = String.format("""
                    UPDATE users
                    SET name = '%s', email = '%s', password = '%s'
                    WHERE user_id = '%d';
                    """, new_user.name, new_user.email, new_user.password, id);

            rowsUpdated = statement.executeUpdate(sql);

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    public void read_users() {
        try {
            open();

            String sql = """
                    SELECT * FROM users;
                    """;

            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                System.out.println(name + " " + email + " " + password);
            }

            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get_user(int id) {
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

    public void delete_user(int id) {
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
