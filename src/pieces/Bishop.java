package pieces;

import board.*;
import java.awt.*;
import java.util.ArrayList;
public class Bishop extends Piece{
    public Bishop(int x, int y, String type, Color color) {
        super(x, y, "Bishop", color);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng = "white_Bishop.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_Bishop.png";
        }
        this.type = "Bishop";
    }

    @Override
    public java.util.List<Tile> validSteps(Board input) {
        java.util.List<Tile> validSteps = new ArrayList<>();
        Board boardState = input;
        Tile[][] tileArray = boardState.getTileArray();
        int xVar = this.x;
        int yVar = this.y;
        boolean pieceFound = false;
        while (!pieceFound && isOnBoard(xVar, yVar)) {
            xVar = xVar + 1;
            yVar = yVar + 1;
            if (isOnBoard(xVar, yVar) &&tileArray[7 - yVar][xVar].getPiece() == null)
                validSteps.add(tileArray[7 - yVar][xVar]);
            else if (isOnBoard(xVar, yVar)) {
                if (!tileArray[7 - yVar][xVar].getPiece().getColor().equals(this.getColor()))
                    validSteps.add(tileArray[7 - yVar][xVar]);
                pieceFound = true;
            }
        }   
        xVar = this.x;
        yVar = this.y;
        pieceFound = false;
        while (!pieceFound && isOnBoard(xVar, yVar)) {
            xVar = xVar - 1;
            yVar = yVar + 1;
            if (isOnBoard(xVar, yVar) &&tileArray[7 - yVar][xVar].getPiece() == null) {
                validSteps.add(tileArray[7 - yVar][xVar]);
            } else if (isOnBoard(xVar, yVar)) {
                if (!tileArray[7 - yVar][xVar].getPiece().getColor().equals(this.getColor())) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
                pieceFound = true;
            }
        }
        xVar = this.x;
        yVar = this.y;
        pieceFound = false;
        while (!pieceFound && isOnBoard(xVar, yVar)) {
            xVar = xVar - 1;
            yVar = yVar - 1;
            if (isOnBoard(xVar, yVar) &&tileArray[7 - yVar][xVar].getPiece() == null) {
                validSteps.add(tileArray[7 - yVar][xVar]);
            } else if (isOnBoard(xVar, yVar)) {
                if (!tileArray[7 - yVar][xVar].getPiece().getColor().equals(this.getColor())) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
                pieceFound = true;
            }
        }
        xVar = this.x;
        yVar = this.y;
        pieceFound = false;
        while (!pieceFound && isOnBoard(xVar, yVar)) {
            xVar = xVar + 1;
            yVar = yVar - 1;
            if (isOnBoard(xVar, yVar) &&tileArray[7 - yVar][xVar].getPiece() == null) {
                validSteps.add(tileArray[7 - yVar][xVar]);
            } else if (isOnBoard(xVar, yVar)) {
                if (!tileArray[7 - yVar][xVar].getPiece().getColor().equals(this.getColor())) {
                    validSteps.add(tileArray[7 - yVar][xVar]);
                }
                pieceFound = true;
            }
        }  
        return validSteps;
    }
}
