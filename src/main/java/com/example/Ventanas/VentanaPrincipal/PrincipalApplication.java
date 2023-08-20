package com.example.Ventanas.VentanaPrincipal;

import com.example.Ventanas.Controller.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import com.example.Ventanas.classes.*;

import java.io.IOException;

public class PrincipalApplication extends Application {
    @Override
    public void start(Stage PrincipalStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene Scene = new Scene(root,1000,800);
        PrincipalStage.setScene(Scene);
        PrincipalController controller =  fxmlLoader.getController();
        controller.setStage(PrincipalStage);
        PrincipalStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}