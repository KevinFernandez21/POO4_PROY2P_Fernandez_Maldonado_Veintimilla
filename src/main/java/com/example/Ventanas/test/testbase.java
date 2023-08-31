package com.example.Ventanas.test;

import com.example.Ventanas.classes.Base;

import java.io.IOException;
import java.util.ArrayList;

public class testbase {
     static ArrayList<Base> ldb;
    public static void main(String[] args) {
        System.out.println("hola");
        try {
            ArrayList<Base> ldb = Base.leerBase();

        } catch (IOException e) {
            System.err.println("Error al leer la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
