package com.example.Ventanas.classes;

public class Sabores {
    private String tipo;
    private Double precio;

    public Sabores(String tipo, Double precio) {
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
