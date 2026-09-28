package com.stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/tp1_stock_indumentaria";
    private static final String USER = "root";
    private static final String PASSWORD = "MI_CONTRASEÑA";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}