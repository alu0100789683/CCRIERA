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
public abstract class Algorithm {
    
    public abstract void output_toString();
    public abstract void output_toFile();
    
    public abstract void test_toString();
    public abstract void test_toFile();
    
    public abstract void generate_test();
    public abstract void run();

}
