/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Maxth
 */
public class King extends Piece {
    //Field for holding the max amount of legaul move that a piece could have
    private final int MAX_LEGAL_NUM = 4;

    public King(Position piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
        
        //Initillizing the arraylist for the legal posistions
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        
        //TEMP VALUE FOR CHAR
        this.pieceChar = 'k';
        
        //Setting up image
        if(pieceColour == Colour.BLACK) {
            this.pieceImageLocation = "./Images/King_Black.png";
        } else {
            this.pieceImageLocation = "./Images/King_White.png";
        }
        //Loading image into field
        try {
            this.pieceImage = ImageIO.read(new File(this.pieceImageLocation));
        } catch (Exception e) {
            System.out.println("Something went wrong did you delete the image file!!! --> " + this.getClass().toString());
            System.exit(1);
        }
        
    }

    @Override
    public ArrayList<Position> getLegalPositions(Board boardStatus) {
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        int jump = 1;
        
        Position tempUp, tempLeft, tempRight, tempDown, tempLeftUp, tempLeftDown, tempRightUp, tempRightDown;
        
        //GETTING UP MOVE
        if(this.piecePosition.getyCord() != 0) {
            tempUp = new Position(this.piecePosition.getxCord(), this.piecePosition.getyCord() - 1);
            
            if(boardStatus.getPieceFromBoard(tempUp).isEmpty() || boardStatus.getPieceFromBoard(tempUp).getPieceColour() != this.getPieceColour()) {
                this.legalPositions.add(tempUp);
            }
        }
        
        //GETTING DOWN MOVE
        if(this.piecePosition.getyCord() != 7) {
            tempDown = new Position(this.piecePosition.getxCord(), this.piecePosition.getyCord() + 1);
        
            if(boardStatus.getPieceFromBoard(tempDown).isEmpty() || boardStatus.getPieceFromBoard(tempDown).getPieceColour() != this.getPieceColour()) {
                this.legalPositions.add(tempDown);
            }
        }
        
        //GETTING LEFT MOVE
        if(this.piecePosition.getxCord() != 0) {
            tempLeft = new Position(this.piecePosition.getxCord() - 1, this.piecePosition.getyCord());
        
            if(boardStatus.getPieceFromBoard(tempLeft).isEmpty() || boardStatus.getPieceFromBoard(tempLeft).getPieceColour() != this.getPieceColour()) {
                this.legalPositions.add(tempLeft);
            }
            
            if(this.piecePosition.getyCord() != 0) {
                //LEFT UP
                tempLeftUp = new Position(this.piecePosition.getxCord() - 1, this.piecePosition.getyCord() - 1);
                
                if(boardStatus.getPieceFromBoard(tempLeftUp).isEmpty() || boardStatus.getPieceFromBoard(tempLeftUp).getPieceColour() != this.getPieceColour()) {
                    this.legalPositions.add(tempLeftUp);
                }
            }
            
            if(this.piecePosition.getyCord() != 7) {
                //LEFT DOWN
                tempLeftDown = new Position(this.piecePosition.getxCord() - 1, this.piecePosition.getyCord() + 1);
                
                if(boardStatus.getPieceFromBoard(tempLeftDown).isEmpty() || boardStatus.getPieceFromBoard(tempLeftDown).getPieceColour() != this.getPieceColour()) {
                    this.legalPositions.add(tempLeftDown);
                }
            }
            
            
        }
        
        //GETTING RIGHT MOVE
        if(this.piecePosition.getxCord() != 7) {
            tempRight = new Position(this.piecePosition.getxCord() + 1, this.piecePosition.getyCord());
        
            if(boardStatus.getPieceFromBoard(tempRight).isEmpty() || boardStatus.getPieceFromBoard(tempRight).getPieceColour() != this.getPieceColour()) {
                this.legalPositions.add(tempRight);
            }
            
            if(this.piecePosition.getyCord() != 0) {
                //RIGHT UP
                tempRightUp = new Position(this.piecePosition.getxCord() + 1, this.piecePosition.getyCord() - 1);
                
                if(boardStatus.getPieceFromBoard(tempRightUp).isEmpty() || boardStatus.getPieceFromBoard(tempRightUp).getPieceColour() != this.getPieceColour()) {
                    this.legalPositions.add(tempRightUp);
                }
            }
            
            if(this.piecePosition.getyCord() != 7) {
                //RIGHT DOWN
                tempRightDown = new Position(this.piecePosition.getxCord() + 1, this.piecePosition.getyCord() + 1);
                
                if(boardStatus.getPieceFromBoard(tempRightDown).isEmpty() || boardStatus.getPieceFromBoard(tempRightDown).getPieceColour() != this.getPieceColour()) {
                    this.legalPositions.add(tempRightDown);
                }
            }
        }

        return this.legalPositions;
    }
}
