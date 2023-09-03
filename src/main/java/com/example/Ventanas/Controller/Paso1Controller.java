package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Base;
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
import javafx.scene.input.MouseEvent;
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
    private Label welcomeText;
    
    @FXML
    private Button btnContinuar1;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private FlowPane container_paso1;
    @FXML
    private ToggleGroup group = new ToggleGroup();
    @FXML
    private Label pre1;

    //externo
    private ArrayList<Base> listaBase;
    @FXML
    void goPaso2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("paso2-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Paso2");
        window.setScene(scene);
        window.show();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarBase();
    }

    public void cargarBase(){
        container_paso1.getChildren().clear();
        container_paso1.setOrientation(Orientation.HORIZONTAL);
        System.out.println("Se esta leyendo la base");
        try{
            listaBase = Base.leerBase();
            for(Base base: listaBase){
                Path file = Paths.get("src", "main","resources","Archivos","imagenes","base",base.getTipo()+".png");

                VBox Contenedor_base = new VBox();

                HBox part1 = new HBox();

                Contenedor_base.getChildren().add(part1);

                FileInputStream fis = new FileInputStream(file.toFile());
                ImageView imagenView = new ImageView(new Image(fis));
                part1.getChildren().add(imagenView);
                imagenView.setFitWidth(80);
                imagenView.setFitHeight(80);

                Label tipo = new Label(base.getTipo());
                part1.getChildren().add(tipo);

                Label precio = new Label(String.valueOf(base.getPrecio()));
                Contenedor_base.getChildren().add(precio);
                part1.getChildren().addAll();


                Contenedor_base.setOnMouseClicked(MouseEvent -> onPrecioClick(base,Contenedor_base));
                container_paso1.getChildren().add(Contenedor_base);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static Base baseSeleccionada;

    public static Base getBaseSeleccionada() {
        return baseSeleccionada;
    }

    private VBox elementoSeleccionado;
    private void onPrecioClick(Base base,VBox contenedorBase){
        pre1.setText("Precio: " + String.valueOf(base.getPrecio()));

        if (elementoSeleccionado != null) {
            elementoSeleccionado.setStyle(null);
            baseSeleccionada = null;
        }

        elementoSeleccionado = contenedorBase;
        baseSeleccionada = base;
        contenedorBase.setStyle("-fx-background-color: orange;");
    }
}
