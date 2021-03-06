/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.arco;
import Classes.ListaDoble;
import Classes.vertice;
import java.util.ArrayList;
import java.util.Random;

/**
 ** Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
 * 
 * Clase que contiene los metodos para la clase de ListaDoble
 *
 * @author edubi
 */
public class MetodosListaDoble {
    public static MetodosListaDoble instance = null;

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
     * 
     * singleton para que exista únicamente una instacia de la clase
     * MetodosListaDoble
     *
     * @return la instancia única del objeto MetodosListaDoble
     */
    public static MetodosListaDoble getInstance() {
        if (instance == null) {
            instance = new MetodosListaDoble();
        }
        return instance;
    }

    public ListaDoble inicio, ultimo, rutaCorta;
    public double instruccionesListaDoble = 0; // asignaciones y comparaciones
    public double memoriaListaDoble = 0; 
     public double pesoCola = 256;
    public double pesoPoda = 320;
    public double pesoArco = 256;
    public double pesoVertice = 320;
    public double pesoListaDoble= 320;
    public double pesoArrayVacio = 192;
    public double pesoArrayConElementos = 640;
    public double pesoVectorStringVacio = 448;
    public double pesoVectorStringConElementos = 2112;

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 09/07/2020
     *
     * Metodo que inserta en la lista doble, al mismo tiempo va buscando 
     * cual de todos los nuevos que van llegando es la ruta mas corta, cuando lo encuentra le
     * asigna un puntero para no perderlo
     * 
     * @param ruta Array que contiene todos los vertices que forman parte de la ruta encontrada por el backtracking
     * @param pesoRuta Peso de la ruta que llego por el parametro anterior
     * @param tieneFin Indica si la rutra que llego es una solución total o parcial
     * @return Verdadero si inserto correctamente o false si hubo problemas
     */
    public boolean insertarRuta(ArrayList<vertice> ruta, int pesoRuta, boolean tieneFin) {
        ListaDoble nuevo = new ListaDoble(ruta, pesoRuta, tieneFin, 0);//1
        instruccionesListaDoble ++;
        memoriaListaDoble += pesoListaDoble;
        if (inicio == null) {//1
            nuevo.posicion = 0;//1
            inicio = ultimo = rutaCorta=nuevo;//2
            instruccionesListaDoble += 4;
            return true;//1
        }
        ListaDoble aux = inicio;//1
        int pos = 1;//1
        memoriaListaDoble += pesoListaDoble + 32;
        instruccionesListaDoble +=3;
        while (aux != null) {//n
            if (aux.sigN == null) {//n
                if ((nuevo.pesoRuta < rutaCorta.pesoRuta  || rutaCorta.pesoRuta == 0) && nuevo.llegaDestino) {//3
                    rutaCorta = nuevo;//1
                    instruccionesListaDoble += 4;
                }
                nuevo. posicion = pos;//1
                aux.sigN = nuevo;//1
                nuevo.antN = aux;//1
                ultimo = nuevo;//1
                instruccionesListaDoble += 7;
                return true;//1
            }
            pos ++;//n
            aux = aux.sigN;//n
            instruccionesListaDoble +=4;
        }
        instruccionesListaDoble ++;
        return false;//1
        
        //Medicion analitica: 4n+ 18
    }

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
     *
     * Medoto que recorre una ruta y la imprime, 
     * mostrando en orden los origenes y destinos, hasta llegar
     * al último nodo
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
     * 
     * Muestra las rutas validas que dio como reultado el backtracking
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
     * 
     * Muestra el total de rutas que logro sacar el backtracking de un determinado grafo
     */
    public int totalRutas() {
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
     * 
     * Busca 5 rutas de forma random en la lista para poder imprimirlas
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
     * Busca una ruta por la posición en la que se encuentra
     * 
     * @param posicion posición de la ruta que se desea buscar
     * @return Devuelve la ruta encontrada
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
      * 
      * Metodo que devuelve x cantidad de rutas según el tamaño del grafo
      * @param tamanoGrafo tamaño del grado en el que se encuentra actualmente
      * @return Todas las rutas que se desean según el tamaño del grafo
      */

    public ArrayList<ListaDoble> rutasPorTamano(int tamanoGrafo) {
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
