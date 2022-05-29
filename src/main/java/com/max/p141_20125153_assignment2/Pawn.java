/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Maxth
 */
public class Pawn extends Piece {
    //Field for holding the max amount of legaul move that a piece could have
    private final int MAX_LEGAL_NUM = 4;
    
    
    public Pawn(Position piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
                
        //Initillizing the arraylist for the legal posistions
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        
        //TEMP VALUE FOR CHAR
        this.pieceChar = 'p';
    
        
        //Setting up image
        if(pieceColour == Colour.BLACK) {
            this.pieceImageLocation = "./Images/Pawn_Black.png";
        } else {
            this.pieceImageLocation = "./Images/Pawn_White.png";
        }
        //Loading image into field
        try {
            this.pieceImage = ImageIO.read(new File(this.pieceImageLocation));
        } catch (Exception e) {
            System.out.println("Something went wrong did you delete the image file!!!");
            System.exit(1);
        }
    }
    
    
    //This is a method for getting all the legal positions in for a pawn
    @Override
    public ArrayList<Position> getLegalPositions(Board boardStatus) {
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        
        int jump = 1;
        //Moving forward ONCE AND TWICE
        Position tempForward;
        
        if(this.piecePosition.getyCord() == 7 && this.pieceColour == Colour.BLACK || this.piecePosition.getyCord() == 0 && this.pieceColour == Colour.WHITE) {
            return null;
        }
        
        if(this.piecePosition.getyCord() == 6 && this.pieceColour == Colour.WHITE || this.piecePosition.getyCord() == 1 && this.pieceColour == Colour.BLACK) {
            jump = 1;
            
            this.legalPositions.add(new Position(this.piecePosition.getxCord(), (this.piecePosition.getyCord() + (jump * this.pieceFlipper))));
            this.legalPositions.add(new Position(this.piecePosition.getxCord(), (this.piecePosition.getyCord() + ((jump + 1) * this.pieceFlipper))));
        } else {
            Position posLooker = new Position(this.piecePosition.getxCord(), this.piecePosition.getyCord() + (1 * this.pieceFlipper));
            
            if(boardStatus.getPieceFromBoard(posLooker).isEmpty()) {
                this.legalPositions.add(new Position(this.piecePosition.getxCord(), (this.piecePosition.getyCord() + (jump * this.pieceFlipper))));
            }
        }
        
        //MOVING TO THE RIGHT
        if(this.piecePosition.getxCord() != 7) {
            Position tempRight = new Position((this.piecePosition.getxCord() + 1), (this.piecePosition.getyCord() + this.pieceFlipper));
            
            if (boardStatus.getPieceFromBoard(tempRight).getPieceColour() != this.getPieceColour() && boardStatus.getPieceFromBoard(tempRight).getPieceColour() != Colour.NONE) {
                this.legalPositions.add(tempRight);
            }
        }
            
        //MOVING TO THE LEFT
        if(this.piecePosition.getxCord() != 0) {
            Position tempLeft = new Position((this.piecePosition.getxCord() - 1), (this.piecePosition.getyCord() + this.pieceFlipper));
            
            if (boardStatus.getPieceFromBoard(tempLeft).getPieceColour() != this.pieceColour && boardStatus.getPieceFromBoard(tempLeft).getPieceColour() != Colour.NONE) {
                this.legalPositions.add(tempLeft);      
            }
        }
    
        return this.legalPositions;
    }  
    
    
}
