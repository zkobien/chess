
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.io.File;

public class BoardTest {
   
    @Test
    void testPieceCodeTranslation() {
      
        Board board = new Board();

        Tile[][] tiles = board.getTileArray();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        tiles[0][0].setPiece(new Knight(0, 0, "Knight", Color.BLACK));

        String testFile = "test_export.txt";
        board.exportGame(testFile);

        Board newBoard = new Board();

        Tile[][] newTiles = newBoard.getTileArray();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                newTiles[r][c].setPiece(null);
            }
        }

        newBoard.importGame(testFile);

        Piece p = newTiles[0][0].getPiece();
        assertNotNull(p, "Piece should exist at 0,0 after import");
        assertTrue(p instanceof Knight, "Piece should be a Knight");
        assertEquals(Color.BLACK, p.getColor(), "Piece should be Black");

        new File(testFile).delete();
    }
}
