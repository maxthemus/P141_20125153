/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Maxth
 */
public class PieceController {
    //Field for holding the game board
    private Board board;

    //Field for holding the colour of the selector
    private Colour playerColour;
    
    //Field for holding the avaliable piece that the player can control
    private ArrayList<Position> avalibalePiecesPositions;
    private static final int MAX_PIECES = 16;
   
    //Field for storing the selected Piece Avalible moves
    private ArrayList<Position> avalibleMoves;
    
    //Field for the Piece we are looking at
    private Piece selectedPiece;
    
    //Constructor for the piece controller
    public PieceController(Board board, Colour playerColour) {
        this.board = board;
        this.playerColour = playerColour;
        
        //Initilizing the avaliable pieces array with the max spaces possible as the MAX_SIZE
        this.avalibalePiecesPositions = new ArrayList<>(MAX_PIECES);
        this.avalibleMoves = new ArrayList<>();
        
        this.selectedPiece = null;
        
    }
      
    public void getAvaliableMoves(Position piecePosition) {
        this.selectedPiece = this.board.getPieceFromBoard(piecePosition);
        
        this.avalibleMoves = this.selectedPiece.getLegalPositions(this.board);
    }
            
    //This is a method which will end the players turn by moving the piece selected to the move poisition
    public void movePiece (Piece from, Piece too) {
        Piece[][] tempBoard = this.board.getBoard();
        
        Piece tempPiece = tempBoard[from.getPiecePosition().getyCord()][from.getPiecePosition().getxCord()];
        
        tempBoard[from.getPiecePosition().getyCord()][from.getPiecePosition().getxCord()] = new Empty(from.getPiecePosition(), Colour.NONE);
        
        Piece temp = from;
        temp.setPosition(new Position(too.getPiecePosition().getxCord(), too.getPiecePosition().getyCord()));
        temp.clearLegalPositions();
        tempBoard[too.getPiecePosition().getyCord()][too.getPiecePosition().getxCord()] = temp;
                
        this.board = new Board(tempBoard);
    }

    public Board getBoard() {
        return board;
    }
    
    
    
}
