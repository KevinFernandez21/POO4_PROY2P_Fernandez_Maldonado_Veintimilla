package com.example.Ventanas.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Pago {

    private String idPago;
    private String idpedido;
    private String nombre;
    private Double total;

    private String fechaCaducidad;
    private metodoPago mPago;
    private static Set<Integer> idPedidos = new HashSet<>();

    /**
     * genera una id unica para el pago
     * @return id para pago
     */
    private int genIdPago(){
        Path ruta = Paths.get("Archivos/pagos.txt");
        ArrayList<Integer> idArchivo = new ArrayList<>();
        int num = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(ruta.toFile()))){
            String ln = br.readLine();
            while(ln != null){
                String datos[] = ln.split(",");
                idArchivo.add(Integer.parseInt(datos[0]));
                ln = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        do{
            num = (int) (Math.random()*8000)+1000;
            System.out.println(num);
        }while(idPedidos.contains(num)||idArchivo.contains(num));

        idPedidos.add(num);
        return num;
    }

    public Pago(String idpedido, String nombre,  Double total, String caducidad, metodoPago mPago){
        this.idPago = String.valueOf(genIdPago());
        this.idpedido = idpedido;
        this.nombre = nombre;
        this.total = total;
        this.fechaCaducidad = caducidad;
        this.mPago = mPago;
    }

    public String toString(){
        return this.idPago +","+ this.idpedido +","+ this.nombre +","+ this.total +","+ this.fechaCaducidad +","+ this.mPago;
    }

}
