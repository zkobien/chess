package pieces;
import java.awt.*;
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
}
