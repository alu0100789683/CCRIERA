/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dionizdev;

/**
 *
 * @author eduardo
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlgorithmHandler a = new AlgorithmHandler("org.dionizdev.Knapsack", 10,5);
        a.generate_test();
        long time = System.nanoTime();
        a.run();
        long timew = System.nanoTime() - time;
        System.out.println("Tiempo de ejecución total: " + timew + "ns");
        a.output_toFile();
    }
    
}
