package com.stock;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        ProductoDAO productoDAO = new ProductoDAO();
        MovimientoDAO movimientoDAO = new MovimientoDAO();

        boolean continuar = true;

        while (continuar) {

            System.out.println();
            System.out.println("===== SISTEMA DE GESTIÓN DE STOCK =====");
            System.out.println("1. Consultar stock");
            System.out.println("2. Consultar stock mínimo");
            System.out.println("3. Consultar historial de movimientos");
            System.out.println("4. Registrar ingreso");
            System.out.println("5. Registrar salida");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = teclado.nextLine();

            switch (opcion) {

                case "1":
                    productoDAO.consultarStock();
                    break;

                case "2":
                    productoDAO.consultarStockMinimo();
                    break;

                case "3":
                    movimientoDAO.consultarHistorial();
                    break;

                case "4":
                    System.out.print("Ingrese el ID del producto: ");
                    int idIngreso = Integer.parseInt(teclado.nextLine());

                    System.out.print("Ingrese la cantidad: ");
                    int cantidadIngreso = Integer.parseInt(teclado.nextLine());

                    movimientoDAO.registrarIngreso(idIngreso, cantidadIngreso);
                    break;

                case "5":
                    System.out.print("Ingrese el ID del producto: ");
                    int idSalida = Integer.parseInt(teclado.nextLine());

                    System.out.print("Ingrese la cantidad: ");
                    int cantidadSalida = Integer.parseInt(teclado.nextLine());

                    movimientoDAO.registrarSalida(idSalida, cantidadSalida);
                    break;

                case "0":
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        teclado.close();
    }
}