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
        TimeHandler timer = new TimeHandler();
        AlgorithmHandler algorithm = new AlgorithmHandler("org.dionizdev.Knapsack_dinamic", 10,5);
        algorithm.generate_test();
        
        timer.start();
            algorithm.run();
        timer.stop();
        
        System.out.println("Tiempo de ejecución total: " + timer.last() + "ns");
        algorithm.output_toFile();
    }
    
}
