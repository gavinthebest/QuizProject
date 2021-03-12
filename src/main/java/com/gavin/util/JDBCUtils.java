package com.gavin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String dbURL = "jdbc:mysql://localhost:3306/QuizProject?useSSL=false";
    static String Username = "root";
    static String password = "Xie102938!";

    public static void connect(Connection conn) throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            conn = DriverManager.getConnection(dbURL, Username, password);
        }
    }

    public static void disconnect(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
