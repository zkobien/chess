
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.*;
import pieces.*;
import java.awt.Color;
import java.awt.FlowLayout;

public class TileTest {


    @Test
    void testCoordinateConversion() {
        Tile tile = new Tile(new FlowLayout());

        int col = 3;
        int row = 4;

        tile.setPos(col, row);

        assertEquals(3, tile.getCol());
        assertEquals(4, tile.getRow());

        assertEquals(3, tile.getPosX());
        assertEquals(3, tile.getPosY());
    }

    @Test
    void testCoordinateInversion() {
        Tile tile = new Tile(new FlowLayout());

        tile.setPos(0, 7);
        assertEquals(0, tile.getPosY());

        tile.setPos(0, 0);
        assertEquals(7, tile.getPosY());
    }

    @Test
    void testPieceAssignment() {
        Tile tile = new Tile(new FlowLayout());
        Piece rook = new Rook(0, 0, "Rook", Color.WHITE);

        tile.setPiece(rook);

        assertNotNull(tile.getPiece());
        assertEquals(rook, tile.getPiece());

        tile.setPiece(null);
        assertNull(tile.getPiece());
    }
}
