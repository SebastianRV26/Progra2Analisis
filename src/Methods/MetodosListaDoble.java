/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.arco;
import Classes.ListaDoble;
import Classes.vertice;
import static Methods.MetodosGrafo.instance;
import java.util.ArrayList;
import java.util.Random;

/**
 ** Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
 * @author edubi
 */
public class MetodosListaDoble {

    
    public static MetodosListaDoble instance = null;

     /**
      *  Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
      * @return 
      */
    public static MetodosListaDoble getInstance() {
        if (instance == null) {
            instance = new MetodosListaDoble();
        }
        return instance;
    }

   public  ListaDoble inicio, ultimo;
    
    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
     * @param ruta
     * @param pesoRuta
     * @param tieneFin
     * @return 
     */
    public boolean insertarRuta(String ruta, int pesoRuta, boolean tieneFin){
          ArrayList<vertice> rutaVertices  = convertirRuta(ruta);
          ListaDoble nuevo = new ListaDoble(rutaVertices, pesoRuta,tieneFin, 0);
          if(inicio == null){
               inicio = ultimo = nuevo;
              return true;
          }
          
          //Esto lo que hace es guardar la ruta mas corta al inicio
          if((nuevo.pesoRuta < inicio.pesoRuta && nuevo.llegaDestino) ||  inicio.pesoRuta == 0){
              nuevo.sigN = inicio;
              inicio.antN = nuevo;
              inicio =nuevo;
              return true;
          }
          ListaDoble aux = inicio;
          while (aux != null) {
              if(aux.sigN == null){
                  aux.sigN = nuevo;
                  nuevo.antN = aux;
                  return true;
              }
            aux = aux.sigN;
        }

        return false;
    }

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
     * @param temp
     */
    public void imprimirRuta(ListaDoble temp) {
        ArrayList<vertice> rutaVertices = temp.verticesRuta;
        for (int i = 0; i < rutaVertices.size() - 1; i++) {
            vertice origen = rutaVertices.get(i);
            vertice destino = rutaVertices.get(i + 1);
            arco aux = origen.sigA;
            while (aux != null) {
                if (aux.destino.equals(destino)) {
                    System.out.println("Origen : " + origen.ID);
                    System.out.println("Destino: " + destino.ID);
                    System.out.println("Peso arco: " + aux.peso);
                }
                aux = aux.sigA;
            }
        }
       System.out.println("Peso ruta : " + temp.pesoRuta);
       System.out.println("========================");
    }
    
/**
 * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
 * @param ruta
 * @return 
 */
    private ArrayList<vertice> convertirRuta(String ruta) {
        MetodosGrafo mg = MetodosGrafo.getInstance();
        ArrayList<vertice> rutaVertices = new ArrayList<>();
        String[] verticesID = ruta.split("/");
        for (String idV : verticesID) {
            if (!idV.equals("")) {
                int id = Integer.parseInt(idV);
                rutaVertices.add(mg.buscar(id));
            }
        }
        return rutaVertices;
    }
    
    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     */
    public void rutasValidas(){
        ListaDoble aux = inicio;
        int cantValidas = 0;
         while (aux != null) {
             if(aux.llegaDestino){
                 cantValidas ++;
             }
            aux = aux.sigN;
        }
         System.out.println("La cantidad de rutas validas del Backtraking es de: "+ cantValidas);
    }

    public void asignarPosicion(){
        ListaDoble aux = inicio;
        int posicion = 0;
        while (aux != null) {
            aux.posicion = posicion;
            posicion ++;
            aux = aux.sigN;
        }
    }

    private int totalRutas() {
        ListaDoble aux = inicio;
        if (aux != null) {
            int cant = 0;
            while (aux != null) {
                cant++;
                aux = aux.sigN;
            }
           return cant;
        }
        return 0;
    }

    
    public void rutasRandom(){
        Random random = new Random();
        ListaDoble aux;
        for (int i = 0; i < 5; i++) {
            aux = buscarRuta( random.nextInt(totalRutas()-1) + 1);
            imprimirRuta(aux);
        }
    }

    private ListaDoble buscarRuta(int posicion) {
        ListaDoble aux = inicio;
        if (aux != null) {
            while (aux != null) {
                if (aux.posicion == posicion) {
                    return aux;
                }
                aux = aux.sigN;
            }
        }
        return null;
    }
}
