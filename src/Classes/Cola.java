/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
 * 
 * Clase que permite crear objetos tipo cola, estos
 * van a ser usados para una lista tipo cola
 * @author edubi
 */
public class Cola {
    public Cola sig;  // Puntero que indica el siguiete de la lista
    public Cola ant;//Punero que indica el anteriorde la lista
    public vertice value;// Es el vertice que se va analizar luego en el diseño Ramificación y poda
    public int pesoArcoLlegada;//Es el peso del arco que estaba apuntando al vertice del parametro anterio

    /**
     * Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
     * 
     * Metodo constructor de la clase cola, permite crear los objetos
     * 
     * @param value Es el vertice que se va analizar luego en el diseño Ramificación y poda
     * @param pesoArcoLlegada  Es el peso del arco que estaba apuntando al vertice del parametro anterior
     */
    public Cola(vertice value, int pesoArcoLlegada) {
        this.value = value;
        this.pesoArcoLlegada = pesoArcoLlegada;
    }

    
    
}
