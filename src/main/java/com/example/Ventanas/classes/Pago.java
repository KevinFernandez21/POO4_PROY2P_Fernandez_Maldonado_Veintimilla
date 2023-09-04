package com.example.Ventanas.classes;

import java.util.HashSet;
import java.util.Set;

public class Pago {

    private String idPago;
    private String idpedido;
    private String nombre;
    private Double total;

    private String fechaCaducidad;
    private metodoPago mPago;
    private static Set<Integer> idPedidos = new HashSet<>();

    private int genIdPago(){
        int num;
        do{
            num = (int) (Math.random()*800)+100;
            System.out.println(num);
        }while(idPedidos.contains(num));

        idPedidos.add(num);
        return num;
    }

    public Pago(String idpedido, String nombre,  Double total, String caducidad, metodoPago mPago){
        this.idPago = String.valueOf(genIdPago());
        this.idpedido = idpedido;
        this.nombre = nombre;
        this.total = total;
        this.fechaCaducidad = caducidad;
        this.mPago = mPago;
    }

    public String toString(){
        return this.idPago +","+ this.idpedido +","+ this.nombre +","+ this.total +","+ this.fechaCaducidad +","+ this.mPago;
    }

}
