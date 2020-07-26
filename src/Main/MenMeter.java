/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.lang.management.ManagementFactory;

/**
 *
 * @author edubi
 */
public class MenMeter {
     private static long OFFSET = measure(new Runnable() {
        @Override
        public void run() {
        }
    });

    /**
     * @return amount of memory allocated while executing provided {@link Runnable}
     */
    public static long measure(Runnable x) {
       long now = getCurrentThreadAllocatedBytes();
       x.run();
       long diff = getCurrentThreadAllocatedBytes() - now;
        System.out.println("Diff " + diff);
       return diff - OFFSET;
    }

    @SuppressWarnings("restriction")
    private static long getCurrentThreadAllocatedBytes() {
        return ((com.sun.management.ThreadMXBean)ManagementFactory.getThreadMXBean()).getThreadAllocatedBytes(Thread.currentThread().getId());
    }
}
