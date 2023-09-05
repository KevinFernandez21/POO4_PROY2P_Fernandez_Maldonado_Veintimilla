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
import javafx.stage.Modality;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.Ventanas.Controller.PrincipalController.nombreCliente;
import static java.time.Duration.*;

public class PedidoController implements Initializable {
    @FXML
    private Label lb_titulo_pedido;

    @FXML
    private AnchorPane contenedor_padre_pedido;

    @FXML
    private AnchorPane contenedor_part1_pedido;

    @FXML
    private AnchorPane contenedor_part2_pedido;

    @FXML
    private AnchorPane contenedor_part3_pedido;

    @FXML
    private Button btnContinuarPedido_pedido;

    @FXML
    private Button btneliminar_pedido;

    @FXML
    private FlowPane pedidoArea_pedido;

    @FXML
    private Label pre4_pedido;

    @FXML
    private Button btnCancelar_pedido;
    static Double precio_recolectado = 0.00;
    public static Pedido pedidoActual;

    /**
     * valida la decision de cancelar un sabor del pedido, tambien elimina el sabor especificado por el usuario
     * @param event
     */
    @FXML
    void AccionCancelar(ActionEvent event){
        Stage emergente = new Stage();
        VBox newroot = new VBox();
        newroot.setAlignment(Pos.CENTER);
        Scene scene = new Scene(newroot);
        newroot.setId("contenedor_padre_emergente");

        Label part1 = new Label("¿Estas seguro que quieres cancelar?");
        newroot.getChildren().add(part1);
        part1.setId("msj_emergente");

        HBox part2 = new HBox();
        part2.setId("part2_emergente");
        newroot.getChildren().add(part2);

        Button confirmar = new Button("Aceptar");
        confirmar.setId("bt1_emergente");
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
                goPassVentana(event,confirmar);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) confirmar.getScene().getWindow();
            stage.close();
        });

        Button cancelar = new Button("Cancelar");
        cancelar.setId("bt2_emergente");
        part2.getChildren().add(cancelar);
        cancelar.setOnAction(ActionEvent->{
            Stage stage = (Stage) cancelar.getScene().getWindow();
            stage.close();
        });

        emergente.setTitle("Confirmacion");

        emergente.setScene(scene);
        emergente.show();
    }

    /**
     * retorna a la escena paso1-view
     * @param event
     * @param b
     * @throws IOException
     */
    void goPassVentana(ActionEvent event,Button b) throws IOException{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        //stage.close();

        FXMLLoader fxmlLoader2 = new FXMLLoader(PrincipalApplication.class.getResource("emergente-view.fxml"));
        Parent root2 = fxmlLoader2.load();

        Scene scene2 = new Scene(root2);
        Stage stage2 = new Stage();

        stage2.initModality(Modality.NONE);
        stage2.setScene(scene2);
        stage2.show();


        FXMLLoader fxmlLoader1 = new FXMLLoader(PrincipalApplication.class.getResource("pass-view.fxml"));
        Parent root1 = fxmlLoader1.load();

        PassController passController = fxmlLoader1.getController();
        passController.setVentanaEmergente(stage2);

        Scene scene1 = new Scene(root1);
        stage.setScene(scene1);
    }
    /**
     * Borra un sabor del pedido, solo lo hace si hay más de un sabor
     * @param event
     */
    @FXML
    void borrarSabor(ActionEvent event) {
        if (elementoSeleccionado != null) {
            Stage emergente = new Stage();
            VBox newroot = new VBox();
            newroot.setAlignment(Pos.CENTER);
            Scene scene = new Scene(newroot);
            newroot.setId("contenedor_padre_emergente");

            Label part1 = new Label("¿Estas seguro de eliminar el componente?");
            newroot.getChildren().add(part1);
            part1.setId("msj_emergente");

            HBox part2 = new HBox();
            part2.setId("part2_emergente");
            newroot.getChildren().add(part2);

            Button confirmar = new Button("Aceptar");
            confirmar.setId("bt1_emergente");
            part2.getChildren().add(confirmar);
            confirmar.setOnAction(ActionEvent->{
                if (elementoSeleccionado.getText().startsWith("Sabor")) {
                    if (elementoSeleccionado.getText().startsWith("Sabor1")) {
                        if (Paso2Controller.getValorseleccionado2() != null) {
                            calcularPrecio(Paso2Controller.getValorseleccionado1().getPrecio());
                            Paso2Controller.setValorseleccionado1(null);
                            pedidoArea_pedido.getChildren().remove(elementoSeleccionado);
                            elementoSeleccionado = null;
                        } else {
                            mostrarMensaje("Debe haber al menos un sabor seleccionado.");
                        }
                    } else if (elementoSeleccionado.getText().startsWith("Sabor2")) {
                        if (Paso2Controller.getValorseleccionado1() != null) {
                            calcularPrecio(Paso2Controller.getValorseleccionado2().getPrecio());
                            Paso2Controller.setValorseleccionado2(null);
                            pedidoArea_pedido.getChildren().remove(elementoSeleccionado);
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
            cancelar.setId("bt1_emergente");
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
    private DecimalFormat decimalFormat = new DecimalFormat("#.0");
    /**
     * Actualizaq el precio del pedido después de eliminar un sabor
     * @param d
     */
    private void calcularPrecio(Double d) {
        precio_recolectado = precio_recolectado-d;
        String formattedTotal = decimalFormat.format(precio_recolectado);
        pre4_pedido.setText("Valor a pagar: " + String.valueOf(formattedTotal));
    }

    /**
     * invoca una ventana emergente
     * @param mensaje
     */
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
        String formattedTotal = decimalFormat.format(precio_recolectado);
        pre4_pedido.setText("Valor a pagar: " + String.valueOf(formattedTotal));
    }
    private Label elementoSeleccionado = null;

    /**
     * carga el pedido del usuario en la escena
     */
    public void cargarPedido(){

        pedidoArea_pedido.getChildren().clear();
        pedidoArea_pedido.setOrientation(Orientation.VERTICAL);

        Label base = new Label("Base: "+Paso1Controller.getBaseSeleccionada().getTipo());
        pedidoArea_pedido.getChildren().add(base);
        base.setId("lb_aparicion");
        base.setOnMouseClicked(MouseEvent->seleccionarElemento(base,Paso1Controller.getBaseSeleccionada()));

        Sabor exite = Paso2Controller.getValorseleccionado2();
        if (exite == null) {
            Label sabor1 = new Label("Sabor1: "+Paso2Controller.getValorseleccionado1().getTipo());
            pedidoArea_pedido.getChildren().add(sabor1);
            sabor1.setId("lb_aparicion");
            sabor1.setOnMouseClicked(MouseEvent->seleccionarElemento(sabor1,Paso2Controller.getValorseleccionado1()));
        } else {
            Label sabor1 = new Label("Sabor1: "+Paso2Controller.getValorseleccionado1().getTipo());
            pedidoArea_pedido.getChildren().add(sabor1);
            sabor1.setId("lb_aparicion");
            sabor1.setOnMouseClicked(MouseEvent->seleccionarElemento(sabor1,Paso2Controller.getValorseleccionado1()));

            Label sabor2 = new Label("Sabor2: "+Paso2Controller.getValorseleccionado2().getTipo());
            pedidoArea_pedido.getChildren().add(sabor2);
            sabor2.setId("lb_aparicion");
            sabor2.setOnMouseClicked(MouseEvent->seleccionarElemento(sabor2,Paso2Controller.getValorseleccionado1()));
        }

        int num = 0;
        for (Topping topping: Paso3Controller.getToppingsseleccionado()){
            num++;
            Label topping1 = new Label("Topping "+num+": "+topping.getTipo());
            pedidoArea_pedido.getChildren().add(topping1);
            topping1.setId("lb_aparicion");
            topping1.setOnMouseClicked(MouseEvent->seleccionarElemento(topping1,Paso2Controller.getValorseleccionado1()));
        }
    }

    /**
     * añade un fondo gris a los elementos seleccionados por el usuario
     * @param elemento
     * @param o
     */
    private void seleccionarElemento(Label elemento,Object o) {
        if (elementoSeleccionado != null) {
            elementoSeleccionado.setStyle("");
        }

        elemento.setStyle("-fx-background-color: #FFE5B4;");
        elementoSeleccionado = elemento;
    }

    /**
     * cambia la escena a pago-view
     * @param event
     * @throws IOException
     */
    @FXML
    void toPago(ActionEvent event) throws IOException {
        Pedido pe = new Pedido(nombreCliente, precio_recolectado);
        pedidoActual = pe;
        Pedido.escribirArchivo(pe);
        pe.escribirBin();
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("pago-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Pago");
        window.setScene(scene);
        window.show();

    }
}
