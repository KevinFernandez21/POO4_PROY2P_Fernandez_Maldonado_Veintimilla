package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Pedido;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;


import java.net.URL;
import java.util.ResourceBundle;

public class EmergenteController implements Initializable {

    @FXML
    private ListView lvPedidos;

    public void initialize(URL url, ResourceBundle rs) {
/*
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {


                    ObservableList<Pedido> observableList = FXCollections.observableArrayList(Pedido.leerArchivo());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lvPedidos.setItems(observableList);
                        }
                    });

                    try{
                        Thread.sleep(5000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }


                }

            }

            });
        }
*/
    }
}
