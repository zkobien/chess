package pieces;
import java.awt.*;
public class Bishop extends Piece{
    Bishop(int x, int y, String type, Color color) {
        super(x, y, type);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng = "white_Bishop.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_Bishop.png";
        }
        this.type = "Bishop";
    }
}
