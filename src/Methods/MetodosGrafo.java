/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.Cola;
import Classes.ListaDoble;
import Classes.arco;
import Classes.vertice;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020
 */
public class MetodosGrafo {

    public static MetodosGrafo instance = null; // instancia de la clase MetodosGrafo

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020
     *
     * singleton para que exista únicamente una instacia de la clase
     * MetodosGrafo
     *
     * @return la instancia única del objeto MetodosGrafo
     */
    public static MetodosGrafo getInstance() {
        if (instance == null) {
            instance = new MetodosGrafo();
        }
        return instance;
    }

    public vertice grafo, ultimo;
    MetodosListaDoble mld = MetodosListaDoble.getInstance();
    MetodosCola mc = MetodosCola.getInstance();
    MetodosPoda mp = MetodosPoda.getInstance();
    public int instrucciones = 0; // asignaciones y comparaciones

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 06/07/2020
     *
     * // * método que inserta un vértice al final para el grafo
     *
     * @param ID el identificador del vértice que deseamos crear
     * @return true o false
     */
    public boolean insertarVertices(int ID) {
        vertice nuevo = new vertice(ID, false);
        if (grafo == null) {
            grafo = nuevo;
            return true;
        }
        vertice aux = grafo;
        while (aux != null) {
            if (aux.sigV == null) {
                aux.sigV = nuevo;
                ultimo = aux.sigV;
                return true;
            }
            aux = aux.sigV;
        }
        return false;
    }

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020
     *
     * método que busca un vértice del grafo
     *
     * @param id el id del vertice que deseamos buscar
     * @return vértice encontrado o null
     */
    public vertice buscar(int id) {
        vertice aux = grafo;
        while (aux != null) {
            if (aux.ID == id) {

                return aux;
            }
            aux = aux.sigV;
        }
        return null;
    }

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 13/06/2020
     *
     * método que inserta un arco para el grafo
     *
     * @param origen vértice de origen de arco al que queremos insertar
     * @param destino vértice destino de arco al que queremos insertar
     * @param peso el peso del arco, número entre el 1 al 10
     * @return "Insertado" o "No se pueden repetir arcos"
     */
   public String insertarArco(vertice origen, vertice destino, int peso) {
        if (buscar(origen, destino) == null) {
            arco nuevo = new arco(peso);
            nuevo.destino = destino;
            if (origen.sigA == null) {
                origen.sigA = nuevo;
            } else {
                nuevo.sigA = origen.sigA;
                origen.sigA.antA = nuevo;
                origen.sigA = nuevo;
            }
            return "Insertado";
        }
        return "No se pueden repetir arcos";
    }

    /**
     * Fecha inicio: 05/07/2020 Ultima modificación: 13/07/2020
     *
     * método que inserta un arco para el grafo
     *
     * @param origen vértice de origen de arco al que queremos insertar
     * @param destino vértice destino de arco al que queremos insertar
     * @param peso el peso del arco, número entre el 1 al 10
     * @return "Insertado" o "No se pueden repetir arcos"
     */
      public boolean insertarArcoDoble(vertice origen, vertice destino, int peso) {
        if (buscar(origen, destino) == null) {
            arco nuevo = new arco(peso);
            arco auxNuevo = new arco(peso);
            nuevo.destino = destino;
            auxNuevo.destino = origen;
            if (origen.sigA == null) {
                origen.sigA = nuevo;
            } else {
                nuevo.sigA = origen.sigA;
                origen.sigA.antA = nuevo;
                origen.sigA = nuevo;
            }
            if (destino.sigA == null) {
                destino.sigA = auxNuevo;
            } else {
                auxNuevo.sigA = destino.sigA;
                destino.sigA.antA = auxNuevo;
                destino.sigA = auxNuevo;
            }
            return true;
        }
        return false;
    }
    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020
     *
     * método que busca un arco del grafo
     *
     * @param origen vértice de origen de arco al que queremos buscar
     * @param destino vértice destino de arco al que queremos buscar
     * @return el arco deseado o null
     */
    public arco buscar(vertice origen, vertice destino) {
        if (origen.sigA != null) {
            arco aux = origen.sigA;
            while (aux != null) {
                if (aux.destino == destino) {
                    return aux;
                }
                aux = aux.sigA;
            }
        }
        return null;
    }

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 05/07/2020. método que
     * llena el grafo fuertemente conexo
     *
     * @param n es la cantidad de nodos que requiere el grafo
     */
    public void llenarGrafo(int n) {
        vertice origen, destino;
        for (int i = 1; i <= n; i++) { // primero se insertan los vertices
            insertarVertices(i);
        }
        for (int i = 1; i <= n; i++) { // luego se insertan los arcos
            origen = buscar(i);
            if (origen.ID != n) {
                for (int j = 1; j <= n; j++) { // para que el grafo sea fuertemente conexo 
                    Random random = new Random();
                    destino = buscar(j);
                    if (destino.ID != origen.ID && destino.ID != 1) {
                        if (origen.ID == 1 || destino.ID == n) {
                            insertarArco(origen, destino, random.nextInt(99) + 1);
                        } else {
                            insertarArcoDoble(origen, destino, random.nextInt(99) + 1);
                        }
                    }
                }
            }
        }
        quitarMarca(grafo);
    }

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020 * método que
     * establece la marca de todos los nodos del grafo como false
     *
     * @param grafo es el primer vértice del grafo
     */
    public void quitarMarca(vertice grafo) {
        vertice aux = grafo;
        while (aux != null) {
            aux.marca = false;
            aux = aux.sigV;
        }
    }

