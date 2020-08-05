/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Classes.Cola;
import Classes.ListaDoble;
import Classes.Poda;
import Classes.arco;
import Classes.vertice;
import Methods.MetodosCola;
import Methods.MetodosGrafo;
import Methods.MetodosListaDoble;
import Methods.MetodosPoda;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sebas
 */
;

public class Main {

    public static vertice[] ultimos = new vertice[8];

    ;
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
        vertice[] grafos = new vertice[tamannio.length];
        for (int i = 0; i < tamannio.length; i++) {
            metGrafo.llenarGrafo(tamannio[i]);
            vertice grafo = metGrafo.grafo;
            grafos[i] = grafo;
            ultimos[i] = metGrafo.ultimo;
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
        int[] tamannioGrafo = {10, 20, 30, 60 };  // array con los tamaÃ±os para los grafos 120, 1000, 3000, 5000  
        vertice[] grafos = crearGrafos(tamannioGrafo); // array con los vérrtices de los grafos

        while (true) {
            System.out.println("Digite un numero del 1 al 6 para ver las diferentes consultas "
                    + "\n 1-Estrategia voraz "
                    + "\n 2-Estrategia genética, todos los cruces realizados y su mutación "
                    + "\n 3-Estrategia backtracking y la cantidad de todas las rutas validas e imprimir 5 rutas al azar "
                    + "\n 4-Estrategia de programación dinámica y las primeras 5 fases durante el proceso de obtención de la ruta corta "
                    + "\n 5-Estrategia de ramificación y poda y algunas la cantidad de rutas podas, e imprimir 5 rutas podas si las hubieran "
                    + "\n 6-Salir"
                    + "\n ====================================");
            Scanner scanObj = new Scanner(System.in);  // Create a Scanner object
            String opcion = scanObj.nextLine();  // Read user input

            switch (opcion) {
                case "1":
                    for (int i = 0; i < tamannioGrafo.length; i++) {
                        System.out.println(i + 1 + "-Grafo con tamaño " + tamannioGrafo[i]);
                        metGrafo.MostrarRutaCortaVoraz(grafos[i], ultimos[i], "V" + grafos[i].ID + "/", 0);
                    }
                    break;
                case "2":
                    for (int i = 0; i < tamannioGrafo.length; i++) {
                        System.out.println(i + 1 + "-Grafo con tamaño " + tamannioGrafo[i]);
                         metGrafo.grafo = grafos[i];
                        
                        if (tamannioGrafo[i] > 500) {
                            metGrafo.rutaCortaGenetica(metGrafo.grafo,"", 0,tamannioGrafo[i], tamannioGrafo[i]/2);
                            metGrafo.datosGenetico();
                        }
                        else{
                            metGrafo.rutaCortaGenetica(metGrafo.grafo,"", 0,tamannioGrafo[i], tamannioGrafo[i]/2);
                            metGrafo.datosGenetico();
                        }
                    }
                    break;
                case "3":
                    for (int i = 0; i < tamannioGrafo.length; i++) {
                        System.out.println(i + 1 + "-Grafo con tamaño " + tamannioGrafo[i]);
                        metGrafo.datosBactraking();
                    }
                    break;
                case "4":
                    for (int i = 0; i < tamannioGrafo.length; i++) {
                        System.out.println(i + 1 + "-Grafo con tamaño " + tamannioGrafo[i]);
                        metGrafo.MostrarRutaCortaDinamica(grafos[i], ultimos[i], tamannioGrafo[i]);
                    }
                    break;
                case "5":
                    for (int i = 0; i < tamannioGrafo.length; i++) {
                        System.out.println(i + 1 + "-Grafo con tamaño " + tamannioGrafo[i]);
                        metGrafo.datosRyP();
                    }
                    break;
                case "6":
                    System.exit(0);
                default:
                    break;
            }
        }

    }

    static void prueba() {
        String string = "1/4/2/3/5/";
        String[] parts = string.split("/");
        for (String part : parts) {
            System.out.println(part);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       menuAlgoritmos();
       
    }

}
