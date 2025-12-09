
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.util.List;

public class QueenTest {
    
    @Test
    void testQueenMovement() {
        
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Queen queen = new Queen(4, 3, "Queen", Color.WHITE);
        tiles[4][4].setPiece(queen);

        List<Tile> moves = queen.validSteps(board);

        boolean straight = false;
        boolean diagonal = false;

        for (Tile t : moves) {
            if (t.getCol() == 4 && t.getRow() == 0) {
                straight = true;
            }
            if (t.getCol() == 7 && t.getRow() == 1) {
                diagonal = true;
            }
        }

        assertTrue(straight);
        assertTrue(diagonal);
    }
}
