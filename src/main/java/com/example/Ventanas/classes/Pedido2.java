package com.example.Ventanas.classes;

public class Pedido2 {
    private String[] Sabores;
    private Double[] precios;

    public Pedido2(String[] sabores, Double[] precios) {
        Sabores = sabores;
        this.precios = precios;
    }

    public String[] getSabores() {
        return Sabores;
    }

    public Double[] getPrecios() {
        return precios;
    }
}
