/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Maxth
 */
public class Menu extends JPanel implements ActionListener {
    //Fields
    public static boolean playingGame = false;
    
    private JFrame frame;
    
    //Text
    private static final String titleText = "Chess GUI";
    private static final String playButtonText = "Play New Game";
    private static final String LoadGameButtonText = "Load Game";
    private static final String exitButtonText = "Exit";
    
    //Components
    private JLabel titleLabel;
    
    private JButton playButton;
    private JButton loadButton;
    private JButton exitButton;
    
    //GameController
    private BoardDrawer gameBoard;
    private BoardLoader gameLoader;
    
    //Constructor
    public Menu() {
        super(new GridLayout(4, 1));
        
        this.setUpPanel();
    }
    
    //Methods
    private void setUpPanel() {
        this.playButton = new JButton(playButtonText);
        this.loadButton = new JButton(LoadGameButtonText);
        this.exitButton = new JButton(exitButtonText);
        
        this.titleLabel = new JLabel(titleText, SwingConstants.CENTER);
        
        this.add(this.titleLabel);
        this.add(this.playButton);
        this.add(this.loadButton);
        this.add(this.exitButton);
        
        this.playButton.addActionListener(this);
        this.loadButton.addActionListener(this);
        this.exitButton.addActionListener(this);
        
        this.gameLoader = new BoardLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ACTION PERFORMED : " + e.getSource());
        
        if(e.getSource() == this.exitButton) {
            System.exit(0);
        } else if(e.getSource() == this.playButton) {
            if(this.playingGame == false) {
                this.createNewGame();
            }
        } else if(e.getSource() == this.loadButton) {
            if(this.playingGame == false) {
                System.out.println("LOADING GAME");
            
                frame = new JFrame("TEST GUI");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Board tempBoard;
                try {
                    tempBoard = this.gameLoader.loadGame();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, "Coudn't find the game\n Please Make a new game");
                    return;
                }
                
                this.gameBoard = new BoardDrawer(tempBoard.getBoard(), this);
                frame.getContentPane().add(gameBoard);
                frame.pack();
                frame.setSize(800,639);

                frame.setVisible(true);
                this.playingGame = true;
            }
        }
    }
    
    private void createNewGame() {
        frame = new JFrame("TEST GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameBoard = new BoardDrawer(this);
        frame.getContentPane().add(gameBoard);
        frame.pack();
        frame.setSize(800,639);

        frame.setVisible(true);
        this.playingGame = true;
    }
    
    public void closeFrame() {
        this.frame.dispose();
    }
    
    
}
