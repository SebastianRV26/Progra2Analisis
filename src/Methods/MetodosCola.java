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

    public double instruccionesCola = 0; // asignaciones y comparaciones
    public double memoriaCola = 0;
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
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     *
     * @return
     */
    public boolean colaVacia() {
        if (inicioCola == null) {//1
            instruccionesCola += 2;
            return true;//1
        } else {
            instruccionesCola += 2;
            return false;//1
        }
    }

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 13/07/2020
     *
     * @param aux
     * @param peso
     */
    public void Insertar(vertice aux, int peso) {
        Cola nuevo = new Cola(aux, peso);//1
       memoriaCola += pesoCola;
        nuevo.value = aux;//1
        nuevo.sig = null;//1
        instruccionesCola += 3;
        if (colaVacia()) {//4
            inicioCola = nuevo;//1
            finalCola = nuevo;//1
            instruccionesCola += 4;
        } else {
            finalCola.sig = nuevo;//1
            finalCola = nuevo;//1
            instruccionesCola += 4;
        }
        //  //Total medicion analitica 12
    }

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     *
     * @return
     */
    public Cola Extraer() {
        if (!colaVacia()) {//4
            Cola aux = inicioCola;//1
            memoriaCola +=pesoCola;
             instruccionesCola +=3;
            if (inicioCola == finalCola) {//1
                inicioCola = null;//1
                finalCola = null;//1
                instruccionesCola +=3;
            } else {
                inicioCola = inicioCola.sig;//1
                instruccionesCola +=2;
            }
           instruccionesCola ++;
            return aux;//1

        } else {
            instruccionesCola +=3;
            return null;//1
        }
        //Total medicion analitica 11
    }       
}
