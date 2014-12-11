/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dionizdev;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author eduardo
 */
public class TimeHandler {

    private ArrayList<Long> a_timer;
    private long last_timer;
    
    public TimeHandler(){
        
    }
    public void start(){
        a_timer = new ArrayList<>();
        last_timer = System.nanoTime();
    }
    public void stop(){
        partial();
    }
    public void partial(){
        long new_timer = System.nanoTime();
        a_timer.add(new_timer - last_timer);
        last_timer = new_timer;
    }
    public long[] getTimes(){
        return makeTimes();
    }
    public long last(){
        return a_timer.get(a_timer.size()-1);
    }
    private long[] makeTimes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}