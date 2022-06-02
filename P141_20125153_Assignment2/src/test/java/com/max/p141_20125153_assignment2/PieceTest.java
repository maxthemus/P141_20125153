/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Maxth
 */
public class PieceTest {
    
  @Test
    void testBlackKingInPosition() {
        Position kingPos = new Position(3, 0);
        
        //REAL BOARD
        Board realBoard = new Board();
        
        System.out.println("Checking if black king is in position");
        assertEquals((new King(new Position(0, 0), Colour.WHITE)).getClass(), realBoard.getPieceFromBoard(kingPos).getClass());
        System.out.println("Black king is in position!");
    }
    
    @Test
    void testWhiteKingInPosition() {
        Position kingPos = new Position(3, 7);
        
        //REAL BOARD
        Board realBoard = new Board();
        
        System.out.println("Checking if White king is in position");
        assertEquals((new King(new Position(0, 0), Colour.WHITE)).getClass(), realBoard.getPieceFromBoard(kingPos).getClass());
        System.out.println("White king is in position!");
    }
}
