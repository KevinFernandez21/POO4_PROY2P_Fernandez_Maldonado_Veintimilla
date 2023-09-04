package com.example.Ventanas.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class PassController {
    @FXML
    private Button btnLocales;

    @FXML
    private Button btnPedido;
    /**
     * Abre una nueva stage con el scene paso1-view
     * @param event
     * @throws IOException
     */
    @FXML
    void goPaso1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("paso1-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);
        URL cssFile = PrincipalApplication.class.getResource("/css/paso1.css");
        scene.getStylesheets().add(cssFile.toExternalForm());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Paso1");
        window.setScene(scene);
        window.show();
    }
    /**
     * Abre una nueva stage con el scene local-view
     * @param event
     * @throws IOException
     */
    @FXML
    void goLocales(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("local-view.fxml"));
        Parent p1 = fxmlLoader.load();

        Scene scene = new Scene(p1);
        URL cssFile = PrincipalApplication.class.getResource("/css/locales.css");
        scene.getStylesheets().add(cssFile.toExternalForm());
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();

        window.setScene(scene);
        window.show();
    }
}