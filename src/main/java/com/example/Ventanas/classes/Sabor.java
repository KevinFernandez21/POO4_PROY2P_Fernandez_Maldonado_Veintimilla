package com.example.Ventanas.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Sabor {
    private String tipo;
    private Double precio;

    public Sabor(String tipo, Double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getPrecio() {
        return precio;
    }
    public static ArrayList<Sabor> leerSabores(String filePath) throws IOException {
        ArrayList<Sabor> listaSabor = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split(",");
                String sabor = info[0];
                Double precio = Double.parseDouble(info[1]);
                listaSabor.add(new Sabor(sabor,precio));
            }
        }

        return listaSabor;
    }
}
