package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Base;
import com.example.Ventanas.classes.Sabor;
import com.example.Ventanas.classes.incompleteStageException;
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
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class Paso2Controller implements Initializable {
    @FXML
    private Label lb_titulo_paso2;

    @FXML
    private AnchorPane contenedor_padre_paso2;

    @FXML
    private AnchorPane contenedor_part1_paso2;

    @FXML
    private AnchorPane contenedor_part3_paso2;
    
    @FXML
    private Button btnContinuar2_paso2;

    @FXML
    private FlowPane contenedor_part2_paso2;

    @FXML
    private Label pre2_paso2;


    @FXML
    void toPaso3(ActionEvent event) throws IOException {
        try {
            if (valorseleccionado1==null) {
                throw new incompleteStageException("No se ha seleccionado nada.");
            }else {
                FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("paso3-view.fxml"));
                Parent p = fxmlLoader.load();
                Scene scene = new Scene(p);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setTitle("Paso3");
                window.setScene(scene);
                window.show();
                System.out.println();
            }
        } catch (incompleteStageException e) {
            // Aquí puedes manejar la excepción, mostrar una alerta o realizar cualquier acción necesaria.
        }

    }
    public void initialize(URL location, ResourceBundle resources) {
        cargarSabor();
        pre2_paso2.setText("Precio total: " + String.valueOf(Paso1Controller.getBaseSeleccionada().getPrecio()));
    }
    public void cargarSabor(){
        contenedor_part2_paso2.getChildren().clear();
        contenedor_part2_paso2.setOrientation(Orientation.HORIZONTAL);
        contenedor_part2_paso2.setHgap(15);
        System.out.println("Se esta leyendo la base");
        VBox contenedor1 = new VBox();
        contenedor_part2_paso2.getChildren().add(contenedor1);

        Label sabor1 = new Label("Sabor 1");
        sabor1.setId("lb_sabor");
        contenedor1.getChildren().add(sabor1);
        contenedor1.setId("contenedor2_paso2");

        ComboBox cbsabores1 = new ComboBox();
        cbsabores1.setId("cb_sabores");
        cbsabores1.getStyleClass().add("cb-sabores");
        try{
            ArrayList<Sabor> sabores = Sabor.leerSabores();
            Collections.sort(sabores);
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
            contenedor2.setId("contenedor2_paso2");
            Label sabor2 = new Label("Sabor 2");
            contenedor2.getChildren().add(sabor2);
            sabor2.setId("lb_sabor");

            ComboBox<Sabor> cbsabores2 = new ComboBox<>();
            cbsabores2.setId("cb_sabores");
            cbsabores2.getStyleClass().add("cb-sabores");
            contenedor2.getChildren().add(cbsabores2);

            try {
                ArrayList<Sabor> sabores = Sabor.leerSabores();
                Collections.sort(sabores);
                cbsabores2.getItems().addAll(sabores);
            } catch (IOException e) {
                e.printStackTrace();
            }

            contenedor_part2_paso2.getChildren().add(contenedor2);

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
        pre2_paso2.setText("Valor a pagar: $" + String.valueOf(valorSeleccionadoCb1));
    }
    public void actualizarLabel2() {
        pre2_paso2.setText("Valor a pagar: $" + String.valueOf(valorSeleccionadoCb2));
    }

}
