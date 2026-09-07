package com.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MovimientoDAO {

    public void registrarIngreso(int idProducto, int cantidad) {

        String movimiento = "INSERT INTO movimientos (id_producto, tipo, cantidad) VALUES (?, 'INGRESO', ?)";
        String stock = "UPDATE productos SET stock = stock + ? WHERE id_producto = ?";

        try (Connection conexion = Conexion.conectar()) {

            PreparedStatement stmtMovimiento = conexion.prepareStatement(movimiento);
            stmtMovimiento.setInt(1, idProducto);
            stmtMovimiento.setInt(2, cantidad);
            stmtMovimiento.executeUpdate();

            PreparedStatement stmtStock = conexion.prepareStatement(stock);
            stmtStock.setInt(1, cantidad);
            stmtStock.setInt(2, idProducto);
            stmtStock.executeUpdate();

            System.out.println("Ingreso registrado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al registrar ingreso: " + e.getMessage());
        }
    }

    public void registrarSalida(int idProducto, int cantidad) {

        String movimiento = "INSERT INTO movimientos (id_producto, tipo, cantidad) VALUES (?, 'SALIDA', ?)";
        String stock = "UPDATE productos SET stock = stock - ? WHERE id_producto = ? AND stock >= ?";

        try (Connection conexion = Conexion.conectar()) {

            PreparedStatement stmtStock = conexion.prepareStatement(stock);
            stmtStock.setInt(1, cantidad);
            stmtStock.setInt(2, idProducto);
            stmtStock.setInt(3, cantidad);

            int actualizado = stmtStock.executeUpdate();

            if (actualizado == 0) {
                System.out.println("No hay stock suficiente.");
                return;
            }

            PreparedStatement stmtMovimiento = conexion.prepareStatement(movimiento);
            stmtMovimiento.setInt(1, idProducto);
            stmtMovimiento.setInt(2, cantidad);
            stmtMovimiento.executeUpdate();

            System.out.println("Salida registrada correctamente.");

        } catch (Exception e) {
            System.out.println("Error al registrar salida: " + e.getMessage());
        }
    }

    public void consultarHistorial() {

        String sql = "SELECT m.id_movimiento, p.nombre, p.talle, p.color, " +
                "m.tipo, m.cantidad, m.fecha " +
                "FROM movimientos m " +
                "INNER JOIN productos p ON m.id_producto = p.id_producto " +
                "ORDER BY m.fecha DESC";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             var resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                System.out.println(
                        "Movimiento: " + resultado.getInt("id_movimiento") +
                                " | Producto: " + resultado.getString("nombre") +
                                " | Talle: " + resultado.getString("talle") +
                                " | Color: " + resultado.getString("color") +
                                " | Tipo: " + resultado.getString("tipo") +
                                " | Cantidad: " + resultado.getInt("cantidad") +
                                " | Fecha: " + resultado.getTimestamp("fecha")
                );
            }

        } catch (Exception e) {
            System.out.println("Error al consultar historial: " + e.getMessage());
        }
    }
}
