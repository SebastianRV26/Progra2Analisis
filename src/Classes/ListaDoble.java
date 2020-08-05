/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
 * 
 *Clase para crear objetos de ListaDoble, con las cuales
 * se va a tner una lista doblemente enlazada
 * 
 * @author edubi
 */
public class ListaDoble {

    public ListaDoble sigN, antN;//Punteros al siguiente y al anterior de la lista
    public int pesoRuta;//  Peso de la ruta que llego
    public boolean llegaDestino;//Indica si la ruta tiene solución o no
    public ArrayList<vertice> verticesRuta;//Array de los vertices que componen  la ruta
    public int posicion;//Posicion en la que se encuentra la ruta en la lista

    /**
     * Fecha inicio: 07/07/2020 Ultima modificación: 08/07/2020
     * 
     * Metodo contructror de la clase ListaDoble, permite crear los objetos
     * 
     * @param verticesRuta Array de los vertices que componen  la ruta
     * @param pesoRuta Peso de la ruta que llego
     * @param llegaDestino Indica si la ruta tiene solución o no
     * @param posicion  Posicion en la que se encuentra la ruta en la lista
     */
    public ListaDoble(ArrayList<vertice> verticesRuta, int pesoRuta, boolean llegaDestino, int posicion) {
        this.llegaDestino = llegaDestino;
        this.pesoRuta = pesoRuta;
        this.verticesRuta = verticesRuta;
        this.posicion = posicion;
        
    }

}
