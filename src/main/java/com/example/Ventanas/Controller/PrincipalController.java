package com.example.Ventanas.Controller;

import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {
    @FXML
    private PasswordField TFcontrasena;

    @FXML
    private TextField TFusuario;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    void goPassVentana(ActionEvent event) throws IOException{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader2 = new FXMLLoader(PrincipalApplication.class.getResource("emergente-view.fxml"));
        Parent root2 = fxmlLoader2.load();
        Scene scene2 = new Scene(root2,400,600);
        Stage stage2 = new Stage();
        EmergenteController controller2 =  fxmlLoader2.getController();
        stage2.initModality(Modality.NONE);
        stage2.setScene(scene2);
        stage2.show();

        FXMLLoader fxmlLoader1 = new FXMLLoader(PrincipalApplication.class.getResource("pass-view.fxml"));
        Parent root1 = fxmlLoader1.load();
        Scene scene1 = new Scene(root1,1000,800);
        Stage stage1 = new Stage();
        PassController controller1 =  fxmlLoader1.getController();
        stage1.initModality(Modality.NONE);
        stage1.setScene(scene1);
        stage1.show();
    }

    public void setStage(Stage PrincipalStage){

    }
}