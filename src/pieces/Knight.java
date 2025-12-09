package pieces;
import board.Board;
import board.Tile;
import java.awt.*;
import java.util.ArrayList;
public class Knight extends Piece {
    public Knight(int x, int y, String type, Color color) {
        super(x, y, "Knight", color);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng = "white_Knight.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_Knight.png";
        }
        this.type = "Knight";
    }
    @Override
    public java.util.List<Tile> validSteps(Board input) {
        java.util.List<Tile> validSteps = new ArrayList<>();
        Board boardState = input;
        Tile[][] tileArray = boardState.getTileArray();
        

        if (isOnBoard(x + 2, y + 1)) {
            if (tileArray[7 - (y + 1)][x + 2].getPiece() == null ||
                    tileArray[7 - (y + 1)][x + 2].getPiece().getColor() != this.color)
                validSteps.add(tileArray[7 - (y + 1)][x + 2]);
        }
        
        if (isOnBoard(x + 2, y - 1)) {
            if (tileArray[7 - (y - 1)][x + 2].getPiece() == null
                    || tileArray[7 - (y - 1)][x + 2].getPiece().getColor() != this.color) {
                validSteps.add(tileArray[7 - (y - 1)][x + 2]);
            }
        }

        if (isOnBoard(x - 2, y + 1)) {
            if (tileArray[7 - (y + 1)][x - 2].getPiece() == null
                    || tileArray[7 - (y + 1)][x - 2].getPiece().getColor() != this.color) {
                validSteps.add(tileArray[7 - (y + 1)][x - 2]);
            }
        }

        if (isOnBoard(x - 2, y - 1)) {
            if (tileArray[7 - (y - 1)][x - 2].getPiece() == null
                    || tileArray[7 - (y - 1)][x - 2].getPiece().getColor() != this.color) {
                validSteps.add(tileArray[7 - (y - 1)][x - 2]);
            }
        }

        if (isOnBoard(x + 1, y + 2)) {
            if (tileArray[7 - (y + 2)][x + 1].getPiece() == null
                    || tileArray[7 - (y + 2)][x + 1].getPiece().getColor() != this.color) {
                validSteps.add(tileArray[7 - (y + 2)][x + 1]);
            }
        }

        if (isOnBoard(x + 1, y - 2)) {
            if (tileArray[7 - (y - 2)][x + 1].getPiece() == null
                    || tileArray[7 - (y - 2)][x + 1].getPiece().getColor() != this.color) {
                validSteps.add(tileArray[7 - (y - 2)][x + 1]);
            }
        }

        if (isOnBoard(x - 1, y + 2)) {
            if (tileArray[7 - (y + 2)][x - 1].getPiece() == null
                    || tileArray[7 - (y + 2)][x - 1].getPiece().getColor() != this.color) {
                validSteps.add(tileArray[7 - (y + 2)][x - 1]);
            }
        }

        if (isOnBoard(x - 1, y - 2)) {
            if (tileArray[7 - (y - 2)][x - 1].getPiece() == null
                    || tileArray[7 - (y - 2)][x - 1].getPiece().getColor() != this.color) {
                validSteps.add(tileArray[7 - (y - 2)][x - 1]);
            }
        }        

        return validSteps;
    }
}
