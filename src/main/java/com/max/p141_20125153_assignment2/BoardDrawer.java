/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Maxth
 */
public class BoardDrawer extends JPanel implements MouseListener{
    //Fields for holding board positions and tiles positions
    private Tile[][] boardTiles;
    private Piece[][] boardPieces;
    
    private int mouseClickedX;
    private int mouseClickedY;
    
    private Piece selectedPiece;
    private ArrayList<Position> piecePositions;
    
    //Draw panel
    private DrawPanel drawPanel;
    
    private Colour playerCurrentTurn;
    
    

    public BoardDrawer() {
        super();
        
        //BY DEFAULT TURN WILL BE WHITE
        this.playerCurrentTurn = Colour.WHITE;
        
        this.boardTiles = new Tile[Board.BOARD_SIZE][Board.BOARD_SIZE];
        
        this.resetTiles();
        

        //TEMP SETTING PIECES TEST
        Board temp = new Board();
        this.boardPieces = temp.getBoard();
        //TEMP DONE
        
        this.drawPanel = new DrawPanel();
        super.add(this.drawPanel, BorderLayout.NORTH);
        
        this.drawPanel.addMouseListener(this);
        
        this.mouseClickedX = -1;
        this.mouseClickedY = -1;
    }

    public BoardDrawer(Piece[][] boardPieces) {
        super();
        
        //BY DEFAULT TURN WILL BE WHITE
        this.playerCurrentTurn = Colour.WHITE;
        
        this.boardPieces = boardPieces;
    
        this.boardTiles = new Tile[Board.BOARD_SIZE][Board.BOARD_SIZE];
        
        this.resetTiles();
        
        this.drawPanel = new DrawPanel();
        super.add(this.drawPanel, BorderLayout.NORTH);
    }
    
    public void setPlayerTurn(Colour currentTurn) {
        this.playerCurrentTurn = currentTurn;
    }

    public void setPieces(Piece[][] boardPieces) {
        this.boardPieces = boardPieces;
    }
    
    //Setting all the tiles back to the original state
    public void resetTiles() {
        for(int row = 0; row < Board.BOARD_SIZE; row++) {
            for(int col = 0; col < Board.BOARD_SIZE; col++) {
                int xCord = ((col) * 75);
                int yCord = ((row) * 75);
                
                //Switching between black and white tiles
                if(row == 0 && col == 0) {
                    this.boardTiles[row][col] = new Tile(xCord, yCord, true);
                } else if(row % 2 == 0 && col % 2 == 0) {
                    this.boardTiles[row][col] = new Tile(xCord, yCord, true);
                }  else if(col % 2 == 1 && row % 2 == 1) {
                    this.boardTiles[row][col] = new Tile(xCord, yCord, true);
                } else {
                    this.boardTiles[row][col] = new Tile(xCord, yCord, false);
                }
            }
        }
    }
    
    //PRIVATE INNER CLASS
    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super.setPreferredSize(new Dimension(600, 600));
            super.setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
        
            //Draw tiles
            for(Tile i[] : boardTiles) {
                for(Tile j : i) {
                    j.drawTile(g);
                }
            }
            
