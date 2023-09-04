package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Base;
import com.example.Ventanas.classes.Pedido;
import com.example.Ventanas.classes.Sabor;
import com.example.Ventanas.classes.Topping;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.Ventanas.Controller.PrincipalController.nombreCliente;
import static java.time.Duration.*;

public class PedidoController implements Initializable {
    @FXML
    private Label lb_titulo;

    @FXML
    private AnchorPane contenedor_padre;

    @FXML
    private AnchorPane contenedor_part1;

    @FXML
    private AnchorPane contenedor_part2;

    @FXML
    private AnchorPane contenedor_part3;

    @FXML
    private Button btnContinuarPedido;

    @FXML
    private Button btneliminar;

    @FXML
    private FlowPane pedidoArea;

    @FXML
    private Label pre4;

    @FXML
    private Button btnCancelar;
    static Double precio_recolectado = 0.00;
    @FXML
    void AccionCancelar(ActionEvent event){
        Stage emergente = new Stage();
        VBox newroot = new VBox();
        newroot.setAlignment(Pos.CENTER);
        Scene scene = new Scene(newroot,300,320);

        Label part1 = new Label("¿Estas seguro que quieres cancelar?");
        newroot.getChildren().add(part1);

        HBox part2 = new HBox();
        newroot.getChildren().add(part2);

        Button confirmar = new Button("Aceptar");
        part2.getChildren().add(confirmar);
        confirmar.setOnAction(ActionEvent-> {
            Paso1Controller.setBaseSeleccionada(null);
            Sabor exite2 = Paso2Controller.getValorseleccionado2();
            Sabor exite1 = Paso2Controller.getValorseleccionado1();

            if (exite1 == null) {
                Paso2Controller.setValorseleccionado2(null);
            }else if (exite2 == null) {
                Paso2Controller.setValorseleccionado1(null);
            }else {
                Paso2Controller.setValorseleccionado1(null);
                Paso2Controller.setValorseleccionado2(null);
            }
            Paso3Controller.getToppingsseleccionado().clear();

            try {
                goPaso1(event,confirmar);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Button cancelar = new Button("Cancelar");
        part2.getChildren().add(cancelar);
        cancelar.setOnAction(ActionEvent->{
            Stage stage = (Stage) cancelar.getScene().getWindow();
            stage.close();
        });

        emergente.setTitle("Confirmacion");
        emergente.setScene(scene);
        emergente.show();
    }
    void goPaso1(ActionEvent event,Button b) throws IOException {
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("paso1-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);
        URL cssFile = PrincipalApplication.class.getResource("/css/paso1.css");
        scene.getStylesheets().add(cssFile.toExternalForm());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Paso1");
        window.setScene(scene);
        window.show();
    }
    @FXML
    void borrarSabor(ActionEvent event) {
        if (elementoSeleccionado != null) {
            Stage emergente = new Stage();
            VBox newroot = new VBox();
            newroot.setAlignment(Pos.CENTER);
            Scene scene = new Scene(newroot,300,320);

            Label part1 = new Label("¿Estas seguro de eliminar el componente?");
            newroot.getChildren().add(part1);

            HBox part2 = new HBox();
            newroot.getChildren().add(part2);

            Button confirmar = new Button("Aceptar");
            part2.getChildren().add(confirmar);
            confirmar.setOnAction(ActionEvent->{
                if (elementoSeleccionado.getText().startsWith("Sabor")) {
                    if (elementoSeleccionado.getText().startsWith("Sabor1")) {
                        if (Paso2Controller.getValorseleccionado2() != null) {
                            calcularPrecio(Paso2Controller.getValorseleccionado1().getPrecio());
                            Paso2Controller.setValorseleccionado1(null);
                            pedidoArea.getChildren().remove(elementoSeleccionado);
                            elementoSeleccionado = null;
                        } else {
                            mostrarMensaje("Debe haber al menos un sabor seleccionado.");
                        }
                    } else if (elementoSeleccionado.getText().startsWith("Sabor2")) {
                        if (Paso2Controller.getValorseleccionado1() != null) {
                            calcularPrecio(Paso2Controller.getValorseleccionado2().getPrecio());
                            Paso2Controller.setValorseleccionado2(null);
                            pedidoArea.getChildren().remove(elementoSeleccionado);
                            elementoSeleccionado = null;
                        } else {
                            mostrarMensaje("Debe haber al menos un sabor seleccionado.");
                        }
                    }
                } else {
                    mostrarMensaje("Solo se pueden borrar sabores.");
                }
                Stage stage = (Stage) confirmar.getScene().getWindow();
                stage.close();
            });
            Button cancelar = new Button("Cancelar");
            part2.getChildren().add(cancelar);
            cancelar.setOnAction(ActionEvent->{
                Stage stage = (Stage) cancelar.getScene().getWindow();
                stage.close();
            });
            emergente.setTitle("Confirmacion");
            emergente.setScene(scene);
            emergente.show();
        } else {
            mostrarMensaje("Por favor, seleccione algo para eliminar.");
        }
    }
    private void calcularPrecio(Double d) {
        precio_recolectado = precio_recolectado-d;
        pre4.setText("Valor a pagar: " + String.valueOf(precio_recolectado));
    }


    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        PauseTransition delay = new PauseTransition(Duration.seconds(7));
        delay.setOnFinished(event -> alert.close());

        alert.show();

        delay.play();
    }
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
    private Label elementoSeleccionado = null;

    public void cargarPedido(){

        pedidoArea.getChildren().clear();
        pedidoArea.setOrientation(Orientation.VERTICAL);

        Label base = new Label("Base: "+Paso1Controller.getBaseSeleccionada().getTipo());
        pedidoArea.getChildren().add(base);
        base.setOnMouseClicked(MouseEvent->seleccionarElemento(base,Paso1Controller.getBaseSeleccionada()));

        Sabor exite = Paso2Controller.getValorseleccionado2();
        if (exite == null) {
            Label sabor1 = new Label("Sabor1: "+Paso2Controller.getValorseleccionado1().getTipo());
            pedidoArea.getChildren().add(sabor1);
            sabor1.setOnMouseClicked(MouseEvent->seleccionarElemento(sabor1,Paso2Controller.getValorseleccionado1()));
        } else {
            Label sabor1 = new Label("Sabor1: "+Paso2Controller.getValorseleccionado1().getTipo());
            pedidoArea.getChildren().add(sabor1);
            sabor1.setOnMouseClicked(MouseEvent->seleccionarElemento(sabor1,Paso2Controller.getValorseleccionado1()));

            Label sabor2 = new Label("Sabor2: "+Paso2Controller.getValorseleccionado2().getTipo());
            pedidoArea.getChildren().add(sabor2);
            sabor2.setOnMouseClicked(MouseEvent->seleccionarElemento(sabor2,Paso2Controller.getValorseleccionado1()));
        }

        int num = 0;
        for (Topping topping: Paso3Controller.getToppingsseleccionado()){
            num++;
            Label topping1 = new Label("Topping "+num+": "+topping.getTipo());
            pedidoArea.getChildren().add(topping1);
            topping1.setOnMouseClicked(MouseEvent->seleccionarElemento(topping1,Paso2Controller.getValorseleccionado1()));
        }
    }
    private void seleccionarElemento(Label elemento,Object o) {
        if (elementoSeleccionado != null) {
            elementoSeleccionado.setStyle("");
        }

        elemento.setStyle("-fx-background-color: lightgray;");
        elementoSeleccionado = elemento;
    }
    @FXML
    void toPago(ActionEvent event) throws IOException {
        Pedido pe = new Pedido(nombreCliente, precio_recolectado);
        Pedido.escribirArchivo(pe);
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("pago-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);
        URL cssFile = PrincipalApplication.class.getResource("/css/pago.css");
        scene.getStylesheets().add(cssFile.toExternalForm());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Pago");
        window.setScene(scene);
        window.show();

    }
}
