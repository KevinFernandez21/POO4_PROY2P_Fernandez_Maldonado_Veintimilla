package com.example.Ventanas.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Sabor implements Comparable<Sabor> {
    private String tipo;
    private Double precio;

    public Sabor(String tipo, Double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
     * getter tipo de sabor
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * getter tipo de precio
     * @return precio
     */
    public Double getPrecio() {
        return precio;
    }

    public String toString(){ return tipo + " -$" +precio;}
    public static ArrayList<Sabor> leerSabores() throws IOException {
        Path file = Paths.get("src", "main","resources","Archivos","sabores.txt");
        ArrayList<Sabor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile(), Charset.forName("UTF-8")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split(",");
                String sabor = info[0];
                Double precio = Double.parseDouble(info[1]);
                lista.add(new Sabor(sabor,precio));
            }
        }
        return lista;
    }

    @Override
    public int compareTo(Sabor o) {
        return this.tipo.compareTo(o.getTipo());
    }
}
