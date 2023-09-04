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

    /**
     * getter nombre del cliente
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * getter apellido del cliente
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }
    /**
     * getter nombre de usuario
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }
    /**
     * getter contraseña de usuario
     * @return contraseña
     */
    public String getContrasena() {
        return contrasena;
    }
    /**
     * getter email del usuario
     * @return email
     */
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
