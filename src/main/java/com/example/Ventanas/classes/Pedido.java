package com.example.Ventanas.classes;

import com.example.Ventanas.interfaz.Pagable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Pedido implements Pagable,Serializable{
    private String idpedido;

    private String nombre;
    private Double total;
    private static Set<Integer> idPedidos = new HashSet<>();


    private int genIdPedido(){
        Path ruta = Paths.get("Archivos/pedidos.txt");
        ArrayList<Integer> idArchivo = new ArrayList<>();
        int num;
        try(BufferedReader br = new BufferedReader(new FileReader(ruta.toFile()))){
            String ln = br.readLine();
            while(ln != null){
                String datos[] = ln.split(",");
                idArchivo.add(Integer.parseInt(datos[0]));
                ln = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        do{
            num = (int) (Math.random()*800)+100;
            System.out.println(num);
        }while(idPedidos.contains(num)||idArchivo.contains(num));

        idPedidos.add(num);
        return num;
    }

    public Pedido(String nombre, Double total) {
        this.idpedido = String.valueOf(genIdPedido());
        this.nombre = nombre;
        this.total = total;
    }

    public Pedido(String id,String nombre, Double total){
        this.idpedido = id;
        this.nombre = nombre;
        this.total = total;
    }

    public String getIdpedido() {
        return idpedido;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getTotal() {
        return total;
    }

    /**
     * escribe en el archivo pedidos.txt los datos del pedido realizado
     * @param pedido pedido realizado por el usuario
     */
    public static void escribirArchivo(Pedido pedido){
        Path ruta = Paths.get("Archivos/pedidos.txt");
        try (FileWriter fileWriter = new FileWriter(ruta.toFile(), true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String lineaPedido = pedido.getIdpedido() + "," + pedido.getNombre() + "," + pedido.getTotal();
            bufferedWriter.write(lineaPedido);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * escribe el objeto pedido serializado
     */
    public void escribirBin() {
        //Path ruta = Paths.get("src/main/resources/Archivos/bins/pedido"+this.idpedido+".bin");
        try(ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Archivos/bins/pedido"+this.idpedido+".bin"))){
            ob.writeObject(this);
            ob.flush();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e2){
            e2.printStackTrace();
        }

    }

    /**
     * lee el archivo de pedidos.txt y retorna un arrayList de pedidos
     * @return arrayList de pedidos
     */
    public static ArrayList<Pedido> leerArchivo(){
        ArrayList<Pedido> lPedidos = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("Archivos/pedidos.txt"))){
            String ln = br.readLine();
            while(ln != null){
                String[] datos = ln.split(",");
                String id = datos[0];
                String name = datos[1];
                Double total = Double.parseDouble(datos[2]);
                Pedido p = new Pedido(id,name,total);
                lPedidos.add(p);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return lPedidos;
    }

    public String toString(){
        return nombre+","+idpedido;
    }

}
