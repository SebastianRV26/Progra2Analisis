/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

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
            System.out.println(ID);
            return true;
        }
        vertice aux = grafo;
        while (aux != null) {
            if (aux.sigV == null) {
                System.out.println(aux.ID);
                System.out.println(ID);
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
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020
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
     * Fecha inicio: 05/07/2020 Ultima modificación: 05/07/2020
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
            System.out.println(ruta + " distancia: " + distancia);
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
            System.out.println(auxMenor.destino.ID + "/");
            rutaCortaVoraz(auxMenor.destino, destino, ruta + auxMenor.destino.ID + "/", distancia + auxMenor.peso);
        } else {
            rutaCortaVoraz(destino, destino, ruta, distancia);
        }
        return;
    }

    public Integer tamannoGrafo() {
        vertice aux = grafo;
        int cant = 0;
        while (aux != null) {
            cant++;
            aux = aux.sigV;
        }
        return cant;
    }

    public void rutaCortaGenetica(vertice vertice) {

    }

    public void rutaCortaBacktracking(vertice vertex, String ruta, int pesoRuta) {
        if ((vertex == null) || (vertex.marca)) {
            return;
        }
        if (vertex.ID == ultimo.ID) {
            mld.insertarRuta(ruta  + vertex.ID + "/", pesoRuta, true);
        } else {
            mld.insertarRuta(ruta + vertex.ID + "/", pesoRuta, false);
        }
        vertex.marca = true;
        arco auxA = vertex.sigA;
        while (auxA != null) {
            rutaCortaBacktracking(auxA.destino, ruta + vertex.ID + "/", pesoRuta + auxA.peso);
            auxA = auxA.sigA;
        }
        vertex.marca = false;
    }

    public void rutaCortaDinamica(vertice vertice) {

    }

    public void rutaCortaRamificacionYPoda(vertice vertice) {

    }
}
