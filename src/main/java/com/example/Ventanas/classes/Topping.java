package com.example.Ventanas.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Topping {
    private String tipo;
    private Double precio;

    public Topping(String tipo, Double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
     * getter tipo de topping
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * precio de topping
     * @return precio
     */
    public Double getPrecio() {
        return precio;
    }

    public String toString() {
        return tipo + " -$" +precio;
    }

    /**
     * lee el archivo topping.txt y retorna un arrayList de toppings
     * @return arrayList de toppings
     * @throws IOException
     */
    public static ArrayList<Topping> leerTopping() throws IOException {
        Path file = Paths.get("src", "main","resources","Archivos","topping.txt");
        ArrayList<Topping> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile(), Charset.forName("UTF-8")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split(",");
                String topping = info[0];
                Double precio = Double.parseDouble(info[1]);
                lista.add(new Topping(topping,precio));
            }
        }
        return lista;
    }
}
