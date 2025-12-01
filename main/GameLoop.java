package main;

import board.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import pieces.*;

public class GameLoop extends MouseAdapter {

    Board board;
    Tile activeTile;
    Color activeColor;
    Tile blackKingTile;
    Tile whiteKingTile;

    public GameLoop(Board board) {
        this.board = board;
        this.activeColor = Color.WHITE;
        this.activeTile = null;
        findKings();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Tile clickedTile = (Tile) me.getSource();

        // CASE 1: Selecting a piece
        if (activeTile == null) {
            if (clickedTile.getPiece() != null && clickedTile.getPiece().getColor() == activeColor) {
                activeTile = clickedTile;
                activeTile.setHiglighted(true);

                // --- KEY CHANGE: Only highlight SAFE moves --
                List<Tile> safeMoves = getSafeMoves(activeTile);
                for (Tile tile : safeMoves) {
                    tile.setHiglighted(true);
                }
            }
        } 
        else {
            if (clickedTile == activeTile) {
                clearHighlights();
                activeTile = null;
            } else if (clickedTile.getHighlighted()) {
                movePiece(activeTile, clickedTile);
            } else if (clickedTile.getPiece() != null && clickedTile.getPiece().getColor() == activeColor) {
                clearHighlights();
                activeTile = clickedTile;
                activeTile.setHiglighted(true);

                List<Tile> safeMoves = getSafeMoves(activeTile);
                for (Tile tile : safeMoves) {
                    tile.setHiglighted(true);
                }
            } else {
                clearHighlights();
                activeTile = null;
            }
        }
    }

    private List<Tile> getSafeMoves(Tile startTile) {
        List<Tile> safeMoves = new ArrayList<>();
        Piece piece = startTile.getPiece();
        List<Tile> potentialMoves = piece.validSteps(board); 

        for (Tile targetTile : potentialMoves) {
            if (simulateMoveAndCheckSafety(startTile, targetTile)) {
                safeMoves.add(targetTile);
            }
        }
        return safeMoves;
    }

   
    private boolean simulateMoveAndCheckSafety(Tile start, Tile end) {
        Piece movedPiece = start.getPiece();
        Piece capturedPiece = end.getPiece();

        int originalX = movedPiece.getPosX();
        int originalY = movedPiece.getPosY();

        start.setPiece(null);
        end.setPiece(movedPiece);
        movedPiece.setPos(end.getPosX(), end.getPosY());

        findKings();

        boolean isSafe = !isKingInCheck(activeColor);

        end.setPiece(capturedPiece);
        start.setPiece(movedPiece);
        movedPiece.setPos(originalX, originalY);

        findKings();

        return isSafe;
    }

    private boolean isKingInCheck(Color color) {
        Tile kingTile = (color == Color.WHITE) ? whiteKingTile : blackKingTile;
        King king = (King) kingTile.getPiece();

        return king.getGuardedTiles(board).contains(kingTile);
    }

    private void movePiece(Tile start, Tile end) {
        Piece piece = start.getPiece();
        end.setPiece(piece);
        start.setPiece(null);
        piece.setPos(end.getPosX(), end.getPosY());

        clearHighlights();
        activeTile = null;

        activeColor = (activeColor == Color.WHITE) ? Color.BLACK : Color.WHITE;

        findKings();

        if (checkMate() && isKingInCheck(activeColor)) {
            System.out.println("checkmate");
        }
        else if (checkMate()) {
            System.out.println("stalemate");
        }
           
    }

    public boolean checkMate() {
       
        for (int i = 0; i < 8; i++) {
            for (int t = 0; t < 8; t++) {
                Tile tile = board.tiles[i][t];
                if (tile.getPiece() != null && tile.getPiece().getColor() == activeColor) {
                    if (!getSafeMoves(tile).isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void findKings() {
        whiteKingTile = null;
        blackKingTile = null;
        Tile[][] tiles = board.getTileArray();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = tiles[row][col].getPiece();
                if (p instanceof King) {
                    if (p.getColor() == Color.WHITE) {
                        whiteKingTile = tiles[row][col];
                    } else {
                        blackKingTile = tiles[row][col];
                    }
                }
            }
        }
    }

    private void clearHighlights() {
        Tile[][] tiles = board.getTileArray();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tiles[row][col].setHiglighted(false);
            }
        }
    }
}
