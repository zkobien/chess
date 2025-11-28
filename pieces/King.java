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
        Board boardState = input;
        Tile[][] tileArray = boardState.getTileArray();
   






        return validSteps;
    }
}
