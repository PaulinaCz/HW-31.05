package com.czerniecka.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionConfig {

    private static final String H2_DB_URL = "jdbc:h2:tcp://localhost/~/db/jdbc-course";
    private static DBConnectionConfig instance;

    private Connection dbConnection;

    private DBConnectionConfig() {
        // It shouldn't be needed
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionConfig getInstance() {
        if (null == instance) {
            instance = new DBConnectionConfig();
        }

        return instance;
    }

    public Connection getConnection() {
        if (null == dbConnection) {
            try {
                dbConnection = DriverManager.getConnection(H2_DB_URL, "sa", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dbConnection;
    }
}
