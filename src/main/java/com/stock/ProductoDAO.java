package com.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductoDAO {

    public void registrar(Producto producto) {

        String sql = "INSERT INTO productos " +
                "(nombre, categoria, talle, color, stock, stock_minimo, proveedor) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, producto.getNombre());
            sentencia.setString(2, producto.getCategoria());
            sentencia.setString(3, producto.getTalle());
            sentencia.setString(4, producto.getColor());
            sentencia.setInt(5, producto.getStock());
            sentencia.setInt(6, producto.getStockMinimo());
            sentencia.setString(7, producto.getProveedor());

            sentencia.executeUpdate();

            System.out.println("Producto registrado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
        }
    }

    public void consultarStock() {

        String sql = "SELECT * FROM productos";

        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {

                System.out.println("ID: " + resultado.getInt("id_producto"));
                System.out.println("Producto: " + resultado.getString("nombre"));
                System.out.println("Categoria: " + resultado.getString("categoria"));
                System.out.println("Talle: " + resultado.getString("talle"));
                System.out.println("Color: " + resultado.getString("color"));
                System.out.println("Stock: " + resultado.getInt("stock"));
                System.out.println("-----------------------------");
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println("Error al consultar stock: " + e.getMessage());
        }
    }

    public void consultarStockMinimo() {

        String sql = "SELECT * FROM productos WHERE stock <= stock_minimo";

        try {
            Connection conexion = Conexion.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            boolean encontrado = false;

            while (resultado.next()) {

                encontrado = true;

                System.out.println(
                        "Producto: " + resultado.getString("nombre") +
                                " | Stock: " + resultado.getInt("stock") +
                                " | Stock minimo: " + resultado.getInt("stock_minimo")
                );
            }

            if (!encontrado) {
                System.out.println("No hay productos con stock minimo.");
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println("Error al consultar stock minimo: " + e.getMessage());
        }
    }
}