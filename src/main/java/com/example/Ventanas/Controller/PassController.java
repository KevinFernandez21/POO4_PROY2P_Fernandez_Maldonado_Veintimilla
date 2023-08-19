package com.example.Ventanas.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class PassController {
    @FXML
    private Button btnLocales;

    @FXML
    private Button btnPedido;

    @FXML
    void btnPaso1(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("paso1-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Paso1");
        window.setScene(scene);
        window.show();

    }

    @FXML
    void goLocales(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("local-view.fxml"));
        Parent p1 = fxmlLoader.load();
        Scene scene = new Scene(p1);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}