/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
 * 
 * Clase para crear objetos de Poda, con las cuales
 * se va a tner una lista doblemente enlazada
 * 
 * @author edubi
 */
public class Poda {
    public Poda sig;//Puntero que indica el siguiente de la lista
    public  Poda ant;//Puntero que india el anterior
    public ArrayList<vertice> ruta;//Array de los vertices que componen  la ruta
    public int pesoRuta;// Peso de la ruta que llego
    public boolean esSolucion; //Indica si la ruta es poda o no
    public int posicion;//Posicion en la que se encuentra la ruta en la lista

    /**
     * Fecha inicio: 11/07/2020 Ultima modificación: 12/07/2020
     * 
     * Metodo contructror de la clase Poda, permite crear los objetos
     * 
     * @param ruta Array de los vertices que componen  la ruta
     * @param pesoRuta Peso de la ruta que llego
     * @param esSolucion Indica si la ruta es poda o no
     * @param posicion   Posicion en la que se encuentra la ruta en la lista
     */
    public Poda( ArrayList<vertice> ruta, int pesoRuta, boolean esSolucion, int posicion) {
        this.ruta = ruta;
        this.pesoRuta = pesoRuta;
        this.esSolucion = esSolucion;
        this.posicion = posicion;
    }   
}