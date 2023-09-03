package com.example.Ventanas.classes;

import com.example.Ventanas.interfaz.Pagable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Pedido implements Pagable,Serializable{
    private String idpedido;

    private String nombre;
    private Double total;
    private static Set<Integer> idPedidos = new HashSet<>();


    private int genIdPedido(){
        //int a = idPedidos.size();
        int num;
        do{
            num = (int) (Math.random()*90)+1;
            System.out.println(num);
        }while(idPedidos.contains(num));

        idPedidos.add(num);
        return num;
    }

    public Pedido(String nombre, Double total) {
        this.idpedido = String.valueOf(genIdPedido());
        this.nombre = nombre;
        this.total = total;
    }

    public String getIdpedido() {
        return idpedido;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getTotal() {
        return total;
    }

    public static void escribirArchivo(Pedido pedido){
        Path ruta = Paths.get("src/main/resources/Archivos/pedidos.txt");
        try (FileWriter fileWriter = new FileWriter(ruta.toFile(), true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String lineaPedido = pedido.getIdpedido() + "," + pedido.getNombre() + "," + pedido.getTotal();
            bufferedWriter.write(lineaPedido);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
