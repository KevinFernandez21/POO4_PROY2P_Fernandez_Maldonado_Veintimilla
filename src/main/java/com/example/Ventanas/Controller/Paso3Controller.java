package com.example.Ventanas.Controller;

import com.example.Ventanas.classes.Sabor;
import com.example.Ventanas.classes.Topping;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Paso3Controller implements Initializable {
    @FXML
    private Button btnContinuar3;

    @FXML
    private Label lb_titulo;

    @FXML
    private AnchorPane contenedor_padre;

    @FXML
    private AnchorPane contenedor_part1;

    @FXML
    protected FlowPane contenedor_part2;

    @FXML
    private AnchorPane contenedor_part3;

    @FXML
    protected Label pre3;
    @FXML
    void goPedido(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("pedido-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);
        URL cssFile = PrincipalApplication.class.getResource("/css/pedido.css");
        scene.getStylesheets().add(cssFile.toExternalForm());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Pedido");
        window.setScene(scene);
        window.show();
    }
    public Double calcularPrecioRecolectado() {
        Double precio_recolectado = 0.00;
        Sabor exite = Paso2Controller.getValorseleccionado2();

        if (exite == null) {
            precio_recolectado = Paso1Controller.getBaseSeleccionada().getPrecio() + Paso2Controller.getValorseleccionado1().getPrecio();
        } else {
            precio_recolectado = Paso1Controller.getBaseSeleccionada().getPrecio() + Paso2Controller.getValorseleccionado1().getPrecio() + Paso2Controller.getValorseleccionado2().getPrecio();
        }
        return precio_recolectado;
    }
    public void initialize(URL location, ResourceBundle resources) {
        cargarTopping();
        pre3.setText("Valor a pagar: " + String.valueOf(calcularPrecioRecolectado()));
    }
    public void cargarTopping(){
        contenedor_part2.getChildren().clear();
        contenedor_part2.setOrientation(Orientation.VERTICAL);
        try{
            ArrayList<Topping> listatopping = Topping.leerTopping();
            for(Topping topping: listatopping){
                HBox contenedor = new HBox();
                contenedor_part2.getChildren().add(contenedor);

                CheckBox select = new CheckBox();
                contenedor.getChildren().add(select);

                Label etiqueta = new Label(topping.toString());
                contenedor.getChildren().add(etiqueta);

                select.setOnAction(actionEvent -> selectCheck(select,topping));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private double totalTopping = 0.00;
    private static ArrayList<Topping> toppingsseleccionado = new ArrayList<>();

    public static ArrayList<Topping> getToppingsseleccionado() {
        return toppingsseleccionado;
    }

    public static void setToppingsseleccionado(ArrayList<Topping> toppingsseleccionado) {
        Paso3Controller.toppingsseleccionado = toppingsseleccionado;
    }

    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    public void selectCheck(CheckBox checkBox, Topping t){

        if (checkBox.isSelected()) {
            totalTopping += t.getPrecio();
            toppingsseleccionado.add(t);
        } else {
            totalTopping -= t.getPrecio();
            toppingsseleccionado.remove(t);
        }
        double total2 = totalTopping + calcularPrecioRecolectado();
        String formattedTotal = decimalFormat.format(total2);
        pre3.setText("Valor a pagar: " + formattedTotal);
    }
}
