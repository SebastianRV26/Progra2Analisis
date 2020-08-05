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
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
    public double pesoListaDoble = 320;
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
        vertice aux = grafo;//1
        instrucciones ++;//1
        while (aux != null) {//1
            if (aux.ID == id) {//1
                instrucciones += 2;
                return aux;//1
            }
            aux = aux.sigV;//1
            instrucciones += 2;
        }
        instrucciones += 2;
        return null;//1
       //Medicion analitica: 7
    }

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 13/06/2020
     *
     * Método que inserta un arco simple para los caminos que salen del
     * primer nodo y los que llegan al ultimo
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
     * Método que inserta un arco doble para los vertices que no sean 
     * el primer nodo  y el ultimo
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
     * Método que llena el grafo fuertemente conexo,
     * respetando las instrucciones dadas por la profesora
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
     * Función que muestra la cantidad de instrucciones y memria utilizada en el
     * algoritmo voraz
     *
     * @param origen vértice de origen de arco al que queremos buscar
     * @param destino vértice destino de arco al que queremos buscar
     * @param ruta vértices y sus respectivos pesos de la ruta encontrada
     * @param distancia distancia total de los arcos por los vértices que pasa
     */
    public void MostrarRutaCortaVoraz(vertice origen, vertice destino, String ruta, int distancia) {
        quitarMarca(origen);
        memoria = 0;
        instrucciones = 0;
        System.out.println("Ruta corta por el diseño voraz");
        rutaCortaVoraz(origen, destino, ruta, distancia);
        System.out.println("Memoria usada por voraz: " + memoria + " bits");
        System.out.println("Instrucciones usadas por voraz: " + instrucciones + "\n");
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
        instrucciones += 2;
        if ((origen == null) || (origen.marca == true)) {
            return;
        }
        instrucciones++;
        if (origen == destino) {
            System.out.println("Voraz: " + ruta + " distancia: " + distancia);
        }
        int min = 100;
        arco auxMenor = null;
        arco aux = origen.sigA;
        instrucciones += 3;
        memoria += 32 + pesoArco * 2;
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
        instrucciones += 3;
        if (auxMenor != null) {
            rutaCortaVoraz(auxMenor.destino, destino, ruta + "->P" + auxMenor.peso + " V" + auxMenor.destino.ID + "/", distancia + auxMenor.peso);
        } else {
            instrucciones++;
            rutaCortaVoraz(destino, destino, ruta, distancia);
        }
        return;
    }
