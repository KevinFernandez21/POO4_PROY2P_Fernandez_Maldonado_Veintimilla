package com.example.Ventanas.Controller;

import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PagoController {
    @FXML
    private Label welcomeText;
    
    @FXML
    private Button btnConfirmar;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    
    @FXML
    void toCompletado(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("completado-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Completado");
        window.setScene(scene);
        window.show();

    }
}
