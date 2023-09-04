package com.example.Ventanas.classes;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class incompleteStageException extends Exception {
    public incompleteStageException(String mensaje){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);

            PauseTransition delay = new PauseTransition(Duration.seconds(7));
            delay.setOnFinished(event -> alert.close());

            alert.show();

            delay.play();

    }
}
