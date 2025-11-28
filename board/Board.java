package board;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import pieces.*;

public class Board extends JPanel {

    
    public Tile[][] tiles = new Tile[8][8];

    public Board() {
        super(new GridLayout(8, 8));
        this.setLayout(new GridLayout(8,8));
        // 3. Build the board
        initializeBoard();
    }

    private void initializeBoard() {
        Color lightColor = new Color(249, 245, 209);
        Color darkColor = new Color(200, 66, 66);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Create the tile with BorderLayout
                Tile square = new Tile(new BorderLayout());
                square.setPreferredSize(new Dimension(100, 100));

                // Color initialization
                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                    square.setOriginalColor(lightColor);
                } else {
                    square.setBackground(darkColor);
                    square.setOriginalColor(darkColor);
                }

                // Setting tile name and position
                square.setTileName(getNumber(row) + getLetter(col));
                square.setPos(col, row);

                JLabel l = new JLabel(getNumber(row));
                l.setFont(new Font("Monospaced", Font.BOLD, 20));

                JLabel t = new JLabel(getLetter(col) + " ");
                t.setHorizontalAlignment(JLabel.RIGHT);
                t.setFont(new Font("Monospaced", Font.BOLD, 20));

                //adding numbers and letters
                if (col == 0) {
                    square.add(l, BorderLayout.NORTH);
                    l.setForeground(square.getBackground() == lightColor ? darkColor : lightColor);
                    
                }
                if (row == 7) {
                    square.add(t, BorderLayout.SOUTH);
                    t.setForeground(square.getBackground() == lightColor ? darkColor : lightColor);
                }

                //adding pieces
                if (row == 1)
                    square.setPiece(new Pawn(square.getPosX(), square.getPosY(), "Pawn", Color.BLACK));
                if (row == 6)
                    square.setPiece(new Pawn(square.getPosX(), square.getPosY(), "Pawn", Color.WHITE));
                if (row == 0) {
                    if (col == 0 || col == 7)
                        square.setPiece(new Rook(square.getPosX(), square.getPosY(), "Rook", Color.BLACK));
                    if (col == 1 || col == 6)
                        square.setPiece(new Knight(square.getPosX(), square.getPosY(), "Knight", Color.BLACK));
                    if (col == 2 || col == 5)
                        square.setPiece(new Bishop(square.getPosX(), square.getPosY(), "Bishop", Color.BLACK));
                    if (col == 3)
                        square.setPiece(new Queen(square.getPosX(), square.getPosY(), "Queen", Color.BLACK));
                    if (col == 4)
                        square.setPiece(new King(square.getPosX(), square.getPosY(), "King", Color.BLACK));
                }
                if (row == 7) {
                    if (col == 0 || col == 7)
                        square.setPiece(new Rook(square.getPosX(), square.getPosY(), "Rook", Color.WHITE));
                    if (col == 1 || col == 6)
                        square.setPiece(new Knight(square.getPosX(), square.getPosY(), "Knight", Color.WHITE));
                    if (col == 2 || col == 5)
                        square.setPiece(new Bishop(square.getPosX(), square.getPosY(), "Bishop", Color.WHITE));
                    if (col == 3)
                        square.setPiece(new Queen(square.getPosX(), square.getPosY(), "Queen", Color.WHITE));
                    if (col == 4)
                        square.setPiece(new King(square.getPosX(), square.getPosY(), "King", Color.WHITE));
                }
                tiles[row][col] = square;
                this.add(square);
            }
        }
    }

    public void activateTiles(java.util.List<Tile> relevantTiles) {
        for (Tile tile : relevantTiles) {
            if (!tile.highlighted)
                tile.setHiglighted(true);
        }
    }

    public void unHighlightAll() {
        for (int i = 0; i < 8; i++) {
            for (int y = 0; y < 8; y++) {
                tiles[i][y].setHiglighted(false);
            }
        }
    }
    
    public java.util.List<Tile> getActiveTiles(Tile source) {
        java.util.List<Tile> output = new ArrayList<>();
        //tile logic

        return output;
    }

    public Tile[][] getTileArray() {
        return this.tiles;
    }


    private String getLetter(int col) {
        return String.valueOf((char) ('a' + col));
    }

    private String getNumber(int row) {
        return Integer.toString(8 - row);
    }

    
}
