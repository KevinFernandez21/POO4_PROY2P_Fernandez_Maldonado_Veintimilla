package com.example.Ventanas.Controller;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.Ventanas.classes.Sabor;
import com.example.Ventanas.classes.incompleteStageException;
import com.example.Ventanas.classes.metodoPago;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.Ventanas.Controller.PedidoController.pedidoActual;
import static com.example.Ventanas.VentanaPrincipal.PrincipalApplication.rutaFiles;

public class PagoController implements Initializable  {

    @FXML
    private VBox contenedor_padre_pago;

    @FXML
    private AnchorPane contenedor_part1_pago;

    @FXML
    private AnchorPane contenedor_part2_pago;

    @FXML
    private AnchorPane contenedor_part3_pago;

    @FXML
    private AnchorPane contenedor_part4_pago;

    @FXML
    private Label lb_titulo_pago;

    @FXML
    private Label lb_vap_pago;

    @FXML
    private Label lb_at_pago;

    @FXML
    private Label lb_iva_pago;

    @FXML
    private Label lb_tap_pago;
    @FXML
    private Button btnCancelar_pago;
    @FXML
    private Button btnConfirmar_pago;

    @FXML
    private RadioButton btTarteja_pago;

    @FXML
    private RadioButton btEfectivo_pago;

    @FXML
    private TextField txtValor_pago;

    @FXML
    private TextField txtAdicional_pago;

    @FXML
    private TextField txtIva_pago;

    @FXML
    private TextField txtTotal_pago;

    @FXML
    private ToggleGroup ModoPago_pago;

    @FXML
    private VBox CreditoBox_pago;




    private static Set<Integer> idPagos = new HashSet<>();


    //Mi idea es que el boton continuar se sete al checkNull, donde si pasa todas las condiciones entonces se invoca a toCompletado
    void checkNull(){
        RadioButton tipoPago = (RadioButton) ModoPago_pago.getSelectedToggle();
        if(tipoPago == null){
            System.out.println("Excepcion aca");
        }else if (tipoPago.getText().equals("Efectivo")){
            metodoPago m = metodoPago.E;
        }else{
            metodoPago m = metodoPago.C;
        }


    }

    /**
     * genera una Id para los pagos, tambien se asegura de que las Id no se repitan
     * @return una Id para el pago
     */
    private int genIdPago(){
        int a = idPagos.size();
        int num = 0;
        while(idPagos.size()==a){
            num = (int) (Math.random()*49)+1;
            idPagos.add(num);
            System.out.println("id del pedido: "+num);
        }
        return num;
    }

    /**
     * Escribe el pago realizado en el archivo pagos.txt
     */
    private void writePago(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFiles+"pagos.txt",true))){
            String valor = txtValor_pago.getText();
            String plus = txtAdicional_pago.getText();
            String iva = txtIva_pago.getText();
            String total = txtTotal_pago.getText();
            int idP = genIdPago();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Genera un layout con textFields para la transacción con tarjeta de credito, se genera solo si el usuario selecciono la opcion de pago con tarjeta de credito
     */
    private void parteCredito(){
        if(btTarteja_pago.isSelected()){
            CreditoBox_pago.getChildren().clear();
            Label lb1 = new Label("Inserte los datos de su tarjeta");
            lb1.setFont(new Font("System Bold",30));
            lb1.setId("lb_titulo_pago3");
            lb1.setPadding(new Insets(0,0,0,55));
            HBox linea1 = new HBox();
            Label lbName = new Label("Nombre:");
            lbName.setPadding(new Insets(0,0,0,55));
            lbName.setId("lb_tap_pago");
            TextField tfName = new TextField();
            tfName.setId("txtValor_pago");
            linea1.getChildren().addAll(lbName,tfName);


            HBox linea2 = new HBox();
            Label lbNumero = new Label("Numero:");
            lbNumero.setId("lb_tap_pago");
            lbNumero.setPadding(new Insets(0,0,0,55));
            TextField tfNumero = new TextField();
            tfNumero.setId("txtValor_pago");
            linea2.getChildren().addAll(lbNumero,tfNumero);


            HBox linea3 = new HBox();
            Label lbFecha = new Label("Fecha Caducidad:");
            lbFecha.setId("lb_tap_pago");
            lbFecha.setPadding(new Insets(0,0,0,55));

            DatePicker dPicker = new DatePicker();
            dPicker.setId("txtValor_pago");
            linea3.getChildren().addAll(lbFecha,dPicker);


            HBox linea4 = new HBox();
            Label lbCVV = new Label("CVV");
            lbCVV.setPadding(new Insets(0,0,0,55));
            lbCVV.setId("lb_tap_pago");

            TextField tfCVV = new TextField();
            tfCVV.setId("txtValor_pago");
            linea4.getChildren().addAll(lbCVV,tfCVV);


            CreditoBox_pago.getChildren().addAll(lb1,linea1,linea2,linea3,linea4);
            CreditoBox_pago.setAlignment(Pos.CENTER_LEFT);
            CreditoBox_pago.setSpacing(10);
        }

    }


    /**
     * calcula los valores de iva y adicionales que se suman al valor total
     * @param conCredito
     */
    private void calcDatos(boolean conCredito){
        Double subtotal = Double.parseDouble(txtValor_pago.getText());
        Double extra = 0.0;
        if(conCredito){
            extra = (subtotal+extra)*0.10;
        }

        Double iva = subtotal*0.12;
        Double total = subtotal + iva + extra;

        txtAdicional_pago.setText(String.format("%.2f", extra));
        txtIva_pago.setText(String.format("%.2f", iva));
        txtTotal_pago.setText(String.format("%.2f", total));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        btEfectivo_pago.getStyleClass().add("radio-button-container");
        btTarteja_pago.getStyleClass().add("radio-button-container");
        //  txtValor.setText("4.12");
        txtValor_pago.setText(String.valueOf(pedidoActual.getTotal()));
        btTarteja_pago.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                parteCredito();
                calcDatos(true);
            }

        });

        btEfectivo_pago.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(btEfectivo_pago.isSelected()){
                    CreditoBox_pago.getChildren().clear();
                    Label lb2 = new Label("Acercate a caja para pagar tu pedido");
                    lb2.setFont(new Font("System Bold Italic",31));
                    CreditoBox_pago.getChildren().add(lb2);
                    CreditoBox_pago.setAlignment(Pos.CENTER);
                }
                calcDatos(false);
            }
        });

    }
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
    @FXML
    void AccionCancelar(ActionEvent event){
        Stage emergente = new Stage();
        VBox newroot = new VBox();
        Scene scene = new Scene(newroot);
        newroot.setId("contenedor_padre_emergente");

        Label part1 = new Label("¿Estas seguro que quieres cancelar?");
        newroot.getChildren().add(part1);
        part1.setId("msj_emergente");

        HBox part2 = new HBox();
        newroot.getChildren().add(part2);
        part2.setId("part2_emergente");


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
     * Cambia la escena a completado-view
     * @param event
     * @throws IOException
     */
    @FXML
    void toCompletado(ActionEvent event) throws IOException {
            try {
                if(ModoPago_pago.getSelectedToggle()==null){
                    throw new incompleteStageException("No se ha seleccionado nada.");
                }else {
                    FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("completado-view.fxml"));
                    Parent p = fxmlLoader.load();
                    Scene scene = new Scene(p);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setTitle("Completado");
                    window.setScene(scene);
                    window.show();
                }
            }catch (incompleteStageException e) {

            }
        }
}
