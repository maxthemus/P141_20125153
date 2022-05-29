/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

/**
 *
 * @author Maxth
 */
public class Position {
    //Static field for translating xCord to xChar
    private static final char[] X_CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    
    //Fields for holding position variables
    private int xCord, yCord;
    private char xChar;
    private int yInt;

    //Fields for holding position for GUI
    private int xCord_GUI, yCord_GUI;
    
    public Position(int xCord, int yCord) {
        //Initializing Fields
        this.xCord = xCord;
        this.yCord = yCord;
        this.xChar = X_CHARS[xCord];
        this.yInt = 8 - yCord;
        
        this.xCord_GUI = ((this.xCord) * 75);
        this.yCord_GUI = ((this.yCord) * 75);
        
    }

    //Getters 
    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public int getxCord_GUI() {
        return xCord_GUI;
    }

    public int getyCord_GUI() {
        return yCord_GUI;
    }
    
    
    
    public boolean comparePositions(Position otherPosition) {
        if(this.getxCord() == otherPosition.getxCord() && this.getyCord() == otherPosition.getyCord()) {
            return true;
        }
        return false;

    }
    
    //ToString method for printing a position
    public String toString() {
        return ("(" + String.valueOf(this.xChar) + "," + this.yInt + ")");
    }
}
