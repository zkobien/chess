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
    Piece activePiece = null;

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
        
        if (selectedTile.getPiece() != null
                && selectedTile.getPiece().getColor() == activeColor) {
            if (activeTile != null) {
                board.unHighlightAll();
            }

            activeTile = selectedTile;
            activePiece = activeTile.getPiece();
            activeTile.setHiglighted(true);

            for (Tile tile : activePiece.validSteps(board)) {
                tile.setHiglighted(true);
            }

        }
        else if (selectedTile.getHighlighted()) {
            selectedTile.setPiece(activePiece);
            activePiece.setPos(selectedTile.getPosX(), selectedTile.getPosY());
            activeTile.setPiece(null);
            board.unHighlightAll();
            if (activeColor == Color.BLACK)
                activeColor = Color.WHITE;
            else
                activeColor = Color.BLACK;
        }

        


    }
    
    

}
 