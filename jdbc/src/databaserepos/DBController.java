package databaserepos;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

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
        String password = getPassword();
        props.setProperty("password", password);

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

    private String getPassword() {
        String result = null;
        try {
            File file = new File("database_config");
            Scanner scanner = new Scanner(file);
            result = scanner.nextLine();
            scanner.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}
