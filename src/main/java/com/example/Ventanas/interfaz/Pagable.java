package com.example.Ventanas.interfaz;
import com.example.Ventanas.classes.Pedido;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
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
}
