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
public class Rook extends Piece{
    //Field for holding the max amount of legaul move that a piece could have
    private final int MAX_LEGAL_NUM = 14;
    
    //CONSTRUCTOR FOR ROOK piece
    public Rook(Position piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
        
        //Initillizing the arraylist for the legal posistions
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        
        //TEMP VALUE FOR CHAR
        this.pieceChar = 'r';
        
        
        //Setting up image
        if(pieceColour == Colour.BLACK) {
            this.pieceImageLocation = "./Images/Rook_Black.png";
        } else {
            this.pieceImageLocation = "./Images/Rook_White.png";
        }
        //Loading image into field
        try {
            this.pieceImage = ImageIO.read(new File(this.pieceImageLocation));
        } catch (Exception e) {
            System.out.println("Something went wrong did you delete the image file!!! --> " + this.getClass().toString());
            System.exit(1);
        }
    }

    //getLegalPositions Method for a ROOK
    @Override
    public ArrayList<Position> getLegalPositions(Board boardStatus) {
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        Piece[][] board = boardStatus.getBoard();
        //getting local var for holding the X and Y cord
        int yCordTemp = this.piecePosition.getyCord();
        int xCordTemp = this.piecePosition.getxCord();
       
        //These are some variables for looping to the edge
        int yTop = (7 - yCordTemp); 
        int yBot = (7 - yTop);
        int xRight = (7 - xCordTemp);
        int xLeft = (7 - xRight);
       //We are going to do 4 checks for the ROOK up, down, left, right
       
       //[DOWN][ACCROSS]
       //UP
       for(int ptr = (yCordTemp - 1); ptr >= 0 ;ptr--) {
           if(board[ptr][xCordTemp].isEmpty()) {
               this.legalPositions.add(new Position(xCordTemp, ptr));
           } else {
               if(board[ptr][xCordTemp].getPieceColour() != this.pieceColour) {
                   this.legalPositions.add(new Position(xCordTemp, ptr));
               }
               break;
           }
       }
       
       //DOWN
       for(int ptr = (yCordTemp + 1); ptr <= 7; ptr++) {
           if(board[ptr][xCordTemp].isEmpty()) {
               this.legalPositions.add(new Position(xCordTemp, ptr));
           } else {
               if(board[ptr][xCordTemp].getPieceColour() != this.pieceColour) {
                   this.legalPositions.add(new Position(xCordTemp, ptr));
               }
               break;
           }
       }
       
       //LEFT
       for(int ptr = (xCordTemp - 1); ptr >= 0; ptr--) {
           if(board[yCordTemp][ptr].isEmpty()) {
               this.legalPositions.add(new Position(ptr, yCordTemp));
           } else {
               if(board[yCordTemp][ptr].getPieceColour() != this.pieceColour) {
                   this.legalPositions.add(new Position(ptr, yCordTemp));
               }
               break;
           }
       }
       
       //RIGHT
       for(int ptr = (xCordTemp + 1); ptr <= 7; ptr++) {
           if(board[yCordTemp][ptr].isEmpty()) {
               this.legalPositions.add(new Position(ptr, yCordTemp));
           } else {
               if(board[yCordTemp][ptr].getPieceColour() != this.pieceColour) {
                   this.legalPositions.add(new Position(ptr, yCordTemp));
               }
               break;
           }
       }
       
       return this.legalPositions;
    }
}
