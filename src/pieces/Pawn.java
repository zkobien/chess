package pieces;

import board.Board;
import board.Tile;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the Pawn chess piece. 
 */
public class Pawn extends Piece {

    /**
     * Constructor for the Pawn.
     *
     * @param x initial column
     * @param y initial row
     * @param type "Pawn"
     * @param color piece color
     */
    public Pawn(int x, int y, String type, Color color) {
        super(x, y, "Pawn", color);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng = "white_Pawn.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_Pawn.png";
        }
        this.type = "Pawn";
    }

    /**
     * Calculates valid moves for the Pawn. Handles forward movement (based on
     * color) and diagonal captures.
     *
     * @param input the current board state
     * @return list of valid destination tiles
     */
    @Override
    public java.util.List<Tile> validSteps(Board input) {
        java.util.List<Tile> validSteps = new ArrayList<>();
        Board boardState = input;
        Tile[][] tileArray = boardState.getTileArray();

        if (this.color == Color.WHITE) {
            int xVar = this.x;
            int yVar = this.y + 1;

            if (isOnBoard(xVar + 1, yVar) && tileArray[7 - yVar][xVar + 1].getPiece() != null
                    && tileArray[7 - yVar][xVar + 1].getPiece().getColor() != this.getColor()) {
                validSteps.add(tileArray[7 - yVar][xVar + 1]);
            }

            if (isOnBoard(xVar - 1, yVar) && tileArray[7 - yVar][xVar - 1].getPiece() != null
                    && tileArray[7 - yVar][xVar - 1].getPiece().getColor() != this.getColor()) {
                validSteps.add(tileArray[7 - yVar][xVar - 1]);
            }

            if (tileArray[7 - yVar][xVar].getPiece() == null) {
                validSteps.add(tileArray[7 - yVar][xVar]);
            }

            if (yVar < 3) {
                yVar++;
                if (tileArray[7 - yVar][xVar].getPiece() == null) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
            }

        } else {
 
            int xVar = this.x;
            int yVar = this.y - 1;

            if (isOnBoard(xVar + 1, yVar) && tileArray[7 - yVar][xVar + 1].getPiece() != null
                    && tileArray[7 - yVar][xVar + 1].getPiece().getColor() != this.getColor()) {
                validSteps.add(tileArray[7 - yVar][xVar + 1]);
            }

            if (isOnBoard(xVar - 1, yVar) && tileArray[7 - yVar][xVar - 1].getPiece() != null
                    && tileArray[7 - yVar][xVar - 1].getPiece().getColor() != this.getColor()) {
                validSteps.add(tileArray[7 - yVar][xVar - 1]);
            }
            if (tileArray[7 - yVar][xVar].getPiece() == null) {
                validSteps.add(tileArray[7 - yVar][xVar]);
            }
            if (yVar > 4) {
                yVar--;
                if (tileArray[7 - yVar][xVar].getPiece() == null) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
            }

        }

        return validSteps;
    }
}
