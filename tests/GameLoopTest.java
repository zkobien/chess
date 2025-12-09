
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.GameLoop;
import board.*;
import pieces.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class GameLoopTest {

    @Test
    void testSelectAndMovePiece() {
        Board board = new Board();
        GameLoop loop = new GameLoop(board);
        Tile[][] tiles = board.getTileArray();

        Tile startTile = tiles[6][0];
        Tile destTile = tiles[5][0];

        MouseEvent pressStart = new MouseEvent(startTile, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
        loop.mousePressed(pressStart);

        assertTrue(startTile.getHighlighted());

        MouseEvent pressEnd = new MouseEvent(destTile, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
        loop.mousePressed(pressEnd);

        assertNull(startTile.getPiece());
        assertNotNull(destTile.getPiece());
        assertTrue(destTile.getPiece() instanceof Pawn);
    }

    @Test
    void testCheckmateDetection() {
        Board board = new Board();
        Tile[][] tiles = board.getTileArray();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c].setPiece(null);
            }
        }

        King whiteKing = new King(0, 0, "King", Color.WHITE);
        tiles[7][0].setPiece(whiteKing);

        Rook blackRook1 = new Rook(0, 7, "Rook", Color.BLACK);
        tiles[0][0].setPiece(blackRook1);

        Rook blackRook2 = new Rook(1, 7, "Rook", Color.BLACK);
        tiles[0][1].setPiece(blackRook2);

        GameLoop loop = new GameLoop(board);

        assertTrue(loop.checkMate());
    }

    @Test
    void testTurnSwitching() {
        Board board = new Board();
        GameLoop loop = new GameLoop(board);
        Tile[][] tiles = board.getTileArray();

        Tile whitePawn = tiles[6][0];
        Tile whiteDest = tiles[5][0];

        loop.mousePressed(new MouseEvent(whitePawn, MouseEvent.MOUSE_PRESSED, 0, 0, 0, 0, 1, false));
        loop.mousePressed(new MouseEvent(whiteDest, MouseEvent.MOUSE_PRESSED, 0, 0, 0, 0, 1, false));

        Tile blackPawn = tiles[1][0];
        loop.mousePressed(new MouseEvent(blackPawn, MouseEvent.MOUSE_PRESSED, 0, 0, 0, 0, 1, false));

        assertTrue(blackPawn.getHighlighted());
    }
}
