package repositories;


import entities.Entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public abstract class Repository {
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    protected Properties properties;


    public Repository() {
        try {
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream("db.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void connect() throws IOException, SQLException {
        String dbUrl = properties.getProperty("db.url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(dbUrl, username, password);
    }

    protected void open() throws SQLException, IOException {
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

    public abstract List<Entity> readAll();
    public abstract Entity getById(Long id);
    public abstract Long create(Entity entity);

    public abstract void update(Entity entity);
    public abstract void delete(Long id);
}
