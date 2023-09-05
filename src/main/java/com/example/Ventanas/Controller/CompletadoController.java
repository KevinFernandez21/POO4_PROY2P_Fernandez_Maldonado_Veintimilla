package com.example.Ventanas.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.Ventanas.Controller.PedidoController.pedidoActual;

public class CompletadoController implements Initializable {
    @FXML
    private Label lbWelcome;

    @FXML
    private AnchorPane contenedor_padre_final;

    @FXML
    private AnchorPane contenedor_part1_final;

    @FXML
    private FlowPane contenedor_part2_final;

    @FXML
    private AnchorPane contenedor_part3_final;

    @FXML
    private Label lb_titulo_final;

    @FXML
    private Label lb_tiempo_final;



    public void initialize(URL url, ResourceBundle resourceBundle){
        String imageUrl = "https://media.giphy.com/media/IbgVl6J5cjw7g3ZuGt/giphy.gif";
        Image image = new Image(imageUrl, true);
        ImageView imageView = new ImageView(new Image(imageUrl, true));
        contenedor_part2_final.getChildren().add(imageView);
        imageView.setFitWidth(450);
        imageView.setFitHeight(250);
    }

}