    public void amplitud(vertice grafo) {
        if (grafo == null) {//1
            System.out.println("No hay grafo");
        } else {
            vertice temp = grafo;//1
            while (temp != null) {//n
                System.out.println("Vertice: " + temp.ID);
                arco aux = temp.sigA;//n == n
                while (aux != null) {//n*n = n a la 2
                    System.out.println("Destino: " + aux.destino.ID);
                    System.out.println(aux.peso);
                    aux = aux.sigA;//n*n = n ala 2
                }
                System.out.println("-----------");
                temp = temp.sigV; //n
            }

        }
    }

    public void rutaCortaVoraz(vertice origen, vertice destino, String ruta, int distancia) {
        if ((origen == null) || (origen.marca == true)) {
            return;
        }
        if (origen == destino) {
            System.out.println("Voraz: " + ruta + " distancia: " + distancia);
        }
        int min = 100;
        arco auxMenor = null;
        arco aux = origen.sigA;
        while (aux != null) {
            if (aux.peso < min && aux.destino.marca == false) {
                min = aux.peso;
                auxMenor = aux;
            }
            aux = aux.sigA;
        }
        origen.marca = true;
        if (auxMenor != null) {
            rutaCortaVoraz(auxMenor.destino, destino, ruta + "->P" + auxMenor.peso + " V" + auxMenor.destino.ID + "/", distancia + auxMenor.peso);
        } else {
            rutaCortaVoraz(destino, destino, ruta, distancia);
        }
        return;
    }

    public void rutaCortaGenetica(ListaDoble temp) {

    }

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 10/07/2020
     *
     * @param vertex
     * @param ruta
     * @param pesoRuta
     */
    ArrayList<vertice> rutaV;

    public void rutaCortaBacktracking(vertice vertex, String ruta, int pesoRuta) {
        if ((vertex == null) || (vertex.marca)) {
            return;
        }

        if (vertex.ID == ultimo.ID) {
            rutaV = convertirRuta(ruta + vertex.ID + "/");
            mld.insertarRuta(rutaV, pesoRuta, true);
        } else {
            rutaV = convertirRuta(ruta + vertex.ID + "/");
            mld.insertarRuta(rutaV, pesoRuta, false);
        }
        vertex.marca = true;
        arco auxA = vertex.sigA;
        while (auxA != null) {
            rutaCortaBacktracking(auxA.destino, ruta + vertex.ID + "/", pesoRuta + auxA.peso);
            auxA = auxA.sigA;
        }
        vertex.marca = false;
    }

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 10/07/2020
     *
     * @param ruta
     * @return
     */
    private ArrayList<vertice> convertirRuta(String ruta) {
        ArrayList<vertice> rutaVertices = new ArrayList<>();
        String[] verticesID = ruta.split("/");
        for (String idV : verticesID) {
            if (!idV.equals("")) {
                int id = Integer.parseInt(idV);
                rutaVertices.add(buscar(id));
            }
        }
        return rutaVertices;
    }

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 18/07/2020. Método que
     * impreme la ruta del algoritmo de programación dinámica Dijkstra
     *
     * @param destino último vértice
     */
    public void mostrarRuta(vertice destino) {
        String ruta = "";
        vertice aux = destino;
        int peso = 0;
        while (aux != null) {
            ruta = "->P" + aux.pesoMin + " V" + aux.ID + "/" + ruta;
            peso += aux.pesoMin;
            aux = aux.antV;
        }
        System.out.println("PD: " + ruta + " distancia: " + peso);
    }

