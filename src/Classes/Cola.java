/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *Fecha inicio: 08/07/2020 Ultima modificación: 08/07/2020
 * @author edubi
 */
public class Cola {
    public Cola sig;  
    public Cola ant;
    public vertice value;
    public int pesoArcoLlegada;

    public Cola(vertice value, int pesoArcoLlegada) {
        this.value = value;
        this.pesoArcoLlegada = pesoArcoLlegada;
    }

    
    
}
