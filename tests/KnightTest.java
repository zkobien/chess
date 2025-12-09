
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.util.List;

public class KnightTest {
   
    @Test
    void testLMovement() {
       
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Knight knight = new Knight(4, 3, "Knight", Color.WHITE);
        tiles[4][4].setPiece(knight);

        List<Tile> moves = knight.validSteps(board);

        assertEquals(8, moves.size());

        boolean hitTarget = false;
        for (Tile t : moves) {
            if (t.getCol() == 5 && t.getRow() == 2) {
                hitTarget = true;
            }
        }
        assertTrue(hitTarget);
    }

    @Test
    void testKnightJumping() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        Knight knight = new Knight(4, 3, "Knight", Color.WHITE);
        tiles[4][4].setPiece(knight);

        Pawn obstacle = new Pawn(4, 4, "Pawn", Color.WHITE);
        tiles[3][4].setPiece(obstacle);

        List<Tile> moves = knight.validSteps(board);

        assertEquals(8, moves.size());
    }
}
