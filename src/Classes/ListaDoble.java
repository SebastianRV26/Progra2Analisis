/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author edubi
 */
public class ListaDoble {

    public ListaDoble sigN, antN;
    public int pesoRuta;
    public boolean llegaDestino;
    public ArrayList<vertice> verticesRuta;
    public int posicion;

    public ListaDoble(ArrayList<vertice> verticesRuta, int pesoRuta, boolean llegaDestino, int posicion) {
        this.llegaDestino = llegaDestino;
        this.pesoRuta = pesoRuta;
        this.verticesRuta = verticesRuta;
        this.posicion = posicion;
        
    }

}
