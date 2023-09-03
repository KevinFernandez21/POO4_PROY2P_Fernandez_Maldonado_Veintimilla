package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Base;
import com.example.Ventanas.classes.Sabor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class Paso2Controller implements Initializable {
    @FXML
    private Label welcomeText;
    
    @FXML
    private Button btnContinuar2;

    @FXML
    private FlowPane container_paso2;

    @FXML
    private Label pre2;

    //externo
    private ArrayList<Sabor> listaSabor;

    @FXML
    void toPaso3(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("paso3-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Paso3");
        window.setScene(scene);
        window.show();
        System.out.println();

    }
    public void initialize(URL location, ResourceBundle resources) {
        cargarSabor();
        pre2.setText("Precio total: " + String.valueOf(Paso1Controller.getBaseSeleccionada().getPrecio()));
    }
    public void cargarSabor(){
        container_paso2.getChildren().clear();
        container_paso2.setOrientation(Orientation.HORIZONTAL);
        System.out.println("Se esta leyendo la base");
        VBox contenedor1 = new VBox();
        container_paso2.getChildren().add(contenedor1);

        Label sabor1 = new Label("Sabor 1");
        contenedor1.getChildren().add(sabor1);

        ComboBox cbsabores1 = new ComboBox();
        try{
            ArrayList<Sabor> sabores = Sabor.leerSabores();
            cbsabores1.getItems().addAll(sabores);
        }catch (IOException e){
            e.printStackTrace();
        }
        cbsabores1.setOnAction(actionEvent -> mostraOtralista(cbsabores1));


        contenedor1.getChildren().add(cbsabores1);
    }
    private static Sabor valorseleccionado1;
    private static Sabor valorseleccionado2;

    public static Sabor getValorseleccionado1() {
        return valorseleccionado1;
    }

    public static Sabor getValorseleccionado2() {
        return valorseleccionado2;
    }

    public static void setValorseleccionado1(Sabor valorseleccionado1) {
        Paso2Controller.valorseleccionado1 = valorseleccionado1;
    }

    public static void setValorseleccionado2(Sabor valorseleccionado2) {
        Paso2Controller.valorseleccionado2 = valorseleccionado2;
    }

    private double valorSeleccionadoCb1 = 0.0;
    private double valorSeleccionadoCb2 = 0.0;

    public void mostraOtralista(ComboBox<Sabor> Cb1) {
        Sabor sabor = Cb1.getValue();
        valorseleccionado1 = sabor;
        if (sabor != null) {
            valorSeleccionadoCb1 = Paso1Controller.getBaseSeleccionada().getPrecio()+ sabor.getPrecio();
            actualizarLabel1();
        }

        if (Cb1.getUserData() == null) {
            VBox contenedor2 = new VBox();
            Label sabor2 = new Label("Sabor 2");
            contenedor2.getChildren().add(sabor2);

            ComboBox<Sabor> cbsabores2 = new ComboBox<>();
            contenedor2.getChildren().add(cbsabores2);

            try {
                ArrayList<Sabor> sabores = Sabor.leerSabores();
                cbsabores2.getItems().addAll(sabores);
            } catch (IOException e) {
                e.printStackTrace();
            }

            container_paso2.getChildren().add(contenedor2);

            cbsabores2.setOnAction(actionEvent -> {
                Sabor saborSeleccionado2 = cbsabores2.getValue();
                valorseleccionado2 = saborSeleccionado2;
                if (saborSeleccionado2 != null) {
                    valorSeleccionadoCb2 = valorSeleccionadoCb1 + saborSeleccionado2.getPrecio();
                    actualizarLabel2();
                }
            });

            Cb1.setUserData(cbsabores2);
        }
    }

    public void actualizarLabel1() {
        pre2.setText("Precio total: " + String.valueOf(valorSeleccionadoCb1));
    }
    public void actualizarLabel2() {
        pre2.setText("Precio total: " + String.valueOf(valorSeleccionadoCb2));
    }

}
