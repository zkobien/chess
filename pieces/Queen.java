package pieces;
import board.Board;
import board.Tile;
import java.awt.*;
import java.util.ArrayList;
public class Queen extends Piece {
    public Queen(int x, int y, String type, Color color) {
        super(x, y, "Queen", color);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng = "white_Queen.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_Queen.png";
        }
        this.type = "Queen";
    }
    @Override
    public java.util.List<Tile> validSteps(Board input) {
        java.util.List<Tile> validSteps = new ArrayList<>();
        Board boardState = input;
        Tile[][] tileArray = boardState.getTileArray();
   
        Rook tempRook = new Rook(this.x, this.y, "Rook", this.getColor());
        Bishop tempBishop = new Bishop(this.x, this.y, "Bishop", this.getColor());

        validSteps = tempRook.validSteps(input);
        validSteps.addAll(tempBishop.validSteps(input));

        return validSteps;
    }
}
