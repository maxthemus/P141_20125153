/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import com.max.p141_20125153_assignment2.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Maxth
 */
public class Tile extends JPanel {
    public static final int TILE_SIZE = 75;
    //FIELDS
    private Color colour;
    private final int xCord, yCord;
    
    //Mouse fields
    private boolean outline;
    

    //CONSTUCTORS
    public Tile(int xCord, int yCord, boolean isWhite) {
        this.xCord = xCord;
        this.yCord = yCord;
        
        if(isWhite) {
            this.colour = Color.gray;
        } else {
            this.colour = Color.DARK_GRAY;
        }
    }
    
    //METHODS
    public void setOutline(boolean updateValue) {
        this.outline = updateValue;
    }
    
    //DRAW METHOD FOR TILE
    public void drawTile(Graphics g) {
        if(outline) {
            Color newColour = new Color(111, 91, 32);
            g.setColor(newColour);
            g.fillRect(xCord, yCord, TILE_SIZE, TILE_SIZE);
        } else {
            g.setColor(this.colour);
            g.fillRect(xCord, yCord, TILE_SIZE, TILE_SIZE);            
        }
    }   
}
