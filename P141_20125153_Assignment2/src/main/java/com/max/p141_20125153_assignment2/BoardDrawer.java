/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Maxth
 */
public class BoardDrawer extends JPanel implements MouseListener, ActionListener {
    //Fields for holding board positions and tiles positions
    private Tile[][] boardTiles;
    private Piece[][] boardPieces;
    
    private int mouseClickedX;
    private int mouseClickedY;
    
    private Piece selectedPiece;
    private ArrayList<Position> piecePositions;
    
    //Draw panel
    private DrawPanel drawPanel;
    private JPanel infoPanel;
    
    public static Colour playerCurrentTurn;
    
    private Menu mainMenu;
    
    //Player Label Field
    JLabel playerTurnLabel;
    private final String currentPlayerLabelString = "Player Current Turn = ";

    public BoardDrawer( Menu mainMenu) {
        super();
        
        this.mainMenu = mainMenu;
        
        //BY DEFAULT TURN WILL BE WHITE
        if(this.playerCurrentTurn == null) {
           this.playerCurrentTurn = Colour.WHITE; 
        }
        
        this.boardTiles = new Tile[Board.BOARD_SIZE][Board.BOARD_SIZE];
        
        this.resetTiles();
        
        this.playerCurrentTurn = Colour.WHITE;
        

        //TEMP SETTING PIECES TEST
        Board temp = new Board();
        this.boardPieces = temp.getBoard();
        //TEMP DONE
        
        this.drawPanel = new DrawPanel();
        super.add(this.drawPanel, BorderLayout.WEST);
        
        this.drawPanel.addMouseListener(this);
        
        this.mouseClickedX = -1;
        this.mouseClickedY = -1;
        
       this.setUpInfoPanel();
    }

    public BoardDrawer(Piece[][] boardPieces, Menu mainMenu) {
        super();
        
        this.mainMenu = mainMenu;
        
        if(this.playerCurrentTurn == null) {
           this.playerCurrentTurn = Colour.WHITE; 
        }
        
        this.boardPieces = boardPieces;
    
        this.boardTiles = new Tile[Board.BOARD_SIZE][Board.BOARD_SIZE];
        
        this.resetTiles();
        
        this.drawPanel = new DrawPanel();
        super.add(this.drawPanel, BorderLayout.WEST);
        
        this.drawPanel.addMouseListener(this);
        
        this.mouseClickedX = -1;
        this.mouseClickedY = -1;
        
        this.setUpInfoPanel();
    }
    
    private void setUpInfoPanel() {
        //INFO PANEL
        this.infoPanel = new JPanel();
        this.infoPanel.setPreferredSize(new Dimension(160, 65));
        this.infoPanel.setBackground(Color.LIGHT_GRAY);
        
        JButton exitButton = new JButton("EXIT and Save Game");
        exitButton.addActionListener(this);
        
        playerTurnLabel = new JLabel(currentPlayerLabelString + (this.playerCurrentTurn == Colour.WHITE ? "White" : "Black"));
        this.infoPanel.add(playerTurnLabel, BorderLayout.NORTH);
        this.infoPanel.add(exitButton, BorderLayout.SOUTH);
        
        super.add(this.infoPanel, BorderLayout.LINE_END);
    }
    
    public void setPlayerTurn(Colour currentTurn) {
        this.playerCurrentTurn = currentTurn;
    }

    public void setPieces(Piece[][] boardPieces) {
        this.boardPieces = boardPieces;
    }
    
    private void saveGame() {
        BoardLoader.saveGame(new Board(this.boardPieces), playerCurrentTurn);
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
        
        this.mouseClickedX = xTile;
        this.mouseClickedY = yTile;
        
        //STEPS
        //Check if selected Piece == null
        if(this.selectedPiece == null) {
            
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
                PieceController tempController = new PieceController(new Board(this.boardPieces), Colour.BLACK);
                tempController.movePiece(this.selectedPiece, this.boardPieces[yTile][xTile]);
                //Because moved must change player turn
                if(this.playerCurrentTurn == Colour.BLACK) {
                    this.playerCurrentTurn = Colour.WHITE;
                } else if(this.playerCurrentTurn == Colour.WHITE) {
                    this.playerCurrentTurn = Colour.BLACK;
                }
                
                //Saving game
                this.saveGame();
                
                //Updating the CurrentPlayer label
                this.playerTurnLabel.setText(currentPlayerLabelString + (this.playerCurrentTurn == Colour.WHITE ? "White" : "Black"));
                
                this.resetSelected();
                this.piecePositions = null;
                
                //CHECK IF GAME HAS BEEN WON
                Colour winner = this.checkWin();
                if(winner != Colour.NONE) {
                    JOptionPane.showMessageDialog(this, "PLAYER " + winner + " HAS WON THE GAME!");
                    Menu.playingGame = false;
                    BoardLoader.clearSaveFile();
                    this.setVisible(false);
                    this.mainMenu.closeFrame();
                } 
            } else {
                //If wasn't a move but a reselect
                this.selectedPiece = this.boardPieces[yTile][xTile];
                if(this.selectedPiece.pieceColour == this.playerCurrentTurn || this.selectedPiece.pieceColour == Colour.NONE) {
                    this.resetSelected();
                    this.boardTiles[yTile][xTile].setOutline(true);

                    //Getting avalible moves
                    if(this.boardPieces[yTile][xTile].pieceColour != Colour.NONE) {
                        this.piecePositions = this.selectedPiece.getLegalPositions(new Board(this.boardPieces));
                        this.outLineMoves();
                    }
                } else {
                    //Send message Saying sorry it is the other players turn
                    JOptionPane.showMessageDialog(this, "Sorry it is " + (this.playerCurrentTurn == Colour.WHITE ? "White" : "Black") + "'s turn");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass() == (new JButton()).getClass()) {
            //Save the game
            this.saveGame();
            JOptionPane.showMessageDialog(this, "Game state is saved, Click Load game to continue the game!");

            mainMenu.playingGame = false;
            mainMenu.closeFrame();
        }
    }
    
    
}
