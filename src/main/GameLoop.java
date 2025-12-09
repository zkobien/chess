package main;

import board.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pieces.*;

/**
 * Class for the main game loop, handles the main game logic, and responds to mouse presses
 */
public class GameLoop extends MouseAdapter {

    Board board;
    Tile activeTile;
    Color activeColor;
    Tile blackKingTile;
    Tile whiteKingTile;
    boolean gameOver = false;
    
    /**
     * Constructor that initializes the game loop.
     * Sets the starting player to White and locates the Kings on the board.
     * @param board the game board object containing the grid and pieces
     */
    public GameLoop(Board board) {
        this.board = board;
        this.activeColor = board.getActiveColor();
        this.activeTile = null;
        findKings();
    }

    /**
     * Handles mouse press events to control game flow.
     * If the game is over, ignores input.
     * Implements the logic for selecting a piece, moving a selected piece, or deselecting.
     * @param me the MouseEvent triggered by clicking a tile
     */
    @Override
    public void mousePressed(MouseEvent me) {
        if(gameOver)
            return;

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
     * Calculates all legal moves for a specific tile's piece.
     * Filters out any moves that would result in the King being in check.
     * Also handles the special validation required for Castling).
     * @param startTile the tile containing the piece to move
     * @return a List of valid move destination Tiles
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

    /**
     * Simulates a move to check if it leaves the King safe.
     * Temporarily executes the move, updates the board state, checks for check, and then reverts the move.
     * @param start the starting tile
     * @param end the destination tile
     * @return true if the move is safe
     */
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
    /**
     * Checks if a king of a color is in check
     * @param color The color of the King that should be checked for check
     * @return true if the selected King is in check
     */
    private boolean isKingInCheck(Color color) {
        Tile kingTile = (color == Color.WHITE) ? whiteKingTile : blackKingTile;
        King king = (King) kingTile.getPiece();

        return king.getGuardedTiles(board).contains(kingTile);
    }
    
    /**
     * Moves a Piece from a start Tile to an end Tile. 
     * Also Handles checkmate and Stalemate situations
     * @param start starting point
     * @param end destination
     */
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
            } else {
                Tile rookStart = tiles[row][0];
                Tile rookEnd = tiles[row][3];

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
        board.setActiveColor(activeColor);
        findKings();

        if (checkMate()) {
            if (isKingInCheck(activeColor)) {
                String winner = (activeColor == Color.WHITE) ? "Black" : "White";
                JOptionPane.showMessageDialog(board,
                        "Checkmate: " + winner + " wins!",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(board,
                        "Stalemate! It's a draw.",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            gameOver = true;
        }
    }

    /**
     * Used for Checkmate logic, checks if the active color has any valid moves
     * @return true if there are no valid moves for the active color
     */
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

    /**
     * Updates the King locations for this GameLoop
     */
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

    /**
     * clears all highlighting from the board
     */
    private void clearHighlights() {
        Tile[][] tiles = board.getTileArray();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tiles[row][col].setHiglighted(false);
            }
        }
    }
}
