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
   






        return validSteps;
    }
}
