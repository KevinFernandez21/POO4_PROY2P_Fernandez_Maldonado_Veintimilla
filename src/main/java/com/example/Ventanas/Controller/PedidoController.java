package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Base;
import com.example.Ventanas.classes.Pedido;
import com.example.Ventanas.classes.Sabor;
import com.example.Ventanas.classes.Topping;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PedidoController implements Initializable {
    @FXML
    private Button btnContinuarPedido;

    @FXML
    private Button btneliminar;

    @FXML
    private FlowPane pedidoArea;

    @FXML
    private Label pre4;

    @FXML
    void borrarSabor(ActionEvent event) {
        if (hayDosSabores()) {
            boolean confirmado = mostrarVentanaConfirmacion();
            if (confirmado) {
                eliminarSabor();
            }
        } else {

            mostrarMensajeError();
        }
    }
    private boolean hayDosSabores() {
        Sabor exite = Paso2Controller.getValorseleccionado2();
        if (exite == null) {
            return false;
        } else {
            return true;
        }
    }

    private void eliminarSabor() {

    }


    private void mostrarMensajeError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Ups, no hay suficientes sabores para eliminar.");
        alert.showAndWait();
    }
    private boolean mostrarVentanaConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres eliminar un sabor?");

        ButtonType botonConfirmar = new ButtonType("Confirmar");
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(botonConfirmar, botonCancelar);


        return alert.showAndWait().filter(response -> response == botonConfirmar).isPresent();
    }
    Double precio_recolectado = 0.00;
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        cargarPedido();

        Sabor exite = Paso2Controller.getValorseleccionado2();

        if (exite == null) {
            precio_recolectado = Paso1Controller.getBaseSeleccionada().getPrecio() + Paso2Controller.getValorseleccionado1().getPrecio();
        } else {
            precio_recolectado = Paso1Controller.getBaseSeleccionada().getPrecio() + Paso2Controller.getValorseleccionado1().getPrecio() + Paso2Controller.getValorseleccionado2().getPrecio();
        }
        for(Topping topping:Paso3Controller.getToppingsseleccionado()){
            precio_recolectado += topping.getPrecio();
        }
        pre4.setText("Valor a pagar: " + String.valueOf(precio_recolectado));
    }
    public void cargarPedido(){
        pedidoArea.getChildren().clear();
        pedidoArea.setOrientation(Orientation.VERTICAL);

        Label base = new Label("Base: "+Paso1Controller.getBaseSeleccionada());
        pedidoArea.getChildren().add(base);

        Sabor exite = Paso2Controller.getValorseleccionado2();

        if (exite == null) {

        } else {

        }

    }
    @FXML
    void toPago(ActionEvent event) throws IOException {


        //----------------------------------------
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("pago-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Pago");
        window.setScene(scene);
        window.show();

    }
}
