/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.Poda;
import Classes.arco;
import Classes.vertice;
import java.util.ArrayList;

/**
 *Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
 * i
 */
public class MetodosPoda {

    
    public Poda inicio, rutaCorta;
    
    
   public static MetodosPoda instance = null;
   /**
    * Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
    * @return 
    */
    public static MetodosPoda getInstance() {
        if (instance == null) {
            instance = new MetodosPoda();
        }
        return instance;
    }
    public double instruccionesPoda = 0;
    public double memoriaPoda = 0;
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
     * Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
     * @param ruta
     * @param pesoRuta
     * @param esSolucion
     * @return
     */
    public boolean insertarPoda(ArrayList<vertice> ruta, int pesoRuta, boolean esSolucion) {
        if (buscarRuta(ruta) == null) {
            Poda nuevo = new Poda(ruta, pesoRuta, esSolucion, 0);
            memoriaPoda += pesoPoda;
            instruccionesPoda += 2;
            if (inicio == null) {
                inicio = rutaCorta = nuevo;
                instruccionesPoda += 4;
                return true;
            }
            Poda aux = inicio;
            int pos = 1;
            memoriaPoda += pesoPoda + 32;
            instruccionesPoda += 2;
            while (aux != null) {
                if (aux.sig == null) {
                    if ((nuevo.pesoRuta < rutaCorta.pesoRuta && nuevo.esSolucion) || rutaCorta.pesoRuta == 0) {
                        rutaCorta = nuevo;
                        instruccionesPoda += 3;
                    }
                    nuevo.posicion = pos;
                    aux.sig = nuevo;
                    nuevo.ant = aux;
                    instruccionesPoda += 6;
                    return true;
                }
                pos++;
                aux = aux.sig;
                instruccionesPoda += 3;
            }
            instruccionesPoda++;
        }
        instruccionesPoda += 3;
        return false;
    }

    /**
     * Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
     */
    public void imprimirRutaPodada(){
        int contador = 1;
        Poda aux = inicio;
        while (aux != null) {     
            if(!aux.esSolucion && contador <= 5){
                imprimirRuta(aux);
                contador ++;
            }
            aux = aux.sig;
        }
    }

    /**
     * Fecha inicio: 12/07/2020 Ultima modificación: 12/07/2020
     * @return 
     */
    public int totalRutasPodadas() {
        int contador = 0;
        Poda aux = inicio;
        while (aux != null) {
            if (!aux.esSolucion) {
                contador++;
            }
            aux = aux.sig;
        }
        return  contador;
    }

    /**
     * Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
     * @param temp 
     */
    public  void imprimirRuta(Poda temp){
         ArrayList<vertice> rutaVertices = temp.ruta;
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
     * Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
     * @param ruta
     * @return 
     */
    
    public Poda buscarRuta( ArrayList<vertice> ruta){
        Poda aux = inicio;
        while (aux != null) {     
            if(aux.ruta.equals(ruta)){
                return  aux;
            }
            aux = aux.sig;
        }
        return  null;
    }
}
