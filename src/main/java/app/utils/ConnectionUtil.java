package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/console_store";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private ConnectionUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
