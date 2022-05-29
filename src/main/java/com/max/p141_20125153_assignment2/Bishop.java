/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Maxth
 */
public class Bishop extends Piece {
    
    private final int MAX_LEGAL_NUM = 21;

    public Bishop(Position piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
    
        //Initillizing the arraylist for the legal posistions
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
    
        //TEMP VALUE FOR CHAR
        this.pieceChar = 'b';
        
        
        //Setting up image
        if(pieceColour == Colour.BLACK) {
            this.pieceImageLocation = "./Images/Bishop_Black.png";
        } else {
            this.pieceImageLocation = "./Images/Bishop_White.png";
        }
        //Loading image into field
        try {
            this.pieceImage = ImageIO.read(new File(this.pieceImageLocation));
        } catch (Exception e) {
            System.out.println("Something went wrong did you delete the image file!!! --> " + this.getClass().toString());            System.exit(1);
        }
    }

    @Override
    public ArrayList<Position> getLegalPositions(Board boardStatus) {
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        Piece[][] board = boardStatus.getBoard();
        
        //LOCAL VAR FOR PIECE POSSITION
        int yCordTemp = this.piecePosition.getyCord();
        int xCordTemp = this.piecePosition.getxCord();
        
        //These are some variables for looping to the edge
        int yTop = (7 - yCordTemp); 
        int yBot = (7 - yTop);
        int xRight = (7 - xCordTemp);
        int xLeft = (7 - xRight);
        
        //Variables for looping to the edges
        int topRight;
        int topLeft; 
        int bottomRight; 
        int bottomLeft;
        
        //filling vars
        //BOTTOM RIGHT
        if(yTop <= xRight) {
            bottomRight = yTop;
        } else {
            bottomRight = xRight;
        }
        
        //BOTTOM LEFT
        if(yTop <= xLeft) {
            bottomLeft = yTop;
        } else {
            bottomLeft = xLeft;
        }
        
        //TOP RIGHT
        if(yBot <= xRight) {
            topRight = yBot;
        } else {
            topRight = xRight;
        }
        
        //TOP LEFT
        if(yBot <= xLeft) {
            topLeft = yBot;
        } else {
            topLeft = xLeft;
        }
                
        //Loops for getting legal positions
        //TOP RIGHT LOOP
        int yAxis = (yCordTemp);
        int xAxis = (xCordTemp);
        for(int ptr = 1; ptr < (topRight + 1); ptr++) {
            if(board[yAxis - ptr][xAxis + ptr].isEmpty()) {
                this.legalPositions.add(new Position((xAxis + ptr), (yAxis - ptr)));
            } else {
                if(board[yAxis - ptr][xAxis + ptr].getPieceColour() != this.pieceColour) {
                    this.legalPositions.add(new Position((xAxis + ptr), (yAxis - ptr)));
                }
                break;
            }
        }
        
        //TOP LEFT LOOP
        yAxis = (yCordTemp);
        xAxis = (xCordTemp);
        for(int ptr = 1; ptr < (topLeft + 1); ptr++) {
            if(board[yAxis - ptr][xAxis - ptr].isEmpty()) {
                this.legalPositions.add(new Position((xAxis - ptr), (yAxis - ptr)));
            } else {
                if(board[yAxis - ptr][xAxis - ptr].getPieceColour() != this.pieceColour) {
                    this.legalPositions.add(new Position((xAxis - ptr), (yAxis - ptr)));
                }
                break;
            }
        }
        
        //BOTTOM RIGHT LOOP
        yAxis = (yCordTemp);
        xAxis = (xCordTemp);
        for(int ptr = 1; ptr < (bottomRight + 1); ptr++) {
            if(board[yAxis + ptr][xAxis + ptr].isEmpty()) {
                this.legalPositions.add(new Position((xAxis + ptr), (yAxis + ptr)));
            } else {
                if(board[yAxis + ptr][xAxis + ptr].getPieceColour() != this.pieceColour) {
                    this.legalPositions.add(new Position((xAxis + ptr), (yAxis + ptr)));
                }
                break;
            }
        }
        
        //BOTTOM LEFT LOOP
        yAxis = (yCordTemp);
        xAxis = (xCordTemp);
        for(int ptr = 1; ptr < (bottomLeft + 1); ptr++) {
            if(board[yAxis + ptr][xAxis - ptr].isEmpty()) {
                this.legalPositions.add(new Position((xAxis - ptr), (yAxis + ptr)));
            } else {
                if(board[yAxis + ptr][xAxis - ptr].getPieceColour() != this.pieceColour) {
                    this.legalPositions.add(new Position((xAxis - ptr), (yAxis + ptr)));
                }
                break;
            }
        }
        
        return this.legalPositions;
    }
}
