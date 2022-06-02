/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.util.ArrayList;

/**
 *
 * @author Maxth
 */
public class BoardConditions {
    //FIELD FOR HOLDING THE PIECES ON THE BOARD
    private Piece[][] board;
    
    //Array list for all possible posisitioins from all players
    private ArrayList<Position> avaliableBlack;
    private ArrayList<Position> avaliableWhite;
    
    //ArrayList for all possible moves from either team
    private ArrayList<Position> avaliablesMovesBlack;
    private ArrayList<Position> avaliablesMovesWhite;

    public BoardConditions(Board board) {
        this.board = board.getBoard();
        
        this.avaliableBlack = new ArrayList<>();
        this.avaliableWhite = new ArrayList<>();
        this.avaliablesMovesBlack = new ArrayList<>();
        this.avaliablesMovesWhite = new ArrayList<>();
    }
    
    private void fillAvaliables () {
         for(int row = 0; row <  8; row++) {
            for (int col = 0; col < 8; col++) {
                if(board[row][col].getPieceColour() == Colour.WHITE) {
                    this.avaliableWhite.add(new Position(col, row));
                }
                if(board[row][col].getPieceColour() == Colour.BLACK) {
                    this.avaliableBlack.add(new Position(col, row));
                }
            }
        }
    }
    
    public Colour hasWon () {
       this.fillAvaliables();
       Board temp = new Board(this.board);
       boolean hasKing = false;
       
       //Now are check the arrays to see if the KING is missing
       for(int i = 0; i < this.avaliableBlack.size(); i++) {
           Piece tempPiece = temp.getPieceFromBoard(this.avaliableBlack.get(i));
           if(tempPiece.getClass() == King.class) {
               hasKing = true;
               break;
           }
       }
       
       if(!hasKing) {
           //BECAUSE WHITE HAS WON
           return Colour.WHITE;
       } 
       
       hasKing = false;
       
       //ELSE WE CHECK THE WHITE TEAM
       for(int i = 0; i < this.avaliableWhite.size(); i++) {
           Piece tempPiece = temp.getPieceFromBoard(this.avaliableWhite.get(i));
           if(tempPiece.getClass() == King.class) {
               hasKing = true;
               break;
           }
       }
       
       if(!hasKing) {
           //BECAUSE BLACK HAS WON
           return Colour.BLACK;
       }
       
       return Colour.NONE;
    }
    
    //CHECK FOR CHECK
    public Colour isCheck() {
        this.fillAvaliables();
        Board boardTemp = new Board(this.board);
        
        //IF RETURN WHITE IT MEANS WHITE IS IN CHECK or RETURN BLACK or NONE 
        //GETTING ALL MOVES FOR BLACK
        for(int i = 0; i < this.avaliableBlack.size(); i++) {
            Piece tempPiece = boardTemp.getPieceFromBoard(this.avaliableBlack.get(i));
            
            ArrayList<Position> tempMoves = tempPiece.getLegalPositions(boardTemp);
            
            try {
                for(int j = 0; j < tempMoves.size(); j++) {
                    this.avaliablesMovesBlack.add(tempMoves.get(j));
                }
            } catch (Exception e) {}
        }
        
        //GETTING ALL MOVES FOR WHITE
        for(int i = 0; i < this.avaliableWhite.size(); i++) {
            Piece tempPiece = boardTemp.getPieceFromBoard(this.avaliableWhite.get(i));
            
            ArrayList<Position> tempMoves = tempPiece.getLegalPositions(boardTemp);
            
            try {
                for(int j = 0; j < tempMoves.size(); j++) {
                    this.avaliablesMovesWhite.add(tempMoves.get(j));
                }
            } catch(Exception e) {}
         }
        
        Position blackKing = null;
        Position whiteKing = null;
        
        //GET POSITION OF BLACK KING
        for(int i = 0; i < this.avaliableBlack.size(); i++) {
            Piece tempPiece = boardTemp.getPieceFromBoard(this.avaliableBlack.get(i));
            
            if(tempPiece.getClass() == King.class && tempPiece.getPieceColour() == Colour.BLACK) {
                blackKing = tempPiece.getPiecePosition();
            }
        }
        
        
        //GET POSITION OF WHITE KING
        for(int i = 0; i < this.avaliableWhite.size(); i++) {
            Piece tempPiece = boardTemp.getPieceFromBoard(this.avaliableWhite.get(i));
            
            if(tempPiece.getClass() == King.class && tempPiece.getPieceColour() == Colour.WHITE) {
                whiteKing = tempPiece.getPiecePosition();
            }
        }
         
        
        //LOOPING THROUGH AVALIABLE MOVES TO SEE IF KING IS ONE OF THE POSITIONS
        //CHECKING BLACK
        for(int i = 0; i < this.avaliablesMovesBlack.size(); i++) {
            if(this.avaliablesMovesBlack.get(i).getxCord() == blackKing.getxCord() && this.avaliablesMovesBlack.get(i).getyCord() == blackKing.getyCord()) {
                //BECAUSE WHITE IS IN CHECK
                return Colour.WHITE;
            }
        }
        
        for(int i = 0; i < this.avaliablesMovesWhite.size(); i++) {
            if(this.avaliablesMovesWhite.get(i).getxCord() == whiteKing.getxCord() && this.avaliablesMovesWhite.get(i).getyCord() == whiteKing.getyCord()) {
                //BECAUSE BLACK IS IN CHECK
                return Colour.BLACK;
            }
        }
         
        return null;
    }
    
    
}
