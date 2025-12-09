
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.util.List;

public class PawnTest {
    
    @Test
    void testWhitePawnFirstMove() {
       
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Pawn pawn = new Pawn(0, 1, "Pawn", Color.WHITE);
        tiles[6][0].setPiece(pawn);

        List<Tile> moves = pawn.validSteps(board);

        boolean singleStep = false;
        boolean doubleStep = false;

        for (Tile t : moves) {
            if (t.getRow() == 5) {
                singleStep = true;
            }
            if (t.getRow() == 4) {
                doubleStep = true;
            }
        }

        assertTrue(singleStep);
        assertTrue(doubleStep);
    }

    @Test
    void testBlackPawnMovement() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Pawn pawn = new Pawn(0, 6, "Pawn", Color.BLACK);
        tiles[1][0].setPiece(pawn);

        List<Tile> moves = pawn.validSteps(board);

        boolean movesDown = false;
        for (Tile t : moves) {
            if (t.getRow() == 2) {
                movesDown = true;
            }
        }
        assertTrue(movesDown);
    }
}
