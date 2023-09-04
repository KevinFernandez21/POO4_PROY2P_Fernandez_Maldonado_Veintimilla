package com.example.Ventanas.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.Ventanas.Controller.PedidoController.pedidoActual;

public class CompletadoController implements Initializable {
    @FXML
    private Label lbWelcome;


    public void initialize(URL url, ResourceBundle resourceBundle){
        lbWelcome.setText("Tu pedido es el #"+pedidoActual.getIdpedido()+". Te llamaremos cuando este listo");
    }

}
