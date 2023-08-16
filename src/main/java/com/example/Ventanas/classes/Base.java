package com.example.Ventanas.classes;

import java.util.ArrayList;

public class Base {
    private String tipo;
    private Double precio;

    public Base(String tipo, Double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getPrecio() {
        return precio;
    }
    public static ArrayList<Base> LeerBase(String rutaArchivo){
        ArrayList<Base> ListaBase = new ArrayList<>();
        return ListaBase;
    }
}
