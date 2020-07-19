/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 * Fecha inicio: 30/06/2020 Ultima modificación: 09/07/2020
 */
public class vertice { //vertice de un grafo

    public int ID; // identificador del vertice
    public vertice sigV; // Puntero para referenciar a los siguientes vertices
    public arco sigA; // Puntero para hacer referencia a los arcos
    public boolean marca; // Marca, utilizado para los diferentes imprimir y recorridos recursivos
    public int distanciaMinima; // distancia mínima para llegar a dicho vértice (Dijkstra)
    public int pesoMin; // Peso del arco para llegar a él
    public vertice antV; // vértice anterior (Dijkstra)

    /**
     * constructor del vértice
     *
     * @param ID identificador del vertice
     * @param m marca del vértice
     */
    public vertice(int ID, boolean m) {
        this.ID = ID;
        this.marca = m;
        this.distanciaMinima = 1000;
        this.antV = null;
        this.pesoMin = 0;
    }

}
