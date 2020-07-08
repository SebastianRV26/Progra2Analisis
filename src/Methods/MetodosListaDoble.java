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

/**
 *
 * @author edubi
 */
public class MetodosListaDoble {

    public static MetodosListaDoble instance = null;

    public static MetodosListaDoble getInstance() {
        if (instance == null) {
            instance = new MetodosListaDoble();
        }
        return instance;
    }

    ListaDoble inicio, ultimo;
    
    public boolean insertarRuta(String ruta, int pesoRuta, boolean tieneFin){
          ArrayList<vertice> rutaVertices  = convertirRuta(ruta);
          ListaDoble nuevo = new ListaDoble(rutaVertices, pesoRuta,tieneFin);
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

    public void verRutaCorta() {
        System.out.println("Ruta corta Backtraking");
        ArrayList<vertice> rutaVertices = inicio.verticesRuta;
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
        System.out.println("Peso ruta corta: " + inicio.pesoRuta);
    }
    

    public ArrayList<vertice> convertirRuta(String ruta) {
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

    //Este metodo es solo para ver si sirve el inserta, en algun punto hay que borrarlo
        public void verPeso(){
        ListaDoble aux = inicio;
        while (aux != null) {            
            System.out.println(aux.pesoRuta + " " + aux.llegaDestino);
            aux = aux.sigN;
        }
    }
}
