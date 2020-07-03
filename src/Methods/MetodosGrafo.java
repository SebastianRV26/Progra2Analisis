/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.arco;
import Classes.vertice;
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
    public int instrucciones = 0; // asignaciones y comparaciones

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020
     *
     * método que inserta un vértice al final para el grafo
     *
     * @param ID el identificador del vértice que deseamos crear
     * @return true o false
     */
    public boolean insertarVertices(int ID) {
        vertice nuevo = new vertice(ID, false);
        if (grafo == null) {
            grafo = nuevo;
            ultimo=nuevo;
            return true;
        }
        ultimo.sigV = nuevo;
        ultimo = nuevo;
        return true;
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
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020. método que
     * llena el grafo fuertemente conexo
     *
     * @param n es la cantidad de nodos que requiere el grafo
     */
    public void llenarGrafo(int n) {
        vertice origen, destino;
        for (int i = 0; i <= n; i++) { // primero se insertan los vertices
            insertarVertices(i);
        }
        for (int i = 0; i <= n; i++) { // luego se insertan los arcos
            origen = buscar(i);
            if (origen.ID != n) {
                for (int j = 0; j < n; j++) { // para que el grafo sea fuertemente conexo 
                    Random random = new Random();
                    destino = buscar(j);
                    if (destino.ID != origen.ID && destino.ID != 0) {
                        insertarArco(origen, destino, random.nextInt(99) + 1);
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

    public void profundidad(vertice grafo) {
        if ((grafo != null) && (grafo.marca == false)) {//2 * n = 2n
            grafo.marca = true;//n
            arco aux = grafo.sigA;//n
            while (aux != null) {//n*n = n a la 2
                System.out.println("Origen: " + grafo.ID);
                System.out.println("Peso: " + aux.peso);
                System.out.println("Destino: " + aux.destino.ID);
                System.out.println("-----------");
                profundidad(aux.destino);//n*n = n a la 2
                aux = aux.sigA;//n*n = n a la 2
            }
        } else {
            return;
        }
        //Total medicion analitica 3n a la 2 + 4n 
    }

    public void amplitud(vertice grafo) {
        if (grafo == null) {//1
            //System.out.println("No hay grafo");=
        } else {
            vertice temp = grafo;//1
            while (temp != null) {//n
                System.out.println("Vertice: " + temp.ID);
                arco aux = temp.sigA;//n == n
                while (aux != null) {//n*n = n a la 2
                    System.out.println("Destino: " + aux.destino.ID);
                    aux = aux.sigA;//n*n = n ala 2
                }
                System.out.println("-----------");
                temp = temp.sigV; //n
            }
        }
        //Total medicion analitica 2n + 3n + 2
    }

    public String rc = "";
    public int minRC = 0;
    public boolean existe = false;
    
    public void rutaCortaVoraz(vertice origen, vertice destino, String ruta, int dist) {
        if ((origen == null) || (origen.marca == true)){
            return;
        } 
        if (origen == destino){  
            if((rc.equals("")) || (minRC > dist) ) {
                rc = ruta + " / "+ destino.ID;
                minRC = dist;
            }  
            existe=true;
            return;
        }
        origen.marca = true;
        arco a = origen.sigA;
        while (a != null) {

                rutaCortaVoraz(a.destino, destino, ruta +" / "+ origen.ID, dist + a.peso);
            a = a.sigA;
        }
        origen.marca = false;
    
    }

    public void rutaCortaGenetica(vertice vertice) {

    }

    public void rutaCortaBacktracking(vertice vertice) {

    }

    public void rutaCortaDinamica(vertice vertice) {

    }

    public void rutaCortaRamificacionYPoda(vertice vertice) {

    }
}
