
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.util.List;

public class BishopTest {
    

    @Test
    void testBishopMovement() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Bishop bishop = new Bishop(4, 3, "Bishop", Color.WHITE);
        tiles[4][4].setPiece(bishop);

        List<Tile> moves = bishop.validSteps(board);

        assertTrue(moves.size() > 0);

        boolean canMoveDiagonal = false;
        for (Tile t : moves) {
            if (t.getCol() == 7 && t.getRow() == 1) {
                canMoveDiagonal = true;
            }
        }
        assertTrue(canMoveDiagonal);
    }

    @Test
    void testBishopBlocked() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Bishop bishop = new Bishop(0, 0, "Bishop", Color.WHITE);
        tiles[7][0].setPiece(bishop);

        Pawn blocker = new Pawn(1, 1, "Pawn", Color.WHITE);
        tiles[6][1].setPiece(blocker);

        List<Tile> moves = bishop.validSteps(board);

        assertEquals(0, moves.size());
    }
}
