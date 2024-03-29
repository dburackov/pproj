package databaserepos;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class DBController {
    private Connection connection;
    Statement statement;
    ResultSet resultSet;
    Properties props;
    String jdbcUrl;

    protected static String sequence;
    protected static String tableName;
    protected static String idFieldName;


    public DBController() throws ClassNotFoundException, SQLException {
        jdbcUrl = "jdbc:postgresql://localhost:5432/petfiesta";
        props = new Properties();
        props.setProperty("user", "spring");
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

    protected int getLastInsertedId() throws SQLException {
        int result = 0;
        open();

        String sql = String.format("""
                SELECT currval('%s');
                """, sequence);

        resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            result = resultSet.getInt("currval");
        }

        close();
        return result;
    }

    public void delete(int id) throws SQLException {
        open();

        String sql = String.format("""
                    DELETE FROM %s
                    WHERE %s = %d
                    """, tableName, idFieldName, id);

        statement.executeUpdate(sql);

        close();
    }

    private String getPassword() {
        String result = null;
        try {
            File file = new File("database_config");
            Scanner scanner = new Scanner(file);
            result = scanner.nextLine();
            scanner.close();
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
