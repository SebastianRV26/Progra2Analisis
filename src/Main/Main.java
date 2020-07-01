/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Classes.vertice;
import Methods.MetodosGrafo;
import java.util.Scanner;

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
        MetodosGrafo metGrafo = MetodosGrafo.getInstance();

        int[] tamannioGrafo = {10, 20, 30, 60, 120, 1000, 3000, 5000}; // array con los tamaÃ±os para los grafos
        vertice[] grafos = crearGrafos(tamannioGrafo); // array con los vérrtices de los grafos

        while (true) {
            System.out.println("Digite un numero del 1 al 6 para ver las diferentes consultas "
                    + "\n 1-Imprima todas las variables de medición para cada una de las estrategias de diseño "
                    + "\n 2-Imprimir la ruta corta encontrada de inicio a fin "
                    + "\n 3-Imprimir todos los cruces realizados para la estrategia genética y su mutación "
                    + "\n 4-Para la estrategia backtracking imprimir la cantidad de todas las rutas validas e imprimir 5 rutas al azar "
                    + "\n 5-Para la estrategia de programación dinámica imprimir algunas 5 fases durante el proceso de obtención de la ruta corta "
                    + "\n 6-Para la estrategia de ramificación y poda imprimir algunas la cantidad de rutas podas, e imprimir 5 rutas podas se las hubieran "
                    + "\n 7-Salir"
                    + "\n ====================================");
            Scanner scanObj = new Scanner(System.in);  // Create a Scanner object
            String opcion = scanObj.nextLine();  // Read user input

            switch (opcion) {
                case "1":
                    
                    break;
                case "2":
                    
                    break;
                case "3":
                    
                    break;
                case "4":
                    
                    break;
                case "5":
                    
                    break;
                case "6":
                    
                    break;
                case "7":
                    System.exit(0);
                default:
                    break;
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodosGrafo metGrafo = MetodosGrafo.getInstance();
        
        metGrafo.llenarGrafo(5);
        metGrafo.profundidad(metGrafo.grafo);
        
        // menuAlgoritmos();
    }

}
