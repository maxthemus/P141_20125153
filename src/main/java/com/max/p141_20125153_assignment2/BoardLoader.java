/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import static com.max.p141_20125153_assignment2.Board.BOARD_SIZE;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Maxth
 */
public class BoardLoader {

    public BoardLoader() {
    }
    
    public static void saveGame (Board board, Colour turn) {
        PrintWriter saveFile = null;
        Piece[][] tempBoard = board.getBoard();
        
        try {
            saveFile = new PrintWriter(new FileOutputStream(new File("./game-saves/save_one.txt")));
        } catch (Exception e) {
            System.out.println("SAVE FILE WAS NOT FOUND");
            BoardLoader.saveGame(board, turn);
        }
        
        if(turn == Colour.BLACK) {
            saveFile.println("B");
        } else if(turn == Colour.WHITE) {
            saveFile.println("W");
        }
        
        for(int row = 0; row < BOARD_SIZE; row++) {
            for(int col = 0; col < BOARD_SIZE; col++) {
                Piece tempPiece = tempBoard[row][col];
                int xPos = col;
                int yPos = row;
                String tempString = "";
                
                if(tempPiece.getClass() == new King(null, Colour.WHITE).getClass()) {
                    //PRINTING DIFFERENT THINGS IF WHITE OR BLACK
                    if(tempPiece.getPieceColour() == Colour.BLACK) {
                        tempString = "B," + xPos +  "," + yPos + "," + "K";
                        saveFile.println(tempString);
                    } else if(tempPiece.getPieceColour() == Colour.WHITE) {
                        tempString = "W," + xPos +  "," + yPos + "," + "K";
                        saveFile.println(tempString);
                    }
                } else if(tempPiece.getClass() == new Queen(null, Colour.WHITE).getClass()) {
                    //PRINTING DIFFERENT THINGS IF WHITE OR BLACK
                    if(tempPiece.getPieceColour() == Colour.BLACK) {
                        tempString = "B," + xPos +  "," + yPos + "," + "Q";
                        saveFile.println(tempString);
                    } else if(tempPiece.getPieceColour() == Colour.WHITE) {
                        tempString = "W," + xPos +  "," + yPos + "," + "Q";
                        saveFile.println(tempString);
                    }
                } else if(tempPiece.getClass() == new Bishop(null, Colour.WHITE).getClass()) {
                    //PRINTING DIFFERENT THINGS IF WHITE OR BLACK
                    if(tempPiece.getPieceColour() == Colour.BLACK) {
                        tempString = "B," + xPos +  "," + yPos + "," + "B";
                        saveFile.println(tempString);
                    } else if(tempPiece.getPieceColour() == Colour.WHITE) {
                        tempString = "W," + xPos +  "," + yPos + "," + "B";
                        saveFile.println(tempString);
                    }
                } else if(tempPiece.getClass() == new Knight(null, Colour.WHITE).getClass()) {
                    //PRINTING DIFFERENT THINGS IF WHITE OR BLACK
                    if(tempPiece.getPieceColour() == Colour.BLACK) {
                        tempString = "B," + xPos +  "," + yPos + "," + "KN";
                        saveFile.println(tempString);
                    } else if(tempPiece.getPieceColour() == Colour.WHITE) {
                        tempString = "W," + xPos +  "," + yPos + "," + "KN";
                        saveFile.println(tempString);
                    }
                } else if(tempPiece.getClass() == new Pawn(null, Colour.WHITE).getClass()) {
                    //PRINTING DIFFERENT THINGS IF WHITE OR BLACK
                    if(tempPiece.getPieceColour() == Colour.BLACK) {
                        tempString = "B," + xPos +  "," + yPos + "," + "P";
                        saveFile.println(tempString);
                    } else if(tempPiece.getPieceColour() == Colour.WHITE) {
                        tempString = "W," + xPos +  "," + yPos + "," + "P";
                        saveFile.println(tempString);
                    }
                } else if(tempPiece.getClass() == new Rook(null, Colour.WHITE).getClass()) {
                    //PRINTING DIFFERENT THINGS IF WHITE OR BLACK
                    if(tempPiece.getPieceColour() == Colour.BLACK) {
                        tempString = "B," + xPos +  "," + yPos + "," + "R";
                        saveFile.println(tempString);
                    } else if(tempPiece.getPieceColour() == Colour.WHITE) {
                        tempString = "W," + xPos +  "," + yPos + "," + "R";
                        saveFile.println(tempString);
                    }
                }
            }
        }
    
        System.out.println("GAME HAS BEEN SAVED");
        System.out.println("+-----------+");
        saveFile.close();
    }
     
    
    public static Board loadGame () throws IOException  {
        Piece[][] tempPieces = new Piece[BOARD_SIZE][BOARD_SIZE];
        //FILLING BOARD WITH EMPTIES
         for(int row = 0; row < BOARD_SIZE; row++) {
            for(int col = 0; col < BOARD_SIZE; col++) {
                tempPieces[row][col] = new Empty(new Position(col, row), Colour.NONE);
            }
        }
        
        BufferedReader gameLoad = null;
        FileReader reader = null;
        Colour currentTurn = null;
        
        try {
            reader = new FileReader("./game-saves/save_one.txt");
            gameLoad = new BufferedReader(reader);
        } catch (Exception e)  {
            System.out.println("COULDN'T FIND FILE");
            throw new IOException();
            
        }
        
        String line = gameLoad.readLine();
        
        //NOW READING THE FILE
        if(line.compareTo("B") == 0) {
            currentTurn = Colour.BLACK;
        } else if(line.compareTo("W") == 0) {
            currentTurn = Colour.WHITE;
        } else {
            System.out.println("FILE WAS CORRUPT");
            throw new IOException();
        }
        
        //READING ALL THE POSITIONS OF THE PIECES IN
        while((line = gameLoad.readLine()) != null) {
            int xPos;
            int yPos;
            Colour pieceColour;
            Piece tempPiece;
            
            StringTokenizer stringSpliter = new StringTokenizer(line, ",", false);
            //ORDER IS : COLOUR,X,Y,PIECE
            String colourString = stringSpliter.nextToken();
            if(colourString.compareTo("B") == 0) {
                pieceColour = Colour.BLACK;
            } else if(colourString.compareTo("W") == 0) {
                pieceColour = Colour.WHITE;
            } else {
                System.out.println("FILE WAS CORRUPT");
                throw new IOException();
            }
            
            xPos = Integer.parseInt(stringSpliter.nextToken());
            if(xPos < 0 || xPos > BOARD_SIZE) {
                System.out.println("FILE WAS CORRUPT");
                throw new IOException();
            } 
            
            yPos = Integer.parseInt(stringSpliter.nextToken());
            if(yPos < 0 || yPos > BOARD_SIZE) {
                System.out.println("FILE WAS CORRUPT");
                throw new IOException();
            } 
            
            //LOOKING AT PIECE TYPE
            String pieceString = stringSpliter.nextToken();
            if(pieceString.compareTo("K") == 0) {
                tempPieces[yPos][xPos] = new King(new Position(xPos, yPos), pieceColour);
            } else if(pieceString.compareTo("Q") == 0) {
                tempPieces[yPos][xPos] = new Queen(new Position(xPos, yPos), pieceColour);
            } else if(pieceString.compareTo("B") == 0) {
                tempPieces[yPos][xPos] = new Bishop(new Position(xPos, yPos), pieceColour);
            } else if(pieceString.compareTo("KN") == 0) {
                tempPieces[yPos][xPos] = new Knight(new Position(xPos, yPos), pieceColour);
            } else if(pieceString.compareTo("P") == 0) {
                tempPieces[yPos][xPos] = new Pawn(new Position(xPos, yPos), pieceColour);
            } else if(pieceString.compareTo("R") == 0) {
                tempPieces[yPos][xPos] = new Rook(new Position(xPos, yPos), pieceColour);
            } else {
                System.out.println("FILE WAS CORRUPT");
                throw new IOException();
            }
        }
        Board tempBoard = new Board(tempPieces);
        
        gameLoad.close();
        
        return tempBoard;
    }
    
}
