package com.example.Ventanas.classes;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.util.Duration;

public class incompleteStageException extends Exception {
    public incompleteStageException(String mensaje){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);

            ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);


            // Agregar el archivo CSS personalizado al DialogPane
            

            PauseTransition delay = new PauseTransition(Duration.seconds(7));
            delay.setOnFinished(event -> alert.close());

            alert.show();

            delay.play();

    }
}
