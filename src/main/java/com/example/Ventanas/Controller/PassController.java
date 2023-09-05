package com.example.Ventanas.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class PassController implements Initializable {
    @FXML
    private AnchorPane contenedor_padre_pass;

    @FXML
    private AnchorPane contenedor_part1_pass;

    @FXML
    private AnchorPane contenedor_part2_pass;

    @FXML
    private Label lb_Welcome_pass;

    @FXML
    private Button btnLocales_pass;

    @FXML
    private Button btnPedido_pass;
    @FXML
    private Stage ventanaEmergente;


    public void setVentanaEmergente(Stage ventanaEmergente) {
        this.ventanaEmergente = ventanaEmergente;
    }


    @FXML
    void goPaso1(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();

//        if (ventanaEmergente != null) {
//            ventanaEmergente.close();
//        }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lb_Welcome_pass.setText("Bienvenid@ "+ PrincipalController.getUsuarioSeleccionado().getNombre());
    }
}