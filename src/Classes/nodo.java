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
public class nodo {

    public nodo sigN, antN;
    public int pesoRuta;
    public boolean llegaDestino;
    public ArrayList<vertice> verticesRuta;

    public nodo(ArrayList<vertice> verticesRuta, int pesoRuta, boolean llegaDestino) {
        this.llegaDestino = llegaDestino;
        this.pesoRuta = pesoRuta;
        this.verticesRuta = verticesRuta;
    }

}
