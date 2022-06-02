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
public class PieceControllerTest {
    
    @Test 
    void testMoveHorseUpLeft () {
        //Real BOARD 
        Board realBoard = new Board();
        PieceController realController = new PieceController(realBoard, Colour.BLACK);
        Position horsePosition = new Position(1, 0);
        Position horseMovePosition = new Position(2, 2);
        System.out.println("MOVING HORSE!");
        realController.movePiece(new Knight(horsePosition, Colour.BLACK), new Knight(horseMovePosition, Colour.BLACK));
        
        
        //CHECK IF HORSE HAS MOVED
        System.out.println("Checking if horse has moved!");
        assertEquals(realBoard.getPieceFromBoard(new Position(2, 2)).getClass() , (new Knight(null, Colour.WHITE)).getClass());
        System.out.println("Horse has moved!");
    }
    
    @Test
    void testQueenCannotMoveAtStartOfGame() {
        Board realBoard = new Board();
        Piece tempQueen = realBoard.getPieceFromBoard(new Position(4, 0));
        
        System.out.println("Testing if piece is Queen");
        assertEquals((new Queen(null, Colour.WHITE)).getClass(), tempQueen.getClass());
        System.out.println("Piece is queen");
        System.out.println("+-------------+");
        
        System.out.println("Testing if Queen can't move");
        assertEquals(0, tempQueen.getLegalPositions(realBoard).size());
        System.out.println("Queen connot move");
    }
    
    @Test
    void testPawnHasTwoMovesAtStart() {
        Board realBoard = new Board();
        Piece tempPawn = realBoard.getPieceFromBoard(new Position(0, 1));
        
        System.out.println("Testing if piece is Pawn");
        assertEquals((new Pawn(null, Colour.WHITE)).getClass(), tempPawn.getClass());
        System.out.println("Piece is Pawn");
        System.out.println("+-------------+");
        
        System.out.println("Testing if Pawn has two avaliable moves");
        assertEquals(2, tempPawn.getLegalPositions(realBoard).size());
        System.out.println("Pawn has 2 avaliable moves!");
    }
}
