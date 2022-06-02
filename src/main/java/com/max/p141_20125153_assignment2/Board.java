/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Maxth
 */
public class Board {
    //Field to store the Pieces on the board in a 2D array
    private Piece[][] board;

    
    //Final Field for storing board height and width
    public static final int BOARD_SIZE = 8;
    
    
    //constructor for fresh board
    public Board() {
        super();
        
        this.board = new Piece[BOARD_SIZE][BOARD_SIZE];
        
        this.resetBoard();
    }

    //constructor for existing board
    public Board(Piece[][] board) {
        super();
        
        this.board = board;
    }
    
    
    
    //Method for creatingg a new board
    private void resetBoard() {
        for(int row = 0; row < BOARD_SIZE; row++) {
            for(int col = 0; col < BOARD_SIZE; col++) {
                this.board[row][col] = new Empty(new Position(col, row), Colour.NONE);
            }
        }
        this.placePieces();
    }
    
    //Method for filling the board with the pieces in there original positions
    private void placePieces() {
        int whiteFront = 6;
        int blackFront = 1;
                
        for(int col = 0; col < BOARD_SIZE; col++) {
            //Placing black PAWN's
            this.board[blackFront][col] = new Pawn(new Position(col, blackFront), Colour.BLACK);
            
            //Placing Backrow
            switch(col) {
                case 0:
                case 7:
                    //CASE FOR ROOK
                    this.board[0][col] = new Rook(new Position(col, 0), Colour.BLACK);
                    this.board[(BOARD_SIZE - 1)][col] = new Rook(new Position(col, (BOARD_SIZE - 1)), Colour.WHITE);
                    break;
                case 1:
                case 6:
                    //CASE FOR KNIGHT
                    this.board[0][col] = new Knight(new Position(col, 0), Colour.BLACK);
                    this.board[(BOARD_SIZE - 1)][col] = new Knight(new Position(col, (BOARD_SIZE - 1)), Colour.WHITE);
                    break;
                case 2:
                case 5:
                    //CASE FOR BISHOP
                    this.board[0][col] = new Bishop(new Position(col, 0), Colour.BLACK);
                    this.board[(BOARD_SIZE - 1)][col] = new Bishop(new Position(col, (BOARD_SIZE - 1)), Colour.WHITE);
                    break;
                case 3:
                    this.board[0][col] = new King(new Position(col, 0), Colour.BLACK);
                    this.board[(BOARD_SIZE - 1)][col] = new King(new Position(col, (BOARD_SIZE - 1)), Colour.WHITE);
                    //CASES FOR KNING's
                    break;
                case 4:
                    //CASES FOR QUEEN's
                    this.board[0][col] = new Queen(new Position(col, 0), Colour.BLACK);
                    this.board[(BOARD_SIZE - 1)][col] = new Queen(new Position(col, (BOARD_SIZE - 1)), Colour.WHITE);
                    break;
            }
             
            //Placing white PAWN's
            this.board[whiteFront][col] = new Pawn(new Position(col, whiteFront), Colour.WHITE);
        }
    }
     
    //Method to print the board Saves typing System.out...
    public void printBoard() {
        System.out.println(this.toString());
    }
    
    //ToString for converting the board into a string format
    public String toString() {
        String temp = "                BLACK\n";
        
        for(int row = 0; row < BOARD_SIZE; row++) {
            temp += (8 - row);
            for(int col = 0; col < BOARD_SIZE; col++) {
                temp += " | " + this.board[row][col].toString();
            }
            temp += " |\n  ";
            if(row < (BOARD_SIZE - 1)) {
                for(int i = 0;  i < 8; i++) {
                    temp += "+---";
                }
                temp += "+\n";
            }
        }
        temp += "  A   B   C   D   E   F   G   H\n";
        temp += "                WHITE";
        return temp;
    }

    public Piece[][] getBoard() {
        return board;
    }
    
    public Piece getPieceFromBoard(Position piecePosition) {
        return this.board[piecePosition.getyCord()][piecePosition.getxCord()];
    }
}
