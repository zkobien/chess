package pieces;
import java.awt.*;
public class Queen extends Piece {
    Queen(int x, int y, String type, Color color) {
        super(x, y, type);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng = "white_Queen.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_Queen.png";
        }
        this.type = "Queen";
    }
}
