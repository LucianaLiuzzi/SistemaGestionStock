package com.stock;

public class Producto {

    private int idProducto;
    private String nombre;
    private String categoria;
    private String talle;
    private String color;
    private int stock;
    private int stockMinimo;
    private String proveedor;

    public Producto(String nombre, String categoria, String talle, String color,
                    int stock, int stockMinimo, String proveedor) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.talle = talle;
        this.color = color;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTalle() {
        return talle;
    }

    public String getColor() {
        return color;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public String getProveedor() {
        return proveedor;
    }
}