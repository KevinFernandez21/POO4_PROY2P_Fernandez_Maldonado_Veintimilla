package com.example.Ventanas.Controller;

import com.example.Ventanas.VentanaPrincipal.PrincipalApplication;
import com.example.Ventanas.classes.Usuario;
import javafx.event.ActionEvent;
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
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;


public class PrincipalController {
    @FXML
    private PasswordField TFcontrasena;

    @FXML
    private TextField TFusuario;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Label lbIncorrecto;
    @FXML
    private AnchorPane contenedor_padre;
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

    @FXML
    void goPassVentana(ActionEvent event) throws IOException{

        boolean validacion;

        if(leido==false){
            leerUsuario();
            leido = true;
        }

        String cuentaIngresada = TFusuario.getText().trim();
        String contraIngresada = TFcontrasena.getText().trim();

        validacion = validarUsuario(cuentaIngresada, contraIngresada);

        if(validacion) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            //stage.close();

            FXMLLoader fxmlLoader2 = new FXMLLoader(PrincipalApplication.class.getResource("emergente-view.fxml"));
            Parent root2 = fxmlLoader2.load();

            Scene scene2 = new Scene(root2, 400, 600);
            Stage stage2 = new Stage();
            URL cssFile = PrincipalApplication.class.getResource("/css/emergente.css");
            scene2.getStylesheets().add(cssFile.toExternalForm());
            EmergenteController controller2 = fxmlLoader2.getController();
            stage2.initModality(Modality.NONE);
            stage2.setScene(scene2);
            stage2.show();


            FXMLLoader fxmlLoader1 = new FXMLLoader(PrincipalApplication.class.getResource("pass-view.fxml"));
            Parent root1 = fxmlLoader1.load();

            Scene scene1 = new Scene(root1, 1000, 800);
            // Stage stage1 = new Stage();
            URL cssFile2 = PrincipalApplication.class.getResource("/css/pass.css");
            scene1.getStylesheets().add(cssFile2.toExternalForm());
            PassController controller1 = fxmlLoader1.getController();
            // stage1.initModality(Modality.NONE);
            // stage1.setScene(scene1);
            // stage1.show();
            stage.setScene(scene1);
        } else{
            lbIncorrecto.setText("DATOS INCORRECTOS, vuelvalo a intentar");
        }
    }

    public void setStage(Stage PrincipalStage){

    }
}