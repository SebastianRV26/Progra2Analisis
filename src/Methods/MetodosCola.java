/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.Cola;
import Classes.vertice;
import java.util.ArrayList;
import java.util.Collections;

/**
 *Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
 * @author edubi
 */
public class MetodosCola {
    Cola inicioCola, finalCola;
    
    
    
    public static MetodosCola instance = null; // instancia de la clase MetodosCola

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     *
     * singleton para que exista únicamente una instacia de la clase
     * MetodosGrafo
     *
     * @return la instancia única del objeto MetodosGrafo
     */
    public static MetodosCola getInstance() {
        if (instance == null) {
            instance = new MetodosCola();
        }
        return instance;
    }

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     * @return 
     */
      public boolean colaVacia() {
        if (inicioCola == null) {//1
            return true;//1
        } else {
            return false;//1
        }
      }
    
      /**
       * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
       * @param aux 
       */
        public void Insertar(vertice aux) {
        Cola nuevo = new Cola(aux);//1
        nuevo.sig = null;//1
        if (colaVacia()) {//4
            inicioCola = nuevo;//1
            finalCola = nuevo;//1
        } else {
            finalCola.sig = nuevo;//1
            finalCola = nuevo;//1
        }
        //  //Total medicion analitica 12
    }
     /**
      * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
      * @return 
      */   
         public vertice Extraer() {
        if (!colaVacia()) {//4
            vertice aux = inicioCola.value;//1
            if (inicioCola == finalCola) {//1
                inicioCola = null;//1
                finalCola = null;//1
            } else {
                inicioCola = inicioCola.sig;//1
            }
            return aux;//1
        } else {
            return null;//1
        }
          //Total medicion analitica 11
    }
    
        public void imprimirCola() {
        Cola recorrido = inicioCola;//1
        ArrayList<vertice> arbolesList = new ArrayList<>();//1
        while (recorrido != null) {//n
            arbolesList.add(recorrido.value);//1*n
            recorrido = recorrido.sig;//1*n
        }
        Collections.reverse(arbolesList);//1
        for (int i = 0; i < arbolesList.size(); i++) {//2n
            System.out.println("Arbol con id " + arbolesList.get(i).ID);
        }
    }
      //Total medicion analitica 5n + 3
}