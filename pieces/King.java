package pieces;
import java.awt.*;
public class King extends Piece {
    King(int x, int y, String type, Color color) {
        super(x, y, type);
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
