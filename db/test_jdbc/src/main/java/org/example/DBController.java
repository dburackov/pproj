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



}
