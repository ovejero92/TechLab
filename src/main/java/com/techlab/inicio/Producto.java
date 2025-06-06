package com.techlab.inicio;

public class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {        return nombre;    }
    public double getPrecio() {        return precio;    }
    public int getId() {        return id;    }

    public String toString() { return "ID: "+id+" | "+nombre + " - $" +precio; }
}
