/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Maxth
 */
abstract class Piece extends JPanel{
    //Field representing the position
    protected Position piecePosition;
    
    //Field storing the Piece Colour
    protected Colour pieceColour;
    
    //Field storing the char used to print the piece
    protected char pieceChar;
    
    //Field for holding all legual positions that the piece can  move to
    protected ArrayList<Position> legalPositions;  
    
    //Field for holding the flipper for moving the position
    protected int pieceFlipper;
    
    //Field for piece image
    protected String pieceImageLocation;
    protected BufferedImage pieceImage;

    //Constructor for Piece
    public Piece(Position piecePosition, Colour pieceColour) {
        super();
        
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
        
        //Calling method for setting the flipper for the class
        this.setFlipper();
    }
    
    //Method for getting the pieceColour
    public Colour getPieceColour() {
        return this.pieceColour;
    }
    
    //Method for getting the Char that represents the Piece
    public char getPieceChar() {
        return pieceChar;
    }
    
    //Method for getting the piece's position
    public Position getPiecePosition() {
        return this.piecePosition;
    }

    //Method for setting the Char that represents the Piece
    public void setPieceChar(char pieceChar) {
        this.pieceChar = pieceChar;
    }
    
    //Method for checking if space is empty
    public boolean isEmpty () {
        return (this.getClass() == Empty.class);
    }
    
    //Method for setting the position of the piece
    public void setPosition(Position newPosition) {
        this.piecePosition = newPosition;
    }
    
    //Method for setting the flipper for the piece
    private void setFlipper () {
        //Setting up the pieceFlipper
        if(this.pieceColour == Colour.BLACK) {
            this.pieceFlipper = 1;
        } else {
            this.pieceFlipper = -1;
        }
    }
    
    //Method for putting all legal positions into a string
    public String allLegalPositionToString() {
        String strings = "";
        
        for(int i = 0; i < this.legalPositions.size(); i++) {
            strings += this.legalPositions.get(i).toString(); 
            if(i < (this.legalPositions.size() - 1)) {
                strings += ", ";
            }
        }
        
        return strings;
    }
    
    //used for clearing the positions in the legal position
    public void clearLegalPositions() {
        this.legalPositions.clear();
    }
    

    //Method for drawing image from the Piece
    public void drawPiece(Graphics g) {
        g.drawImage(this.pieceImage, this.piecePosition.getxCord_GUI(), this.piecePosition.getyCord_GUI(), this);
    }
    
    //ToString method returns the char for the piece and is UPPER IF BLACK
    @Override
    public String toString() {
        if(this.pieceFlipper == -1) {
            return String.valueOf(this.getPieceChar()).toUpperCase();
        } else {
            return String.valueOf(this.getPieceChar());
        }
    }
    
    //METHODS THAT MUST BE IMPLEMENTED
    public abstract ArrayList<Position> getLegalPositions(Board boardStatus);
 }
