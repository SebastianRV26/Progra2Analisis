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
public class Poda {
    public Poda sig;
    public  Poda ant;
    public ArrayList<vertice> ruta;
    public int pesoRuta;
    public boolean esSolucion; 
    public int posicion;

    public Poda( ArrayList<vertice> ruta, int pesoRuta, boolean esSolucion, int posicion) {
        this.ruta = ruta;
        this.pesoRuta = pesoRuta;
        this.esSolucion = esSolucion;
        this.posicion = posicion;
    }   
}