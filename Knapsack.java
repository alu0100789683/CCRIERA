/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dionizdev;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class Knapsack extends Algorithm{
    
    private final int n;
    private int w;
    private final double ratio;
    private final double [][] vpMatrix;
    
    private final String test_output;
    private final int vlimit;
    private final int wlimit;
    
    private int result_value;
    private int result_weight;
    
    private long time;
    
    public Knapsack(int n, String test_output){
        this.n = n;
        this.w = 0;
        this.ratio = 0.7;
        this.vlimit = 20;
        this.wlimit = 10;
        this.result_value = 0;
        this.result_weight = 0;
        this.test_output = test_output;
        this.vpMatrix = new double[n][3];
        this.generate_test();
    }
    
    public Knapsack(int n, String test_output, double ratio, int vlimit, int wlimit){
        this.n = n;
        this.w = 0;
        this.ratio = ratio;
        this.vlimit = vlimit;
        this.wlimit = wlimit;
        this.result_value = 0;
        this.result_weight = 0;
        this.test_output = test_output;
        this.vpMatrix = new double[n][3];
        this.generate_test();
    }
    
    @Override
    public void generate_test() {
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            vpMatrix[i][0] = Math.abs((rand.nextInt() % vlimit))+1;
            vpMatrix[i][1] = Math.abs((rand.nextInt() % wlimit))+1;
            vpMatrix[i][2] = (double)Math.round(vpMatrix[i][0]/vpMatrix[i][1] * 100) / 100;
            this.w += vpMatrix[i][1];
        }
        this.w *= this.ratio;
        sort(vpMatrix, n);
        test_toFile();
    }
    @Override
    public void test_toFile() {
        FileWriter fw = null;
        double aux;
        try {
            
            fw = new FileWriter(new File(this.test_output));
            fw.write(n + "\n");
            fw.write(w + "\n");
            for (double[] vtemp : vpMatrix) {
                fw.write(vtemp[0] + "\t" + vtemp[1] + "\t" + vtemp[2] + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Knapsack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void output_toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void output_toFile() {
        FileWriter fw = null;
        double aux;
        try {
            fw = new FileWriter(new File(this.test_output+"_result"));
            fw.write("Valor: " + result_value + " - Peso: " + result_weight + " * Time: " + time + "ns");
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Knapsack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void test_toString() {
    
    }
    @Override
    public void run() {
        long time_aux = System.nanoTime();
        for (int i = 0; i < n; i++) {
            if(result_weight + vpMatrix[i][1] <= wlimit ){
                result_value += vpMatrix[i][0];
                result_weight += vpMatrix[i][1];
            }
        }
        time = System.nanoTime() - time_aux;
    }

    public void sort(double [][] unarray, int lalong){
        int i, j;
        double [] aux;
        for (i = 0; i < lalong -1 ; i++) {
            for (j = i + 1; j < lalong ; j++) {
                if (unarray[i][2] < unarray[j][2]) {
                    aux = unarray[i];
                    unarray[i] = unarray[j];
                    unarray[j] = aux;
                }
            }
         }
    }
}