            //Draw Pieces
            for(Piece i[] : boardPieces) {
                for(Piece j : i) {
                    if(j != null) {
                        j.drawPiece(g);
                    }
                }
            }
        }
    }
    
    private void resetSelected() {
        for(int row = 0; row < Board.BOARD_SIZE; row++) {
            for(int col = 0; col < Board.BOARD_SIZE; col++) {
                this.boardTiles[row][col].setOutline(false);
            }
        }
    }
    
    private boolean checkIfMove() {
        System.out.println("in here");
        if(this.piecePositions != null) {
            for(int i = 0; i < this.piecePositions.size(); i++) {
                if((this.piecePositions.get(i).getxCord() == this.mouseClickedX) && this.piecePositions.get(i).getyCord() == this.mouseClickedY) {
                    //THEN MOVE THE PIECE
                    return true;
                } 
            }
        }
        
        return false;
    }
    
    private void outLineMoves() {
        for(int i = 0; i < this.piecePositions.size(); i++) {
            int xCordTemp = this.piecePositions.get(i).getxCord();
            int yCordTemp = this.piecePositions.get(i).getyCord();
            
            this.boardTiles[yCordTemp][xCordTemp].setOutline(true);
        }
    }
    
    
    //Mouselistener Methods
    @Override
    public void mouseClicked(MouseEvent e) {
        int xOnPanel = e.getX();
        int yOnPanel = e.getY();
        
        int xTile = xOnPanel / 75;
        int yTile = yOnPanel / 75;
        
        System.out.println("Mouse Clicked at (" + xTile + ", " + yTile + ")");
        
        this.mouseClickedX = xTile;
        this.mouseClickedY = yTile;
        
        //STEPS
        //Check if selected Piece == null
        if(this.selectedPiece == null) {
            System.out.println("Piece just selected");
            
            //if piece hasn't been selected
            this.selectedPiece = this.boardPieces[yTile][xTile];
            if(this.selectedPiece.pieceColour == this.playerCurrentTurn || this.selectedPiece.pieceColour == Colour.NONE) {
                this.resetSelected();
                this.boardTiles[yTile][xTile].setOutline(true);
            
                //Getting avalible moves
                if(this.boardPieces[yTile][xTile].pieceColour != Colour.NONE) {
                    this.piecePositions = this.selectedPiece.getLegalPositions(new Board(this.boardPieces));
                    this.outLineMoves();
                }
            }
        } else {
            //if piece has been seleted
            if(this.checkIfMove()) {
                System.out.println("moving piece");
                PieceController tempController = new PieceController(new Board(this.boardPieces), Colour.BLACK);
                tempController.movePiece(this.selectedPiece, this.boardPieces[yTile][xTile]);
                //Because moved must change player turn
                if(this.playerCurrentTurn == Colour.BLACK) {
                    this.playerCurrentTurn = Colour.WHITE;
                } else if(this.playerCurrentTurn == Colour.WHITE) {
                    this.playerCurrentTurn = Colour.BLACK;
                }
                
                
                
                this.resetSelected();
                this.piecePositions = null;
                
                //CHECK IF GAME HAS BEEN WON
                Colour winner = this.checkWin();
                if(winner != Colour.NONE) {
                    JOptionPane.showMessageDialog(this, "PLAYER " + winner + " HAS WON THE GAME!");
                    System.exit(0);
                } 
            } else {
                //If wasn't a move but a reselect
                this.selectedPiece = this.boardPieces[yTile][xTile];
                if(this.selectedPiece.pieceColour == this.playerCurrentTurn || this.selectedPiece.pieceColour == Colour.NONE) {
                     this.resetSelected();
                    System.out.println("OUTLINES");
                    this.boardTiles[yTile][xTile].setOutline(true);

                    //Getting avalible moves
                    if(this.boardPieces[yTile][xTile].pieceColour != Colour.NONE) {
                        this.piecePositions = this.selectedPiece.getLegalPositions(new Board(this.boardPieces));
                        this.outLineMoves();
                    }
                }
            }
        }
        
        this.drawPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private Colour checkWin() {
        boolean foundKingWhite = false;
        boolean foundKingBlack = false;
        
         for(int row = 0; row < Board.BOARD_SIZE; row++) {
            for(int col = 0; col < Board.BOARD_SIZE; col++) {
                if(this.boardPieces[col][row].getClass() == (new King(null, null)).getClass()) {
                    if(this.boardPieces[col][row].pieceColour == Colour.BLACK) {
                        foundKingBlack = true;
                    } else if(this.boardPieces[col][row].pieceColour == Colour.WHITE) {
                        foundKingWhite = true;
                    }
                }
            }
         }
         
         if(!foundKingBlack) {
             return Colour.WHITE;
         } else if(!foundKingWhite) {
             return Colour.BLACK;
         } 
         return Colour.NONE;
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("TEST GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BoardDrawer());
        frame.pack();
        frame.setSize(615,639);
        
        frame.setVisible(true);
        
    }    
}
