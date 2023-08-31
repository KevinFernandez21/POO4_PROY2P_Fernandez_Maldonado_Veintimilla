package com.example.Ventanas.classes;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;
    private String email;

    public Usuario(String nombre, String apellido, String usuario, String contrasena, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEmail() {
        return email;
    }
    public static ArrayList<Usuario> LeerUsuario(String RutaArchivo){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        return listaUsuarios;
    }

    public String toString(){
        return usuario+"-"+contrasena;
    }

}
