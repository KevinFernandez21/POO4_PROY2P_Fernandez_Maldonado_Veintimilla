package com.example.Ventanas.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Paso2Controller {
    @FXML
    private Label welcomeText;
    
    @FXML
    private Button btnContinuar2;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    
    @FXML
    void toPaso3(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("paso3-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Paso3");
        window.setScene(scene);
        window.show();

    }
}
