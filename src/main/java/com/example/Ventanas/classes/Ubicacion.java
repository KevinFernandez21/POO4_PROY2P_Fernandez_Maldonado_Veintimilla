package com.example.Ventanas.classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.example.Ventanas.VentanaPrincipal.PrincipalApplication.rutaImagen;

public class Ubicacion {
    private Double coordenadaX;
    private Double coordenadaY;
    private String nombre;
    private String horario;
    private ImageView ImageHelado;
    private static Image icono;
    private int segundos;

    public Ubicacion(Double coordenadaX, Double getCoordenaday, String nombre, String horario){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = getCoordenaday;
        this.nombre = nombre;
        this.horario = horario;
        try {
            icono = new Image(new FileInputStream(rutaImagen + "Helado_Punto2.png"));
            this.ImageHelado = new ImageView(icono);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        this.segundos = 5;
    }
    /**
     * getter coordenadaX
     * @return coordenadaX
     */
    public Double getCoordenadaX() {
        return coordenadaX;
    }
    /**
     * getter coordenadaY
     * @return coordenadaY
     */
    public Double getCoordenadaY() {
        return coordenadaY;
    }
    /**
     * getter nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * getter horario
     * @return horario
     */
    public String getHorario() {
        return horario;
    }
    /**
     * getter imageHelado
     * @return imageHelado
     */
    public ImageView getImg(){
        return this.ImageHelado;
    }

    public int getSegundos(){return this.segundos;}

    public void setSegundos(int seg){this.segundos = seg;}
}