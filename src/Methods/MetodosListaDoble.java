/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.nodo;
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

    nodo inicio, ultimo;
    
    public boolean insertarRuta(String ruta, int pesoRuta, boolean tieneFin){
          ArrayList<vertice> rutaVertices  = convertirRuta(ruta);
          nodo nuevo = new nodo(rutaVertices, pesoRuta,tieneFin);
          if(inicio == null){
              inicio = ultimo = nuevo;
              return true;
          }
          if((nuevo.pesoRuta < inicio.pesoRuta && nuevo.llegaDestino) ||  inicio.pesoRuta == 0){
              nuevo.sigN = inicio;
              inicio.antN = nuevo;
              inicio =nuevo;
              return true;
          }
          nodo aux = inicio;
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

    
    public void verPeso(){
        nodo aux = inicio;
        while (aux != null) {            
            System.out.println(aux.pesoRuta);
            aux = aux.sigN;
        }
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

}
