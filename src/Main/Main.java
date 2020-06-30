/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Classes.vertice;
import Methods.MetodosGrafo;

/**
 *
 * @author Sebas
 */
public class Main {

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020 * método que
     * crea los grafos de distintos tamaños y los almacena en un arreglo
     *
     * @param tamannio tamaños requeridos para crear los grafos
     * @return un arreglo con el primer vértice de los grafos de distintos
     * tamaños
     */
    public static vertice[] crearGrafos(int[] tamannio) {
        MetodosGrafo metGrafo = MetodosGrafo.getInstance();
        vertice[] grafos = new vertice[6];
        for (int i = 0; i < tamannio.length; i++) {
            metGrafo.llenarGrafo(tamannio[i]);
            vertice grafo = metGrafo.grafo;
            grafos[i] = grafo;
            metGrafo.grafo = null;
        }
        return grafos;
    }

    /**
     * Fecha inicio: 30/06/2020 Ultima modificación: 30/06/2020 * método que
     * muestra la posibilidad al usuario de escoger el algoritmo que desea ver y
     * ejecuta el algoritmo seleccionado
     */
    public static void menuAlgoritmos() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        menuAlgoritmos();
    }

}
