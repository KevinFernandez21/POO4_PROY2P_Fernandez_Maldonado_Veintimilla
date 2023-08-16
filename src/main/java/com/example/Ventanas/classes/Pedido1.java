package com.example.Ventanas.classes;

public class Pedido1 {
    private String Base;
    private Double precios;

    public Pedido1(String base, Double precios) {
        Base = base;
        this.precios = precios;
    }

    public String getBase() {
        return Base;
    }

    public Double getPrecios() {
        return precios;
    }
}
