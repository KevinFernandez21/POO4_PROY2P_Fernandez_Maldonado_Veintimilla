package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Pedido;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EmergenteController implements Initializable {

    @FXML
    private ListView lvPedidos;

    private Timer tm;


    public void initialize(URL url, ResourceBundle rs) {
        tm = new Timer();
        tm.scheduleAtFixedRate(new TimerTask(){

            public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ordenarListaPedidos();
                    }
                });
            }


        }, 0, 5000);

    }

    private void ordenarListaPedidos(){
        Task<Void> task = new Task<Void>(){

            protected Void call(){
                try(BufferedReader br = new BufferedReader((new FileReader("src/main/resources/Archivos/pedidos.txt")))){
                    List<String> pedidosFormato = new ArrayList<>();
                    String ln= br.readLine();

                    while (ln != null){
                        String datos[] = ln.split(",");
                        String name = datos[1];
                        String id = datos[0];
                        pedidosFormato.add(name+","+id);

                        ln = br.readLine();
                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lvPedidos.getItems().clear();
                            lvPedidos.getItems().addAll(pedidosFormato);
                        }
                    });
                }catch(IOException e){
                    e.printStackTrace();
                }
                return null;
            }


        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}
