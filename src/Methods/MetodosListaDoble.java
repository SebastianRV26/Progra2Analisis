/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.arco;
import Classes.ListaDoble;
import Classes.vertice;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 ** Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
 *
 * @author edubi
 */
public class MetodosListaDoble {
    public static MetodosListaDoble instance = null;

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
     *
     * @return
     */
    public static MetodosListaDoble getInstance() {
        if (instance == null) {
            instance = new MetodosListaDoble();
        }
        return instance;
    }

    public ListaDoble inicio, ultimo, rutaCorta;

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 09/07/2020
     *
     * @param ruta
     * @param pesoRuta
     * @param tieneFin
     * @return
     */
    public boolean insertarRuta(ArrayList<vertice> ruta, int pesoRuta, boolean tieneFin) {
        ListaDoble nuevo = new ListaDoble(ruta, pesoRuta, tieneFin, 0);
        if (inicio == null) {
            nuevo.posicion = 0;
            inicio = ultimo = rutaCorta=nuevo;
            return true;
        }
        ListaDoble aux = inicio;
        int pos = 1;
        while (aux != null) {
            if (aux.sigN == null) {
                if ((nuevo.pesoRuta < rutaCorta.pesoRuta  || rutaCorta.pesoRuta == 0) && nuevo.llegaDestino) {
                    rutaCorta = nuevo;
                }
                nuevo. posicion = pos;
                aux.sigN = nuevo;
                nuevo.antN = aux;
                ultimo = nuevo;
                return true;
            }
             pos ++;
            aux = aux.sigN;
        }

        return false;
    }

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
     *
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
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     */
    public void rutasValidas() {
        ListaDoble aux = inicio;
        int cantValidas = 0;
        while (aux != null) {
            if (aux.llegaDestino) {
                cantValidas++;
            }
            aux = aux.sigN;
        }
        System.out.println("La cantidad de rutas validas del Backtraking es de: " + cantValidas);
    }

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     */
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

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     */
    public void rutasRandom() {
        Random random = new Random();
        ListaDoble aux;
        for (int i = 0; i < 5; i++) {
            aux = buscarRuta(random.nextInt(totalRutas() - 1) + 1);
            imprimirRuta(aux);
        }
    }

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     *
     * @param posicion
     * @return
     */
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

     /**
      * Fecha inicio: 21/07/2020 Ultima modificación: 21/07/2020
      * @param tamanoGrafo
      * @return 
      */

    public ArrayList<ListaDoble> rutasPorTamano(int tamanoGrafo) {
        ListaDoble aux = inicio;
        ArrayList<ListaDoble> listaRutas = null;
        int cont = 0;
        if (tamanoGrafo <= 20) {
            while (cont != 20) {
                listaRutas.add(buscarRuta(cont));
                cont++;
            }
        } else {
            while (cont != 100) {
                listaRutas.add(buscarRuta(cont));
                cont++;
            }
        }
        return listaRutas;
    }
}
