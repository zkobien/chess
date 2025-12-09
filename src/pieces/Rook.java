package pieces;
import board.Board;
import board.Tile;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the Rook chess piece.
 */
public class Rook extends Piece {
    
    /**
     * Constructor for the Rook.
     * @param x initial column
     * @param y initial row
     * @param type "Rook"
     * @param color piece color
     */
    public Rook(int x, int y, String type, Color color) {
        super(x, y, "Rook", color);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng = "white_Rook.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_Rook.png";
        }
        this.type = "Rook";
    }

    /**
     * Calculates valid moves for the Rook.
     * Scans all four directions (Up, Down, Left, Right) until blocked.
     * @param input the current board state
     * @return list of valid destination tiles
     */
    @Override
    public java.util.List<Tile> validSteps(Board input) {
        java.util.List<Tile> validSteps = new ArrayList<>();
        Board boardState = input;
        Tile[][] tileArray = boardState.getTileArray();

        int xVar = this.x+1;
        int yVar = this.y;
        boolean pieceFound = false;
        while (isOnBoard(xVar, yVar) && !pieceFound) {
            if (isOnBoard(xVar, yVar) && tileArray[7 - yVar][xVar].getPiece() == null)
                validSteps.add(tileArray[7 - yVar][xVar]);
            else if (isOnBoard(xVar, yVar)) {
                if (!tileArray[7 - yVar][xVar].getPiece().getColor().equals(this.getColor())) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
                pieceFound = true;
            }
            xVar++;
        }

        xVar = this.x - 1;
        yVar = this.y;
        pieceFound = false;
        while (isOnBoard(xVar, yVar) && !pieceFound) {
            if (isOnBoard(xVar, yVar) && tileArray[7 - yVar][xVar].getPiece() == null) {
                validSteps.add(tileArray[7 - yVar][xVar]);
            } else if (isOnBoard(xVar, yVar)) {
                if (!tileArray[7 - yVar][xVar].getPiece().getColor().equals(this.getColor())) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
                pieceFound = true;
            }
            xVar--;
        }

        xVar = this.x;
        yVar = this.y+1;
        pieceFound = false;
        while (isOnBoard(xVar, yVar) && !pieceFound) {
            if (isOnBoard(xVar, yVar) && tileArray[7 - yVar][xVar].getPiece() == null) {
                validSteps.add(tileArray[7 - yVar][xVar]);
            } else if (isOnBoard(xVar, yVar)) {
                if (!tileArray[7 - yVar][xVar].getPiece().getColor().equals(this.getColor())) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
                pieceFound = true;
            }
            yVar++;
        }

        xVar = this.x;
        yVar = this.y - 1;
        pieceFound = false;
        while (isOnBoard(xVar, yVar) && !pieceFound) {
            if (isOnBoard(xVar, yVar) && tileArray[7 - yVar][xVar].getPiece() == null) {
                validSteps.add(tileArray[7 - yVar][xVar]);
            } else if (isOnBoard(xVar, yVar)) {
                if (!tileArray[7 - yVar][xVar].getPiece().getColor().equals(this.getColor())) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
                pieceFound = true;
            }
            yVar--;
        }
        return validSteps;
    }
}