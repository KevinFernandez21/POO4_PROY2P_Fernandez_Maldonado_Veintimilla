package com.example.Ventanas.Controller;
import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.Ventanas.classes.Pago;
import com.example.Ventanas.classes.incompleteStageException;
import com.example.Ventanas.classes.metodoPago;

import com.example.Ventanas.interfaz.Pagable;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.time.LocalDate;

import static com.example.Ventanas.Controller.PedidoController.pedidoActual;
import static com.example.Ventanas.Controller.PrincipalController.nombreCliente;
import static com.example.Ventanas.VentanaPrincipal.PrincipalApplication.rutaFiles;

public class PagoController implements Initializable, Pagable {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btnConfirmar;

    @FXML
    private RadioButton btTarteja;

    @FXML
    private RadioButton btEfectivo;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtAdicional;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtTotal;

    @FXML
    private ToggleGroup ModoPago;

    @FXML
    private VBox CreditoBox;

    private static Set<Integer> idPagos = new HashSet<>();

    //Variables para pago

    String fechaCaducidad;
    Double finalTotal;
    ///////////////////


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
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
            String valor = txtValor.getText();
            String plus = txtAdicional.getText();
            String iva = txtIva.getText();
            String total = txtTotal.getText();
            int idP = genIdPago();


        }catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Genera un layout con textFields para la transacción con tarjeta de credito, se genera solo si el usuario selecciono la opcion de pago con tarjeta de credito
     */
    private void parteCredito() {
        if(btTarteja.isSelected()){
            CreditoBox.getChildren().clear();
            Label lb1 = new Label("Inserte los datos de su tarjeta");
            lb1.setFont(new Font("System Bold",12));
            lb1.setPadding(new Insets(0,0,0,55));
            HBox linea1 = new HBox();
            Label lbName = new Label("Nombre:");
            lbName.setPadding(new Insets(0,0,0,55));
            TextField tfName = new TextField();
            tfName.maxWidth(50);
            linea1.getChildren().addAll(lbName,tfName);
            linea1.setSpacing(65);

            HBox linea2 = new HBox();
            Label lbNumero = new Label("Numero:");
            lbNumero.setPadding(new Insets(0,0,0,55));
            TextField tfNumero = new TextField();
            tfNumero.maxWidth(50);
            linea2.getChildren().addAll(lbNumero,tfNumero);
            linea2.setSpacing(65);

            HBox linea3 = new HBox();
            Label lbFecha = new Label("Fecha Caducidad:");
            lbFecha.setPadding(new Insets(0,0,0,55));
            DatePicker dPicker = new DatePicker();

            dPicker.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    fechaCaducidad = dPicker.getValue().toString();
                }
            });
            linea3.getChildren().addAll(lbFecha,dPicker);
            linea3.setSpacing(20);

            HBox linea4 = new HBox();
            Label lbCVV = new Label("CVV");
            lbCVV.setPadding(new Insets(0,0,0,55));
            TextField tfCVV = new TextField();
            tfCVV.setMaxWidth(50);
            linea4.getChildren().addAll(lbCVV,tfCVV);
            linea4.setSpacing(90);

            CreditoBox.getChildren().addAll(lb1,linea1,linea2,linea3,linea4);
            CreditoBox.setAlignment(Pos.CENTER_LEFT);
            CreditoBox.setSpacing(10);


            btnConfirmar.setOnAction(new EventHandler<ActionEvent>(){

                public void handle(ActionEvent event){
                    boolean condicion = false;
                    metodoPago m = null;
                    RadioButton tipoPago = (RadioButton) ModoPago.getSelectedToggle();
                    if(tipoPago == null){
                        System.out.println("error");
                    }else if (tipoPago.getText().equals("Efectivo")){
                         m = metodoPago.E;

                    }else{
                         m = metodoPago.C;

                    }

                    if(tfNumero.getText().equals("")||tfName.getText().equals("")||tfCVV.getText().equals("")||dPicker.getValue()==null){
                        System.out.println("error, parametro vacio");
                    }else{
                        Pago p = new Pago(pedidoActual.getIdpedido(),tfName.getText(),Double.parseDouble(txtTotal.getText().replace(',','.')),dPicker.getValue().toString(),m);
                        Pagable.generarTransaccion(p);
                        condicion = true;
                    }
                    if(condicion){
                        try {
                            toCompletado(event);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }

                }


            });

        }


    }


    /**
     * calcula los valores de iva y adicionales que se suman al valor total
     * @param conCredito
     */
    private void calcDatos(boolean conCredito){
        Double subtotal = Double.parseDouble(txtValor.getText().replace(',','.'));
        Double extra = 0.0;
        if(conCredito){
            extra = (subtotal+extra)*0.10;
        }

        Double iva = subtotal*0.12;
        Double total = subtotal + iva + extra;

        txtAdicional.setText(String.format("%,.2f", extra));
        txtIva.setText(String.format("%,.2f", iva));
        txtTotal.setText(String.format("%,.2f", total));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        Double subtotal = pedidoActual.getTotal();

        txtValor.setText(String.format("%,.2f", subtotal));
        btTarteja.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                parteCredito();
                calcDatos(true);
            }

        });

        btEfectivo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(btEfectivo.isSelected()){
                    CreditoBox.getChildren().clear();
                    Label lb2 = new Label("Acercate a caja para pagar tu pedido");
                    lb2.setFont(new Font("System Bold Italic",16));
                    CreditoBox.getChildren().add(lb2);
                    CreditoBox.setAlignment(Pos.CENTER);

                    btnConfirmar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            boolean condicion = false;
                            metodoPago m = null;
                            RadioButton tipoPago = (RadioButton) ModoPago.getSelectedToggle();
                            if(tipoPago == null){
                                System.out.println("error");
                            }else if (tipoPago.getText().equals("Efectivo")){
                                m = metodoPago.E;

                            }else{
                                m = metodoPago.C;
                            }

                            Pago p = new Pago(pedidoActual.getIdpedido(),nombreCliente,Double.parseDouble(txtTotal.getText().replace(',','.')),LocalDate.now().toString(),m);
                            Pagable.generarTransaccion(p);
                            if(condicion) {
                                try {
                                    toCompletado(event);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                System.out.println("ingrese opcion de pago");
                            }

                        }
                    });
                }
                calcDatos(false);
            }
        });

    }

    /**
     * Cambia la escena a completado-view
     * @param event
     * @throws IOException
     */
    void toCompletado(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("completado-view.fxml"));
        Parent p = fxmlLoader.load();
        Scene scene = new Scene(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Completado");
        window.setScene(scene);
        window.show();

    }
}
