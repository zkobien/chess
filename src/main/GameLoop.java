package main;

import board.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import pieces.*;

/**
 * class for the maind gameloop
 */
public class GameLoop extends MouseAdapter {

    Board board;
    Tile activeTile;
    Color activeColor;
    Tile blackKingTile;
    Tile whiteKingTile;
    
    /**
     * constructor, initializes the game loop
     * 
     * @param board input board objec
     */
    public GameLoop(Board board) {
        this.board = board;
        this.activeColor = Color.WHITE;
        this.activeTile = null;
        findKings();
    }

    
    @Override
    public void mousePressed(MouseEvent me) {
        Tile clickedTile = (Tile) me.getSource();

        if (activeTile == null) {
            if (clickedTile.getPiece() != null && clickedTile.getPiece().getColor() == activeColor) {
                activeTile = clickedTile;
                activeTile.setHiglighted(true);

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
    /**
     * 
     * @param startTile
     * @return
     */
    private List<Tile> getSafeMoves(Tile startTile) {
        List<Tile> safeMoves = new ArrayList<>();
        Piece piece = startTile.getPiece();
        List<Tile> potentialMoves = piece.validSteps(board);

        for (Tile targetTile : potentialMoves) {
            //castling logic
            if (piece instanceof King && Math.abs(startTile.getCol() - targetTile.getCol()) == 2) {

                if (isKingInCheck(piece.getColor())) {
                    continue;
                }
                int row = startTile.getRow();

                int colDiff = (targetTile.getCol() > startTile.getCol()) ? 1 : -1;
                int middleCol = startTile.getCol() + colDiff;

                Tile middleTile = board.getTileArray()[row][middleCol];

                if (!simulateMoveAndCheckSafety(startTile, middleTile)) {
                    continue;
                }
            }

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

        if (piece instanceof King && Math.abs(start.getCol() - end.getCol()) == 2) {
            int row = start.getRow();
            Tile[][] tiles = board.getTileArray();

            if (end.getCol() > start.getCol()) {
                Tile rookStart = tiles[row][7]; 
                Tile rookEnd = tiles[row][5];  

                Piece rook = rookStart.getPiece();
                if (rook != null) {
                    rookEnd.setPiece(rook);
                    rookStart.setPiece(null);
                    rook.setPos(rookEnd.getPosX(), rookEnd.getPosY());
                    rook.setStepped(true);
                }
            } // Long Castle (Queen-side) -> King moved Left (Col decreased)
            else {
                Tile rookStart = tiles[row][0]; // A-file Rook (Col 0)
                Tile rookEnd = tiles[row][3];   // D-file Rook Destination (Col 3)

                Piece rook = rookStart.getPiece();
                if (rook != null) {
                    rookEnd.setPiece(rook);
                    rookStart.setPiece(null);
                    rook.setPos(rookEnd.getPosX(), rookEnd.getPosY());
                    rook.setStepped(true);
                }
            }
        }

        end.setPiece(piece);
        start.setPiece(null);
        piece.setPos(end.getPosX(), end.getPosY());
        piece.setStepped(true);

        clearHighlights();
        activeTile = null;
        activeColor = (activeColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
        findKings();

        if (checkMate() && isKingInCheck(activeColor)) {
            System.out.println("checkmate");
        } else if (checkMate()) {
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
    
    public final void findKings() {
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
