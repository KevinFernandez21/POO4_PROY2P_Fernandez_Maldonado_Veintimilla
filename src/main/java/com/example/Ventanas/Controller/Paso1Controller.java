package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Base;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


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
        System.out.println("Controlador inicializado.");
        cargarBase();
    }
    public void cargarBase(){
        container_paso1.getChildren().clear();
        container_paso1.setOrientation(Orientation.VERTICAL);
        System.out.println("Se esta leyendo la base");
        try{
            listaBase = Base.leerBase();
            for(Base base: listaBase){
                CheckBox tipo = new CheckBox(base.getTipo());
                container_paso1.getChildren().add(tipo);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
