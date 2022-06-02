/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import javax.swing.JFrame;

/**
 *
 * @author Maxth
 */
public class MenuController extends JFrame {
    //Fields
    
    //Constructor
    public MenuController() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(new Menu());
        this.pack();
        this.setSize(615,639);
        
        this.setVisible(true);
    }
    
    
    //Methods
    
    
}
