package pieces;
import java.awt.*;
public class Rook extends Piece {
    Rook(int x, int y, String type, Color color) {
        super(x, y, type);
        if (color == Color.WHITE) {
            this.color = Color.WHITE;
            this.piecePng="white_Rook.png";
        } else {
            this.color = Color.BLACK;
            this.piecePng = "black_Rook.png";
        }
        this.type = "Rook";
    }
}
