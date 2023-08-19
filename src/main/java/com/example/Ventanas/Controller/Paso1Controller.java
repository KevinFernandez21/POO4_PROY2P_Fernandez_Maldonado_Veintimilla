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


public class Paso1Controller {
    @FXML
    private Label welcomeText;
    
    @FXML
    private Button btnContinuar1;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    
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
}
