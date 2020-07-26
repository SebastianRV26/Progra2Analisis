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
    public int memoria = 0; // memoria consumida
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
     * Fecha inicio: 30/06/2020 Ultima modificación: 06/07/2020
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
        instrucciones ++;
        while (aux != null) {
            if (aux.ID == id) {
                instrucciones += 2;
                return aux;
            }
            aux = aux.sigV;
            instrucciones += 2;
        }
        instrucciones += 2;
        return null;
    }

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 13/06/2020
     *
     * Método que inserta un arco para el grafo
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
     * Método que inserta un arco para el grafo
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
     * Método que busca un arco del grafo
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
     * Fecha inicio: 30/06/2020 Ultima modificación: 05/07/2020.
     *
     * Método que llena el grafo fuertemente conexo
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
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020
     *
     * Método que establece la marca de todos los nodos del grafo como false
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

    public void amplitud(vertice grafo) { // BORRAR LUEGO
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

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 10/07/2020.
     *
     * Método de ruta corta que consiste en irse por el arco de menor peso hasta
     * llegar al destino, no está permitido devolverse
     *
     * @param origen vértice de origen de arco al que queremos buscar
     * @param destino vértice destino de arco al que queremos buscar
     * @param ruta vértices y sus respectivos pesos de la ruta encontrada
     * @param distancia distancia total de los arcos por los vértices que pasa
     */
    public void rutaCortaVoraz(vertice origen, vertice destino, String ruta, int distancia) {
        if ((origen == null) || (origen.marca == true)) {
            instrucciones += 2;
            return;
        }
        if (origen == destino) {
            instrucciones++;
            System.out.println("Voraz: " + ruta + " distancia: " + distancia);
        }
        int min = 100; // 
        arco auxMenor = null;
        arco aux = origen.sigA;
        instrucciones += 3;
        while (aux != null) {
            instrucciones++;
            if (aux.peso < min && aux.destino.marca == false) {
                min = aux.peso;
                auxMenor = aux;
                instrucciones += 4;
            }
            aux = aux.sigA;
            instrucciones++;
        }
        origen.marca = true;
        instrucciones += 2;
        if (auxMenor != null) {
            instrucciones++;
            rutaCortaVoraz(auxMenor.destino, destino, ruta + "->P" + auxMenor.peso + " V" + auxMenor.destino.ID + "/", distancia + auxMenor.peso);
        } else {
            instrucciones++;
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
            instrucciones ++;
            return;
        }
        instrucciones +=2;
        if (vertex.ID == ultimo.ID) {
            rutaV = convertirRuta(ruta + vertex.ID + "/");
            mld.insertarRuta(rutaV, pesoRuta, true);
            memoria += pesoArrayConElementos;
            instrucciones += 3;
        } else {
            rutaV = convertirRuta(ruta + vertex.ID + "/");
            mld.insertarRuta(rutaV, pesoRuta, false);
            memoria += pesoArrayConElementos;
            instrucciones += 3;
        }
        vertex.marca = true;
        arco auxA = vertex.sigA;
        memoria += pesoArco;
        instrucciones +=2;
        while (auxA != null) {
            rutaCortaBacktracking(auxA.destino, ruta + vertex.ID + "/", pesoRuta + auxA.peso);
            auxA = auxA.sigA;
            instrucciones +=3;
        }
        vertex.marca = false;
        instrucciones ++;
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
        instrucciones += 2;
        memoria += pesoArrayVacio + pesoVectorStringConElementos;
        for (int i = 0; i < verticesID.length; i++) {
            String idV = verticesID[i];
            memoria += 8*idV.length();
            if (!idV.equals("")) {
                int id = Integer.parseInt(idV);
                memoria += 32;
                rutaVertices.add(buscar(id));
                instrucciones += 3;
            }
            instrucciones += 4;
        }
        memoria += pesoArrayConElementos;
        instrucciones += 2;
        return rutaVertices;
    }

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 26/07/2020.
     *
     * Método que impreme la ruta del algoritmo de programación dinámica
     * Dijkstra
     *
     * @param destino último vértice
     */
    public void mostrarRuta(vertice destino) {
        String ruta = "";
        vertice aux = destino;
        instrucciones += 2;
        while (aux != null) {
            ruta = "->P" + aux.pesoMin + " V" + aux.ID + "/" + ruta;
            aux = aux.antV;
            instrucciones += 2;
        }
        instrucciones++;
        System.out.println("PD: " + ruta + " distancia: " + destino.distanciaMinima);
    }

    /**
     * Fecha inicio: 07/07/2020. Ultima modificación: 26/07/2020.
     *
     * Lógica del algoritmo Dijkstra: analizar la mejor manera de llegar al
     * destino con forme a la menor distancia de llegar de un vértice a otro
     *
     * @param origen vértice de origen de arco al que queremos insertar
     * @param destino vértice destino de arco al que queremos insertar
     */
    public void rutaCortaDinamica(vertice origen, vertice destino) {
        vertice aux = origen;
        int min;
        aux.distanciaMinima = 0;
        instrucciones += 2;
        byte cont = 1;
        while (aux != null) {
            arco auxA = aux.sigA;
            arco auxMin = null;
            min = 10000;
            instrucciones += 4;
            while (auxA != null) {
                instrucciones++;
                if (auxA.destino.distanciaMinima > auxA.peso + aux.distanciaMinima && auxA.destino.marca != true) {
                    auxA.destino.distanciaMinima = auxA.peso + aux.distanciaMinima;
                    auxA.destino.antV = aux;
                    auxA.destino.pesoMin = auxA.peso;
                    instrucciones += 3;
                }
                instrucciones += 2;
                if (min > auxA.peso + aux.distanciaMinima && !auxA.destino.marca) {
                    min = auxA.peso + aux.distanciaMinima;
                    auxMin = auxA;
                    instrucciones += 2;
                }
                instrucciones += 3;
                auxA = auxA.sigA;
            }
            if (cont < 6) { // no se cuenta como memoria
                vertice aux2 = origen;
                System.out.println("--------------------------------------------");
                System.out.println("PD fase " + cont);
                while (aux2 != null) {
                    System.out.println(aux2.ID + " distancia: " + aux2.distanciaMinima);
                    aux2 = aux2.sigV;
                }
                cont++;
            }
            instrucciones++;
            if (aux == destino) {
                break;
            }
            aux.marca = true;
            aux = auxMin.destino;
            instrucciones += 3;
        }
        mostrarRuta(destino);
    }

    public String rutaActual = "";
    ArrayList<vertice> listaRuta;
     int rutaMinima = 0;

    /**
     * Fecha inicio: 09/07/2020 Ultima modificación: 12/07/2020
     *
     * @param ruta
     * @param dist
     */
      public void RamificacionyPoda(String ruta, int dist) {
        while (!mc.colaVacia()) {
            Cola auxCola = mc.Extraer();
            vertice origen = auxCola.value;
            memoria += pesoCola + pesoVertice; 
            if (origen.marca) {
                instrucciones += 3;
                return;
            }
            instrucciones += 4;
            if ((rutaActual == "" || rutaMinima > dist)) {
                instrucciones += 2;
                if (origen.equals(ultimo)) {
                    rutaMinima = dist;
                     listaRuta = convertirRuta(rutaActual);
                    rutaActual = ruta + origen.ID + "/";
                    memoria += 8*rutaActual.length();
                    memoria += 32;
                    memoria += pesoArrayConElementos;
                    mp.insertarPoda(listaRuta, rutaMinima, true);
                    instrucciones += 5;
                } else {
                    origen.marca = true;
                    arco auxA = origen.sigA;
                    memoria +=pesoArco;
                    instrucciones += 2;
                    while (auxA != null) {
                        mc.Insertar(auxA.destino, auxA.peso);
                        RamificacionyPoda(ruta + origen.ID + "/", dist + auxA.peso);
                        auxA = auxA.sigA;
                        instrucciones += 4;
                    }
                    origen.marca = false;
                    instrucciones += 2;
                }

            } else {
                listaRuta = convertirRuta(ruta + origen.ID + "/");
                mp.insertarPoda(listaRuta, dist, false);
                instrucciones += 2;
            }
        }
        instrucciones += 1 +mp.instruccionesPoda + mc.instruccionesCola;
        memoria += mp.memoriaPoda + mc.memoriaCola;
    }

    /**
     * Fecha inicio: 21/07/2020 Ultima modificación: 21/07/2020
     *
     * @return
     */
    public int tamanoGrafo() {
        vertice aux = grafo;
        int total = 0;
        while (aux != null) {
            total++;
            aux = aux.sigV;
        }
        return total;
    }

    public void datosRyP() {
        memoria = 0;
        instrucciones = 0;
        mc.Insertar(grafo, 0);
        RamificacionyPoda("", 0);
        System.out.println("Ruta corta por el diseño Ramificación y Poda");
        mp.imprimirRuta(mp.rutaCorta);
        System.out.println("Memoria usada por RyP: " + memoria + " " + "bits");
        System.out.println("Instrucciones usadas por RyP: "+ instrucciones);
        System.out.println("Total de rutas podadas: " + mp.totalRutasPodadas());
        System.out.println("=====================");
        System.out.println("5 ejemplos de rutas podadas");
        mp.imprimirRutaPodada();
    }
    
    public void datosBactraking() {
        memoria = 0;
        instrucciones = 0;
        rutaCortaBacktracking(grafo, "", 0);
        System.out.println("Ruta corta por el diseño Bactraking");
        mld.imprimirRuta(mld.rutaCorta);
        System.out.println("Memoria usada por Bactraking: " + memoria + " " + "bits");
        System.out.println("Instrucciones usadas por Bactraking: " + instrucciones);
        mld.rutasValidas();
        System.out.println("Rutas random del backtraking");
        mld.rutasRandom();
    }
}
