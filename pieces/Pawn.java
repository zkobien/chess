package pieces;
import java.awt.*;
public class Pawn extends Piece {
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
}
