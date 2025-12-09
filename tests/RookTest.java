
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.util.List;

public class RookTest {
    

    @Test
    void testRookMovement() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Rook rook = new Rook(4, 3, "Rook", Color.WHITE);
        tiles[4][4].setPiece(rook);

        List<Tile> moves = rook.validSteps(board);

        boolean horizontal = false;
        boolean vertical = false;

        for (Tile t : moves) {
            if (t.getCol() == 7 && t.getRow() == 4) {
                horizontal = true;
            }
            if (t.getCol() == 4 && t.getRow() == 7) {
                vertical = true;
            }
        }

        assertTrue(horizontal);
        assertTrue(vertical);
    }

    @Test
    void testRookBlocked() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Rook rook = new Rook(0, 0, "Rook", Color.WHITE);
        tiles[7][0].setPiece(rook);

        Pawn blocker = new Pawn(0, 1, "Pawn", Color.WHITE);
        tiles[6][0].setPiece(blocker);
        Pawn blocker2 = new Pawn(1, 0, "Pawn", Color.WHITE);
        tiles[7][1].setPiece(blocker2);

        List<Tile> moves = rook.validSteps(board);

        assertEquals(0, moves.size());
    }
}
