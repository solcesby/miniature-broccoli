package app.utils;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class ConnectionUtil {

    private static volatile ConnectionUtil instance;

    private static final String URL = "jdbc:postgresql://localhost:5432/console_store";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private ConnectionUtil() {
    }

    public static ConnectionUtil getInstance() {
        ConnectionUtil localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionUtil.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionUtil();
                    log.info("instance {} were created", instance);
                }
            }
        }
        log.info("returning {} instance", instance);
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        log.debug("connection created");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
