package com.example.Ventanas.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public static ArrayList<Topping> leerTopping(String filePath) throws IOException {
        ArrayList<Topping> listaTopping = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split(",");
                String topping = info[0];
                Double precio = Double.parseDouble(info[1]);
                listaTopping.add(new Topping(topping,precio));
            }
        }

        return listaTopping;
    }
}
