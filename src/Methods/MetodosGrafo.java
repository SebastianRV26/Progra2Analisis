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
////////////////////////////////////////////////////////////////////////////////
    
    ArrayList<ArrayList<vertice>> Manipulados = new ArrayList();
    ArrayList<vertice> padre1;
    ArrayList<vertice> padre2;
    
    public void ImprimirGenetico(ArrayList<ArrayList<vertice>> Manipulados){
        ArrayList<ArrayList<vertice>> prueba = new ArrayList<>();
        ListaDoble aux = mld.inicio;
        
        while (aux != null) {
           if(aux.llegaDestino){
               prueba.add(aux.verticesRuta);
           }
            aux = aux.sigN;
        }
        
        System.out.println(prueba);
        ListaDoble temp = aux;
        String ruta  = " ";
        
        //esto imprime las rutas
        for (ArrayList<vertice> arrayList : prueba) {//  este prueba tiene todas las rutas 
            for (vertice object : arrayList) {// tiene vertices , es la ruta de la que esta compuesta esa ruta
                ruta = ruta + object.ID + "/";
            }
            System.out.println(ruta);
            
            ruta = "";
        }
    }
    public void ImprimirTodasRutas(ArrayList<ArrayList<vertice>> Manipulados){
        System.out.println("Estan son todas las rutas con las que se va a trabajar");
        String ruta  = " ";
        //esto imprime las rutas
        for (ArrayList<vertice> arrayList : Manipulados) {//  este prueba tiene todas las rutas 
            for (vertice object : arrayList) {// tiene vertices , es la ruta de la que esta compuesta esa ruta
                ruta = ruta + object.ID + "/";
            }
            System.out.println(ruta);
            ruta = "";
        }
        System.out.println("////////////////////////");
    }
    public void ImprimirRuta(ArrayList<vertice> Manipulados){
        System.out.println("Esta es la ruta");
        String ruta  = " ";
        //esto imprime la ruta
            for (vertice object : Manipulados) {// tiene vertices , es la ruta de la que esta compuesta esa ruta
                ruta = ruta + object.ID + "/";
            }
            System.out.println(ruta);
            ruta = "";
    }
    
    /**
     * 
     * @param tamGrafo
     * @return ArrayList<ArrayList<vertice>> que son todas las Arraylist de rutas.
     */
    public ArrayList<ArrayList<vertice>> generarPoblacion(int tamGrafo){
        ArrayList<ArrayList<vertice>> poblacionInicial = new ArrayList<>();
        ListaDoble aux = mld.inicio;
        while (aux != null) {
           if(aux.llegaDestino){
               if(aux.verticesRuta.size()>(tamGrafo/2)){
                   poblacionInicial.add(aux.verticesRuta);
               }          
           }
            aux = aux.sigN;
        }
        ListaDoble temp = aux;
        return poblacionInicial;
    }
    
    /**
     * 
     * @param poblacion todas las rutas a analizar
     */
    public void ag_escogerPadres(ArrayList<ArrayList<vertice>> poblacion){
        Random random = new Random();
        int index2 = random.nextInt(poblacion.size())-1;
        if(padre1==null & padre2==null){
            int index = random.nextInt(poblacion.size())-1;
            padre1 = poblacion.get(index);
            padre2 = poblacion.get(index2);
            poblacion.remove(poblacion.get(index2));
            poblacion.remove(poblacion.get(index));
        }//end 1er if
        else if(padre1!=null){
            
            padre2 = poblacion.get(index2);
            poblacion.remove(poblacion.get(index2));
        }
        Manipulados.add(padre1);
        Manipulados.add(padre2);
    }
    
    public void ag_cruzar(ArrayList<vertice> padre,ArrayList<vertice> madre){
        
    }
    
    public void ag_evaluarFitness(){
        
    }
    
    public void ag_mutar(ArrayList<vertice> hijo, int probMutacion){
        //si no hay mutacion el hijo debe salir igual
        ArrayList<vertice> hijoMutado = hijo;
        double pto1 = Math.random()*hijo.size();
        double pto2 = Math.random()*hijo.size();
    }
    

    public void rutaCortaGenetica(int tamGrafo ) {
        ArrayList<ArrayList<vertice>> poblacion = new ArrayList<>();
        poblacion = generarPoblacion(8);
        //ImprimirTodasRutas(poblacion);
        ArrayList<vertice> hijoPrueba = poblacion.get(6);
        padre1 = hijoPrueba;
        ag_escogerPadres(poblacion);
        ImprimirTodasRutas(Manipulados);
        
       //ImprimirGenetico(poblacion);
    
        
        ArrayList<ArrayList<vertice>> prueba = new ArrayList<>();
        for (int i = 0; i < prueba.size(); i++) {
            prueba.get(i);
            prueba.get(i+1);
            prueba.remove(i+ 1);
        }
 
    }

      //  for (ArrayList<vertice> arrayList : prueba) {
        //    for (ArrayList<vertice> array1 : prueba) {
            
          //  System.out.println(array1);
            
        //}

    
    /**
     *   Fecha inicio: 30/06/2020 Ultima modificación: 10/07/2020
     * @param vertex
     * @param ruta
     * @param pesoRuta 
     */

      ArrayList<vertice> rutaV ;
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

    public void rutaCortaDinamica(vertice vertice) {

    }
   

    
    
    public int rutaMinima = 0;
    public String rutaActual = "";  
    ArrayList<vertice> listaRuta;
    
/**
 * Fecha inicio: 09/07/2020 Ultima modificación: 12/07/2020
 * @param origen
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
            if ((rutaActual.equals("") || rutaMinima > dist)) {
                if (origen.equals(ultimo)) {
                    rutaMinima = dist;
                    rutaActual = ruta + origen.ID + "/";
                    System.out.println(rutaActual);
                    System.out.println("Tiene solucion");
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
                System.out.println(ruta + origen.ID + "/");
                mp.insertarPoda(listaRuta, dist, false);
                System.out.println("Ruta podada");
            }
        }
    }
}
