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

    
    /**
     * Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
     * @param ruta
     * @param pesoRuta
     * @param esSolucion
     * @return 
     */
    public boolean insertarPoda( ArrayList<vertice> ruta, int pesoRuta, boolean esSolucion) {
        if (buscarRuta(ruta) == null) {
            Poda nuevo = new Poda(ruta, pesoRuta, esSolucion, 0);
            if (inicio == null) {
                inicio = rutaCorta = nuevo;
                return true;
            }
            Poda aux = inicio;
            int pos = 1;
            while (aux != null) {
                if (aux.sig == null) {
                    if ((nuevo.pesoRuta < rutaCorta.pesoRuta && nuevo.esSolucion) || rutaCorta.pesoRuta == 0) {
                        rutaCorta = nuevo;
                    }
                    nuevo.posicion = pos;
                    aux.sig = nuevo;
                    nuevo.ant = aux;

                    return true;
                }
                pos++;
                aux = aux.sig;
            }

        }
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
