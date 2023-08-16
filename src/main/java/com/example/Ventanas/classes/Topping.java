package com.example.Ventanas.classes;

public class Topping {
    private String tipo;
    private Double precio;

    public Topping(String tipo, Double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getPrecio() {
        return precio;
    }
}
