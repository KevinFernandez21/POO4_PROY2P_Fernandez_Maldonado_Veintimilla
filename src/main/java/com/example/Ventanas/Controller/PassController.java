package com.example.Ventanas.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class PassController {
    @FXML
    private AnchorPane contenedor_padre;

    @FXML
    private AnchorPane contenedor_part1;

    @FXML
    private AnchorPane contenedor_part2;

    @FXML
    private Label lb_Welcome;

    @FXML
    private Button btnLocales;

    @FXML
    private Button btnPedido;
    private Stage ventanaEmergente;

    public void setVentanaEmergente(Stage ventanaEmergente) {
        this.ventanaEmergente = ventanaEmergente;
    }
    @FXML
    void goPaso1(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();

        if (ventanaEmergente != null) {
            ventanaEmergente.close();
        }

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
        Stage window = new Stage();

        window.setScene(scene);
        window.show();
    }
}