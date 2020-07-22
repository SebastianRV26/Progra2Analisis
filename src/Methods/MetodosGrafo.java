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
    public void ImprimirRuta(List<vertice> Manipulados){
        System.out.println("Esta es la sublista: ");
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
        if(padre1==null && padre2==null){
            int index = random.nextInt(poblacion.size())-1;
            padre1 = poblacion.get(index);
            padre2 = poblacion.get(index2);
            poblacion.remove(poblacion.get(index2));
            poblacion.remove(poblacion.get(index));
        }//end 1er if
//        else if(padre1!=null){
//            
//            padre2 = poblacion.get(index2);
//            poblacion.remove(poblacion.get(index2));
//        }
        
    }
    
    public void ag_cruzar(ArrayList<vertice> padre,ArrayList<vertice> madre, int tamGrafo){
        ArrayList<vertice> hijo1 = new ArrayList();
        ArrayList<vertice> hijo2 = new ArrayList();
        
        List <vertice> sub1 = new ArrayList<vertice>();
        List <vertice> sub2 = new ArrayList<vertice>();
        List <vertice> sub3 = new ArrayList<vertice>();
        List <vertice> sub4 = new ArrayList<vertice>();
     
        //punto de cruce
        int randomNum;
        boolean esta;
        boolean esta2;
        int cont=0; 
        while(true){
            
            randomNum = ThreadLocalRandom.current().nextInt(2, padre.size());
            System.out.println("Punto de cruse: "+randomNum);
            esta = padre.contains(buscar(randomNum));
            esta2 = madre.contains(buscar(randomNum));
            if(esta&&esta2){
            System.out.println("estan ambos");
            //recorre los padres para buscar el punto de cruce que se hace random
                for (int i = 0; i < padre.size()-1; i++) {
                    if(padre.get(i).ID==randomNum){
                        sub1= padre.subList(0, i);//creo la sublista 1
                        sub2 = padre.subList(i, padre.size());//creo la sublista 2
                        ImprimirRuta(sub1);
                        ImprimirRuta(sub2);   
                    }
                }
                for(int j = 0; j < madre.size()-1; j++){
                    if(madre.get(j).ID==randomNum){
                        sub3 = madre.subList(0, j);//creo la sublista 3
                        sub4 = madre.subList(j, madre.size());//creo la sublista 4
                        ImprimirRuta(sub3);
                        ImprimirRuta(sub4); 
                    }   
                }
                //esto arma al hijo1 con sub 1-4
                for (int a = 0; a < sub1.size(); a++){
                    hijo1.add(sub1.get(a));
                }
                for (int b = 0; b < sub4.size(); b++){
                    hijo1.add(sub4.get(b));
                }
                //esto arma al hijo2 con sub 3-2
                for (int a = 0; a < sub3.size(); a++){
                    hijo2.add(sub3.get(a));  
                }
                for (int b = 0; b < sub2.size(); b++){
                    hijo2.add(sub2.get(b));
                }
            break;
            }
            cont++;
            if(cont>6){
                madre = padre;
            }
            System.err.println("volvio a sacar punto de cruce");   
        }
        
        
//        if((hijo2.size()<tamGrafo/2)||(hijo1.size()<tamGrafo/2)){
//                System.out.println("vamos a cambiar una ruta agregando un vertice");
//                if(hijo1.size()<tamGrafo/2){
//                     System.out.println("tam"+(tamGrafo/2)+"tam del hijo: "+hijo1.size());
//                    ImprimirRuta(hijo1);
//                   while (hijo1.size()>=tamGrafo/2) {
//                       ag_mutar(hijo1);
//                   }
//                   System.out.println("cambio al hijo"); 
//                }
//                else if(hijo2.size()<tamGrafo/2){
//                     System.out.println("tam"+(tamGrafo/2)+"tam de la hija: "+hijo2.size());
//                   ImprimirRuta(hijo2);
//                   while (hijo2.size()>=tamGrafo/2) {
//                       ag_mutar(hijo2);
//                   }
//                   System.out.println("cambio a la hija"); 
//                }
//        }
   
        //Se evaluan las rutas
        if(ag_evaluarFitness2(padre)<ag_evaluarFitness2(madre)){
            if (ag_evaluarFitness2(padre)<ag_evaluarFitness2(hijo1)) {
                if (ag_evaluarFitness2(padre)<ag_evaluarFitness2(hijo2)) {
                    System.out.println("Es mejor el padre");
                    //ImprimirRuta(padre); 
                    //ag_evaluarFitness(padre);
                    if(ag_mutar(padre)){
                        System.err.println("muto el padre");
                    }
                    padre1 = null;
                    padre2 = null; 
                }  
            }
            else{
                if(ag_evaluarFitness2(hijo1)<ag_evaluarFitness2(hijo2)){
                   System.out.println("Es mejor el hijo1");
                   //ImprimirRuta(hijo1);
                   //ag_evaluarFitness(hijo1);
                   Manipulados.add(hijo1);
                    padre1 = null;
                    padre2 = null;  
                }
                else{
                    System.out.println("Es mejor el hijo2");
                    //ImprimirRuta(hijo2);
                    //ag_evaluarFitness(hijo2);
                    Manipulados.add(hijo2);
                    padre1 = null;
                    padre2 = null;
                }
            }    
        }
        else{
            if (ag_evaluarFitness2(madre)<ag_evaluarFitness2(hijo1)) {
                if (ag_evaluarFitness2(madre)<ag_evaluarFitness2(hijo2)) {
                    System.out.println("Es mejor la madre");
                    //ImprimirRuta(madre); 
                    //ag_evaluarFitness(madre);
                    if(ag_mutar(madre)){
                        System.out.println("muto el padre siendo madre");
                    }
                    padre1 = null;
                    padre2 = null; 
                }  
            }
            else{
                if(ag_evaluarFitness2(hijo1)<ag_evaluarFitness2(hijo2)){
                   System.out.println("Es mejor el hijo1");
                   //ImprimirRuta(hijo1); 
                    //ag_evaluarFitness(hijo1);
                   Manipulados.add(hijo1);
                    padre1 = null;
                    padre2 = null;  
                }
                else{
                    System.out.println("Es mejor el hijo2");
                    //ImprimirRuta(hijo2); 
                    //ag_evaluarFitness(hijo2);
                    Manipulados.add(hijo2);
                    padre1 = null;
                    padre2 = null;
                }
            }
        } 
        
    }
   
    
    
    
    /**
     * 
     */
    public int ag_evaluarFitness(ArrayList<vertice> ruta){
        int peso = 0;
        String rutaStr =  "";
        rutaStr = ""+ ruta.get(0).ID;
        for (int i = 0; i < ruta.size()-1; i++){            
             arco auxA = buscar(ruta.get(i), ruta.get(i+1));
             rutaStr+=" Peso: -> "+auxA.peso+" Vertice destino: "+auxA.destino.ID;
             peso= peso + auxA.peso;
        }
        System.out.println("Ruta: "+rutaStr);
        System.out.println("peso ruta: "+peso);
        return peso;
    }
    /**
     * 
     * @param ruta
     * @return 
     */
    public int ag_evaluarFitness2(ArrayList<vertice> ruta){
        int peso = 0;
        for (int i = 0; i < ruta.size()-1; i++){
             arco auxA = buscar(ruta.get(i), ruta.get(i+1));
             peso= peso + auxA.peso;
        }
        return peso;
    }
    
    public boolean ag_mutar(ArrayList<vertice> hijo){
        //si no hay mutacion el hijo debe salir igual
        ArrayList<vertice> hijoMutado = hijo;
        ArrayList<vertice> hijoMutado2 = new ArrayList();
        //recorrer el hijo nuevo
        int numero = 0;
        arco actual= null;
        vertice vO = null;
        vertice vD = null;
        
        for (int i = 0; i < hijoMutado.size()-1; i++) {
            
            //preguntar por el arco de mayor tamaño 
            arco auxA = buscar(hijoMutado.get(i),hijoMutado.get(i+1));
            if(auxA.peso>numero){
                numero = auxA.peso;
                actual = auxA;
                vO = hijoMutado.get(i);
                vD = hijoMutado.get(i+1);
                System.out.println("Arco mas grande: "+numero+"vertice de origen: "+vO.ID);   
            } 
        }
        //cambiarlo por otro vertice destino
        vertice vNuevo = null;
        arco auxA2= vO.sigA ;
        while(auxA2!=null){
            if(!hijoMutado.contains(auxA2.destino)){
                vNuevo = auxA2.destino;
                System.out.println("el nuevo vertice: "+vNuevo.ID);
            }
            auxA2 = auxA2.sigA;
            if(auxA2==null){
                System.out.println("no encontro vertice disponible");
            }
        }
        
        for (int i = 0; i < hijo.size(); i++) {
            if(hijo.get(i)==vO){
                if(vNuevo!=null){
                   hijoMutado2.add(hijo.get(i));
                hijoMutado2.add(vNuevo); 
                }
            }
            else{
                hijoMutado2.add(hijo.get(i));
            }
            
        }
        ImprimirRuta(hijoMutado2); 
        // ver si la ruta aun existe y si mejora
        if( ag_evaluarFitness2(hijo)<ag_evaluarFitness2(hijoMutado2)){
            // sale hijo nuevo sin mutar
            System.out.println("no sirvio");
            Manipulados.add(hijo);
            padre1 = null;
            return false;
        }
        else{
            //si mejora sale hijo mutado
            System.out.println("si sirvio");
            ag_evaluarFitness(hijoMutado2);
            Manipulados.add(hijoMutado2);
            padre1 = null;
            return true;
        }
        //return hijo
    }
    

    public void rutaCortaGenetica(int tamGrafo ) {
    // 2.Imprimir la ruta corta encontrada de inicio a fin, indicado los vértices 
    //por donde pasa y la distancia respectiva entre cada uno, así como la suma total de la ruta.
    //3.Imprimir todos los cruces realizados para la estrategia genética y su 
    //mutación. Así como la puntación asignada a cada cromosoma.

        ArrayList<ArrayList<vertice>> poblacion = new ArrayList<>();
        poblacion = generarPoblacion(8);
        //ag_escogerPadres(poblacion);
        //ag_cruzar(padre1, padre2);
        System.out.println("tama de la poblacion: "+poblacion.size());
        int cont=0;
        while(cont<tamGrafo/2){
            ag_escogerPadres(poblacion);
            ImprimirRuta(padre1);
            ImprimirRuta(padre2);
            ag_cruzar(padre1, padre2,tamGrafo);
            System.out.println("Escogemos padres de nuevo");
        cont++;
        }
        
        
        ImprimirTodasRutas(Manipulados);
      

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
