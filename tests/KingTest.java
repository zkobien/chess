import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.util.List;




public class KingTest {
 
    @Test
    void testStandardMovement() {
        

        Board board = new Board(); 
        board.unHighlightAll(); 

        Tile[][] tiles = board.getTileArray();
        for(int r=0; r<8; r++) {
            for(int c=0; c<8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        King king = new King(4, 3, "King", Color.WHITE); 
        tiles[4][4].setPiece(king);

        // 3. Get valid steps
        List<Tile> moves = king.validSteps(board);

        assertEquals(8, moves.size(), "King in middle should have 8 moves");
    }

    @Test
    void testWhiteShortCastle() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        // 1. Clear Board
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        King king = new King(4, 0, "King", Color.WHITE);
        tiles[7][4].setPiece(king);

        Rook rook = new Rook(7, 0, "Rook", Color.WHITE);
        tiles[7][7].setPiece(rook);

        List<Tile> moves = king.validSteps(board);

        boolean canCastle = false;
        for (Tile t : moves) {

            if (t.getCol() == 6 && t.getRow() == 7) {
                canCastle = true;
                break;
            }
        }
        assertTrue(canCastle, "King should be able to short castle");
    }
}