/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Maxth
 */
public class Empty extends Piece{

    
    
    public Empty(Position piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
        
        //THIS WILL ALWAYS BE
        this.pieceChar = ' ';
        
        this.pieceImageLocation = "EMPTY";
    }

    @Override
    public ArrayList<Position> getLegalPositions(Board boardStatus) {
        //DOESN'T DO ANYTHING BECAUSE THE SPACE IS EMPTY AND NOT A PIECE
        return null;
    }    
}