////////////////////////////////////////////////////////////////////////////////

    ArrayList<ArrayList<vertice>> Manipulados = new ArrayList();
    ArrayList<vertice> padre1;
    ArrayList<vertice> padre2;
    ArrayList<ArrayList<vertice>> poblacion = new ArrayList();
    
    public void generarPadres(vertice vertex, String ruta, int pesoRuta, int ultimo ) {
        if ((vertex == null) || (vertex.marca)) {
            return;
        }
        if (vertex.ID == ultimo) {
            poblacion.add(convertirRuta(ruta + vertex.ID + "/"));
            // array.append convertirRuta(ruta + vertex.ID + "/");
        }
        // si array.len == 200 retorne 
        if (poblacion.size()==220){
                return;
            }
        vertex.marca = true;
        arco auxA = vertex.sigA;
        while (auxA != null) {
            generarPadres(auxA.destino, ruta + vertex.ID + "/", pesoRuta + auxA.peso, ultimo);
            // si array.len == 200 retorne 
            if (poblacion.size()==220){
                return;
            }
            auxA = auxA.sigA;
        }
        vertex.marca = false;
    }
    
    /**
     * 
     * @param Manipulados 
     */
    public void ImprimirTodasRutas(ArrayList<ArrayList<vertice>> Manipulados){
        System.out.println("Estan son todas las rutas con las que se va a trabajar");
        String ruta = " ";
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
    /**
     * 
     * @param Manipulados 
     */
    public void ImprimirRuta(ArrayList<vertice> Manipulados){
        System.out.println("Esta es la ruta");
        String ruta = " ";
        System.out.println(Manipulados.size());
        //esto imprime la ruta
        
        
        for (int i = 0; i < Manipulados.size(); i++) { // tiene vertices , es la ruta de la que esta compuesta esa ruta
                ruta = ruta + Manipulados.get(i).ID + "/";
        }

        
        
        System.out.println(ruta);
        ruta = "";
    }
    /**
     * 
     * @param Manipulados 
     */
    public void ImprimirRuta(List<vertice> Manipulados){
        System.out.println("Esta es la sublista: ");
        String ruta = " ";
        //esto imprime la ruta
        for (vertice object : Manipulados) {// tiene vertices , es la ruta de la que esta compuesta esa ruta
            ruta = ruta + object.ID + "/";
        }
        System.out.println(ruta);
        ruta = "";
    }

    /**
     * 
     *
     * @param tamGrafo
     * @return ArrayList<ArrayList<vertice>> que son todas las Arraylist de
     * rutas.
     */
    public ArrayList<ArrayList<vertice>> generarPoblacion(int tamGrafo) {
        ArrayList<ArrayList<vertice>> poblacionInicial = new ArrayList<>();
        ListaDoble aux = mld.inicio;
        while (aux != null) {
            if (aux.llegaDestino) {
                if (aux.verticesRuta.size() > (tamGrafo / 2)) {
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
     * @param poblacionPadres todas las rutas a analizar
     */
    public void ag_escogerPadres(ArrayList<ArrayList<vertice>> poblacionPadres) {
        Random random = new Random();
        int index2, index;
        while (true) {
            index2 = random.nextInt(poblacionPadres.size() - 1) + 1;
            index = random.nextInt(poblacionPadres.size() - 1);
            if (index2 != index) {
                break;
            }
        }
        if (padre1 == null && padre2 == null) {

            padre1 = poblacionPadres.get(index);
            padre2 = poblacionPadres.get(index2);
            poblacionPadres.remove(poblacionPadres.get(index2));
            poblacionPadres.remove(poblacionPadres.get(index));
        }//end 1er if    
    }
    /**
     * 
     * @param padre
     * @param madre
     * @param tamGrafo 
     */
    public void ag_cruzar(ArrayList<vertice> padre,ArrayList<vertice> madre, int tamGrafo){
        ArrayList<vertice> hijo1 = new ArrayList();
        ArrayList<vertice> hijo2 = new ArrayList();

        List<vertice> sub1 = new ArrayList<vertice>();
        List<vertice> sub2 = new ArrayList<vertice>();
        List<vertice> sub3 = new ArrayList<vertice>();
        List<vertice> sub4 = new ArrayList<vertice>();

        //punto de cruce
        int randomNum;
        boolean esta;
        boolean esta2;
        int cont = 0;
        while (true) {

            
            randomNum = ThreadLocalRandom.current().nextInt(2, tamGrafo);
            System.out.println("Punto de cruse: " + randomNum);
            esta = padre.contains(buscar(randomNum));
            esta2 = madre.contains(buscar(randomNum));
            if (esta && esta2) {
                System.out.println("estan ambos");
                //recorre los padres para buscar el punto de cruce que se hace random
                for (int i = 0; i < padre.size() - 1; i++) {
                    if (padre.get(i).ID == randomNum) {
                        sub1 = padre.subList(0, i);//creo la sublista 1
                        sub2 = padre.subList(i, padre.size());//creo la sublista 2
                        ImprimirRuta(sub1);
                        ImprimirRuta(sub2);
                    }
                }
                for (int j = 0; j < madre.size() - 1; j++) {
                    if (madre.get(j).ID == randomNum) {
                        sub3 = madre.subList(0, j);//creo la sublista 3
                        sub4 = madre.subList(j, madre.size());//creo la sublista 4
                        ImprimirRuta(sub3);
                        ImprimirRuta(sub4);
                    }
                }
                //esto arma al hijo1 con sub 1-4
                for (int a = 0; a < sub1.size(); a++) {
                    hijo1.add(sub1.get(a));
                }
                for (int b = 0; b < sub4.size(); b++) {
                    hijo1.add(sub4.get(b));
                }
                //esto arma al hijo2 con sub 3-2
                for (int a = 0; a < sub3.size(); a++) {
                    hijo2.add(sub3.get(a));
                }
                for (int b = 0; b < sub2.size(); b++) {
                    hijo2.add(sub2.get(b));
                }
                ag_evaluar(padre, madre, hijo1, hijo2);
                break;
            }
            cont++;
            if (cont > 6) {
                if (ag_evaluarFitness2(padre) < ag_evaluarFitness2(madre)) {
                    System.out.println("se escogio el padre, como mejor ruta");
                    ImprimirRuta(padre);
                    ag_evaluarFitness(padre);
                    System.out.println("se escogen nuevos padres");
                    Manipulados.add(padre);
                    padre1 = null;
                    padre2 = null;
                } else {
                    System.out.println("se escogio la madre, como mejor ruta");
                    ImprimirRuta(madre);
                    ag_evaluarFitness(madre);
                    System.out.println("se escogen nuevos padres");
                    Manipulados.add(madre);
                    padre1 = null;
                    padre2 = null;
                }
                break;
            }
            System.out.println("volvio a sacar punto de cruce");   
        }  
    }
   /**
    * 
    * @param padre
    * @param madre
    * @param hijo1
    * @param hijo2 
    */
    public void ag_evaluar(ArrayList<vertice> padre,ArrayList<vertice> madre,ArrayList<vertice> hijo1,ArrayList<vertice> hijo2){
        if(ag_evaluarFitness2(padre)<ag_evaluarFitness2(madre)){
            if (ag_evaluarFitness2(padre)<ag_evaluarFitness2(hijo1)) {
                if (ag_evaluarFitness2(padre)<ag_evaluarFitness2(hijo2)) {
                    System.out.println("Es mejor el padre");
                    ImprimirRuta(padre);
                    ag_evaluarFitness(padre);
                    if (ag_mutar(padre)) {
                        System.err.println("muto el padre");
                    }
                    padre1 = null;
                    padre2 = null;

                } else {
                    System.out.println("Es mejor el hijo2");
                    ImprimirRuta(hijo2);
                    ag_evaluarFitness(hijo2);
                    Manipulados.add(hijo2);
                    padre1 = null;
                    padre2 = null;
                }
            } else {
                if (ag_evaluarFitness2(hijo1) < ag_evaluarFitness2(hijo2)) {
                    System.out.println("Es mejor el hijo1");
                    ImprimirRuta(hijo1);
                    ag_evaluarFitness(hijo1);
                    Manipulados.add(hijo1);
                    padre1 = null;
                    padre2 = null;
                } else {
                    System.out.println("Es mejor el hijo2");
                    ImprimirRuta(hijo2);
                    ag_evaluarFitness(hijo2);
                    Manipulados.add(hijo2);
                    padre1 = null;
                    padre2 = null;
                }
            }
        } else {
            if (ag_evaluarFitness2(madre) < ag_evaluarFitness2(hijo1)) {
                if (ag_evaluarFitness2(madre) < ag_evaluarFitness2(hijo2)) {
                    System.out.println("Es mejor la madre");
                    ImprimirRuta(madre);
                    ag_evaluarFitness(madre);
                    if (ag_mutar(madre)) {
                        System.out.println("muto el padre siendo madre");
                    }
                    padre1 = null;
                    padre2 = null;
                } else {
                    System.out.println("Es mejor el hijo2");
                    ImprimirRuta(hijo2);
                    ag_evaluarFitness(hijo2);
                    Manipulados.add(hijo2);
                    padre1 = null;
                    padre2 = null;
                }
            } else {
                if (ag_evaluarFitness2(hijo1) < ag_evaluarFitness2(hijo2)) {
                    System.out.println("Es mejor el hijo1");
                    ImprimirRuta(hijo1);
                    ag_evaluarFitness(hijo1);
                    Manipulados.add(hijo1);
                    padre1 = null;
                    padre2 = null;
                } else {
                    System.out.println("Es mejor el hijo2");
                    ImprimirRuta(hijo2);
                    ag_evaluarFitness(hijo2);
                    Manipulados.add(hijo2);
                    padre1 = null;
                    padre2 = null;
                }
            }
        }
    }
    
    
    /**
     * 
     * @param ruta
     * @return 
     */
    public int ag_evaluarFitness(ArrayList<vertice> ruta){
        int peso = 0;
        String rutaStr = "";
        rutaStr = "" + ruta.get(0).ID;
        for (int i = 0; i < ruta.size() - 1; i++) {
            arco auxA = buscar(ruta.get(i), ruta.get(i + 1));
            rutaStr += " Peso: -> " + auxA.peso + " Vertice destino: " + auxA.destino.ID;
            peso = peso + auxA.peso;
        }
        System.out.println("Ruta: " + rutaStr);
        System.out.println("peso ruta: " + peso);
        return peso;
    }

    /**
     *
     * @param ruta
     * @return
     */
    public int ag_evaluarFitness2(ArrayList<vertice> ruta) {
        int peso = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            arco auxA = buscar(ruta.get(i), ruta.get(i + 1));
            peso = peso + auxA.peso;
        }
        return peso;
    }
    /**
     * 
     * @param hijo
     * @return 
     */
    public boolean ag_mutar(ArrayList<vertice> hijo){
        //si no hay mutacion el hijo debe salir igual
        ArrayList<vertice> hijoMutado = hijo;
        ArrayList<vertice> hijoMutado2 = new ArrayList();
        //recorrer el hijo nuevo
        int numero = 0;
        arco actual = null;
        vertice vO = null;
        vertice vD = null;

        for (int i = 0; i < hijoMutado.size() - 1; i++) {
            //preguntar por el arco de mayor tamaño 
            arco auxA = buscar(hijoMutado.get(i), hijoMutado.get(i + 1));
            if (auxA.peso > numero) {
                numero = auxA.peso;
                actual = auxA;
                vO = hijoMutado.get(i);
                vD = hijoMutado.get(i + 1);
                System.out.println("Arco mas grande: " + numero + "vertice de origen: " + vO.ID);
            }
        }
        //cambiarlo por otro vertice destino
        vertice vNuevo = null;
        arco auxA2 = vO.sigA;
        while (auxA2 != null) {
            if (!hijoMutado.contains(auxA2.destino)) {
                vNuevo = auxA2.destino;
                System.out.println("el nuevo vertice: " + vNuevo.ID);
            }
            auxA2 = auxA2.sigA;
        }
        for (int i = 0; i < hijo.size(); i++) {
            if (hijo.get(i) == vO) {
                if (vNuevo != null) {
                    hijoMutado2.add(hijo.get(i));
                    hijoMutado2.add(vNuevo);
                }
            } else {
                hijoMutado2.add(hijo.get(i));
            }
        }
        ImprimirRuta(hijoMutado2);
        // ver si la ruta aun existe y si mejora
        if (ag_evaluarFitness2(hijo) < ag_evaluarFitness2(hijoMutado2)) {
            // sale hijo nuevo sin mutar
            System.out.println("no sirvio");
            Manipulados.add(hijo);
            padre1 = null;
            return false;
        } else {
            //si mejora sale hijo mutado
            System.out.println("si sirvio");
            ag_evaluarFitness(hijoMutado2);
            Manipulados.add(hijoMutado2);
            padre1 = null;
            return true;
        }
        //return hijo
    }
    
    /**
     * 
     * @param tamGrafo
     * @param cantVeces 
     */
    public void rutaCortaGenetica(int tamGrafo, int cantVeces) {
        //ArrayList<ArrayList<vertice>> poblacion = new ArrayList<>();
        
        //poblacion = generarPoblacion(8);

        int cont = 0;
        while (cont < cantVeces) {
            ag_escogerPadres(poblacion);
            System.out.println("Padre  "  + padre1.size());
            for (int i = 0; i < padre1.size(); i++) {
                System.out.println(padre1.get(i).ID);
            }
            ImprimirRuta(padre1);
            ImprimirRuta(padre2);
            ag_cruzar(padre1, padre2, tamGrafo);
            System.out.println("Escogemos padres de nuevo");
            cont++;
        }
        ImprimirTodasRutas(Manipulados);
        while (Manipulados.size() != 1) {
            for (int i = 0; i < Manipulados.size(); i++) {
                ag_escogerPadres(Manipulados);
                ImprimirRuta(padre1);
                ImprimirRuta(padre2);
                ag_cruzar(padre1, padre2, tamGrafo);
                System.out.println("Escogemos padres de nuevo");
            }
            ImprimirTodasRutas(Manipulados);
        }
        ag_evaluarFitness(Manipulados.get(0));
    }
    
    

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 10/07/2020
     * 
     * Metodo que implementa el diseño Backtracking, 
     * su funcionalidad es encontrar todos las posibles rutas que tenga 
     * un grafo de determinado tamaño, logra esto recorriendo el grafo
     * de con el metoo de profundidad, el cual se modificao para poder implementarlo
     * con el backtracking
     *
     * @param vertex Este parametro indica el vertice al cual  el metodo va analisar 
     * @param ruta Es la ruta que lleva el metodo guardada hasta el vertice actual
     * @param pesoRuta Es el peos  que lleva el metodo guardado hasta el vertice actual
     */
    ArrayList<vertice> rutaV;

    public void rutaCortaBacktracking(vertice vertex,vertice ultimo, String ruta, int pesoRuta) {
        if ((vertex == null) || (vertex.marca)) {//2n
            instrucciones ++;
            return;
        }
        instrucciones +=2;
        if (vertex.ID == ultimo.ID) {//n
            rutaV = convertirRuta(ruta + vertex.ID + "/"); //12n a la 2 +4n
            mld.insertarRuta(rutaV, pesoRuta, true);//4n a la 2+18n
            memoria += pesoArrayConElementos;
            instrucciones += 3;
        } else {
            rutaV = convertirRuta(ruta + vertex.ID + "/");//12n a la 2 +4n
            mld.insertarRuta(rutaV, pesoRuta, false);///4n a la 2+18n
            memoria += pesoArrayConElementos;
            instrucciones += 3;
        }
        vertex.marca = true;//n
        arco auxA = vertex.sigA;//n
        memoria += pesoArco;
        instrucciones +=2;
        while (auxA != null) {//n a la 2
            rutaCortaBacktracking(auxA.destino,ultimo, ruta + vertex.ID + "/", pesoRuta + auxA.peso);///n a la 2
            auxA = auxA.sigA;//n a la 2
            instrucciones +=3;
        }
        vertex.marca = false;//n
        instrucciones ++;
        
        //Medicion analitica: 35n a la 2+50n
    }

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 10/07/2020
     *
     * Metodo que convierte un string a donde esta guarda una ruta
     * a un arraylist de vertices, esto para poder acceder de forma mas sencilla
     *  a la informcación de los vertices
     * @param ruta Por este parametro llega la ruta en string, esta es la que se convierte
     * @return El metodo retorna un arraylist con los vertices que componen la ruta
     */
    private ArrayList<vertice> convertirRuta(String ruta) {
        ArrayList<vertice> rutaVertices = new ArrayList<>();//1
        String[] verticesID = ruta.split("/");//1
        instrucciones += 2;
        memoria += pesoArrayVacio + pesoVectorStringConElementos;
        for (int i = 0; i < verticesID.length; i++) {//2n +1
            String idV = verticesID[i];//n
            memoria += 8*idV.length();
            if (!idV.equals("")) {//1n
                int id = Integer.parseInt(idV);//1(n)=n
                memoria += 32;
                rutaVertices.add(buscar(id));//8(n)=8n
                instrucciones += 3;
            }
            instrucciones += 4;
        }
        memoria += pesoArrayConElementos;
        instrucciones += 2;
        return rutaVertices;//1
       //Medicion analitica: 12n +4
    }

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 26/07/2020.
     *
     * Función que muestra la cantidad de instrucciones y memria utilizada en el
     * algoritmo de programación dinámica
     *
     * @param origen vértice de origen de arco al que queremos insertar
     * @param destino vértice destino de arco al que queremos insertar
     * @param tamannio tamaño del grafo
     */
    public void MostrarRutaCortaDinamica(vertice origen, vertice destino, int tamannio) {
        quitarMarca(origen);
        memoria = 0;
        instrucciones = 0;
        System.out.println("Ruta corta por el diseño programación dinámica");
        rutaCortaDinamica(origen, destino);
        System.out.println("Memoria usada por programación dinámica con tamaño " + tamannio + ": " + memoria + " bits");
        System.out.println("Instrucciones usadas por programación dinámica con tamaño " + tamannio + ": " + instrucciones + "\n");
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
        memoria += pesoVertice;
        instrucciones += 2;
        while (aux != null) {
            ruta = "->P" + aux.pesoMin + " V" + aux.ID + "/" + ruta;
            aux = aux.antV;
            instrucciones += 2;
        }
        memoria += ruta.length() * 8;
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
        memoria += pesoVertice + 32;
        arco auxA;
        arco auxMin;
        memoria += pesoArco * 2;
        aux.distanciaMinima = 0;
        instrucciones += 2;
        byte cont = 1; // no se cuenta, es para las primeras 5 fases
        while (aux != null) {
            auxA = aux.sigA;
            auxMin = null;
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
     * Metodo que busca la ruta mas corta mediante el diseño de
     * ramificación y poda, este algoritmo primero va iniciar a recorrer 
     * el grafo desde el nodo inicio, este ya va a estar agregado en la lista de 
     * nodos vivos, despues de revisar este nodo se generan sus respectivos hijos, esto  es para ver  cual 
     * ruta es la mejor, si hay una ruta que su peso sea mayor a la menor encontrada hasta el momento se poda, en caso 
     * contrario, si la ruta aun no hallegado al destino, pero aun es una ruta fiable se sigue recorriendo, hasta encontrar 
     * un punto donde se pode o que cambie la ruta mas corta
     *
     * @param ruta parametro que va indica la ruta por la que se encuentra actualmente el recorrido
     * @param dist parametro que va indica el peso que tiene la ruta  por la que se encuentra actualmente el recorrido
     */
      public void RamificacionyPoda(vertice ultimo, String ruta, int dist) {
            while (!mc.colaVacia()) {//4n a la 2
            Cola auxCola = mc.Extraer();//12n
            vertice origen = auxCola.value;//1n
            memoria += pesoCola + pesoVertice; 
            if (origen.marca) {//1n
                instrucciones += 3;
                return;//1
            }
            instrucciones += 4;
            if ((rutaActual == "" || rutaMinima > dist)) {//2n
                instrucciones += 2;
                if (origen.equals(ultimo)) {//1n
                    rutaMinima = dist;//1n
                     listaRuta = convertirRuta(rutaActual);//12n a la 2+5n
                    rutaActual = ruta + origen.ID + "/";//1n
                    memoria += 8*rutaActual.length();
                    memoria += 32;
                    memoria += pesoArrayConElementos;
                    mp.insertarPoda(listaRuta, rutaMinima, true);//4n a la 2 +23n
                    instrucciones += 5;
                } else {
                    origen.marca = true;//1n
                    arco auxA = origen.sigA;//1n
                    memoria +=pesoArco;
                    instrucciones += 2;
                    while (auxA != null) {//n a la 2
                        mc.Insertar(auxA.destino, auxA.peso);//13n a la 2
                        RamificacionyPoda(ultimo, ruta + origen.ID + "/", dist + auxA.peso);//n a la 3
                        auxA = auxA.sigA;//1n  a la 2
                        instrucciones += 4;
                    }
                    origen.marca = false;//1 a la 2
                    instrucciones += 2;
                }

            } else {
                listaRuta = convertirRuta(ruta + origen.ID + "/");//12n a la 2+5n
                mp.insertarPoda(listaRuta, dist, false);//4n a la 2 +23n
                instrucciones += 2;
            }
        }
        instrucciones += 1 + mp.instruccionesPoda + mc.instruccionesCola;
        memoria += mp.memoriaPoda + mc.memoriaCola;
        //Medicion analitica: n a la 3+55n a la 2 + 77n 
    }

    /**
     * Fecha inicio: 21/07/2020 Ultima modificación: 21/07/2020
     *
     * Metodo que devuelve el tamaño de un grafo
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

    /**Fecha inicio: 24/07/2020 Ultima modificación: 24/07/20
     * 
     * Metodo que muestra todo los datos relacionados ocn 
     * el diseño Ramificación y poda, como por ejemplo:
     * Memoria
     * Instrucciones
     * Rutas mas corta
     * Total de rutas podadas
     * @param grafo
     * @param ultimo
     */
    public void datosRyP(vertice grafo, vertice ultimo) {
        memoria = 0;
        instrucciones = 0;
        mc.Insertar(grafo, 0);
        RamificacionyPoda(ultimo,"", 0);
        System.out.println("Ruta corta por el diseño Ramificación y Poda");
        mp.imprimirRuta(mp.rutaCorta);
        System.out.println("Memoria usada por RyP: " + memoria + " " + "bits");
        System.out.println("Instrucciones usadas por RyP: " + instrucciones);
        System.out.println("Total de rutas podadas: " + mp.totalRutasPodadas());
        System.out.println("=====================");
        System.out.println("5 ejemplos de rutas podadas");
        mp.imprimirRutaPodada();
    }
    
    /**Fecha inicio: 24/07/2020 Ultima modificación: 24/07/20
     * 
     * Metodo que muestra todo los datos relacionados ocn 
     * el diseño Backtracking, como por ejemplo:
     * Memoria
     * Instrucciones
     * Rutas mas corta
     * Total de rutas validas
     * @param grafo
     * @param ultimo
     */
    public void datosBactraking(vertice grafo,vertice ultimo) {
        memoria = 0;
        instrucciones = 0;
        rutaCortaBacktracking(grafo,ultimo, "", 0);
        System.out.println("Ruta corta por el diseño Bactraking");
        mld.imprimirRuta(mld.rutaCorta);
        System.out.println("Memoria usada por Bactraking: " + memoria + " " + "bits");
        System.out.println("Instrucciones usadas por Bactraking: " + instrucciones);
        mld.rutasValidas();
        System.out.println("Rutas random del backtraking");
        mld.rutasRandom();
    }
    public void datosGenetico(){
        memoria = 0;
        instrucciones = 0;
        System.out.println("Ruta corta por el diseño de Algoritmo Genetico");
        //genetico
        System.out.println("Memoria usada por Algoritmo Genetico: " + memoria + " " + "bits");
        System.out.println("Instrucciones usadas por Algoritmo Genetico: " + instrucciones);
        
    }
}
