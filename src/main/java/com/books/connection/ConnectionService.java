package com.books.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionService {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/books";
    private static final String USERNAME = "shapkinsa";
    private static final String PASSWORD = "2244";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
