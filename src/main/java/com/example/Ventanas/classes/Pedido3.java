package com.example.Ventanas.classes;

public class Pedido3 {
    private String[] toppings;
    private Double[] precios;

    public Pedido3(String[] toppings, Double[] precios) {
        this.toppings = toppings;
        this.precios = precios;
    }

    public String[] getToppings() {
        return toppings;
    }

    public Double[] getPrecios() {
        return precios;
    }
}
