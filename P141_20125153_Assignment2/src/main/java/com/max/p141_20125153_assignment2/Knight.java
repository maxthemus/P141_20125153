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
public class Knight extends Piece{
    //Field for holding the max amount of legaul move that a piece could have
    private final int MAX_LEGAL_NUM = 8;
    
    //CONSTRUCTOR FOR KNIGHT piece

    public Knight(Position piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
        
        //Initillizing the arraylist for the legal posistions
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        
        //TEMP VALUE FOR CHAR
        this.pieceChar = 'h';
        
        
        //Setting up image
        if(pieceColour == Colour.BLACK) {
            this.pieceImageLocation = "./Images/Horse_Black.png";
        } else {
            this.pieceImageLocation = "./Images/Horse_White.png";
        }
        //Loading image into field
        try {
            this.pieceImage = ImageIO.read(new File(this.pieceImageLocation));
        } catch (Exception e) {
            System.out.println("Something went wrong did you delete the image file!!!");
            System.exit(1);
        }
    }
    
    //getLegalPositions Method for a KNIGHT
    @Override
    public ArrayList<Position> getLegalPositions(Board boardStatus) {
        this.legalPositions = new ArrayList<>(this.MAX_LEGAL_NUM);
        Piece[][] board = boardStatus.getBoard();
        //getting local var for holding the X and Y cord
        int yCordTemp = this.piecePosition.getyCord();
        int xCordTemp = this.piecePosition.getxCord();
        
        if(this.piecePosition.getyCord() >= 2) {
            //TOP RIGHT 
            if(this.piecePosition.getxCord() <= 6) {
                if(board[yCordTemp - 2][xCordTemp + 1].isEmpty()) {
                    this.legalPositions.add(new Position(xCordTemp + 1, yCordTemp - 2));
                } else {
                    if(board[yCordTemp - 2][xCordTemp + 1].getPieceColour() != this.pieceColour) {
                        this.legalPositions.add(new Position(xCordTemp + 1, yCordTemp - 2));
                    }
                }
            }
            
            if(this.piecePosition.getxCord() >= 1) {
                //TOP LEFT
                if(board[yCordTemp - 2][xCordTemp - 1].isEmpty()) {
                    this.legalPositions.add(new Position(xCordTemp - 1, yCordTemp - 2));
                } else {
                    if(board[yCordTemp - 2][xCordTemp - 1].getPieceColour() != this.pieceColour) {
                        this.legalPositions.add(new Position(xCordTemp - 1, yCordTemp - 2));
                    }
                }
            }
        }
        
        if(this.piecePosition.getyCord() <= 5) {
            //BOTTOM RIGHT 
            if(this.piecePosition.getxCord() >= 1 && this.piecePosition.getxCord() <= 6) {
                if(board[yCordTemp + 2][xCordTemp + 1].isEmpty()) {
                    this.legalPositions.add(new Position(xCordTemp + 1, yCordTemp + 2));
                } else {
                    if(board[yCordTemp + 2][xCordTemp + 1].getPieceColour() != this.pieceColour) {
                        this.legalPositions.add(new Position(xCordTemp + 1, yCordTemp + 2));
                    }
                }
            }
        
            if(this.piecePosition.getxCord() <= 6 && this.piecePosition.getxCord() >= 1) {
                //BOTTOM LEFT
                if(board[yCordTemp + 2][xCordTemp - 1].isEmpty()) {
                    this.legalPositions.add(new Position(xCordTemp - 1, yCordTemp + 2));
                } else {
                    if(board[yCordTemp + 2][xCordTemp - 1].getPieceColour() != this.pieceColour) {
                        this.legalPositions.add(new Position(xCordTemp - 1, yCordTemp + 2));
                    }
                }
            }
        }
        
        if(this.piecePosition.getxCord() >= 2) {
            if(this.piecePosition.getyCord() >= 1) {
                //LEFT TOP
                if(board[yCordTemp - 1][xCordTemp - 2].isEmpty()) {
                    this.legalPositions.add(new Position(xCordTemp - 2, yCordTemp - 1));
                } else {
                    if(board[yCordTemp - 1][xCordTemp - 2].getPieceColour() != this.pieceColour) {
                        this.legalPositions.add(new Position(xCordTemp - 2, yCordTemp - 1));
                    }
                }
            }
            
            if(this.piecePosition.getyCord() <= 6) {
                //LEFT BOTTOM
                if(board[yCordTemp + 1][xCordTemp - 2].isEmpty()) {
                    this.legalPositions.add(new Position(xCordTemp - 2, yCordTemp + 1));
                } else {
                    if(board[yCordTemp + 1][xCordTemp - 2].getPieceColour() != this.pieceColour) {
                        this.legalPositions.add(new Position(xCordTemp - 2, yCordTemp - 1));
                    }
                }
            }
        }
        
        if(this.piecePosition.getxCord() <= 5) {
            if(this.piecePosition.getyCord() >= 1) {
                //RIGHT TOP
                if(board[yCordTemp - 1][xCordTemp + 2].isEmpty()) {
                    this.legalPositions.add(new Position(xCordTemp + 2, yCordTemp - 1));
                } else {
                    if(board[yCordTemp - 1][xCordTemp + 2].getPieceColour() != this.pieceColour) {
                        this.legalPositions.add(new Position(xCordTemp + 2, yCordTemp - 1));
                    }
                }
            }
            
            if(this.piecePosition.getyCord() <= 6) {
                //RIGHT BOTTOM
                if(board[yCordTemp + 1][xCordTemp + 2].isEmpty()) {
                    this.legalPositions.add(new Position(xCordTemp + 2, yCordTemp + 1));
                } else {
                    if(board[yCordTemp + 1][xCordTemp + 2].getPieceColour() != this.pieceColour) {
                        this.legalPositions.add(new Position(xCordTemp + 2, yCordTemp - 1));
                    }
                }
            }
        }
        
        return this.legalPositions;
    }
    
}
