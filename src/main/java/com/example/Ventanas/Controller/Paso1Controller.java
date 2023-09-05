package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Base;
import com.example.Ventanas.classes.incompleteStageException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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


public class Paso1Controller implements Initializable {
    @FXML
    private AnchorPane contenedor_padre_paso1;

    @FXML
    private Label lb_titulo_paso1;
    
    @FXML
    private Button btnContinuar1_paso1;

    @FXML
    private AnchorPane contenedor_part1_paso1;

    @FXML
    private AnchorPane contenedor_part3_paso1;
    @FXML
    private FlowPane contenedor_part2_paso1;
    @FXML
    private ToggleGroup group = new ToggleGroup();
    @FXML
    private Label pre1_paso1;

    //externo
    private ArrayList<Base> listaBase;
    @FXML
    void goPaso2(ActionEvent event) throws IOException {
        try {
            if (baseSeleccionada==null) {
                throw new incompleteStageException("No se ha seleccionado nada.");
            }else {
                FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("paso2-view.fxml"));
                Parent p = fxmlLoader.load();
                Scene scene = new Scene(p);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setTitle("Paso2");
                window.setScene(scene);
                window.show();
            }
        } catch (incompleteStageException e) {
            // Aquí puedes manejar la excepción, mostrar una alerta o realizar cualquier acción necesaria.
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarBase();

    }

    public void cargarBase() {

            contenedor_part2_paso1.getChildren().clear();
            contenedor_part2_paso1.setOrientation(Orientation.HORIZONTAL);
            contenedor_part2_paso1.setHgap(13);

            try{
                listaBase = Base.leerBase();
                for(Base base: listaBase){
                    Path file = Paths.get("src", "main","resources","Archivos","imagenes","base",base.getTipo()+".png");
                    Insets padding = new Insets(10);

                    VBox Contenedor_base = new VBox();
                    Contenedor_base.setSpacing(10);

                    Contenedor_base.setId("contenedor_base");
                    Contenedor_base.setPrefWidth(220);
                    Contenedor_base.setPrefHeight(120);
                    Contenedor_base.setPadding(padding);

                    HBox part1 = new HBox();
                    part1.setId("contenedor_base_part1");
                    part1.setSpacing(10);

                    Contenedor_base.getChildren().add(part1);

                    FileInputStream fis = new FileInputStream(file.toFile());
                    ImageView imagenView = new ImageView(new Image(fis));
                    part1.getChildren().add(imagenView);
                    imagenView.setId("img_paso1");
                    imagenView.setFitWidth(120);
                    imagenView.setFitHeight(120);

                    Label tipo = new Label(base.getTipo());
                    part1.getChildren().add(tipo);
                    tipo.setId("lb_tipo_paso1");

                    Label precio = new Label("$"+String.valueOf(base.getPrecio()));
                    Contenedor_base.getChildren().add(precio);
                    precio.setId("lb_precio_paso1");
                    part1.getChildren().addAll();



                    Contenedor_base.setOnMouseClicked(MouseEvent -> onPrecioClick(base,Contenedor_base));
                    contenedor_part2_paso1.getChildren().add(Contenedor_base);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

    }
    private static Base baseSeleccionada;

    public static Base getBaseSeleccionada() {
        return baseSeleccionada;
    }

    public static void setBaseSeleccionada(Base baseSeleccionada) {
        Paso1Controller.baseSeleccionada = baseSeleccionada;
    }

    private VBox elementoSeleccionado;
    private void onPrecioClick(Base base,VBox contenedorBase){
        pre1_paso1.setText("Valor a pagar: $" + String.valueOf(base.getPrecio()));

        if (elementoSeleccionado != null) {
            elementoSeleccionado.setStyle(null);
            baseSeleccionada = null;
        }

        elementoSeleccionado = contenedorBase;
        baseSeleccionada = base;
        contenedorBase.setStyle("-fx-background-color: #FFE5B4;");
    }
}