    /**
     * Lógica del algoritmo Dijkstra
     *
     * @param origen vértice de origen de arco al que queremos insertar
     * @param destino vértice destino de arco al que queremos insertar
     */
    public void rutaCortaDinamica(vertice origen, vertice destino) { // Dijkstra
        vertice aux = origen;
        vertice ant = origen;
        int min;
        ant.distanciaMinima = 0;
        while (aux != null) {
            arco auxA = aux.sigA;
            arco auxMin = null;
            min = 10000;
            while (auxA != null) { // para encontrar el más pequeño
                if (auxA.peso + ant.distanciaMinima < min && auxA.destino.marca != true) { // 
                    min = auxA.peso;
                    auxMin = auxA;
                }
                auxA = auxA.sigA;
            }
            if (auxMin != null) {
                if (auxMin.destino.distanciaMinima > (auxMin.peso /*+ ant.distanciaMinima*/)) {
                    auxMin.destino.marca = true;
                    auxMin.destino.distanciaMinima = auxMin.peso /*+ ant.distanciaMinima*/;
                    auxMin.destino.antV = ant;
                    ant = auxMin.destino;
                }
                auxMin.destino.pesoMin = auxMin.peso;
            }
            if (aux == destino) {
                break;
            }
            aux = auxMin.destino;
        }
        mostrarRuta(destino);
    }

    public String rutaActual = "";
    ArrayList<vertice> listaRuta;
     int rutaMinima = 0;

    /**
     * Fecha inicio: 09/07/2020 Ultima modificación: 12/07/2020
     *
     * @param actual
     * @param ruta
     * @param dist
     */
  
     public void RamificacionyPoda(String ruta, int dist) { 
        while (!mc.colaVacia()) { 
             Cola auxCola = mc.Extraer(); 
            vertice origen = auxCola.value; 
            if (origen.marca) { 
                return; 
            } 

            if ((rutaActual == "" || rutaMinima > dist)) { 
                if (origen.equals(ultimo)) { 
                    rutaMinima = dist; 
                    rutaActual = ruta + origen.ID + "/"; 
                    listaRuta = convertirRuta(rutaActual); 
                    mp.insertarPoda(listaRuta, rutaMinima, true); 
                } else { 
                    origen.marca = true; 
                    arco auxA = origen.sigA; 
                    while (auxA != null) { 
                        mc.Insertar(auxA.destino, auxA.peso); 
                        RamificacionyPoda(ruta + origen.ID + "/", dist + auxA.peso); 
                        auxA = auxA.sigA; 
                    } 
                    origen.marca = false; 
                } 
            } else { 
                listaRuta = convertirRuta(ruta+ origen.ID + "/"); 
                mp.insertarPoda(listaRuta, dist, false); 
            }
        } 
    } 
     
     
     /**
      * Fecha inicio: 21/07/2020 Ultima modificación: 21/07/2020
      * @return 
      */
     public int tamanoGrafo(){
         vertice aux = grafo;
         int total = 0;
         while (aux != null) {
             total ++;             
             aux = aux.sigV;
         }
         return total;
     }
/*
     public void RamificacionyPoda(vertice actual, String ruta, int dist) { 

        if (actual.equals(ultimo) && rutaMinima > dist) {
            rutaMinima = dist;
            rutaActual = ruta + actual.ID + "/";
            listaRuta = convertirRuta(rutaActual);
            mp.insertarPoda(listaRuta, rutaMinima, true);
        } else {
            while (!mc.colaVacia()) {
                arco auxA = actual.sigA;
                while (auxA != null) {
                    mc.Insertar(auxA.destino, auxA.peso);
                    auxA = auxA.sigA;
                }
                Cola auxCola = mc.Extraer();
                actual = auxCola.value;
                RamificacionyPoda(actual, ruta, dist + auxCola.pesoArcoLlegada);
                listaRuta = convertirRuta(ruta + actual.ID + "/");
                mp.insertarPoda(listaRuta, dist, false);
            }
        }
    }
    */
   
}
