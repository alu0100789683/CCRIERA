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
public class Knapsack_dinamic extends Algorithm{
    
    private final int n;
    private int w;
    private final double ratio;
    private final double [][] vpMatrix;
    
    private final String test_output;
    private final int vlimit;
    private final int wlimit;
    
    private long time;
    
    //Matrix pd
    
    private int[][] pd_matrix;
    
    //
    
    public Knapsack_dinamic(int n, String test_output){
        this.n = n;
        this.w = 0;
        this.ratio = 0.7;
        this.vlimit = 20;
        this.wlimit = 10;
        
        this.test_output = test_output;
        this.vpMatrix = new double[n][3];
        this.generate_test();
        
        this.pd_matrix  = new int[w+1][n+1];
    }
    
    public Knapsack_dinamic(int n, String test_output, double ratio, int vlimit, int wlimit){
        this.n = n;
        this.w = 0;
        this.ratio = ratio;
        this.vlimit = vlimit;
        this.wlimit = wlimit;
        
        this.test_output = test_output;
        this.vpMatrix = new double[n][3];
        
        this.generate_test();
        
        this.pd_matrix  = new int[w+1][n+1];
    }
    
    @Override
    public void generate_test() {
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            //Valor
            vpMatrix[i][0] = Math.abs((rand.nextInt() % vlimit))+1;
            //Peso
            vpMatrix[i][1] = Math.abs((rand.nextInt() % wlimit))+1;
            //Ratio
            vpMatrix[i][2] = (double)Math.round(vpMatrix[i][0]/vpMatrix[i][1] * 100) / 100;
            this.w += vpMatrix[i][1];
        }
        this.w *= this.ratio;
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
            Logger.getLogger(Knapsack_dinamic.class.getName()).log(Level.SEVERE, null, ex);
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
            for (int[] pd : pd_matrix) {
                for (int i : pd) {
                    fw.write(i+"\t");
                }
                fw.write("\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Knapsack_dinamic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void test_toString() {
    
    }
    @Override
    public void run() {
        long time_aux = System.nanoTime();
        //Inicializar a 0
        for (int i = 0; i <= w; i++) {
            for (int j = 0; j < n; j++) {
                this.pd_matrix[i][j] = 0;
            }
        }
        
        //Algoritmo
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= n; j++) {
                //si el peso del objeto mayor que capacidad
                if(i-1 < vpMatrix[j-1][1]){
                    pd_matrix[i][j] = pd_matrix[i-1][j];
                }else{
                    //PESO posicion
                    int wo = (int) ((i-1) - vpMatrix[j-1][1]);
                    System.out.println("PesoMax: "+w+" Compruebo: "+wo);
                    System.out.println("PesoObj: "+vpMatrix[j-1][1]);
                    if(pd_matrix[i-1][j] >= pd_matrix[i-1][wo] + vpMatrix[j-1][0]){
                        pd_matrix[i][j] = pd_matrix[i-1][j];
                    }else{
                        pd_matrix[i][j] = (int) (pd_matrix[i-1][wo] + vpMatrix[j-1][0]);
                    }
                }
            }
        }
        
        time = System.nanoTime() - time_aux;
    }
}