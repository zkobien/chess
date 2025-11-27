package pieces;
import java.awt.*;
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
}
