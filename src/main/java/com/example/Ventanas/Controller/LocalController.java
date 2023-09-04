package com.example.Ventanas.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import com.example.Ventanas.classes.Ubicacion;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;

import static com.example.Ventanas.VentanaPrincipal.PrincipalApplication.rutaFiles;
import static com.example.Ventanas.VentanaPrincipal.PrincipalApplication.rutaImagen;

public class LocalController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Pane pane;

    @FXML
    private ImageView BackMapa;

    private int count = 0;
    private final Label text = new Label(Integer.toString(count));

    private final static ArrayList<Integer> cuentaRegresiva = new ArrayList<>();


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private void incrementCount() {
        count++;
        text.setText(Integer.toString(count));
    }

    private void crearCuentaRegresiva(){
        cuentaRegresiva.add(5);
        cuentaRegresiva.add(4);
        cuentaRegresiva.add(3);
        cuentaRegresiva.add(2);
        cuentaRegresiva.add(1);
    }

    /**
     * crea un arrayList con numeros del 1 al 10
     * @return un numero aleatorio entre 1 y 10
     */
    public int generaNum(){
        ArrayList<Integer> lNumeros = new ArrayList<>();
        lNumeros.add(1);
        lNumeros.add(2);
        lNumeros.add(3);
        lNumeros.add(4);
        lNumeros.add(5);
        lNumeros.add(6);
        lNumeros.add(7);
        lNumeros.add(8);
        lNumeros.add(9);
        lNumeros.add(10);
        Random rand = new Random();
        return lNumeros.get(rand.nextInt(lNumeros.size()));
    }

    //    private void autocerrar(Label lb) {
//        int i = 5;
//        while(i > 0) {
//            try {
//                Thread.sleep(1000);
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
//            lb.setText(String.valueOf(--i));
//
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb){

        try {
            FileInputStream mpImagen = new FileInputStream(rutaImagen + "mapa2.png");
            BackMapa.setImage(new Image(mpImagen));
        }catch(FileNotFoundException ex1){
            ex1.printStackTrace();
        }

        Thread puntos = new Thread(new Runnable(){

            public void run(){
                generarPuntos();
            }

        });

        puntos.setDaemon(true);
        puntos.start();
    }

    /**
     * lee el archivo de usuarios.txt y guarda los datos de usuarios en un arrayList
     * @return arrayList con las ubicaciones de las heladerias
     */
    public ArrayList<Ubicacion> leerUbicacion(){
        Path rutaUb = Paths.get(rutaFiles+"locales.txt");
        ArrayList<Ubicacion> lUb = new ArrayList<>();
        try(BufferedReader br = (new BufferedReader(new FileReader(rutaUb.toFile())))){
            String ln = br.readLine();
            while(ln!=null){
                String[] datos = ln.split(",");
                double coordX = Double.parseDouble(datos[0]);
                double coordY = Double.parseDouble(datos[1]);
                String nombre = datos[2];
                String horario = datos[3];
                Ubicacion ub = new Ubicacion(coordX, coordY, nombre, horario);
                lUb.add(ub);
                ln = br.readLine();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return lUb;
    }

    /**
     * coloca las imageView de las ubicaciones en un Pane, tambien viene con ventanas emergentes al presionar en las imageView
     */
    public void generarPuntos(){

        ArrayList<Ubicacion> lUb = leerUbicacion();


        for(Ubicacion i: lUb){

            i.getImg().setLayoutX(i.getCoordenadaX());
            i.getImg().setLayoutY(i.getCoordenadaY());
            i.getImg().setFitHeight(25);
            i.getImg().setFitWidth(25);

            i.getImg().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    VBox root = new VBox();
                    VBox parteA = new VBox();
                    VBox parteB = new VBox();
                    HBox parteC = new HBox();
                    HBox parteC1 = new HBox();
                    HBox parteC2 = new HBox();
                    Label heladeria = new Label("HELADERIA X");
                    heladeria.setPadding(new Insets(0,0,15,0));
                    parteA.getChildren().add(heladeria);
                    parteA.setAlignment(Pos.CENTER);

                    Label ubicacion = new Label("Ubicacion: "+i.getNombre());
                    ubicacion.setPadding(new Insets(10,0,0,15));
                    Label horario = new Label("Horario: "+i.getHorario());
                    horario.setPadding(new Insets(0,0,10,15));
                    parteB.getChildren().addAll(ubicacion,horario);
                    parteB.setAlignment(Pos.CENTER_LEFT);
                    parteB.setSpacing(15);

                    Label lbS1 = new Label("La ventana se cerrara en: ");
                    Label lbS2 = new Label("5");
                    parteC1.getChildren().addAll(lbS1,text);
                    parteC1.setPadding(new Insets(10,0,0,15));

                    Button cerrar = new Button("cerrar");
                    cerrar.setOnAction(new EventHandler<ActionEvent>(){

                        public void handle(ActionEvent event){
                            Stage st = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            st.close();
                        }

                    });
                    parteC2.setPadding(new Insets(10,0,0,85));
                    parteC2.getChildren().add(cerrar);
                    parteC.getChildren().addAll(parteC1,parteC2);
                    parteC.setSpacing(70);

                    root.setAlignment(Pos.CENTER_LEFT);
                    root.getChildren().addAll(parteA,new Separator(),parteB, new Separator(),parteC);
                    root.setSpacing(5);
                    Scene popUp = new Scene(root,400,200);
                    Stage stagePop = new Stage();
                    stagePop.setScene(popUp);
                    stagePop.setResizable(false);
                    stagePop.setTitle("Información del local");


                    stagePop.show();
                    Thread th = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                Thread.sleep(5000);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    stagePop.close();
                                }
                            });


//                            Platform.runLater(new Runnable() {
//                                @Override
//                                public void run() {
//                                    stagePop.close();
//                                }
//                            });
                        }

                    });

                    th.start();
                    stagePop.show();




                }
            });
        }

        // iconos cada X segundo
        int segundos = generaNum();
        for(Ubicacion k: lUb){
            System.out.println("son "+ lUb.size() + " puntos, segundos hasta el proximo punto: "+segundos);
            Platform.runLater(new Runnable(){
                public void run(){
                    pane.getChildren().add(k.getImg());
                }

            });
            try{
                Thread.sleep(segundos*1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
