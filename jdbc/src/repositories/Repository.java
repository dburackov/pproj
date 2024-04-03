package repositories;


import entities.Entity;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static java.lang.System.console;

public abstract class Repository {
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    protected Properties properties;

    protected String sequenceName;


    public Repository() throws IOException {
        try (FileInputStream inputStream = new FileInputStream("db.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            console().printf("Error connecting to database\n");
        }
    }

    protected void connect() throws SQLException {
        String dbUrl = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        connection = DriverManager.getConnection(dbUrl, username, password);
    }

    protected void open() throws SQLException {
        connect();
        statement = connection.createStatement();
    }

    protected void close() throws SQLException {
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

    protected Long getLastInsertedId() throws SQLException {
        Long lastInsertedId = null;

        String sql = String.format("""
                SELECT currval('%s');
                """, sequenceName);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            lastInsertedId = resultSet.getLong("currval");
        }

        return lastInsertedId;
    }

    public abstract List<Entity> readAll() throws SQLException;
    public abstract Entity getById(Long id) throws SQLException;
    public abstract Long create(Entity entity) throws SQLException;

    public abstract void update(Entity entity) throws SQLException;
    public abstract void delete(Long id) throws SQLException;
}
