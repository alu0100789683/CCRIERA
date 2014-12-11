/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dionizdev;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class AlgorithmHandler {
    
    private final Algorithm bank[];
    private final String class_str;
    private final int num_iter;
    private final int num_n;
    
    public AlgorithmHandler(String class_str, int num_iter, int num_n){
        this.bank = new Algorithm[num_iter];
        this.class_str = class_str;
        this.num_iter = num_iter;
        this.num_n = num_n;
    }
    public void generate_test(){
        try {
            for (int i = 0; i < num_iter; i++) {
                    this.bank[i] =
                        (Algorithm)Class.forName(this.class_str)
                                .getConstructor(
                                   int.class,
                                   String.class
                                ).newInstance(
                                   this.num_n,
                                   "src/output/"+this.class_str+"/out_"+i
                                );
            } 
        } catch (Exception ex) {
            Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void run() {
        for (Algorithm algorithm : bank) {
            algorithm.run();
        }
    }

    void output_toFile() {
        for (Algorithm algorithm : bank) {
            algorithm.output_toFile();
        }
    }
}
