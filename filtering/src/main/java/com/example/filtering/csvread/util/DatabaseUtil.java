package com.example.filtering.csvread.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";

    static {
        try{
            //MySQL Jdbc 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Failed to load JDBC driver",e);
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
