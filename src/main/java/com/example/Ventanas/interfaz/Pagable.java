package com.example.Ventanas.interfaz;
import com.example.Ventanas.classes.Pago;
import com.example.Ventanas.classes.Pedido;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public interface Pagable {
    public static void guardarPedido(Pedido pedido) {
        Path ruta = Paths.get("src/main/resources/Archivos/serializado/");
        String nombreArchivo = "pedido" + pedido.getIdpedido() + ".bin";
        try (FileOutputStream fileOutputStream = new FileOutputStream(ruta.toFile());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(pedido);
            System.out.println("Pedido guardado en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * escribe en el archivo pagos.txt el pago realizado por el usuario
     * @param pago pago realizado por el usuario
     */
    public static void generarTransaccion(Pago pago){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/Archivos/pagos.txt",true))){
            bw.write(pago.toString());
            bw.newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
