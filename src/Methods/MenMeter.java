/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import java.lang.management.ManagementFactory;

/**
 *
 * @author edubi
 */
public class MenMeter {
    
    /**
     * Variable que guarda el primer peso encontrado por el metodo la primera vez para poder luego restarlo al segundo encontrado
     */
     private static long OFFSET = measure(new Runnable() {
        @Override
        public void run() {
        }
    });

    /**
     * El metodo que encuentra el tamaño de un objeto en bytes
     * 
     * @returncantidad de memoria asignada durante la ejecución proporcionada {@link Runnable}
     */
    public static long measure(Runnable x) {
       long now = getCurrentThreadAllocatedBytes();
       x.run();
       long diff = getCurrentThreadAllocatedBytes() - now;
        System.out.println("Diff " + diff);
       return diff - OFFSET;
    }

      /**
     * Hilo que encuentra el peso de un objeto
     */
    @SuppressWarnings("restriction")
    private static long getCurrentThreadAllocatedBytes() {
        return ((com.sun.management.ThreadMXBean)ManagementFactory.getThreadMXBean()).getThreadAllocatedBytes(Thread.currentThread().getId());
    }
}
