package com.example.Ventanas.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Base {
    private String tipo;
    private Double precio;

    public Base(String tipo, Double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
     * getter tipo
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * getter precio
     * @return
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * lee el archivo base.txt y retorna un arrayList de bases
     * @return arrayList de bases
     * @throws IOException
     */
    public static ArrayList<Base> leerBase() throws IOException{
        Path file = Paths.get("Archivos","base.txt");
        ArrayList<Base> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile(),Charset.forName("UTF-8")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String info[] = line.split(",");
                String tipo = info[0];
                Double precio = Double.parseDouble(info[1]);
                lista.add(new Base(tipo,precio));
            }
        }
        return lista;
    }
}
