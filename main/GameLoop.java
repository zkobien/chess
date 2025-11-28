package main;

import board.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pieces.*;


public class GameLoop extends MouseAdapter {
    Board board;
    Tile activeTile;
    Color activeColor;


    public GameLoop(Board board_in) {
        this.board = board_in;
        activeTile = null;
        activeColor = Color.WHITE;
    }
        
    public void startGame() {
        //game logic
    }

    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println("heafnásgnsaékugj");
        Tile selectedTile = (Tile) me.getSource();
        Piece activePiece = null;
        
        if (selectedTile.getPiece() != null
                && selectedTile.getPiece().getColor() == activeColor) {
            if (activeTile != null) {
                activeTile.setHiglighted(false);

            }
                    
            
            activeTile = selectedTile;
            activePiece = activeTile.getPiece();
            activeTile.setHiglighted(true);


            
           
        }


    }
    
    

}
 