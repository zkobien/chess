
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.io.File;

public class BoardTest {

    @Test
    void testBoardInitialization() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        assertNotNull(tiles[0][0].getPiece());
        assertTrue(tiles[0][0].getPiece() instanceof Rook);
        assertEquals(Color.BLACK, tiles[0][0].getPiece().getColor());

        assertNotNull(tiles[7][4].getPiece());
        assertTrue(tiles[7][4].getPiece() instanceof King);
        assertEquals(Color.WHITE, tiles[7][4].getPiece().getColor());

        assertNull(tiles[4][4].getPiece());
    }

    @Test
    void testExportAndImport() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        tiles[0][0].setPiece(new Rook(0, 7, "Rook", Color.BLACK));
        tiles[7][7].setPiece(new King(7, 0, "King", Color.WHITE));

        String filename = "test_save_game.txt";
        board.exportGame(filename);

        Board newBoard = new Board();
        Tile[][] newTiles = newBoard.getTileArray();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                newTiles[r][c].setPiece(null);
            }
        }

        newBoard.importGame(filename);

        Piece p1 = newTiles[0][0].getPiece();
        assertNotNull(p1);
        assertTrue(p1 instanceof Rook);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(7, p1.getPosY());

        Piece p2 = newTiles[7][7].getPiece();
        assertNotNull(p2);
        assertTrue(p2 instanceof King);
        assertEquals(Color.WHITE, p2.getColor());
        assertEquals(0, p2.getPosY());

        new File(filename).delete();
    }
}
