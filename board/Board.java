package board;

import java.awt.*;
import javax.swing.*;

public class Board extends JPanel {

    // 1. Initialize the array immediately
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
                }else {
                    square.setBackground(darkColor);
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

                tiles[row][col] = square;
                this.add(square);
            }
        }
    }

    private String getLetter(int col) {
        return String.valueOf((char) ('a' + col));
    }

    private String getNumber(int row) {
        return Integer.toString(8 - row);
    }
}
