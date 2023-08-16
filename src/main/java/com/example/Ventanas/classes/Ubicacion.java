package com.example.Ventanas.classes;

import java.util.Date;

public class Ubicacion {
    private Double coordenadaX;
    private Double getCoordenaday;
    private String nombre;
    private Date horario;

    public Ubicacion(Double coordenadaX, Double getCoordenaday, String nombre, Date horario) {
        this.coordenadaX = coordenadaX;
        this.getCoordenaday = getCoordenaday;
        this.nombre = nombre;
        this.horario = horario;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public Double getGetCoordenaday() {
        return getCoordenaday;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getHorario() {
        return horario;
    }
}
