package pieces;
import board.Board;
import board.Tile;
import java.awt.*;
import java.util.ArrayList;
public class King extends Piece {
    public King(int x, int y, String type, Color color) {
        super(x, y, "King", color);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng = "white_King.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_King.png";
        }
        this.type = "King";
    }
    @Override
    public java.util.List<Tile> validSteps(Board input) {
        java.util.List<Tile> validSteps = new ArrayList<>();
        java.util.List<Tile> guardedTiles = getGuardedTiles(input);
        Board boardState = input;
        Tile[][] tileArray = boardState.getTileArray();

        for (int i = -1; i <= 1; i++) {
            for (int t = -1; t <= 1; t++) {
                if (isOnBoard(x + i, y + t) && !guardedTiles.contains(tileArray[7 - (y + t)][x + i])
                        && (tileArray[7 - (y + t)][x + i].getPiece() == null
                                || tileArray[7 - (y + t)][x + i].getPiece()
                                        .getColor() != this.color)) {
                    validSteps.add(tileArray[7 - (y + t)][x + i]);
                }
            }
        }

        return validSteps;
    }
    
    public java.util.List<Tile> getGuardedTiles(Board input) {
        java.util.List<Tile> guardedTiles = new ArrayList<>();
        Tile[][] tileArray = input.getTileArray();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                Tile t = tileArray[row][col];
                Piece enemy = t.getPiece();

                if (enemy != null && enemy.getColor() != this.getColor()) {

                    if (enemy instanceof King) {
                        int kx = enemy.getPosX();
                        int ky = enemy.getPosY();                      
                        for (int i = -1; i <= 1; i++) {
                            for (int j = -1; j <= 1; j++) {
                               
                                if (i == 0 && j == 0) {
                                    continue;
                                }

                                if (isOnBoard(kx + i, ky + j)) {
                                    guardedTiles.add(tileArray[7 - (ky + j)][kx + i]);
                                }
                            }
                        }
                    } 
                    else if (enemy instanceof Pawn) {
                        int px = enemy.getPosX();
                        int py = enemy.getPosY();

                        int direction = (enemy.getColor() == Color.WHITE) ? 1 : -1;

                        int[] attackOffsets = {-1, 1};

                        for (int offset : attackOffsets) {
                            if (isOnBoard(px + offset, py + direction)) {
                                guardedTiles.add(tileArray[7 - (py + direction)][px + offset]);
                            }
                        }
                    } 
                    else {
                        guardedTiles.addAll(enemy.validSteps(input));
                    }
                }
            }
        }
        return guardedTiles;
    }
}
