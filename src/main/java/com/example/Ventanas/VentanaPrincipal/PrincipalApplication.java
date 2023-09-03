package com.example.Ventanas.VentanaPrincipal;

import com.example.Ventanas.Controller.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.example.Ventanas.classes.*;

import java.io.IOException;

public class PrincipalApplication extends Application {
    public static String rutaFiles = "src/main/resources/Archivos/";
    public static String rutaImagen = "src/main/resources/Archivos/imagenes.icono/";
    @Override
    public void start(Stage PrincipalStage) throws IOException {
        String cssFile = getClass().getResource("/css/principal.css").toExternalForm();
        setUserAgentStylesheet(cssFile);

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root,700,700);
        PrincipalStage.setScene(scene);
        PrincipalController controller =  fxmlLoader.getController();
        controller.setStage(PrincipalStage);
        PrincipalStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}