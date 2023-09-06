package com.example.Ventanas.Controller;

import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import com.example.Ventanas.classes.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import com.example.Ventanas.classes.*;
import javafx.stage.WindowEvent;

import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;


public class PrincipalController {
    @FXML
    private AnchorPane contenedor_padre;

    @FXML
    private AnchorPane contenedor_part1;

    @FXML
    private AnchorPane contenedor_part2;

    @FXML
    private Label lb_usuario;

    @FXML
    private Label lb_contrasena;

    @FXML
    private PasswordField TFcontrasena;

    @FXML
    private TextField TFusuario;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Label lbIncorrecto;

    static ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    public static String nombreCliente = "";
    static boolean leido = false; //para verificar que se halla leido el archivo Usuarios.txt solo una vez

    /**
     * valida las credenciales ingresadas en la lista de usuarios
     * @param cuenta nombre de usuario
     * @param contra contraseña de la cuenta
     * @return true si es que las credenciales son correctas, de no ser asi retorna false
     */
    public static boolean validarUsuario(String cuenta, String contra){
        for(Usuario i: listaUsuarios){
            if( (cuenta.equals(i.getUsuario())) && (contra.equals(i.getContrasena())) ){
                nombreCliente = i.getNombre();
                return true;
            }
        }
        return false;
    }

    public static void leerUsuario(){

        Path ruta = Paths.get("src/main/resources/Archivos/Usuarios.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(ruta.toFile()))){
            String ln = br.readLine();
            while(ln != null){
                String datos[] = ln.split(",");
                String nombre = datos[0];
                String apellido = datos[1];
                String usuario = datos[2];
                String contrasena = datos[3];
                String email = datos[4];
                Usuario u = new Usuario(nombre,apellido,usuario,contrasena,email);
                listaUsuarios.add(u);
                ln = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private static Usuario usuarioSeleccionado;

    public static Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    @FXML
    void goPassVentana(ActionEvent event) throws IOException{

        boolean validacion;

        if(leido==false){
            leerUsuario();
            leido = true;
        }

        String cuentaIngresada = TFusuario.getText().trim();
        String contraIngresada = TFcontrasena.getText().trim();

        for(Usuario i: listaUsuarios){
            if( (cuentaIngresada.equals(i.getUsuario())) && (contraIngresada.equals(i.getContrasena())) ){
                usuarioSeleccionado = i;
            }
        }

        validacion = validarUsuario(cuentaIngresada, contraIngresada);

        if(validacion) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            //stage.close();

            FXMLLoader fxmlLoader2 = new FXMLLoader(PrincipalApplication.class.getResource("emergente-view.fxml"));
            Parent root2 = fxmlLoader2.load();

            Scene scene2 = new Scene(root2);
            Stage stage2 = new Stage();

            stage2.initModality(Modality.NONE);
            stage2.setScene(scene2);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>(){

                public void handle(WindowEvent event1){
                    stage2.close();
                }

            });

            stage2.show();


            FXMLLoader fxmlLoader1 = new FXMLLoader(PrincipalApplication.class.getResource("pass-view.fxml"));
            Parent root1 = fxmlLoader1.load();

            PassController passController = fxmlLoader1.getController();
            passController.setVentanaEmergente(stage2);

            Scene scene1 = new Scene(root1);
            stage.setScene(scene1);
        } else{
            lbIncorrecto.setText("Usuario o Contraseña incorrecta.");
            lbIncorrecto.setId("lb_Incorrecto");
        }
    }

    public void setStage(Stage PrincipalStage){

    }
}