package com.example.Ventanas.interfaz;
import com.example.Ventanas.classes.Pago;
import com.example.Ventanas.classes.Pedido;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public interface Pagable {

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
